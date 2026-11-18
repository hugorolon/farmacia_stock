package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.TransferenciaProductoDTO;

@Component
public class TransferenciaProductoApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public TransferenciaProductoApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/transferencia-productos";
    }

    public List<TransferenciaProductoDTO> findAll() {
        TransferenciaProductoDTO[] entities = restTemplate.getForObject(apiUrl, TransferenciaProductoDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<TransferenciaProductoDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, TransferenciaProductoDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public TransferenciaProductoDTO save(TransferenciaProductoDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, TransferenciaProductoDTO.class);
    }

    public TransferenciaProductoDTO update(Long id, TransferenciaProductoDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), TransferenciaProductoDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public long getRowCount() { return count(); }
}
