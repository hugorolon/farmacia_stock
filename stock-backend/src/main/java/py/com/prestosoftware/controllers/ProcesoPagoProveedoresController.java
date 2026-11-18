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

import py.com.prestosoftware.data.models.ProcesoPagoProveedores;
import py.com.prestosoftware.data.repository.ProcesoPagoProveedoresRepository;
import py.com.prestosoftware.domain.services.ProcesoPagoProveedoresService;

@RestController
@RequestMapping("/api/proceso-pago-proveedores")
public class ProcesoPagoProveedoresController {

    private final ProcesoPagoProveedoresService service;
    private final ProcesoPagoProveedoresRepository repository;

    public ProcesoPagoProveedoresController(ProcesoPagoProveedoresService service, ProcesoPagoProveedoresRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<ProcesoPagoProveedores> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ProcesoPagoProveedores findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProcesoPagoProveedores no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<ProcesoPagoProveedores> save(@RequestBody ProcesoPagoProveedores entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public ProcesoPagoProveedores update(@PathVariable Long id, @RequestBody ProcesoPagoProveedores entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProcesoPagoProveedores no encontrado: " + id));

        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        ProcesoPagoProveedores entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ProcesoPagoProveedores no encontrado: " + id));
        repository.delete(entity);
    }
}