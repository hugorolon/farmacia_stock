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

import py.com.prestosoftware.data.models.UnidadMedida;
import py.com.prestosoftware.data.repository.UnidadMedidaRepository;
import py.com.prestosoftware.domain.services.UnidadMedidaService;

@RestController
@RequestMapping("/api/unidad-medidas")
public class UnidadMedidaController {

    private final UnidadMedidaService service;
    private final UnidadMedidaRepository repository;

    public UnidadMedidaController(UnidadMedidaService service, UnidadMedidaRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<UnidadMedida> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public UnidadMedida findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UnidadMedida no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<UnidadMedida> save(@RequestBody UnidadMedida entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public UnidadMedida update(@PathVariable Long id, @RequestBody UnidadMedida entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UnidadMedida no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        UnidadMedida entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UnidadMedida no encontrado: " + id));
        repository.delete(entity);
    }
}