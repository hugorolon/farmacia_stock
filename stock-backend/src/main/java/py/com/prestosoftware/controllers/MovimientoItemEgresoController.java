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

import py.com.prestosoftware.data.models.MovimientoItemEgreso;
import py.com.prestosoftware.data.models.MovimientoItemIngreso;
import py.com.prestosoftware.data.repository.MovimientoItemEgresoRepository;
import py.com.prestosoftware.domain.services.MovimientoItemEgresoService;

@RestController
@RequestMapping("/api/movimiento-item-egresos")
public class MovimientoItemEgresoController {

    private final MovimientoItemEgresoService service;
    private final MovimientoItemEgresoRepository repository;

    public MovimientoItemEgresoController(MovimientoItemEgresoService service, MovimientoItemEgresoRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<MovimientoItemEgreso> findAll() {
        return repository.findAll();
    }

     @GetMapping("/cab/{cabId}")
    public List<MovimientoItemEgreso> findByCabId(@PathVariable Integer cabId) {
        return repository.findByCabId(cabId);
    }

    @GetMapping("/{id}")
    public MovimientoItemEgreso findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MovimientoItemEgreso no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<MovimientoItemEgreso> save(@RequestBody MovimientoItemEgreso entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public MovimientoItemEgreso update(@PathVariable Long id, @RequestBody MovimientoItemEgreso entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MovimientoItemEgreso no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        MovimientoItemEgreso entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MovimientoItemEgreso no encontrado: " + id));
        repository.delete(entity);
    }
}