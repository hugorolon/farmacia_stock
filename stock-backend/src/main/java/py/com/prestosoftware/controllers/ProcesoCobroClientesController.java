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

import py.com.prestosoftware.data.models.ProcesoCobroClientes;
import py.com.prestosoftware.data.repository.ProcesoCobroClientesRepository;
import py.com.prestosoftware.domain.services.ProcesoCobroClientesService;

@RestController
@RequestMapping("/api/proceso-cobro-clientes")
public class ProcesoCobroClientesController {

    private final ProcesoCobroClientesService service;
    private final ProcesoCobroClientesRepository repository;

    public ProcesoCobroClientesController(ProcesoCobroClientesService service, ProcesoCobroClientesRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<ProcesoCobroClientes> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ProcesoCobroClientes findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProcesoCobroClientes no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<ProcesoCobroClientes> save(@RequestBody ProcesoCobroClientes entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public ProcesoCobroClientes update(@PathVariable Long id, @RequestBody ProcesoCobroClientes entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProcesoCobroClientes no encontrado: " + id));

        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        ProcesoCobroClientes entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProcesoCobroClientes no encontrado: " + id));
        repository.delete(entity);
    }
}