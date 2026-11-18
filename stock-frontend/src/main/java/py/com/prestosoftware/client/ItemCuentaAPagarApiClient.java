package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.ItemCuentaAPagarDTO;

@Component
public class ItemCuentaAPagarApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public ItemCuentaAPagarApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/item-cuenta-apagar";
    }

    public List<ItemCuentaAPagarDTO> findAll() {
        ItemCuentaAPagarDTO[] entities = restTemplate.getForObject(apiUrl, ItemCuentaAPagarDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public ItemCuentaAPagarDTO findById(Integer id) {
        return restTemplate.getForObject(apiUrl + "/" + id, ItemCuentaAPagarDTO.class);
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public ItemCuentaAPagarDTO save(ItemCuentaAPagarDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, ItemCuentaAPagarDTO.class);
    }

    public ItemCuentaAPagarDTO update(Integer id, ItemCuentaAPagarDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), ItemCuentaAPagarDTO.class).getBody();
    }

    public void remove(Integer id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public ItemCuentaAPagarDTO cambiaEstadoSituacion(int situacion, Long id) {
        return restTemplate.exchange(apiUrl + "/" + id + "/situacion/" + situacion,
                org.springframework.http.HttpMethod.PUT, null, ItemCuentaAPagarDTO.class).getBody();
    }

    public java.util.List<ItemCuentaAPagarDTO> findByCabId(Integer cabId) { return findAll(); }
    public void remove(ItemCuentaAPagarDTO entity) { if (entity != null && entity.getId() != null) remove(entity.getId()); }
}
