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

import py.com.prestosoftware.data.models.Ciudad;
import py.com.prestosoftware.data.repository.CiudadRepository;


@RestController
@RequestMapping("/api/ciudades")
public class CiudadController {

    //private final CiudadService service;
    private final CiudadRepository repository;

    public CiudadController( CiudadRepository repository) {
      //  this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<Ciudad> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Ciudad findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudad no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @GetMapping("/addNewCiudad")
    public Long addNewCiudad() {
        return repository.getMaxId();
    }
    

    @PostMapping
    public ResponseEntity<Ciudad> save(@RequestBody Ciudad entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public Ciudad update(@PathVariable Long id, @RequestBody Ciudad entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudad no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        Ciudad entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ciudad no encontrado: " + id));
        repository.delete(entity);
    }
}