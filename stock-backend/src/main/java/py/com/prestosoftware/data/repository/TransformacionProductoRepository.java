package py.com.prestosoftware.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import py.com.prestosoftware.data.models.TransformacionProducto;
import java.util.List;

@Repository
public interface TransformacionProductoRepository extends JpaRepository<TransformacionProducto, Long> {
	
	@Query("SELECT coalesce(max(id), 0) FROM TransformacionProducto e")
	Long getMaxId();

	@Query(value = "SELECT t.id, t.fecha, t.cantidad, t.situacion, t.usuario, t.obs, "
			+ "t.producto_origen, p.descripcion, t.deposito_origen, t.deposito_destino "
			+ "FROM transformacion_productos t "
			+ "LEFT JOIN productos p ON p.id = t.producto_origen",
			nativeQuery = true)
	List<Object[]> findSummaryRows();

}
