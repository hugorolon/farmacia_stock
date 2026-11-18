package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.DevolucionDTO;

@Component
public class DevolucionApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public DevolucionApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/devoluciones";
    }

    public List<DevolucionDTO> findAll() {
        DevolucionDTO[] entities = restTemplate.getForObject(apiUrl, DevolucionDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<DevolucionDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, DevolucionDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public DevolucionDTO save(DevolucionDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, DevolucionDTO.class);
    }

    public DevolucionDTO update(Long id, DevolucionDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), DevolucionDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public java.util.Optional<DevolucionDTO> findNota(Long id, String situacion, String tipo) { return findById(id); }
    public java.util.List<DevolucionDTO> getNotasPendientes(java.util.Date fecha, String situacion) { return findAll(); }
    public java.util.List<Object[]> getDetallesDevolucion(Long id) {
        Object[][] rows = restTemplate.getForObject(apiUrl + "/" + id + "/detalles", Object[][].class);
        return rows == null ? java.util.Collections.emptyList() : java.util.Arrays.asList(rows);
    }
    public long getRowCount() { return count(); }
}
