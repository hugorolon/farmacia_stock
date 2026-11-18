package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.ClienteDTO;
import py.com.prestosoftware.data.dtos.VentaTemporalDTO;

@Component
public class VentaTemporalApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public VentaTemporalApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/venta-temporales";
    }

    public List<VentaTemporalDTO> findAll() {
        VentaTemporalDTO[] entities = restTemplate.getForObject(apiUrl, VentaTemporalDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<VentaTemporalDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, VentaTemporalDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public VentaTemporalDTO save(VentaTemporalDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, VentaTemporalDTO.class);
    }

    public VentaTemporalDTO update(Long id, VentaTemporalDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), VentaTemporalDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public Long getNroTimbrado() { return restTemplate.getForObject(apiUrl + "/timbrado/nro", Long.class); }
    public long getRowCount() { return count(); }
    public java.util.List<Object[]> retriveVentaTemporalDetalleByIdVentaTemporal(Long ventaId) { Object[][] value = restTemplate.getForObject(apiUrl + "/" + ventaId + "/detalles", Object[][] .class); return value == null ? java.util.Collections.emptyList() : java.util.Arrays.asList(value); }
    public VentaTemporalDTO saveTimbrado(String numero, String tipo) { return null; }

    public VentaTemporalDTO save(VentaTemporalDTO venta, ClienteDTO cliente, String condicionPago) {
        java.util.Map<String, Object> request = new java.util.LinkedHashMap<String, Object>();
        request.put("venta", venta);
        request.put("cliente", cliente);
        request.put("condicionPago", condicionPago);
        return restTemplate.postForObject(apiUrl + "/completo", request, VentaTemporalDTO.class);
    }

    public java.util.List<VentaTemporalDTO> getVentasFiltro(java.util.Date desde, java.util.Date hasta,
            String situacion, int formaPago) {
        VentaTemporalDTO[] rows = restTemplate.getForObject(apiUrl + "/filtro?desde=" + desde.getTime()
                + "&hasta=" + hasta.getTime() + "&situacion=" + situacion + "&formaPago=" + formaPago,
                VentaTemporalDTO[].class);
        return rows == null ? java.util.Collections.emptyList() : java.util.Arrays.asList(rows);
    }

    public List<VentaTemporalDTO> getNotasPorFechas(java.util.Date desde, java.util.Date hasta) {
        VentaTemporalDTO[] rows = restTemplate.getForObject(apiUrl + "/notas?desde=" + desde.getTime()
                + "&hasta=" + hasta.getTime(), VentaTemporalDTO[].class);
        return rows == null ? Collections.emptyList() : Arrays.asList(rows);
    }
}
