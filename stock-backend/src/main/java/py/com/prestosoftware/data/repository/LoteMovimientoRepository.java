package py.com.prestosoftware.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import py.com.prestosoftware.data.models.LoteMovimiento;

@Repository
public interface LoteMovimientoRepository extends JpaRepository<LoteMovimiento, Long> {

	List<LoteMovimiento> findByTipoAndReferenciaIdAndRevertido(String tipo, Long referenciaId, Integer revertido);

	List<LoteMovimiento> findByLoteIdOrderByFechaAsc(Long loteId);

	List<LoteMovimiento> findByProductoIdOrderByFechaAsc(Long productoId);
}
