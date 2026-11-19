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

    /** Lotes vigentes con stock en orden FEFO (el primero es el proximo a venderse). */
    public List<LoteDTO> findDisponibles(Long productoId) {
        return api.findAll(RESOURCE + "/producto/" + productoId + "/disponibles", LoteDTO[].class);
    }

    /** Stock vendible del producto (suma de lotes no vencidos). */
    public Double getStockDisponible(Long productoId) {
        Double stock = api.findById(RESOURCE + "/producto", productoId + "/stock-disponible", Double.class);
        return stock == null ? 0d : stock;
    }

    /** Stock del producto en lotes ya vencidos (no vendible). */
    public Double getStockVencido(Long productoId) {
        Double stock = api.findById(RESOURCE + "/producto", productoId + "/stock-vencido", Double.class);
        return stock == null ? 0d : stock;
    }

    /**
     * Revierte el stock de lotes de una compra o venta anulada.
     * @param tipo "COMPRA" o "VENTA"
     */
    public Integer revertir(String tipo, Long referenciaId, Long usuarioId) {
        return api.post(RESOURCE + "/revertir?tipo=" + tipo + "&referencia=" + referenciaId
                + (usuarioId != null ? "&usuario=" + usuarioId : ""), null, Integer.class);
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
