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

import py.com.prestosoftware.data.models.Tamanho;
import py.com.prestosoftware.data.repository.TamanhoRepository;
import py.com.prestosoftware.domain.services.TamanhoService;

@RestController
@RequestMapping("/api/tamanhos")
public class TamanhoController {

    private final TamanhoService service;
    private final TamanhoRepository repository;

    public TamanhoController(TamanhoService service, TamanhoRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<Tamanho> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Tamanho findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tamanho no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<Tamanho> save(@RequestBody Tamanho entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public Tamanho update(@PathVariable Long id, @RequestBody Tamanho entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tamanho no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        Tamanho entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tamanho no encontrado: " + id));
        repository.delete(entity);
    }
}