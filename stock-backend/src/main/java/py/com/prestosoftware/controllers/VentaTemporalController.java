package py.com.prestosoftware.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;

import py.com.prestosoftware.data.models.Cliente;
import py.com.prestosoftware.data.models.VentaTemporal;
import py.com.prestosoftware.data.repository.VentaTemporalRepository;
import py.com.prestosoftware.domain.services.VentaTemporalService;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/api/venta-temporales")
public class VentaTemporalController {

    private final VentaTemporalService service;
    private final VentaTemporalRepository repository;
    private final ObjectMapper objectMapper;

    public VentaTemporalController(VentaTemporalService service, VentaTemporalRepository repository,
            ObjectMapper objectMapper) {
        this.service = service;
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<VentaTemporal> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public VentaTemporal findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "VentaTemporal no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @GetMapping("/notas")
    public List<VentaTemporal> notas(@RequestParam("desde") long desde,
            @RequestParam("hasta") long hasta) {
        Date fechaDesde = new Date(desde);
        Date fechaHasta = new Date(hasta);
        List<VentaTemporal> result = new ArrayList<>();
        for (Object[] row : repository.findSummaryRows(fechaDesde, fechaHasta)) {
            VentaTemporal summary = new VentaTemporal();
            summary.setId(((Number) row[0]).longValue());
            summary.setFecha((Date) row[1]);
            summary.setClienteNombre(row[2] == null ? "" : (String) row[2]);
            summary.setTotalGeneral(row[3] == null ? null : ((Number) row[3]).doubleValue());
            summary.setCondicion(((Number) row[4]).intValue());
            summary.setSituacion(row[5] == null ? null : (String) row[5]);
            result.add(summary);
        }
        return result;
    }

    @GetMapping("/filtro")
    public List<VentaTemporal> filtro(@RequestParam("desde") long desde,
            @RequestParam("hasta") long hasta,
            @RequestParam(value = "situacion", defaultValue = "") String situacion,
            @RequestParam(value = "formaPago", defaultValue = "0") int formaPago) {
        return notas(desde, hasta);
    }

    @PostMapping
    public ResponseEntity<VentaTemporal> save(@RequestBody VentaTemporal entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    /** Recibe {venta, cliente, condicionPago}; si cliente tiene id 999999 se da de alta como cliente nuevo. */
    @PostMapping("/completo")
    public ResponseEntity<VentaTemporal> saveCompleto(@RequestBody Map<String, Object> request) {
        VentaTemporal venta = objectMapper.convertValue(request.get("venta"), VentaTemporal.class);
        if (venta == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Se espera {venta, cliente, condicionPago}");
        }
        Cliente clienteNuevo = objectMapper.convertValue(request.get("cliente"), Cliente.class);
        String condicion = objectMapper.convertValue(request.get("condicionPago"), String.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(venta, clienteNuevo, condicion));
    }

    @PutMapping("/{id}")
    public VentaTemporal update(@PathVariable Long id, @RequestBody VentaTemporal entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "VentaTemporal no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        VentaTemporal entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "VentaTemporal no encontrado: " + id));
        repository.delete(entity);
    }
}