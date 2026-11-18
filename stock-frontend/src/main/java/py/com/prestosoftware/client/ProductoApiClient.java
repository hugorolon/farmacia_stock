package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.GrupoDTO;
import py.com.prestosoftware.data.dtos.ProductoDTO;
import py.com.prestosoftware.data.dtos.SubgrupoDTO;

@Component
public class ProductoApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public ProductoApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/productos";
    }

    public List<ProductoDTO> findAll() {
        ProductoDTO[] entities = restTemplate.getForObject(apiUrl, ProductoDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<ProductoDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, ProductoDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public List<ProductoDTO> getProductoByGrupo(GrupoDTO grupo) {
        if (grupo == null) {
            return Collections.emptyList();
        }
        return findAll().stream()
                .filter(producto -> producto.getGrupo() != null
                        && producto.getGrupo().getId().equals(grupo.getId()))
                .collect(Collectors.toList());
    }

    public java.util.List<ProductoDTO> findByNombre(String nombre) {
        String normalized = nombre == null ? "" : nombre;
        return findAll().stream().filter(entity -> FrontendApiClientSupport.nombreContains(entity, normalized)).collect(Collectors.toList());
    }

    public long getRowCount() { return count(); }
    public long getRowMax() { return count(); }
    public long getRoxMax() { return count(); }
    public java.util.List<ProductoDTO> findAllByNombre() { return findAll(); }
    public java.util.List<ProductoDTO> getProductoBySubrupo(SubgrupoDTO subgrupo) { return findAll(); }
    public void updateStock(ProductoDTO producto) {
        if (producto != null) {
            save(producto);
        }
    }
    public void updateStock(List<ProductoDTO> productos) {
        if (productos != null) {
            productos.forEach(this::save);
        }
    }

    public ProductoDTO save(ProductoDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, ProductoDTO.class);
    }

    public ProductoDTO update(Long id, ProductoDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), ProductoDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public void activarLote(Long id) {
        restTemplate.put(apiUrl + "/" + id + "/lote/activar", null);
    }

    public void inactivarLote(Long id) {
        restTemplate.put(apiUrl + "/" + id + "/lote/inactivar", null);
    }

    public java.util.Optional<ProductoDTO> findByFilter(String filter) { return findAll().stream().filter(x -> filter.equals(x.getDescripcion())).findFirst(); }
    public java.util.Optional<ProductoDTO> findByReferencia(String referencia) { return findAll().stream().filter(x -> referencia.equals(x.getReferencia())).findFirst(); }
    public ProductoDTO findProductByFilter(String filter) { return findByFilter(filter).orElse(null); }
    public ProductoDTO getStockDepositoByProductoId(Long productoId) { return findById(productoId).orElse(null); }
}
