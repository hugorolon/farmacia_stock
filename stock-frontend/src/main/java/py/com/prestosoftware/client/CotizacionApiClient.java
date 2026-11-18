package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.CotizacionDTO;

@Component
public class CotizacionApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public CotizacionApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/cotizaciones";
    }

    public List<CotizacionDTO> findAll() {
        CotizacionDTO[] entities = restTemplate.getForObject(apiUrl, CotizacionDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<CotizacionDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, CotizacionDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public List<CotizacionDTO> findByNombre(String nombre) {
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

    public java.util.Optional<CotizacionDTO> findCotizacionByMoneda(Long monedaId) {
        return findAll().stream()
                .filter(cotizacion -> cotizacion.getMoneda() != null
                        && cotizacion.getMoneda().getId().equals(monedaId))
                .findFirst();
    }

    public CotizacionDTO save(CotizacionDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, CotizacionDTO.class);
    }

    public CotizacionDTO update(Long id, CotizacionDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), CotizacionDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }
}
