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

import py.com.prestosoftware.data.models.Departamento;
import py.com.prestosoftware.data.repository.DepartamentoRepository;
import py.com.prestosoftware.domain.services.DepartamentoService;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoService service;
    private final DepartamentoRepository repository;

    public DepartamentoController(DepartamentoService service, DepartamentoRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<Departamento> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Departamento findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento no encontrado: " + id));
    }

    /** Próximo id sugerido (máximo + 1), solo para mostrar en el formulario. */
    @GetMapping("/next-id")
    public long nextId() {
        Long max = repository.getMaxId();
        return (max == null ? 0 : max) + 1;
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<Departamento> save(@RequestBody Departamento entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public Departamento update(@PathVariable Long id, @RequestBody Departamento entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        Departamento entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento no encontrado: " + id));
        repository.delete(entity);
    }
}