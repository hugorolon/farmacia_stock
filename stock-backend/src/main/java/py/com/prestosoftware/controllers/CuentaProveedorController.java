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

import py.com.prestosoftware.data.models.CuentaProveedor;
import py.com.prestosoftware.data.repository.CuentaProveedorRepository;
import py.com.prestosoftware.domain.services.CuentaProveedorService;

@RestController
@RequestMapping("/api/cuenta-proveedores")
public class CuentaProveedorController {

    private final CuentaProveedorService service;
    private final CuentaProveedorRepository repository;

    public CuentaProveedorController(CuentaProveedorService service, CuentaProveedorRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<CuentaProveedor> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public CuentaProveedor findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CuentaProveedor no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<CuentaProveedor> save(@RequestBody CuentaProveedor entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public CuentaProveedor update(@PathVariable Long id, @RequestBody CuentaProveedor entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CuentaProveedor no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        CuentaProveedor entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CuentaProveedor no encontrado: " + id));
        repository.delete(entity);
    }
}