package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.ProcesoCobroVentasDTO;

@Component
public class ProcesoCobroVentasApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public ProcesoCobroVentasApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/proceso-cobro-ventas";
    }

    public List<ProcesoCobroVentasDTO> findAll() {
        ProcesoCobroVentasDTO[] entities = restTemplate.getForObject(apiUrl, ProcesoCobroVentasDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<ProcesoCobroVentasDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, ProcesoCobroVentasDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public ProcesoCobroVentasDTO save(ProcesoCobroVentasDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, ProcesoCobroVentasDTO.class);
    }

    public ProcesoCobroVentasDTO update(Long id, ProcesoCobroVentasDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), ProcesoCobroVentasDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public ProcesoCobroVentasDTO findByPveVenta(Integer ventaId) { return findAll().stream().findFirst().orElse(null); }
    public void remove(ProcesoCobroVentasDTO entity) { if (entity != null && entity.getId() != null) remove(entity.getId()); }
}
