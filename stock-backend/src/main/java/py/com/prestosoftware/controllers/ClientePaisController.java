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

import py.com.prestosoftware.data.models.ClientePais;
import py.com.prestosoftware.data.repository.ClientePaisRepository;
import py.com.prestosoftware.domain.services.ClientePaisService;

@RestController
@RequestMapping("/api/cliente-pais")
public class ClientePaisController {

    private final ClientePaisService service;
    private final ClientePaisRepository repository;

    public ClientePaisController(ClientePaisService service, ClientePaisRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<ClientePais> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ClientePais findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ClientePais no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<ClientePais> save(@RequestBody ClientePais entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public ClientePais update(@PathVariable Long id, @RequestBody ClientePais entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ClientePais no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        ClientePais entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ClientePais no encontrado: " + id));
        repository.delete(entity);
    }
}