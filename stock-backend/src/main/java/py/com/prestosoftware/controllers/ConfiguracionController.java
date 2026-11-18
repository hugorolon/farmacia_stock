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

import py.com.prestosoftware.data.models.Configuracion;
import py.com.prestosoftware.data.repository.ConfiguracionRepository;
import py.com.prestosoftware.domain.services.ConfiguracionService;

@RestController
@RequestMapping("/api/configuraciones")
public class ConfiguracionController {

    private final ConfiguracionService service;
    private final ConfiguracionRepository repository;

    public ConfiguracionController(ConfiguracionService service, ConfiguracionRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<Configuracion> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Configuracion findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Configuracion no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<Configuracion> save(@RequestBody Configuracion entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public Configuracion update(@PathVariable Long id, @RequestBody Configuracion entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Configuracion no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        Configuracion entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Configuracion no encontrado: " + id));
        repository.delete(entity);
    }
}