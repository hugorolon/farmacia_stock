package py.com.prestosoftware.controllers;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.format.annotation.DateTimeFormat;

import py.com.prestosoftware.data.models.AperturaCierreCaja;
import py.com.prestosoftware.data.repository.AperturaCierreCajaRepository;
import py.com.prestosoftware.data.models.Caja;
import py.com.prestosoftware.domain.services.AperturaCierreCajaService;

@RestController
@RequestMapping("/api/apertura-cierre-cajas")
public class AperturaCierreCajaController {

    private final AperturaCierreCajaService service;
    private final AperturaCierreCajaRepository repository;

    public AperturaCierreCajaController(AperturaCierreCajaService service, AperturaCierreCajaRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<AperturaCierreCaja> findAll() {
        return repository.findAll();
    }

    @GetMapping("/buscar")
    public AperturaCierreCaja findByCajaAndFechaApertura(
            @RequestParam("cajaId") Long cajaId,
            @RequestParam("fecha") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha) {
        Caja caja = new Caja();
        caja.setId(cajaId);

        return service.findByCajaAndFechaApertura(caja, fecha)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No existe apertura para la caja " + cajaId + " en la fecha indicada"));
    }

    @GetMapping("/{id}")
    public AperturaCierreCaja findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AperturaCierreCaja no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<AperturaCierreCaja> save(@RequestBody AperturaCierreCaja entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public AperturaCierreCaja update(@PathVariable Long id, @RequestBody AperturaCierreCaja entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AperturaCierreCaja no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        AperturaCierreCaja entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "AperturaCierreCaja no encontrado: " + id));
        repository.delete(entity);
    }
}