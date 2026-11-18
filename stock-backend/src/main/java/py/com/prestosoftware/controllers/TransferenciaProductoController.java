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

import py.com.prestosoftware.data.models.TransferenciaProducto;
import py.com.prestosoftware.data.repository.TransferenciaProductoRepository;
import py.com.prestosoftware.domain.services.TransferenciaProductoService;

@RestController
@RequestMapping("/api/transferencia-productos")
public class TransferenciaProductoController {

    private final TransferenciaProductoService service;
    private final TransferenciaProductoRepository repository;

    public TransferenciaProductoController(TransferenciaProductoService service, TransferenciaProductoRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<TransferenciaProducto> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public TransferenciaProducto findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TransferenciaProducto no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<TransferenciaProducto> save(@RequestBody TransferenciaProducto entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public TransferenciaProducto update(@PathVariable Long id, @RequestBody TransferenciaProducto entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TransferenciaProducto no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        TransferenciaProducto entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TransferenciaProducto no encontrado: " + id));
        repository.delete(entity);
    }
}