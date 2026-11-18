package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.EmpresaDTO;

@Component
public class EmpresaApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public EmpresaApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/empresas";
    }

    public List<EmpresaDTO> findAll() {
        EmpresaDTO[] entities = restTemplate.getForObject(apiUrl, EmpresaDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<EmpresaDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, EmpresaDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public long getRowCount() {
        return count();
    }

    public List<EmpresaDTO> findByNombre(String nombre) {
        String normalizedNombre = nombre == null ? "" : nombre.toLowerCase();
        return findAll().stream()
                .filter(empresa -> empresa.getNombre() != null
                        && empresa.getNombre().toLowerCase().contains(normalizedNombre))
                .collect(Collectors.toList());
    }

    public EmpresaDTO save(EmpresaDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, EmpresaDTO.class);
    }

    public EmpresaDTO update(Long id, EmpresaDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), EmpresaDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }
}
