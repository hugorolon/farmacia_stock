package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import py.com.prestosoftware.data.dtos.CajaDTO;

@Component
public class CajaApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public CajaApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/cajas";
    }

    public List<CajaDTO> findAll() {
        CajaDTO[] cajas = restTemplate.getForObject(apiUrl, CajaDTO[].class);
        return cajas == null ? Collections.emptyList() : Arrays.asList(cajas);
    }

    public List<CajaDTO> findByNombre(String name) {
        CajaDTO[] cajas = restTemplate.getForObject(apiUrl + "?nombre={nombre}", CajaDTO[].class, name);
        return cajas == null ? Collections.emptyList() : Arrays.asList(cajas);
    }

    public long getRowCount() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public java.util.Optional<CajaDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, CajaDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public void save(CajaDTO caja) {
        restTemplate.postForEntity(apiUrl, caja, Void.class);
    }
}
