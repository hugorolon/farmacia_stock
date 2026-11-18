package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.CompraDTO;
import org.springframework.web.util.UriComponentsBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CompraApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public CompraApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/compras";
    }

    public List<CompraDTO> findAll() {
        CompraDTO[] entities = restTemplate.getForObject(apiUrl, CompraDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<CompraDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, CompraDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    /**
     * Guarda la compra tal cual. Si ya tiene id se actualiza (PUT); si es nueva se crea con lanzCaja=1.
     * El POST del backend espera {lanzCaja, compra, condicionPago}, no la compra sola.
     * Para registrar una compra con sus movimientos usar save(lanzCaja, compra, condicionPago).
     */
    public CompraDTO save(CompraDTO entity) {
        return entity.getId() != null
                ? update(entity.getId(), entity)
                : save(1, entity, entity.getCondicion() == 0 ? "Contado" : "Credito");
    }

    public CompraDTO update(Long id, CompraDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), CompraDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public CompraDTO save(int lanzCaja, CompraDTO entity, String condicionPago) {
        java.util.Map<String, Object> request = new java.util.LinkedHashMap<String, Object>();
        request.put("lanzCaja", lanzCaja);
        request.put("compra", entity);
        request.put("condicionPago", condicionPago);
        return restTemplate.postForObject(apiUrl, request, CompraDTO.class);
    }

    public java.util.Optional<CompraDTO> findNota(Long id, String situacion) { return findById(id); }
    public java.util.List<CompraDTO> getNotasPendientes(String situacion) { return findAll(); }
    public java.util.List<CompraDTO> getNotasPendientesBySituacion(String situacion) { return findAll(); }
    public java.util.List<CompraDTO> getNotasPendientesByFechaAndSituacion(java.util.Date fecha, String situacion) { return findAll(); }
    public java.util.List<CompraDTO> getComprasFiltro(Date desde, Date hasta, String filtro, int estado) {
        if (desde == null || hasta == null) {
            return Collections.emptyList();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String uri = UriComponentsBuilder.fromHttpUrl(apiUrl + "/filtro")
                .queryParam("desde", dateFormat.format(desde))
                .queryParam("hasta", dateFormat.format(hasta))
                .queryParam("situacion", filtro == null ? "" : filtro)
                .queryParam("forma", estado)
                .build()
                .encode()
                .toUriString();

        CompraDTO[] entities = restTemplate.getForObject(uri, CompraDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }
    public CompraDTO findByCompraIdAndProveedorId(Long compraId, Long proveedorId) { return findById(compraId).orElse(null); }

    public java.util.List<CompraDTO> findByProveedorId(Long proveedorId) { return findAll(); }
}
