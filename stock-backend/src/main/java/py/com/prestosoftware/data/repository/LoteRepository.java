package py.com.prestosoftware.data.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import py.com.prestosoftware.data.models.Lotes;

@Repository
public interface LoteRepository extends JpaRepository<Lotes, Long> {

	@Query(value = "SELECT * FROM Lotes p WHERE p.id_Producto = ?1 ORDER BY p.id desc", nativeQuery = true)
	List<Lotes> findLotesByProductoId(Long productoId);

	@Query("SELECT coalesce(max(id), 0) FROM Lotes e")
	Long getMaxId();

	/** Lote de un producto por su codigo (sin distinguir mayusculas ni espacios). */
	@Query(value = "SELECT * FROM lotes l WHERE l.id_producto = ?1 AND upper(trim(l.codigo_lote)) = upper(trim(?2)) ORDER BY l.id LIMIT 1", nativeQuery = true)
	Optional<Lotes> findByProductoAndCodigo(Long productoId, String codigoLote);

	/**
	 * Lotes con stock y no vencidos, en orden FEFO (primero el que vence antes).
	 */
	@Query(value = "SELECT * FROM lotes l WHERE l.id_producto = ?1 AND COALESCE(l.stock_actual, 0) > 0 "
			+ "AND CAST(l.fecha_vencimiento AS date) >= CAST(?2 AS date) "
			+ "ORDER BY l.fecha_vencimiento ASC, l.id ASC", nativeQuery = true)
	List<Lotes> findDisponiblesFefo(Long productoId, Date hoy);

	@Query(value = "SELECT COALESCE(SUM(l.stock_actual), 0) FROM lotes l WHERE l.id_producto = ?1 "
			+ "AND COALESCE(l.stock_actual, 0) > 0 AND CAST(l.fecha_vencimiento AS date) >= CAST(?2 AS date)", nativeQuery = true)
	Double getStockDisponible(Long productoId, Date hoy);

	@Query(value = "SELECT COALESCE(SUM(l.stock_actual), 0) FROM lotes l WHERE l.id_producto = ?1 "
			+ "AND COALESCE(l.stock_actual, 0) > 0 AND CAST(l.fecha_vencimiento AS date) < CAST(?2 AS date)", nativeQuery = true)
	Double getStockVencido(Long productoId, Date hoy);
}
