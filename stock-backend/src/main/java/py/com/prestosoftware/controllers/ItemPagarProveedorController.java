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

import py.com.prestosoftware.data.models.ItemPagarProveedor;
import py.com.prestosoftware.data.repository.ItemPagarProveedorRepository;
import py.com.prestosoftware.domain.services.ItemPagarProveedorService;

@RestController
@RequestMapping("/api/item-pagar-proveedor")
public class ItemPagarProveedorController {

    private final ItemPagarProveedorService service;
    private final ItemPagarProveedorRepository repository;

    public ItemPagarProveedorController(ItemPagarProveedorService service, ItemPagarProveedorRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<ItemPagarProveedor> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ItemPagarProveedor findById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemPagarProveedor no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<ItemPagarProveedor> save(@RequestBody ItemPagarProveedor entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public ItemPagarProveedor update(@PathVariable Integer id, @RequestBody ItemPagarProveedor entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemPagarProveedor no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Integer id) {
        ItemPagarProveedor entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ItemPagarProveedor no encontrado: " + id));
        repository.delete(entity);
    }
}