package py.com.prestosoftware.client;

import java.util.List;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.LoteDTO;

@Component
public class LoteApiClient {

    private static final String RESOURCE = "/api/lotes";

    private final RestApiClient api;

    public LoteApiClient(RestApiClient api) {
        this.api = api;
    }

    public List<LoteDTO> findAll() {
        return api.findAll(RESOURCE, LoteDTO[].class);
    }

    public java.util.Optional<LoteDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(api.findById(RESOURCE, id, LoteDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public List<LoteDTO> findLotesByProductoId(Long productoId) {
        return api.findAll(RESOURCE + "/producto/" + productoId, LoteDTO[].class);
    }

    public long count() {
        return api.count(RESOURCE);
    }

    public LoteDTO save(LoteDTO entity) {
        return api.save(RESOURCE, entity, LoteDTO.class);
    }

    public void remove(Long id) {
        api.remove(RESOURCE, id);
    }

    public void remove(LoteDTO entity) {
        if (entity != null && entity.getId() != null) {
            remove(entity.getId());
        }
    }

    public long getRowCount() {
        Long max = api.findLastResourceValue(RESOURCE + "/max");
        return max == null ? 0L : max;
    }
}
