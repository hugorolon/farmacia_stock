package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.ProcesoPagoComprasDTO;

@Component
public class ProcesoPagoComprasApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public ProcesoPagoComprasApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/proceso-pago-compras";
    }

    public List<ProcesoPagoComprasDTO> findAll() {
        ProcesoPagoComprasDTO[] entities = restTemplate.getForObject(apiUrl, ProcesoPagoComprasDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<ProcesoPagoComprasDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, ProcesoPagoComprasDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public ProcesoPagoComprasDTO save(ProcesoPagoComprasDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, ProcesoPagoComprasDTO.class);
    }

    public ProcesoPagoComprasDTO update(Long id, ProcesoPagoComprasDTO entity) {
        entity.setId(id.intValue());
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), ProcesoPagoComprasDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public ProcesoPagoComprasDTO findByPcoCompraAndPcoProceso(Integer compraId, Integer proceso) { return findAll().stream().findFirst().orElse(null); }
    public void remove(ProcesoPagoComprasDTO entity) { if (entity != null && entity.getId() != null) remove(entity.getId().longValue()); }
}
