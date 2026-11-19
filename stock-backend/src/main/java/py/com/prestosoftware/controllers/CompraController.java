package py.com.prestosoftware.controllers;

import java.util.List;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.format.annotation.DateTimeFormat;

import py.com.prestosoftware.data.models.Compra;
import py.com.prestosoftware.data.repository.CompraRepository;
import py.com.prestosoftware.domain.services.CompraService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    private final CompraService service;
    private final CompraRepository repository;
    private final ObjectMapper objectMapper;

    public CompraController(CompraService service, CompraRepository repository, ObjectMapper objectMapper) {
        this.service = service;
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<Compra> findAll() {
        return service.findAll();
    }

    @GetMapping("/filtro")
    public List<Compra> findByFiltro(
            @RequestParam("desde") @DateTimeFormat(pattern = "yyyy-MM-dd") Date desde,
            @RequestParam("hasta") @DateTimeFormat(pattern = "yyyy-MM-dd") Date hasta,
            @RequestParam(value = "situacion", defaultValue = "") String situacion,
            @RequestParam(value = "forma", defaultValue = "0") int forma) {
        return service.getComprasFiltro(desde, hasta, situacion, forma);
    }

    @GetMapping("/{id}")
    public Compra findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return service.getRowCount();
    }

    @PostMapping
    public ResponseEntity<Compra> save(@RequestBody Map<String, Object>  request) {
        if (request.get("compra") == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Se espera {lanzCaja, compra, condicionPago}; para actualizar use PUT /{id}");
        }
        Integer lanzCaja = objectMapper.convertValue(request.get("lanzCaja"), Integer.class);
        int lanzamientoCaja = lanzCaja == null ? 1 : lanzCaja;
        String condicion = objectMapper.convertValue(request.get("condicionPago"), String.class);
        Compra entity = objectMapper.convertValue(request.get("compra"), Compra.class);
        System.out.println("CompraController.save: LanzamientoCaja: " + lanzamientoCaja + ", Condicion: " + condicion + ", Compra: " + entity);

        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(lanzamientoCaja, entity, condicion));
        } catch (IllegalArgumentException e) {
            // p. ej. producto con control de lote sin nro. de lote / vencimiento
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Compra update(@PathVariable Long id, @RequestBody Compra entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        Compra entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Compra no encontrado: " + id));
        repository.delete(entity);
    }
}