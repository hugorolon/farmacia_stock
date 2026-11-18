package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.ProcesoCobroClientesDTO;

@Component
public class ProcesoCobroClientesApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public ProcesoCobroClientesApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/proceso-cobro-clientes";
    }

    public List<ProcesoCobroClientesDTO> findAll() {
        ProcesoCobroClientesDTO[] entities = restTemplate.getForObject(apiUrl, ProcesoCobroClientesDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<ProcesoCobroClientesDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, ProcesoCobroClientesDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public ProcesoCobroClientesDTO save(ProcesoCobroClientesDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, ProcesoCobroClientesDTO.class);
    }

    public ProcesoCobroClientesDTO update(Long id, ProcesoCobroClientesDTO entity) {

        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), ProcesoCobroClientesDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }
}
