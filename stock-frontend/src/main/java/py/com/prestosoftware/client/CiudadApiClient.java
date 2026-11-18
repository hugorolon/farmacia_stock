package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.CiudadDTO;

@Component
public class CiudadApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public CiudadApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/ciudades";
    }

    public List<CiudadDTO> findAll() {
        CiudadDTO[] entities = restTemplate.getForObject(apiUrl, CiudadDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<CiudadDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, CiudadDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public Long addNewCiudad() {
        return restTemplate.getForObject(apiUrl + "/addNewCiudad", Long.class);
    }
    
    public List<CiudadDTO> findByNombre(String nombre) {
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

    public CiudadDTO save(CiudadDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, CiudadDTO.class);
    }

    public CiudadDTO update(Long id, CiudadDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), CiudadDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }
}
