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

import py.com.prestosoftware.data.models.ItemCuentaAPagar;
import py.com.prestosoftware.data.repository.ItemCuentaAPagarRepository;
import py.com.prestosoftware.domain.services.ItemCuentaAPagarService;

@RestController
@RequestMapping("/api/item-cuenta-apagar")
public class ItemCuentaAPagarController {

    private final ItemCuentaAPagarService service;
    private final ItemCuentaAPagarRepository repository;

    public ItemCuentaAPagarController(ItemCuentaAPagarService service, ItemCuentaAPagarRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<ItemCuentaAPagar> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ItemCuentaAPagar findById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemCuentaAPagar no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<ItemCuentaAPagar> save(@RequestBody ItemCuentaAPagar entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public ItemCuentaAPagar update(@PathVariable Integer id, @RequestBody ItemCuentaAPagar entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemCuentaAPagar no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @PutMapping("/{secuencia}/situacion/{situacion}")
    public ResponseEntity<Void> cambiaEstadoSituacion(@PathVariable Long secuencia,
            @PathVariable int situacion) {
        service.cambiaEstadoSituacion(situacion, secuencia);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Integer id) {
        ItemCuentaAPagar entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemCuentaAPagar no encontrado: " + id));
        repository.delete(entity);
    }
}