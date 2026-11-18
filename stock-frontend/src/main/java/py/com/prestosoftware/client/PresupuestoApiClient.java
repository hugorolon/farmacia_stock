package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.PresupuestoDTO;

@Component
public class PresupuestoApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public PresupuestoApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/presupuestos";
    }

    public List<PresupuestoDTO> findAll() {
        PresupuestoDTO[] entities = restTemplate.getForObject(apiUrl, PresupuestoDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<PresupuestoDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, PresupuestoDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public long getRowCount() { return count(); }
    public PresupuestoDTO getPresupuesto(Long id) {
        return findById(id).orElse(null);
    }

    public PresupuestoDTO save(PresupuestoDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, PresupuestoDTO.class);
    }

    public PresupuestoDTO update(Long id, PresupuestoDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), PresupuestoDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }
}
