package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.VentaDTO;
import py.com.prestosoftware.data.dtos.VentaDetalleDTO;

@Component
public class VentaApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public VentaApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/ventas";
    }

    public List<VentaDTO> findAll() {
        VentaDTO[] entities = restTemplate.getForObject(apiUrl, VentaDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<VentaDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, VentaDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    /**
     * Guarda la venta tal cual, sin movimientos de stock ni de caja.
     * Si ya tiene id se actualiza (PUT); si es nueva se crea con lanzCaja=1.
     * Para registrar una venta con sus movimientos usar save(lanzCaja, venta, cliente, condicionPago).
     */
    public VentaDTO save(VentaDTO entity) {
        VentaDTO saved = entity.getId() != null
                ? update(entity.getId(), entity)
                : save(1, entity, null, entity.getCondicion() == 0 ? "Contado" : "Credito");
        // El backend devuelve la entidad sin relaciones cargadas (items, cliente...): se recarga completa.
        return saved == null || saved.getId() == null ? saved : findById(saved.getId()).orElse(saved);
    }

    public VentaDTO update(Long id, VentaDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), VentaDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public java.util.Optional<VentaDTO> findNota(Long id, String situacion) { return findById(id); }
    public java.util.List<VentaDTO> getNotasPendientesBySituacion(String situacion) { return findAll(); }
    public java.util.List<VentaDTO> getNotasPendientesByFechaAndSituacion(java.util.Date fecha, String situacion) { return findAll(); }
    public Long getNroTimbrado() {
        Long nro = restTemplate.getForObject(apiUrl + "/timbrado/nro", Long.class);
        return nro == null ? 0L : nro;
    }
    public long getRowCount() { return count(); }
    public java.util.List<Object[]> retriveVentaDetalleByIdVenta(Long ventaId) { Object[][] value = restTemplate.getForObject(apiUrl + "/" + ventaId + "/detalles", Object[][] .class); return value == null ? java.util.Collections.emptyList() : java.util.Arrays.asList(value); }
    public VentaDTO saveRemoved(int tipo, VentaDTO venta, java.util.List<VentaDetalleDTO> detalles, String motivo) { return save(venta); }
    public void saveTimbrado(String ventaId, String nroTimbrado) {
        restTemplate.put(apiUrl + "/" + ventaId + "/timbrado?nro={nro}", null, nroTimbrado.trim());
    }

    public java.util.List<VentaDTO> findByClienteId(Long clienteId) { return findAll(); }
    public VentaDTO findByVentaIdAndClienteId(Long ventaId, Long clienteId) { return findById(ventaId).orElse(null); }
    public VentaDTO save(int lanzCaja, VentaDTO venta, py.com.prestosoftware.data.dtos.ClienteDTO cliente, String condicionPago) {
        java.util.Map<String, Object> request = new java.util.LinkedHashMap<String, Object>();
        request.put("lanzCaja", lanzCaja);
        request.put("condicionPago", condicionPago);
        request.put("venta", venta);
        request.put("cliente", cliente);
        return restTemplate.postForObject(apiUrl, request, VentaDTO.class);
    }

    public List<VentaDTO> findByFecha() {
        VentaDTO[] rows = restTemplate.getForObject(apiUrl + "/fecha", VentaDTO[].class);
        return rows == null ? Collections.emptyList() : Arrays.asList(rows);
    }

    public List<VentaDTO> getVentasFiltro(java.util.Date desde, java.util.Date hasta,
            String situacion, int formaPago) {
        VentaDTO[] rows = restTemplate.getForObject(apiUrl + "/filtro?desde=" + desde.getTime()
                + "&hasta=" + hasta.getTime() + "&situacion=" + situacion + "&formaPago=" + formaPago,
                VentaDTO[].class);
        return rows == null ? Collections.emptyList() : Arrays.asList(rows);
    }

    public VentaDTO findByFilter(String filter) {
        return restTemplate.getForObject(apiUrl + "/filter?value=" + filter, VentaDTO.class);
    }

    public java.util.List<Object[]> retriveVentasByProductoId(Long productoId) {
        Object[][] rows = restTemplate.getForObject(apiUrl + "/producto/" + productoId, Object[][].class);
        return rows == null ? Collections.emptyList() : Arrays.asList(rows);
    }
}
