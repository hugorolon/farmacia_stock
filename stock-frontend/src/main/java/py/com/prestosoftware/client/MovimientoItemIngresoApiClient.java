package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.MovimientoItemIngresoDTO;

@Component
public class MovimientoItemIngresoApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public MovimientoItemIngresoApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/movimiento-item-ingresos";
    }

    public List<MovimientoItemIngresoDTO> findAll() {
        MovimientoItemIngresoDTO[] entities = restTemplate.getForObject(apiUrl, MovimientoItemIngresoDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<MovimientoItemIngresoDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, MovimientoItemIngresoDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public MovimientoItemIngresoDTO save(MovimientoItemIngresoDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, MovimientoItemIngresoDTO.class);
    }

    public MovimientoItemIngresoDTO update(Long id, MovimientoItemIngresoDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), MovimientoItemIngresoDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public List<MovimientoItemIngresoDTO> findByCabId(Integer cabId) {
        MovimientoItemIngresoDTO[] entities = restTemplate.getForObject(
                apiUrl + "/cab/" + cabId, MovimientoItemIngresoDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }
    public java.util.List<MovimientoItemIngresoDTO> save(java.util.List<MovimientoItemIngresoDTO> entities) {
        java.util.List<MovimientoItemIngresoDTO> saved = new java.util.ArrayList<>();
        for (MovimientoItemIngresoDTO entity : entities) saved.add(save(entity));
        return saved;
    }
    public void remove(MovimientoItemIngresoDTO entity) { remove(entity.getId()); }
}
