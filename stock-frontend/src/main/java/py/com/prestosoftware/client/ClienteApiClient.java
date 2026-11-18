package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.ClienteDTO;

@Component
public class ClienteApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public ClienteApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/clientes";
    }

    public List<ClienteDTO> findAll() {
        ClienteDTO[] entities = restTemplate.getForObject(apiUrl, ClienteDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<ClienteDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, ClienteDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public long addNewClient() {
        return restTemplate.getForObject(apiUrl + "/next-id", Long.class);
    }

    public List<ClienteDTO> findByNombre(String nombre) {
        String normalizedNombre = nombre == null ? "" : nombre.toLowerCase();
        return findAll().stream()
                .filter(cliente -> cliente.getNombre() != null
                        && cliente.getNombre().toLowerCase().contains(normalizedNombre))
                .collect(Collectors.toList());
    }
    public ClienteDTO findByNombreEquals(String nombre) {
        return findByNombre(nombre).stream().findFirst().orElse(null);
    }
    public ClienteDTO findByRuc(String ruc) {
        return findAll().stream()
                .filter(cliente -> ruc != null && ruc.equals(cliente.getCiruc()))
                .findFirst().orElse(null);
    }

    public ClienteDTO save(ClienteDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, ClienteDTO.class);
    }

    public ClienteDTO update(Long id, ClienteDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), ClienteDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public java.util.List<ClienteDTO> findAllOrderByName() { return findAll(); }
}
