package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.CajaDTO;
import py.com.prestosoftware.data.dtos.MovimientoCajaDTO;

@Component
public class MovimientoCajaApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public MovimientoCajaApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/movimiento-cajas";
    }

    public List<MovimientoCajaDTO> findAll() {
        MovimientoCajaDTO[] entities = restTemplate.getForObject(apiUrl, MovimientoCajaDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<MovimientoCajaDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, MovimientoCajaDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public MovimientoCajaDTO save(MovimientoCajaDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, MovimientoCajaDTO.class);
    }

    public List<MovimientoCajaDTO> save(List<MovimientoCajaDTO> entities) {
        MovimientoCajaDTO[] saved = restTemplate.postForObject(apiUrl + "/batch", entities, MovimientoCajaDTO[].class);
        return saved == null ? Collections.emptyList() : Arrays.asList(saved);
    }

    public MovimientoCajaDTO update(Long id, MovimientoCajaDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), MovimientoCajaDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }
    public void remove(MovimientoCajaDTO entity) { if (entity != null && entity.getId() != null) remove(entity.getId()); }

    // ---- Consultas de MovimientoCajaService (fechas viajan como yyyy-MM-dd en hora local) ----

    public List<MovimientoCajaDTO> findByFechaAndCajaAndSituacion(java.util.Date fecha, CajaDTO caja, String situacion) {
        MovimientoCajaDTO[] rows = restTemplate.getForObject(apiUrl + "/filtro?fecha={fecha}&cajaId={cajaId}&situacion={situacion}",
                MovimientoCajaDTO[].class, fmt(fecha), caja.getId(), situacion);
        return rows == null ? Collections.emptyList() : Arrays.asList(rows);
    }

    /** null si la venta/nota no tiene movimiento de caja. */
    public MovimientoCajaDTO findByIdVenta(String idVenta) {
        return restTemplate.getForObject(apiUrl + "/venta/{id}", MovimientoCajaDTO.class, idVenta);
    }

    /** null si no existe. */
    public MovimientoCajaDTO findByNotaNroAndTipoOperacion(String nro, String tipo) {
        return restTemplate.getForObject(apiUrl + "/nota/{nro}/tipo/{tipo}", MovimientoCajaDTO.class, nro, tipo);
    }

    /** null si no existe. */
    public MovimientoCajaDTO findByNotaNro(String nro) {
        return restTemplate.getForObject(apiUrl + "/nota/{nro}", MovimientoCajaDTO.class, nro);
    }

    /** Sumas (notaValor, valorM01..M05) de la caja en la fecha y situacion; vacio si no hay movimientos. */
    public java.util.Optional<MovimientoCajaDTO> getTotalsMovCaja(CajaDTO caja, java.util.Date fecha, String situacion) {
        return java.util.Optional.ofNullable(restTemplate.getForObject(
                apiUrl + "/totales?cajaId={cajaId}&fecha={fecha}&situacion={situacion}",
                MovimientoCajaDTO.class, caja.getId(), fmt(fecha), situacion));
    }

    /** Ultima fecha con movimientos de ingreso anterior a la dada; null si no hay. */
    public java.util.Date findLastDateMov(java.util.Date fecha) {
        return restTemplate.getForObject(apiUrl + "/ultima-fecha?fecha={fecha}", java.util.Date.class, fmt(fecha));
    }

    public java.util.Optional<Double> totalVentaContado(java.util.Date fecha) {
        if (fecha == null) return java.util.Optional.of(0d);
        return total(apiUrl + "/totales/venta-contado?fecha={fecha}", fmt(fecha));
    }

    public java.util.Optional<Double> totalCompraContado(java.util.Date fecha, CajaDTO caja) {
        if (fecha == null) return java.util.Optional.of(0d);
        return total(apiUrl + "/totales/compra-contado?fecha={fecha}&cajaId={cajaId}", fmt(fecha), caja.getId());
    }

    public java.util.Optional<Double> totalOtrosIngresos(java.util.Date fecha, CajaDTO caja) {
        if (fecha == null) return java.util.Optional.of(0d);
        return total(apiUrl + "/totales/otros-ingresos?fecha={fecha}&cajaId={cajaId}", fmt(fecha), caja.getId());
    }

    public java.util.Optional<Double> totalOtrosEgresos(java.util.Date fecha, CajaDTO caja) {
        if (fecha == null) return java.util.Optional.of(0d);
        return total(apiUrl + "/totales/otros-egresos?fecha={fecha}&cajaId={cajaId}", fmt(fecha), caja.getId());
    }

    public java.util.Optional<Double> totalEntrada(java.util.Date desde, java.util.Date hasta, CajaDTO caja) {
        return total(apiUrl + "/totales/entrada?desde={desde}&hasta={hasta}&cajaId={cajaId}", fmt(desde), fmt(hasta), caja.getId());
    }

    public java.util.Optional<Double> totalSalida(java.util.Date desde, java.util.Date hasta, CajaDTO caja) {
        return total(apiUrl + "/totales/salida?desde={desde}&hasta={hasta}&cajaId={cajaId}", fmt(desde), fmt(hasta), caja.getId());
    }

    private java.util.Optional<Double> total(String url, Object... params) {
        Double value = restTemplate.getForObject(url, Double.class, params);
        return java.util.Optional.of(value == null ? 0d : value);
    }

    private static String fmt(java.util.Date fecha) {
        return new java.text.SimpleDateFormat("yyyy-MM-dd").format(fecha);
    }
}
