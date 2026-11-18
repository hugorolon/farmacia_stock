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

import py.com.prestosoftware.data.models.AjusteStock;
import py.com.prestosoftware.data.repository.AjusteStockRepository;
import py.com.prestosoftware.domain.services.AjusteStockService;

@RestController
@RequestMapping("/api/ajuste-stocks")
public class AjusteStockController {

    private final AjusteStockService service;
    private final AjusteStockRepository repository;

    public AjusteStockController(AjusteStockService service, AjusteStockRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<AjusteStock> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public AjusteStock findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AjusteStock no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<AjusteStock> save(@RequestBody AjusteStock entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public AjusteStock update(@PathVariable Long id, @RequestBody AjusteStock entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AjusteStock no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        AjusteStock entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AjusteStock no encontrado: " + id));
        repository.delete(entity);
    }
}