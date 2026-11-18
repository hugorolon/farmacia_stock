package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.TamanhoDTO;

@Component
public class TamanhoApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public TamanhoApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/tamanhos";
    }

    public List<TamanhoDTO> findAll() {
        TamanhoDTO[] entities = restTemplate.getForObject(apiUrl, TamanhoDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<TamanhoDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, TamanhoDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public java.util.List<TamanhoDTO> findByNombre(String nombre) {
        String normalized = nombre == null ? "" : nombre;
        return findAll().stream().filter(entity -> FrontendApiClientSupport.nombreContains(entity, normalized)).collect(Collectors.toList());
    }

    public long getRowCount() { return count(); }
    public long getRowMax() { return count(); }
    public long getRoxMax() { return count(); }

    public TamanhoDTO save(TamanhoDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, TamanhoDTO.class);
    }

    public TamanhoDTO update(Long id, TamanhoDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), TamanhoDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }
}
