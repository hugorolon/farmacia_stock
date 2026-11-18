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

import py.com.prestosoftware.data.models.ListaPrecio;
import py.com.prestosoftware.data.repository.ListaPrecioRepository;
import py.com.prestosoftware.domain.services.ListaPrecioService;

@RestController
@RequestMapping("/api/lista-precios")
public class ListaPrecioController {

    private final ListaPrecioService service;
    private final ListaPrecioRepository repository;

    public ListaPrecioController(ListaPrecioService service, ListaPrecioRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<ListaPrecio> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ListaPrecio findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ListaPrecio no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<ListaPrecio> save(@RequestBody ListaPrecio entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public ListaPrecio update(@PathVariable Long id, @RequestBody ListaPrecio entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ListaPrecio no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        ListaPrecio entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ListaPrecio no encontrado: " + id));
        repository.delete(entity);
    }
}