package py.com.prestosoftware.data.repository;

import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import py.com.prestosoftware.data.models.Caja;
import py.com.prestosoftware.data.models.AperturaCierreCaja;

@Repository
public interface AperturaCierreCajaRepository extends JpaRepository<AperturaCierreCaja, Long> {
	
	Optional<AperturaCierreCaja> findFirstOptionalByCajaIdAndFechaAperturaOrderByFechaAperturaAsc(Long cajaId, Date fechaApertura);
	
	@Query(value = "SELECT * FROM apertura_cierre_cajas "
			+ "WHERE caja_id = :cajaId "
			+ "AND fecha_apertura = CAST(:fecha AS date) "
			+ "ORDER BY id DESC LIMIT 1",
			nativeQuery = true)
	Optional<AperturaCierreCaja> findFirstByCajaAndFechaApertura(
			@Param("cajaId") Long cajaId,
			@Param("fecha") java.sql.Date fecha);
	
	Optional<AperturaCierreCaja> findOptionalByCajaAndFechaAperturaAndUsuario(Caja caja, Date fechaApertura, Long usuario);
	
	@Query(value = "SELECT * FROM apertura_cierre_cajas ORDER BY id DESC LIMIT 1 ", nativeQuery = true)
	Optional<AperturaCierreCaja> getUltAperturaCierre();
	
	@Query("SELECT coalesce(max(id), 0) FROM AperturaCierreCaja e")
	Long getMaxId();

}
