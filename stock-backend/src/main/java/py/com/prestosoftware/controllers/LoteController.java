package py.com.prestosoftware.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import py.com.prestosoftware.data.models.Lotes;
import py.com.prestosoftware.domain.services.LoteService;

@RestController
@RequestMapping("/api/lotes")
public class LoteController {

    private final LoteService service;

    public LoteController(LoteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Lotes> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Lotes findById(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lote no encontrado: " + id));
    }

    @GetMapping("/producto/{productoId}")
    public List<Lotes> findByProductoId(@PathVariable Long productoId) {
        return service.findLotesByProductoId(productoId);
    }

    @GetMapping("/max")
    public long getMaxId() {
        return service.getRowCount();
    }

    @PostMapping
    public ResponseEntity<Lotes> save(@RequestBody Lotes entity) {
        service.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        Lotes entity = service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lote no encontrado: " + id));
        service.remove(entity);
    }
}
