package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.ClientePaisDTO;

@Component
public class ClientePaisApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public ClientePaisApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/cliente-pais";
    }

    public List<ClientePaisDTO> findAll() {
        ClientePaisDTO[] entities = restTemplate.getForObject(apiUrl, ClientePaisDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<ClientePaisDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, ClientePaisDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public ClientePaisDTO save(ClientePaisDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, ClientePaisDTO.class);
    }

    public ClientePaisDTO update(Long id, ClientePaisDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), ClientePaisDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public ClientePaisDTO findByRuc(String ruc) {
        return findAll().stream().filter(x -> ruc.equals(x.getCiruc())).findFirst().orElse(null);
    }
}
