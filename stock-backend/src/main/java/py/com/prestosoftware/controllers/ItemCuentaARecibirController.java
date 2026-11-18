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

import py.com.prestosoftware.data.models.ItemCuentaARecibir;
import py.com.prestosoftware.data.repository.ItemCuentaARecibirRepository;
import py.com.prestosoftware.domain.services.ItemCuentaARecibirService;

@RestController
@RequestMapping("/api/item-cuenta-arecibir")
public class ItemCuentaARecibirController {

    private final ItemCuentaARecibirService service;
    private final ItemCuentaARecibirRepository repository;

    public ItemCuentaARecibirController(ItemCuentaARecibirService service, ItemCuentaARecibirRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<ItemCuentaARecibir> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ItemCuentaARecibir findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemCuentaARecibir no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<ItemCuentaARecibir> save(@RequestBody ItemCuentaARecibir entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public ItemCuentaARecibir update(@PathVariable Long id, @RequestBody ItemCuentaARecibir entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemCuentaARecibir no encontrado: " + id));

        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        ItemCuentaARecibir entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemCuentaARecibir no encontrado: " + id));
        repository.delete(entity);
    }
}