package py.com.prestosoftware.client;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.PagarProveedorDTO;

@Component
public class PagarProveedorApiClient {

    //private final String apiUrl;
    private final RestApiClient api;
    private static final String apiUrl = "/api/pagar-proveedores";

    public PagarProveedorApiClient(RestApiClient api) {
        this.api = api;
    }

    //public PagarProveedorApiClient(RestTemplate restTemplate,
   //         @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
     //   this.restTemplate = restTemplate;
     //   this.apiUrl = backendUrl + "/api/pagar-proveedores";
    //}

    public List<PagarProveedorDTO> findAll() {
        List<PagarProveedorDTO> entities = api.findAll(apiUrl, PagarProveedorDTO[].class);
        return entities == null ? Collections.emptyList() : entities;
    }

    public PagarProveedorDTO findById(Integer id) {
        return api.findById(apiUrl , id, PagarProveedorDTO.class);
    }

    
    public long getRowCount() {
        Long max = api.findLastResourceValue(apiUrl + "/max");
        return max == null ? 0L : max;
    }

    public PagarProveedorDTO save(PagarProveedorDTO entity) {
        //return restTemplate.postForObject(apiUrl+ "/completo", entity, PagarProveedorDTO.class);
        return api.post(apiUrl + "/completo", entity, PagarProveedorDTO.class);
    }

    public PagarProveedorDTO update(Integer id, PagarProveedorDTO entity) {
        return api.update(apiUrl, id, entity, PagarProveedorDTO.class);
    }

    public void remove(PagarProveedorDTO request) {
         api.post(apiUrl + "/completo/remove", request, Void.class);
    }

    

    public List<Object[]> findProveedorPagadoView(Long proveedorId, Integer situacion) {
        List<Object[]> rows = api.findObjectRows(apiUrl + "/view/proveedor-pagado?p=" + proveedorId + "&p=" + situacion, Object[].class);
        return rows == null ? Collections.emptyList() : rows;
    }

    public List<Object[]> findDetallePagarProveedorView(Long proveedorId) {
        List<Object[]> rows = api.findObjectRows(apiUrl + "/view/detalle?p=" + proveedorId, Object[].class);
        return rows == null ? Collections.emptyList() : rows;
    }

    public java.util.List<PagarProveedorDTO> findByFecha(java.util.Date fecha) {
        List<PagarProveedorDTO> rows = api.findAll(apiUrl + "/fecha/" + fecha.getTime(), PagarProveedorDTO[].class);
        return rows == null ? Collections.emptyList() : rows;
    }

    public PagarProveedorDTO findByPppPago(Integer numero) {
        return api.findById(apiUrl, "/numero/" + numero, PagarProveedorDTO.class);
    }

    public java.util.List<PagarProveedorDTO> findByFecha() {
        List<PagarProveedorDTO> rows = api.findAll(apiUrl + "/fecha-hoy", PagarProveedorDTO[].class);
        return rows == null ? Collections.emptyList() : rows;
    }
    //public void remove(PagarProveedorDTO entity) { if (entity != null && entity.getPprNumero() != null) remove(entity.getPprNumero()); }
}
