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

import py.com.prestosoftware.data.models.CuentaARecibir;
import py.com.prestosoftware.data.repository.CuentaARecibirRepository;
import py.com.prestosoftware.domain.services.CuentaARecibirService;

@RestController
@RequestMapping("/api/cuenta-arecibir")
public class CuentaARecibirController {

    private final CuentaARecibirService service;
    private final CuentaARecibirRepository repository;

    public CuentaARecibirController(CuentaARecibirService service, CuentaARecibirRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<CuentaARecibir> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public CuentaARecibir findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CuentaARecibir no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<CuentaARecibir> save(@RequestBody CuentaARecibir entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public CuentaARecibir update(@PathVariable Long id, @RequestBody CuentaARecibir entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CuentaARecibir no encontrado: " + id));

        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        CuentaARecibir entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CuentaARecibir no encontrado: " + id));
        repository.delete(entity);
    }
}