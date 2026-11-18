package py.com.prestosoftware.controllers;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

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

import py.com.prestosoftware.data.models.MovimientoEgreso;
import py.com.prestosoftware.data.repository.MovimientoEgresoRepository;
import py.com.prestosoftware.domain.services.MovimientoEgresoService;

@RestController
@RequestMapping("/api/movimiento-egresos")
public class MovimientoEgresoController {

    private final MovimientoEgresoService service;
    private final MovimientoEgresoRepository repository;

    public MovimientoEgresoController(MovimientoEgresoService service, MovimientoEgresoRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<MovimientoEgreso> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public MovimientoEgreso findById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MovimientoEgreso no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return service.getRowCount();
    }

    @GetMapping("/fecha/{fecha}")
    public List<Object[]> findByDateObjects(@PathVariable long fecha) {
        Date date = new Date(fecha);
        List<Object[]> result = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (Object[] row : repository.getEgresosDelDiaObjects(date)) {
            Object[] summary = row.clone();
            if (summary[1] instanceof Date) {
                summary[1] = formatter.format((Date) summary[1]);
            }
            result.add(summary);
        }
        return result;
    }

    @PostMapping
    public ResponseEntity<MovimientoEgreso> save(@RequestBody MovimientoEgreso entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public MovimientoEgreso update(@PathVariable Integer id, @RequestBody MovimientoEgreso entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MovimientoEgreso no encontrado: " + id));

        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Integer id) {
        MovimientoEgreso entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MovimientoEgreso no encontrado: " + id));
        repository.delete(entity);
    }
}