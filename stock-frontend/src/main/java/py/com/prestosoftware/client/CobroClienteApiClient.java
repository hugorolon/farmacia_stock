package py.com.prestosoftware.client;

import java.util.List;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.CobroClienteDTO;

@Component
public class CobroClienteApiClient {

    private static final String RESOURCE = "/api/cobro-clientes";

    private final RestApiClient api;

    public CobroClienteApiClient(RestApiClient api) {
        this.api = api;
    }

    public List<CobroClienteDTO> findAll() {
        return api.findAll(RESOURCE, CobroClienteDTO[].class);
    }

    public CobroClienteDTO findById(Integer id) {
        return api.findById(RESOURCE, id, CobroClienteDTO.class);
    }

    public long count() {
        return api.count(RESOURCE);
    }

    public CobroClienteDTO save(CobroClienteDTO entity) {
        return api.save(RESOURCE, entity, CobroClienteDTO.class);
    }

    public CobroClienteDTO update(Integer id, CobroClienteDTO entity) {
        return api.update(RESOURCE, id, entity, CobroClienteDTO.class);
    }

    public void remove(Integer id) {
        api.remove(RESOURCE, id);
    }

    public List<py.com.prestosoftware.data.dtos.ItemCobroClienteDTO> findByIclNumero(Integer numero) {
        return api.findAll(RESOURCE + "/items/" + numero, py.com.prestosoftware.data.dtos.ItemCobroClienteDTO[].class);
    }

    public CobroClienteDTO save(CobroClienteDTO entity, List<py.com.prestosoftware.data.dtos.ItemCobroClienteDTO> items, Double monto) {
        java.util.Map<String, Object> request = new java.util.LinkedHashMap<String, Object>();
        request.put("cobro", entity);
        request.put("items", items);
        request.put("monto", monto);
        return api.post(RESOURCE + "/completo", request, CobroClienteDTO.class);
    }

    public CobroClienteDTO saveWithDetails(CobroClienteDTO entity,
            List<py.com.prestosoftware.ui.viewmodel.DetalleCobroClienteView> detalles, Double monto) {
        java.util.List<py.com.prestosoftware.data.dtos.ItemCobroClienteDTO> items = detalles.stream()
                .map(detalle -> {
                    py.com.prestosoftware.data.dtos.ItemCobroClienteDTO item =
                            new py.com.prestosoftware.data.dtos.ItemCobroClienteDTO();
                    item.setIclSecuenciaCuenta(detalle.getIca_Secuencia());
                    item.setIclMonto(detalle.getCobro());
                    return item;
                }).collect(java.util.stream.Collectors.toList());
        java.util.Map<String, Object> request = new java.util.LinkedHashMap<String, Object>();
        request.put("cobro", entity);
        request.put("items", items);
        request.put("detalles", detalles);
        request.put("monto", monto);
        return api.post(RESOURCE + "/completo", request, CobroClienteDTO.class);
    }

    public void remove(CobroClienteDTO entity,
            List<py.com.prestosoftware.data.dtos.ItemCobroClienteDTO> items,
            List<py.com.prestosoftware.ui.viewmodel.DetalleCobroClienteView> detalles) {
        java.util.Map<String, Object> request = new java.util.LinkedHashMap<String, Object>();
        request.put("cobro", entity);
        request.put("items", items);
        request.put("detalles", detalles);
        api.post(RESOURCE + "/completo/remove", request, Void.class);
    }

    public long getRowCount() {
        Long max = api.findLastResourceValue(RESOURCE + "/max");
        return max == null ? 0L : max;
    }

    public List<Object[]> findClienteCobradoView(Long clienteId, Integer numero) {
        return api.findObjectRows(RESOURCE + "/view/cliente-cobrado", clienteId, numero);
    }

    public List<Object[]> findDetalleCobroClienteView(Long clienteId) {
        return api.findObjectRows(RESOURCE + "/view/detalle", clienteId);
    }

    public List<CobroClienteDTO> findByDateObjects(java.util.Date fecha) {
        return api.findAll(RESOURCE + "/fecha/" + fecha.getTime(), CobroClienteDTO[].class);
    }

    public List<CobroClienteDTO> findByFecha(java.util.Date fecha) {
        return findByDateObjects(fecha);
    }
}
