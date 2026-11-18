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

import py.com.prestosoftware.data.models.ItemCobroCliente;
import py.com.prestosoftware.data.repository.ItemCobroClienteRepository;
import py.com.prestosoftware.domain.services.ItemCobroClienteService;

@RestController
@RequestMapping("/api/item-cobro-clientes")
public class ItemCobroClienteController {

    private final ItemCobroClienteService service;
    private final ItemCobroClienteRepository repository;

    public ItemCobroClienteController(ItemCobroClienteService service, ItemCobroClienteRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<ItemCobroCliente> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ItemCobroCliente findById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemCobroCliente no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<ItemCobroCliente> save(@RequestBody ItemCobroCliente entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public ItemCobroCliente update(@PathVariable Integer id, @RequestBody ItemCobroCliente entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemCobroCliente no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Integer id) {
        ItemCobroCliente entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemCobroCliente no encontrado: " + id));
        repository.delete(entity);
    }
}