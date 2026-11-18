package py.com.prestosoftware.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import py.com.prestosoftware.data.models.Devolucion;
import py.com.prestosoftware.data.repository.DevolucionRepository;
import py.com.prestosoftware.domain.services.DevolucionService;

@RestController
@RequestMapping("/api/devoluciones")
public class DevolucionController {

    private final DevolucionService service;
    private final DevolucionRepository repository;

    public DevolucionController(DevolucionService service, DevolucionRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<Devolucion> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Devolucion findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Devolucion no encontrado: " + id));
    }

    @GetMapping("/{id}/detalles")
    public List<Object[]> getDetalles(@PathVariable Long id) {
        return service.getDetallesDevolucion(id);
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<Devolucion> save(@RequestBody Devolucion entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public Devolucion update(@PathVariable Long id, @RequestBody Devolucion entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Devolucion no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        Devolucion entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Devolucion no encontrado: " + id));
        repository.delete(entity);
    }
}