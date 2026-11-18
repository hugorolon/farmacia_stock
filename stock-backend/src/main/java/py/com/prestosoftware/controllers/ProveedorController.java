package py.com.prestosoftware.controllers;

import java.util.List;
import java.util.function.Function;

import jakarta.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import py.com.prestosoftware.data.models.Ciudad;
import py.com.prestosoftware.data.models.Departamento;
import py.com.prestosoftware.data.models.Empresa;
import py.com.prestosoftware.data.models.Pais;
import py.com.prestosoftware.data.models.Proveedor;
import py.com.prestosoftware.data.repository.ProveedorRepository;
import py.com.prestosoftware.domain.services.ProveedorService;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {

    private final ProveedorService service;
    private final ProveedorRepository repository;
    private final EntityManager entityManager;

    public ProveedorController(ProveedorService service, ProveedorRepository repository, EntityManager entityManager) {
        this.service = service;
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @GetMapping
    public List<Proveedor> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Proveedor findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proveedor no encontrado: " + id));
    }

    /** Próximo id sugerido (máximo + 1), solo para mostrar en el formulario. */
    @GetMapping("/next-id")
    public long nextId() {
        Long max = repository.getMaxId();
        return (max == null ? 0 : max) + 1;
    }

    @GetMapping("/count")
    public long count() {
        return service.addNewProveedor();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Proveedor> save(@RequestBody Proveedor entity) {
        resolveAssociations(entity);
        if (entity.getId() != null) {
            Proveedor existing = repository.findById(entity.getId()).orElse(null);
            if (existing != null) {
                BeanUtils.copyProperties(entity, existing, "id");
                service.save(existing);
                return ResponseEntity.ok(existing);
            }
        }

        entity.setId(null);
        service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @PutMapping("/{id}")
    @Transactional
    public Proveedor update(@PathVariable Long id, @RequestBody Proveedor entity) {
        Proveedor existing = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proveedor no encontrado: " + id));
        resolveAssociations(entity);
        BeanUtils.copyProperties(entity, existing, "id");
        service.save(existing);
        return existing;
    }

    private void resolveAssociations(Proveedor proveedor) {
        proveedor.setCiudad(resolveReference(
                proveedor.getCiudad(), Ciudad::getId, Ciudad::getNombre, Ciudad.class, "ciudad"));
        proveedor.setDepartmento(resolveReference(
                proveedor.getDepartmento(), Departamento::getId, Departamento::getNombre,
                Departamento.class, "departamento"));
        proveedor.setPais(resolveReference(
                proveedor.getPais(), Pais::getId, Pais::getNombre, Pais.class, "país"));
        proveedor.setEmpresa(resolveReference(
                proveedor.getEmpresa(), Empresa::getId, Empresa::getNombre, Empresa.class, "empresa"));
    }

    private <T> T resolveReference(T reference, Function<T, Long> idGetter,
            Function<T, String> nameGetter, Class<T> type, String relationship) {
        if (reference == null) {
            return null;
        }

        Long id = idGetter.apply(reference);
        if (id == null) {
            if (nameGetter.apply(reference) == null || nameGetter.apply(reference).isBlank()) {
                return null;
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La relación " + relationship + " debe incluir un ID existente");
        }

        T persistedReference = entityManager.find(type, id);
        if (persistedReference == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    relationship + " no encontrado: " + id);
        }
        return persistedReference;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        Proveedor entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proveedor no encontrado: " + id));
        repository.delete(entity);
    }
}