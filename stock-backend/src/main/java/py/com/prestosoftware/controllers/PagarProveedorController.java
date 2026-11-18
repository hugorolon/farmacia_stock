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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import py.com.prestosoftware.data.models.PagarProveedor;
import py.com.prestosoftware.data.repository.PagarProveedorRepository;
import py.com.prestosoftware.domain.services.PagarProveedorService;

@RestController
@RequestMapping("/api/pagar-proveedores")
public class PagarProveedorController {

    private final PagarProveedorService service;
    private final PagarProveedorRepository repository;

    public PagarProveedorController(PagarProveedorService service, PagarProveedorRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<PagarProveedor> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public PagarProveedor findById(@PathVariable Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PagarProveedor no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @GetMapping("/max")
    public Integer max() {
        return repository.getMaxId();
    }

    @GetMapping("/view/detalle")
    public List<Object[]> findDetallePagarProveedorView(@RequestParam("p") Long proveedorId) {
        return service.findDetallePagarProveedorView(proveedorId);
    }

    @GetMapping("/view/proveedor-pagado")
    public List<Object[]> findProveedorPagadoView(@RequestParam("p") List<String> parametros) {
        if (parametros.size() < 2) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Se requieren proveedorId y numero");
        }
        return service.findProveedorPagadoView(
                Long.valueOf(parametros.get(0)),
                Integer.valueOf(parametros.get(1)));
    }

    @GetMapping("/fecha/{fecha}")
    public List<PagarProveedor> findByFecha(@PathVariable long fecha) {
        java.sql.Date fechaSql = new java.sql.Date(fecha);
        return repository.findByFecha(fechaSql);
    }

    @GetMapping("/fecha-hoy")
    public List<PagarProveedor> findByFecha() {
        return service.findByFecha();
    }

    @PostMapping("/completo")
    public PagarProveedor save(@RequestBody PagarProveedor entity) {
        //return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
         return service.save(entity);
    }

    @PutMapping("/{id}")
    public PagarProveedor update(@PathVariable Integer id, @RequestBody PagarProveedor entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PagarProveedor no encontrado: " + id));

        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Integer id) {
        PagarProveedor entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PagarProveedor no encontrado: " + id));
        repository.delete(entity);
    }
}