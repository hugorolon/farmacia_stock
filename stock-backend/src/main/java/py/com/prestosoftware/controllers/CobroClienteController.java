package py.com.prestosoftware.controllers;

import java.util.List;
import java.util.Map;
import java.util.Date;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import py.com.prestosoftware.data.models.CobroCliente;
import py.com.prestosoftware.data.models.ItemCobroCliente;
import py.com.prestosoftware.data.repository.CobroClienteRepository;
import py.com.prestosoftware.data.repository.ItemCobroClienteRepository;
import py.com.prestosoftware.domain.services.CobroClienteService;
import py.com.prestosoftware.ui.viewmodel.DetalleCobroClienteView;

@RestController
@RequestMapping("/api/cobro-clientes")
public class CobroClienteController {

    private final CobroClienteService service;
    private final CobroClienteRepository repository;
    private final ItemCobroClienteRepository itemRepository;
    private final ObjectMapper objectMapper;

    public CobroClienteController(CobroClienteService service, CobroClienteRepository repository,
            ItemCobroClienteRepository itemRepository, ObjectMapper objectMapper) {
        this.service = service;
        this.repository = repository;
        this.itemRepository = itemRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<CobroCliente> findAll() {
        return repository.findAll();
    }

    @GetMapping("/view/detalle")
    public List<Object[]> findDetalleCobroClienteView(@RequestParam("p") Long clienteId) {
        return service.findDetalleCobroClienteView(clienteId);
    }

    @GetMapping("/fecha/{fecha}")
    public List<CobroCliente> findByFecha(@PathVariable long fecha) {
        return repository.findByFecha(new Date(fecha));
    }

    @GetMapping("/view/cliente-cobrado")
    public List<Object[]> findClienteCobradoView(
            @RequestParam("p") List<String> parametros) {
        if (parametros.size() < 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Se requieren clienteId y numero");
        }
        return service.findClienteCobradoView(
                Long.valueOf(parametros.get(0)),
                Integer.valueOf(parametros.get(1)));
    }

    @GetMapping("/items/{numero}")
    public List<ItemCobroCliente> findItems(@PathVariable Integer numero) {
        return itemRepository.findByIclNumero(numero);
    }

    @PostMapping("/completo")
    public CobroCliente saveCompleto(@RequestBody Map<String, Object> request) {
        CobroCliente cobro = objectMapper.convertValue(request.get("cobro"), CobroCliente.class);
        List<DetalleCobroClienteView> detalles = objectMapper.convertValue(
                request.get("detalles") != null ? request.get("detalles") : request.get("items"),
                objectMapper.getTypeFactory().constructCollectionType(
                        List.class, DetalleCobroClienteView.class));
        Double monto = Double.valueOf(request.get("monto").toString());
        return service.save(cobro, detalles, monto);
    }

    @PostMapping("/completo/remove")
    public void removeCompleto(@RequestBody Map<String, Object> request) {
        CobroCliente cobro = objectMapper.convertValue(request.get("cobro"), CobroCliente.class);
        List<ItemCobroCliente> items = objectMapper.convertValue(
                request.get("items"),
                objectMapper.getTypeFactory().constructCollectionType(
                        List.class, ItemCobroCliente.class));
        List<DetalleCobroClienteView> detalles = objectMapper.convertValue(
                request.get("detalles"),
                objectMapper.getTypeFactory().constructCollectionType(
                        List.class, DetalleCobroClienteView.class));
        service.remove(cobro, items, detalles);
    }

    @GetMapping("/{id}")
    public CobroCliente findById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CobroCliente no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @GetMapping("/max")
    public Integer max() {
        return repository.getMaxId();
    }

    @PostMapping
    public ResponseEntity<CobroCliente> save(@RequestBody CobroCliente entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public CobroCliente update(@PathVariable Integer id, @RequestBody CobroCliente entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CobroCliente no encontrado: " + id));

        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Integer id) {
        CobroCliente entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CobroCliente no encontrado: " + id));
        repository.delete(entity);
    }
}