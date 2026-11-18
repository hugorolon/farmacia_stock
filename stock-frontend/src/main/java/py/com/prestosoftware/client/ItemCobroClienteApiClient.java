package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.ItemCobroClienteDTO;

@Component
public class ItemCobroClienteApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public ItemCobroClienteApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/item-cobro-clientes";
    }

    public List<ItemCobroClienteDTO> findAll() {
        ItemCobroClienteDTO[] entities = restTemplate.getForObject(apiUrl, ItemCobroClienteDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public ItemCobroClienteDTO findById(Integer id) {
        return restTemplate.getForObject(apiUrl + "/" + id, ItemCobroClienteDTO.class);
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public ItemCobroClienteDTO save(ItemCobroClienteDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, ItemCobroClienteDTO.class);
    }

    public ItemCobroClienteDTO update(Integer id, ItemCobroClienteDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), ItemCobroClienteDTO.class).getBody();
    }

    public void remove(Integer id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public java.util.List<ItemCobroClienteDTO> findByIclNumero(Integer numero) {
        ItemCobroClienteDTO[] entities = restTemplate.getForObject(apiUrl + "/cobro/" + numero, ItemCobroClienteDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }
}
