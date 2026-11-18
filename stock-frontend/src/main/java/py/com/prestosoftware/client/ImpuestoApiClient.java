package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.ImpuestoDTO;

@Component
public class ImpuestoApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public ImpuestoApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/impuestos";
    }

    public List<ImpuestoDTO> findAll() {
        ImpuestoDTO[] entities = restTemplate.getForObject(apiUrl, ImpuestoDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<ImpuestoDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, ImpuestoDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public List<ImpuestoDTO> findByNombre(String nombre) {
        return findAll().stream()
                .filter(entity -> FrontendApiClientSupport.nombreContains(entity, nombre))
                .collect(Collectors.toList());
    }

    public long getRowCount() {
        return count();
    }

    public long getRowMax() {
        return count();
    }

    public long getRoxMax() {
        return count();
    }

    public ImpuestoDTO save(ImpuestoDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, ImpuestoDTO.class);
    }

    public ImpuestoDTO update(Long id, ImpuestoDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), ImpuestoDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }
}
