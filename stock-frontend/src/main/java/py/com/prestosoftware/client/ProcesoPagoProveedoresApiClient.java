package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.ProcesoPagoProveedoresDTO;

@Component
public class ProcesoPagoProveedoresApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public ProcesoPagoProveedoresApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/proceso-pago-proveedores";
    }

    public List<ProcesoPagoProveedoresDTO> findAll() {
        ProcesoPagoProveedoresDTO[] entities = restTemplate.getForObject(apiUrl, ProcesoPagoProveedoresDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<ProcesoPagoProveedoresDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, ProcesoPagoProveedoresDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public ProcesoPagoProveedoresDTO save(ProcesoPagoProveedoresDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, ProcesoPagoProveedoresDTO.class);
    }

    public ProcesoPagoProveedoresDTO update(Long id, ProcesoPagoProveedoresDTO entity) {

        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), ProcesoPagoProveedoresDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public ProcesoPagoProveedoresDTO findByPppPago(Integer pago) {
        return findAll().stream().filter(x -> pago.equals(x.getPppPago())).findFirst().orElse(null);
    }
    public void remove(ProcesoPagoProveedoresDTO entity) { if (entity != null && entity.getPppProceso() != null) remove(entity.getPppProceso().longValue()); }
}
