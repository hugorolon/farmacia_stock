package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.ItemCuentaARecibirDTO;

@Component
public class ItemCuentaARecibirApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public ItemCuentaARecibirApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/item-cuenta-arecibir";
    }

    public List<ItemCuentaARecibirDTO> findAll() {
        ItemCuentaARecibirDTO[] entities = restTemplate.getForObject(apiUrl, ItemCuentaARecibirDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<ItemCuentaARecibirDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, ItemCuentaARecibirDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public ItemCuentaARecibirDTO save(ItemCuentaARecibirDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, ItemCuentaARecibirDTO.class);
    }

    public ItemCuentaARecibirDTO update(Long id, ItemCuentaARecibirDTO entity) {

        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), ItemCuentaARecibirDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public java.util.List<ItemCuentaARecibirDTO> findByCabId(Integer cabId) { return findAll(); }
    public java.util.List<ItemCuentaARecibirDTO> save(java.util.List<ItemCuentaARecibirDTO> entities) {
        java.util.List<ItemCuentaARecibirDTO> saved = new java.util.ArrayList<>();
        for (ItemCuentaARecibirDTO entity : entities) saved.add(save(entity));
        return saved;
    }
    public void remove(ItemCuentaARecibirDTO entity) { if (entity != null && entity.getIcaSecuencia() != null) remove(entity.getIcaSecuencia().longValue()); }
}
