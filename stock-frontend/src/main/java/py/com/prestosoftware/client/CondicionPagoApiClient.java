package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.CondicionPagoDTO;

@Component
public class CondicionPagoApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public CondicionPagoApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/condicion-pagos";
    }

    public List<CondicionPagoDTO> findAll() {
        CondicionPagoDTO[] entities = restTemplate.getForObject(apiUrl, CondicionPagoDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<CondicionPagoDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, CondicionPagoDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public List<CondicionPagoDTO> findByNombre(String nombre) {
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

    public CondicionPagoDTO save(CondicionPagoDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, CondicionPagoDTO.class);
    }

    public CondicionPagoDTO update(Long id, CondicionPagoDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), CondicionPagoDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public CondicionPagoDTO findByCantDia(int cantDia) { return findAll().stream().filter(x -> x.getCantDia() == cantDia).findFirst().orElse(null); }
}
