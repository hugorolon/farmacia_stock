package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.MovimientoIngresoDTO;

@Component
public class MovimientoIngresoApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public MovimientoIngresoApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/movimiento-ingresos";
    }

    public List<MovimientoIngresoDTO> findAll() {
        MovimientoIngresoDTO[] entities = restTemplate.getForObject(apiUrl, MovimientoIngresoDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public MovimientoIngresoDTO findById(Integer id) {
        return restTemplate.getForObject(apiUrl + "/" + id, MovimientoIngresoDTO.class);
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public MovimientoIngresoDTO save(MovimientoIngresoDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, MovimientoIngresoDTO.class);
    }

    public MovimientoIngresoDTO update(Integer id, MovimientoIngresoDTO entity) {

        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), MovimientoIngresoDTO.class).getBody();
    }

    public void remove(Integer id) {
        restTemplate.delete(apiUrl + "/" + id);
    }
    public void remove(MovimientoIngresoDTO entity) { if (entity != null && entity.getMinNumero() != null) remove(entity.getMinNumero()); }

    public MovimientoIngresoDTO findByMinProceso(Integer proceso) {
        return findAll().stream().filter(x -> proceso.equals(x.getMinProceso())).findFirst().orElse(null);
    }
    public long getRowCount() { 
         Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);    
        return count == null ? 0 : count;
    }
    public java.util.List<MovimientoIngresoDTO> findByDateObjects(java.util.Date fecha) {
        MovimientoIngresoDTO[] rows = restTemplate.getForObject(apiUrl + "/fecha/" + fecha.getTime(), MovimientoIngresoDTO[].class);
        return rows == null ? java.util.Collections.emptyList() : java.util.Arrays.asList(rows);
    }
    public java.util.List<Object[]> findByDateObjectsRows(java.util.Date fecha) {
        Object[][] rows = restTemplate.getForObject(apiUrl + "/fecha/" + fecha.getTime(), Object[][].class);
        return rows == null ? java.util.Collections.emptyList() : java.util.Arrays.asList(rows);
    }
}
