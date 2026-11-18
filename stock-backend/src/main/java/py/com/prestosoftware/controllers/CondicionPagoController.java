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

import py.com.prestosoftware.data.models.CondicionPago;
import py.com.prestosoftware.data.repository.CondicionPagoRepository;
import py.com.prestosoftware.domain.services.CondicionPagoService;

@RestController
@RequestMapping("/api/condicion-pagos")
public class CondicionPagoController {

    private final CondicionPagoService service;
    private final CondicionPagoRepository repository;

    public CondicionPagoController(CondicionPagoService service, CondicionPagoRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<CondicionPago> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public CondicionPago findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CondicionPago no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<CondicionPago> save(@RequestBody CondicionPago entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public CondicionPago update(@PathVariable Long id, @RequestBody CondicionPago entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CondicionPago no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        CondicionPago entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CondicionPago no encontrado: " + id));
        repository.delete(entity);
    }
}