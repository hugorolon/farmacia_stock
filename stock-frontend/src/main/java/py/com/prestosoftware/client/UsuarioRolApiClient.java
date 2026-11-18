package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.UsuarioRolDTO;

@Component
public class UsuarioRolApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public UsuarioRolApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/usuario-roles";
    }

    public List<UsuarioRolDTO> findAll() {
        UsuarioRolDTO[] entities = restTemplate.getForObject(apiUrl, UsuarioRolDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<UsuarioRolDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, UsuarioRolDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public java.util.List<UsuarioRolDTO> findByNombre(String nombre) {
        String normalized = nombre == null ? "" : nombre;
        return findAll().stream().filter(entity -> FrontendApiClientSupport.nombreContains(entity, normalized)).collect(Collectors.toList());
    }

    public long getRowCount() { return count(); }
    public long getRowMax() { return count(); }
    public long getRoxMax() { return count(); }
    public java.util.List<UsuarioRolDTO> findByUsuarioRol(String nombre) {
        return findAll().stream()
                .filter(entity -> nombre == null || FrontendApiClientSupport.nombreContains(entity, nombre))
                .collect(Collectors.toList());
    }
    public boolean hasRole(Long usuarioId, String role) {
        return findAll().stream().anyMatch(entity -> usuarioId != null
                && entity.getUsuario() != null
                && usuarioId.equals(entity.getUsuario().getId()));
    }

    public UsuarioRolDTO save(UsuarioRolDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, UsuarioRolDTO.class);
    }

    public UsuarioRolDTO update(Long id, UsuarioRolDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), UsuarioRolDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }
}
