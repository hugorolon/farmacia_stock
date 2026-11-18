package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.CuentaARecibirDTO;

@Component
public class CuentaARecibirApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public CuentaARecibirApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/cuenta-arecibir";
    }

    public List<CuentaARecibirDTO> findAll() {
        CuentaARecibirDTO[] entities = restTemplate.getForObject(apiUrl, CuentaARecibirDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<CuentaARecibirDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, CuentaARecibirDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public CuentaARecibirDTO save(CuentaARecibirDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, CuentaARecibirDTO.class);
    }

    public CuentaARecibirDTO update(Long id, CuentaARecibirDTO entity) {

        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), CuentaARecibirDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public CuentaARecibirDTO findByCarProceso(Integer proceso) { return findAll().stream().filter(x -> proceso.equals(x.getCarProceso())).findFirst().orElse(null); }
    public java.util.List<CuentaARecibirDTO> findByCarProcesoList(Integer proceso) { return findAll(); }
    public void remove(CuentaARecibirDTO entity) { if (entity != null && entity.getCarNumero() != null) remove(entity.getCarNumero().longValue()); }
}
