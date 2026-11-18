package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.CuentaProveedorDTO;
import py.com.prestosoftware.data.dtos.ProveedorDTO;

@Component
public class CuentaProveedorApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public CuentaProveedorApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/cuenta-proveedores";
    }

    public List<CuentaProveedorDTO> findAll() {
        CuentaProveedorDTO[] entities = restTemplate.getForObject(apiUrl, CuentaProveedorDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<CuentaProveedorDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, CuentaProveedorDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public CuentaProveedorDTO save(CuentaProveedorDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, CuentaProveedorDTO.class);
    }

    public CuentaProveedorDTO update(Long id, CuentaProveedorDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), CuentaProveedorDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public CuentaProveedorDTO addNew() { return new CuentaProveedorDTO(); }
    public java.util.List<CuentaProveedorDTO> findPendientes(ProveedorDTO proveedor, String situacion) {
        return findAll().stream()
                .filter(x -> proveedor != null && x.getProveedor() != null
                        && proveedor.getId().equals(x.getProveedor().getId()))
                .collect(java.util.stream.Collectors.toList());
    }
}
