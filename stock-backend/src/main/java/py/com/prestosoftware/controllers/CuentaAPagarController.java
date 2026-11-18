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

import py.com.prestosoftware.data.models.CuentaAPagar;
import py.com.prestosoftware.data.repository.CuentaAPagarRepository;
import py.com.prestosoftware.domain.services.CuentaAPagarService;

@RestController
@RequestMapping("/api/cuenta-apagar")
public class CuentaAPagarController {

    private final CuentaAPagarService service;
    private final CuentaAPagarRepository repository;

    public CuentaAPagarController(CuentaAPagarService service, CuentaAPagarRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<CuentaAPagar> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public CuentaAPagar findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CuentaAPagar no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<CuentaAPagar> save(@RequestBody CuentaAPagar entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public CuentaAPagar update(@PathVariable Long id, @RequestBody CuentaAPagar entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CuentaAPagar no encontrado: " + id));

        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        CuentaAPagar entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CuentaAPagar no encontrado: " + id));
        repository.delete(entity);
    }
}