package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.MovimientoItemEgresoDTO;
import py.com.prestosoftware.data.dtos.MovimientoItemIngresoDTO;

@Component
public class MovimientoItemEgresoApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public MovimientoItemEgresoApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/movimiento-item-egresos";
    }

    public List<MovimientoItemEgresoDTO> findAll() {
        MovimientoItemEgresoDTO[] entities = restTemplate.getForObject(apiUrl, MovimientoItemEgresoDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<MovimientoItemEgresoDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, MovimientoItemEgresoDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public MovimientoItemEgresoDTO save(MovimientoItemEgresoDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, MovimientoItemEgresoDTO.class);
    }

    public MovimientoItemEgresoDTO update(Long id, MovimientoItemEgresoDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), MovimientoItemEgresoDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public List<MovimientoItemEgresoDTO> findByCabId(Integer cabId) {
        MovimientoItemEgresoDTO[] entities = restTemplate.getForObject(
                apiUrl + "/cab/" + cabId, MovimientoItemEgresoDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }
    
    public java.util.List<MovimientoItemEgresoDTO> save(java.util.List<MovimientoItemEgresoDTO> entities) {
        java.util.List<MovimientoItemEgresoDTO> saved = new java.util.ArrayList<>();
        for (MovimientoItemEgresoDTO entity : entities) saved.add(save(entity));
        return saved;
    }
    public void remove(MovimientoItemEgresoDTO entity) { if (entity != null && entity.getId() != null) remove(entity.getId()); }
}
