package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.ConfiguracionDTO;
import py.com.prestosoftware.data.dtos.EmpresaDTO;
import py.com.prestosoftware.data.dtos.UsuarioDTO;

@Component
public class ConfiguracionApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public ConfiguracionApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/configuraciones";
    }

    public List<ConfiguracionDTO> findAll() {
        ConfiguracionDTO[] entities = restTemplate.getForObject(apiUrl, ConfiguracionDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<ConfiguracionDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, ConfiguracionDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public ConfiguracionDTO save(ConfiguracionDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, ConfiguracionDTO.class);
    }

    public ConfiguracionDTO update(Long id, ConfiguracionDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), ConfiguracionDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public java.util.Optional<ConfiguracionDTO> findByEmpresaId(EmpresaDTO empresa) {
        if (empresa == null || empresa.getId() == null) {
            return java.util.Optional.empty();
        }

        return findAll().stream()
                .filter(configuracion -> configuracion.getEmpresaId() != null
                        && configuracion.getEmpresaId().getId() != null
                        && configuracion.getEmpresaId().getId().equals(empresa.getId()))
                .findFirst();
    }
    public java.util.Optional<ConfiguracionDTO> findByUserId(UsuarioDTO usuario) { return java.util.Optional.empty(); }
}
