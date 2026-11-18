package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.CuentaAPagarDTO;
import py.com.prestosoftware.data.dtos.ProveedorDTO;

@Component
public class CuentaAPagarApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public CuentaAPagarApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/cuenta-apagar";
    }

    public List<CuentaAPagarDTO> findAll() {
        CuentaAPagarDTO[] entities = restTemplate.getForObject(apiUrl, CuentaAPagarDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<CuentaAPagarDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, CuentaAPagarDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public CuentaAPagarDTO save(CuentaAPagarDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, CuentaAPagarDTO.class);
    }

    public CuentaAPagarDTO update(Long id, CuentaAPagarDTO entity) {

        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), CuentaAPagarDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public java.util.List<CuentaAPagarDTO> findPendientes(ProveedorDTO proveedor, String situacion) {
        return findAll().stream()
                .filter(x -> proveedor != null && proveedor.getId().equals(x.getIdEntidad()))
                .collect(java.util.stream.Collectors.toList());
    }

    public CuentaAPagarDTO findByCapProcesoAndIdEntidad(Integer proceso, Long entidadId) {
        return findAll().stream().filter(x -> proceso.equals(x.getCapProceso()) && entidadId.equals(x.getIdEntidad())).findFirst().orElse(null);
    }
    public void remove(CuentaAPagarDTO entity) { if (entity != null && entity.getCapNumero() != null) remove(entity.getCapNumero().longValue()); }
}
