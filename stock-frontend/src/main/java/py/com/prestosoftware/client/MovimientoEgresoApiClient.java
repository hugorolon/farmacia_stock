package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.MovimientoEgresoDTO;

@Component
public class MovimientoEgresoApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public MovimientoEgresoApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/movimiento-egresos";
    }

    public List<MovimientoEgresoDTO> findAll() {
        MovimientoEgresoDTO[] entities = restTemplate.getForObject(apiUrl, MovimientoEgresoDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public MovimientoEgresoDTO findById(Integer id) {
        return restTemplate.getForObject(apiUrl + "/" + id, MovimientoEgresoDTO.class);
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public MovimientoEgresoDTO save(MovimientoEgresoDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, MovimientoEgresoDTO.class);
    }

    public MovimientoEgresoDTO update(Integer id, MovimientoEgresoDTO entity) {

        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), MovimientoEgresoDTO.class).getBody();
    }

    public void remove(Integer id) {
        restTemplate.delete(apiUrl + "/" + id);
    }
    public void remove(MovimientoEgresoDTO entity) { if (entity != null && entity.getMegNumero() != null) remove(entity.getMegNumero()); }

    public MovimientoEgresoDTO findByMegProceso(Integer proceso) {
        return findAll().stream().filter(x -> proceso.equals(x.getMegProceso())).findFirst().orElse(null);
    }
    public long getRowCount() { 
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);    
        return count == null ? 0 : count;
    }
    public java.util.List<MovimientoEgresoDTO> findByDateObjects(java.util.Date fecha) {
        MovimientoEgresoDTO[] rows = restTemplate.getForObject(apiUrl + "/fecha/" + fecha.getTime(), MovimientoEgresoDTO[].class);
        return rows == null ? java.util.Collections.emptyList() : java.util.Arrays.asList(rows);
    }
    public java.util.List<Object[]> findByDateObjectsRows(java.util.Date fecha) {
        Object[][] rows = restTemplate.getForObject(apiUrl + "/fecha/" + fecha.getTime(), Object[][].class);
        return rows == null ? java.util.Collections.emptyList() : java.util.Arrays.asList(rows);
    }
}
