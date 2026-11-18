package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.UsuarioDTO;

@Component
public class UsuarioApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public UsuarioApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/usuarios";
    }

    public List<UsuarioDTO> findAll() {
        UsuarioDTO[] entities = restTemplate.getForObject(apiUrl, UsuarioDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<UsuarioDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, UsuarioDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public java.util.List<UsuarioDTO> findByNombre(String nombre) {
        String normalized = nombre == null ? "" : nombre;
        return findAll().stream().filter(entity -> FrontendApiClientSupport.nombreContains(entity, normalized)).collect(Collectors.toList());
    }

    public long getRowCount() { return count(); }
    public long getRowMax() { return count(); }
    public long getRoxMax() { return count(); }
    public java.util.Optional<UsuarioDTO> login(String usuario, String clave) {
        UsuarioDTO[] users = restTemplate.getForObject(apiUrl + "?usuario={usuario}&clave={clave}", UsuarioDTO[].class, usuario, clave);
        return users == null || users.length == 0 ? java.util.Optional.empty() : java.util.Optional.of(users[0]);
    }
    public java.util.List<UsuarioDTO> findByUsuario(String usuario) {
        return findAll().stream()
                .filter(entity -> usuario != null && usuario.equals(entity.getUsuario()))
                .collect(Collectors.toList());
    }

    public UsuarioDTO save(UsuarioDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, UsuarioDTO.class);
    }

    public UsuarioDTO update(Long id, UsuarioDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), UsuarioDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }
}
