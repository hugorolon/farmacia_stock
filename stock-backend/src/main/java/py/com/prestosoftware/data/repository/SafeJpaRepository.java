package py.com.prestosoftware.data.repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PersistenceUnitUtil;

/**
 * Repositorio base para todas las entidades del sistema.
 *
 * <p>Corrige dos cambios de comportamiento de Hibernate 6.6 (Spring Boot 3.4+) frente a
 * Hibernate 5, causados por cómo el frontend envía los datos:
 * <ol>
 * <li><b>Id tentativo:</b> {@code save()} con un id {@link GeneratedValue} que no existe en la
 * base lanzaba {@code OptimisticLockingFailureException}. Ahora se anula el id y se inserta.</li>
 * <li><b>Relaciones desde JSON:</b> las relaciones {@code @ManyToOne}/{@code @OneToOne} sin
 * cascade llegan como objetos sueltos (p. ej. {@code "departamento": {"id": 1, ...}} o sin id).
 * Hibernate las rechaza con {@code TransientObjectException}. Ahora se reemplazan por la entidad
 * real de la base (si traen id) o por {@code null} (si no traen id).</li>
 * </ol>
 */
public class SafeJpaRepository<T, ID> extends SimpleJpaRepository<T, ID> {

	private final JpaEntityInformation<T, ?> entityInformation;
	private final EntityManager entityManager;
	/** Campo @Id con @GeneratedValue, o null si la entidad usa id asignado/compuesto. */
	private final Field generatedIdField;
	/** Relaciones @ManyToOne/@OneToOne sin cascade (solo referencian registros existentes). */
	private final List<Field> referenceFields;

	public SafeJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityInformation = entityInformation;
		this.entityManager = entityManager;
		this.generatedIdField = findGeneratedIdField(entityInformation.getJavaType());
		this.referenceFields = findReferenceFields(entityInformation.getJavaType());
	}

	@Override
	@Transactional
	public <S extends T> S save(S entity) {
		if (entity != null) {
			clearIdIfNotPersisted(entity);
			resolveReferences(entity);
		}
		return super.save(entity);
	}

	@SuppressWarnings("unchecked")
	private void clearIdIfNotPersisted(T entity) {
		if (generatedIdField == null) {
			return;
		}
		Object id = entityInformation.getId(entity);
		if (id == null || entityInformation.isNew(entity)) {
			return;
		}
		if (!existsById((ID) id)) {
			setField(generatedIdField, entity, generatedIdField.getType().isPrimitive() ? 0 : null);
		}
	}

	private void resolveReferences(T entity) {
		if (referenceFields.isEmpty()) {
			return;
		}
		PersistenceUnitUtil util = entityManager.getEntityManagerFactory().getPersistenceUnitUtil();
		for (Field field : referenceFields) {
			Object reference = getField(field, entity);
			if (reference == null || entityManager.contains(reference)) {
				continue;
			}
			Object referenceId = util.getIdentifier(reference);
			if (referenceId == null) {
				// Objeto vacío (combo sin selección): no hay relación.
				setField(field, entity, null);
				continue;
			}
			Object persisted = entityManager.find(field.getType(), referenceId);
			if (persisted == null) {
				throw new EntityNotFoundException(field.getType().getSimpleName() + " no encontrado: " + referenceId
						+ " (campo '" + field.getName() + "' de " + entity.getClass().getSimpleName() + ")");
			}
			setField(field, entity, persisted);
		}
	}

	private static Field findGeneratedIdField(Class<?> type) {
		for (Field field : allFields(type)) {
			if (field.isAnnotationPresent(Id.class) && field.isAnnotationPresent(GeneratedValue.class)) {
				field.setAccessible(true);
				return field;
			}
		}
		return null;
	}

	private static List<Field> findReferenceFields(Class<?> type) {
		List<Field> result = new ArrayList<>();
		for (Field field : allFields(type)) {
			ManyToOne manyToOne = field.getAnnotation(ManyToOne.class);
			OneToOne oneToOne = field.getAnnotation(OneToOne.class);
			boolean plainReference = (manyToOne != null && manyToOne.cascade().length == 0)
					|| (oneToOne != null && oneToOne.cascade().length == 0 && oneToOne.mappedBy().isEmpty());
			if (plainReference) {
				field.setAccessible(true);
				result.add(field);
			}
		}
		return result;
	}

	private static List<Field> allFields(Class<?> type) {
		List<Field> fields = new ArrayList<>();
		for (Class<?> c = type; c != null && c != Object.class; c = c.getSuperclass()) {
			for (Field field : c.getDeclaredFields()) {
				fields.add(field);
			}
		}
		return fields;
	}

	private static Object getField(Field field, Object target) {
		try {
			return field.get(target);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException("No se pudo leer " + field, e);
		}
	}

	private static void setField(Field field, Object target, Object value) {
		try {
			field.set(target, value);
		} catch (IllegalAccessException e) {
			throw new IllegalStateException("No se pudo asignar " + field, e);
		}
	}
}
