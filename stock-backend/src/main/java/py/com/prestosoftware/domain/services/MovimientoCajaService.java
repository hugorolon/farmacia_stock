package py.com.prestosoftware.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import py.com.prestosoftware.data.models.Caja;
import py.com.prestosoftware.data.models.MovimientoCaja;
import py.com.prestosoftware.data.repository.MovimientoCajaRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoCajaService {

    private MovimientoCajaRepository repository;

    @Autowired
    public MovimientoCajaService(MovimientoCajaRepository repository) {
        this.repository = repository;
    }

    public List<MovimientoCaja> findAll() {
        return repository.findAll();
    }
    
    public List<MovimientoCaja> findByFechaAndCajaAndSituacion(Date fecha, Caja caja, String situacion) {
    	return repository.findByFechaAndCajaAndSituacionOrderByIdAsc(fecha, caja, situacion);
    }
    
    public Date findLastDateMov(Date fecha) {
    	return repository.findLastDateMov(fecha);
    }
    
    public Optional<Double> totalVentaContado(Date fecha) {
    	return repository.getTotalsVentaContado(fecha);
    }
    
    public Optional<Double> totalOtrosIngresos(Date fecha, Caja caja) {
    	return repository.getTotalsOtrosIngresos(caja.getId(), fecha);
    }
    
    public Optional<Double> totalCompraContado(Date fecha, Caja caja) {
    	return repository.getTotalsCompraContado(fecha);
    }
 
    public Optional<Double> totalOtrosEgresos(Date fecha, Caja caja) {
    	return repository.getTotalsOtrosEgresos(caja.getId(), fecha);
    }
    public Optional<Double> totalEntrada(Date fechaInicial, Date fechaFinal, Caja caja) {
    	return repository.getTotalsEntrada(caja.getId(), fechaInicial, fechaFinal);
    }
    
    public Optional<Double> totalSalida(Date fechaInicial, Date fechaFinal, Caja caja) {
    	return repository.getTotalsSalida(caja.getId(), fechaInicial, fechaFinal);
    }
    
    public Optional<MovimientoCaja> findByIdVenta(String idVenta) {
    	return repository.getMovimientoCajaPorNota(idVenta);
    }
    
    public Optional<MovimientoCaja> findByNotaNroAndTipoOperacion(String id, String mov) {
    	return repository.findByNotaNroAndTipoOperacion(id, mov);
    }
    
    public MovimientoCaja findByNotaNro(String id) {
    	return repository.findByNotaNro(id);
    }
    
    /** Sumas de los movimientos de la caja en la fecha y situacion dadas (vacio si no hay movimientos). */
    public Optional<MovimientoCaja> getTotalsMovCaja(Caja caja, Date fecha, String situacion) {
    	List<Object[]> rows = repository.getTotalsEntradaCaja(caja.getId(), fecha, situacion);
    	if (rows.isEmpty() || rows.get(0)[6] == null || ((Number) rows.get(0)[6]).longValue() == 0) {
    		return Optional.empty();
    	}
    	Object[] r = rows.get(0);
    	MovimientoCaja totales = new MovimientoCaja();
    	totales.setCaja(caja);
    	totales.setFecha(fecha);
    	totales.setSituacion(situacion);
    	totales.setNotaValor(toDouble(r[0]));
    	totales.setValorM01(toDouble(r[1]));
    	totales.setValorM02(toDouble(r[2]));
    	totales.setValorM03(toDouble(r[3]));
    	totales.setValorM04(toDouble(r[4]));
    	totales.setValorM05(toDouble(r[5]));
    	return Optional.of(totales);
    }

    private static Double toDouble(Object value) {
    	return value == null ? 0d : ((Number) value).doubleValue();
    }

//    @Transactional
//    public void save(MovimientoCaja pago) {
//        repository.save(pago);
//        repository.flush();
//    }
    
    @Transactional
    public MovimientoCaja save(MovimientoCaja pago) {
        return repository.save(pago);
    }
    
    @Transactional
    public List<MovimientoCaja> save(List<MovimientoCaja> pagos) {
        return repository.saveAll(pagos);
    }

    public void remove(MovimientoCaja pago) {
        repository.delete(pago);
    }

}
