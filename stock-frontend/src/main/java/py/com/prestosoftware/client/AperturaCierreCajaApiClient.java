package py.com.prestosoftware.client;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import py.com.prestosoftware.data.dtos.AperturaCierreCajaDTO;
import py.com.prestosoftware.data.dtos.CajaDTO;

@Component
public class AperturaCierreCajaApiClient {

    private final RestTemplate restTemplate;
    private final String apiUrl;

    public AperturaCierreCajaApiClient(RestTemplate restTemplate,
            @Value("${app.backend.url:http://localhost:8081}") String backendUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = backendUrl + "/api/apertura-cierre-cajas";
    }

    public List<AperturaCierreCajaDTO> findAll() {
        AperturaCierreCajaDTO[] entities = restTemplate.getForObject(apiUrl, AperturaCierreCajaDTO[].class);
        return entities == null ? Collections.emptyList() : Arrays.asList(entities);
    }

    public java.util.Optional<AperturaCierreCajaDTO> findById(Long id) {
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(apiUrl + "/" + id, AperturaCierreCajaDTO.class));
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return java.util.Optional.empty(); // no existe el registro
        }
    }

    public long count() {
        Long count = restTemplate.getForObject(apiUrl + "/count", Long.class);
        return count == null ? 0 : count;
    }

    public AperturaCierreCajaDTO save(AperturaCierreCajaDTO entity) {
        return restTemplate.postForObject(apiUrl, entity, AperturaCierreCajaDTO.class);
    }

    public AperturaCierreCajaDTO update(Long id, AperturaCierreCajaDTO entity) {
        entity.setId(id);
        return restTemplate.exchange(apiUrl + "/" + id,
                org.springframework.http.HttpMethod.PUT, new org.springframework.http.HttpEntity<>(entity), AperturaCierreCajaDTO.class).getBody();
    }

    public void remove(Long id) {
        restTemplate.delete(apiUrl + "/" + id);
    }

    public java.util.Optional<AperturaCierreCajaDTO> findByCajaAndFechaApertura(CajaDTO caja, java.util.Date fecha) {
        if (caja == null || caja.getId() == null || fecha == null) {
            return java.util.Optional.empty();
        }

        String fechaTexto = new SimpleDateFormat("yyyy-MM-dd").format(fecha);
        try {
            return java.util.Optional.ofNullable(restTemplate.getForObject(
                    apiUrl + "/buscar?cajaId={cajaId}&fecha={fecha}",
                    AperturaCierreCajaDTO.class, caja.getId(), fechaTexto));
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == 404) {
                return java.util.Optional.empty();
            }
            throw e;
        }
    }

    public java.util.Optional<AperturaCierreCajaDTO> findOptionalByCajaAndFechaAperturaUsuario(
            CajaDTO caja, java.util.Date fecha, Long usuario) {
        if (usuario == null) {
            return findByCajaAndFechaApertura(caja, fecha);
        }

        if (caja == null || caja.getId() == null || fecha == null) {
            return java.util.Optional.empty();
        }

        return findAll().stream()
                .filter(apertura -> apertura.getCaja() != null
                        && apertura.getCaja().getId() != null
                        && apertura.getCaja().getId().equals(caja.getId())
                        && usuario.equals(apertura.getUsuario())
                        && sameDay(apertura.getFechaApertura(), fecha))
                .findFirst();
    }

    private boolean sameDay(java.util.Date first, java.util.Date second) {
        if (first == null || second == null) {
            return false;
        }

        Calendar firstCalendar = Calendar.getInstance();
        firstCalendar.setTime(first);
        Calendar secondCalendar = Calendar.getInstance();
        secondCalendar.setTime(second);

        return firstCalendar.get(Calendar.YEAR) == secondCalendar.get(Calendar.YEAR)
                && firstCalendar.get(Calendar.DAY_OF_YEAR) == secondCalendar.get(Calendar.DAY_OF_YEAR);
    }
    public java.util.Optional<AperturaCierreCajaDTO> getInfoAperturaCierreCaja() { return findAll().isEmpty() ? java.util.Optional.empty() : java.util.Optional.of(findAll().get(0)); }
}
