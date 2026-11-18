package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.ItemPagarProveedorDTO;

@Component
public class ItemPagarProveedorApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public ItemPagarProveedorApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/item-pagar-proveedor";
    }

    public List<ItemPagarProveedorDTO> findAll() {
        ItemPagarProveedorDTO[] entities = restTemplate.getForObject(apiUrl, ItemPagarProveedorDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public ItemPagarProveedorDTO findById(Integer id) {
        return restTemplate.getForObject(apiUrl + "/" + id, ItemPagarProveedorDTO.class);
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public ItemPagarProveedorDTO save(ItemPagarProveedorDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, ItemPagarProveedorDTO.class);
    }

    public ItemPagarProveedorDTO update(Integer id, ItemPagarProveedorDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), ItemPagarProveedorDTO.class).getBody();
    }

    public void remove(Integer id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public java.util.List<ItemPagarProveedorDTO> findByIppNumero(Integer numero) {
        ItemPagarProveedorDTO[] entities = restTemplate.getForObject(apiUrl + "/pago/" + numero, ItemPagarProveedorDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }
    public void remove(ItemPagarProveedorDTO entity) { if (entity != null && entity.getId() != null) remove(entity.getId()); }
    public java.util.List<ItemPagarProveedorDTO> saveDetalles(java.util.List<ItemPagarProveedorDTO> entities) {
        if (entities == null) {
            throw new IllegalArgumentException("La lista de detalles no puede ser nula");
        }
        java.util.List<ItemPagarProveedorDTO> saved = new java.util.ArrayList<>();
        for (ItemPagarProveedorDTO entity : entities) saved.add(save(entity));
        return saved;
    }
}
