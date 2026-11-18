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

import py.com.prestosoftware.data.models.Impuesto;
import py.com.prestosoftware.data.repository.ImpuestoRepository;
import py.com.prestosoftware.domain.services.ImpuestoService;

@RestController
@RequestMapping("/api/impuestos")
public class ImpuestoController {

    private final ImpuestoService service;
    private final ImpuestoRepository repository;

    public ImpuestoController(ImpuestoService service, ImpuestoRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<Impuesto> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Impuesto findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Impuesto no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<Impuesto> save(@RequestBody Impuesto entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public Impuesto update(@PathVariable Long id, @RequestBody Impuesto entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Impuesto no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        Impuesto entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Impuesto no encontrado: " + id));
        repository.delete(entity);
    }
}