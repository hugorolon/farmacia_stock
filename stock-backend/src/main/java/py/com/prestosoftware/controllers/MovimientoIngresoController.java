package py.com.prestosoftware.controllers;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

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
import java.text.SimpleDateFormat;

import py.com.prestosoftware.data.models.MovimientoIngreso;
import py.com.prestosoftware.data.repository.MovimientoIngresoRepository;
import py.com.prestosoftware.domain.services.MovimientoIngresoService;

@RestController
@RequestMapping("/api/movimiento-ingresos")
public class MovimientoIngresoController {

    private final MovimientoIngresoService service;
    private final MovimientoIngresoRepository repository;

    public MovimientoIngresoController(MovimientoIngresoService service, MovimientoIngresoRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<MovimientoIngreso> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public MovimientoIngreso findById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MovimientoIngreso no encontrado: " + id));
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
        for (Object[] row : repository.getIngresosDelDiaObjects(date)) {
            Object[] summary = row.clone();
            if (summary[1] instanceof Date) {
                summary[1] = formatter.format((Date) summary[1]);
            }
            result.add(summary);
        }
        return result;
    }

    @PostMapping
    public ResponseEntity<MovimientoIngreso> save(@RequestBody MovimientoIngreso entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public MovimientoIngreso update(@PathVariable Integer id, @RequestBody MovimientoIngreso entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MovimientoIngreso no encontrado: " + id));

        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Integer id) {
        MovimientoIngreso entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MovimientoIngreso no encontrado: " + id));
        repository.delete(entity);
    }
}