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

import py.com.prestosoftware.data.models.Empaque;
import py.com.prestosoftware.data.repository.EmpaqueRepository;
import py.com.prestosoftware.domain.services.EmpaqueService;

@RestController
@RequestMapping("/api/empaques")
public class EmpaqueController {

    private final EmpaqueService service;
    private final EmpaqueRepository repository;

    public EmpaqueController(EmpaqueService service, EmpaqueRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<Empaque> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Empaque findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empaque no encontrado: " + id));
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
    public ResponseEntity<Empaque> save(@RequestBody Empaque entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public Empaque update(@PathVariable Long id, @RequestBody Empaque entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empaque no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        Empaque entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empaque no encontrado: " + id));
        repository.delete(entity);
    }
}