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

import py.com.prestosoftware.data.models.PlanCuenta;
import py.com.prestosoftware.data.repository.PlanCuentaRepository;
import py.com.prestosoftware.domain.services.PlanCuentaService;

@RestController
@RequestMapping("/api/plan-cuentas")
public class PlanCuentaController {

    private final PlanCuentaService service;
    private final PlanCuentaRepository repository;

    public PlanCuentaController(PlanCuentaService service, PlanCuentaRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<PlanCuenta> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public PlanCuenta findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PlanCuenta no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<PlanCuenta> save(@RequestBody PlanCuenta entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public PlanCuenta update(@PathVariable Long id, @RequestBody PlanCuenta entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PlanCuenta no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        PlanCuenta entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PlanCuenta no encontrado: " + id));
        repository.delete(entity);
    }
}