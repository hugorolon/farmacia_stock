package py.com.prestosoftware.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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

import py.com.prestosoftware.data.models.Caja;
import py.com.prestosoftware.data.models.MovimientoCaja;
import py.com.prestosoftware.data.repository.MovimientoCajaRepository;
import py.com.prestosoftware.domain.services.MovimientoCajaService;

@RestController
@RequestMapping("/api/movimiento-cajas")
public class MovimientoCajaController {

    private final MovimientoCajaService service;
    private final MovimientoCajaRepository repository;

    public MovimientoCajaController(MovimientoCajaService service, MovimientoCajaRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<MovimientoCaja> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public MovimientoCaja findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MovimientoCaja no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    // ---- Consultas (MovimientoCajaService) ----
    // Fechas como yyyy-MM-dd. Si no hay resultado se responde 204 (el cliente recibe null).

    @GetMapping("/filtro")
    public List<MovimientoCaja> findByFechaAndCajaAndSituacion(
            @RequestParam("fecha") @DateTimeFormat(iso = ISO.DATE) Date fecha,
            @RequestParam("cajaId") Long cajaId,
            @RequestParam("situacion") String situacion) {
        return service.findByFechaAndCajaAndSituacion(fecha, new Caja(cajaId), situacion);
    }

    @GetMapping("/venta/{idVenta}")
    public ResponseEntity<MovimientoCaja> findByIdVenta(@PathVariable String idVenta) {
        return okOrNoContent(service.findByIdVenta(idVenta).orElse(null));
    }

    @GetMapping("/nota/{nro}")
    public ResponseEntity<MovimientoCaja> findByNotaNro(@PathVariable String nro) {
        return okOrNoContent(service.findByNotaNro(nro));
    }

    @GetMapping("/nota/{nro}/tipo/{tipo}")
    public ResponseEntity<MovimientoCaja> findByNotaNroAndTipoOperacion(@PathVariable String nro, @PathVariable String tipo) {
        return okOrNoContent(service.findByNotaNroAndTipoOperacion(nro, tipo).orElse(null));
    }

    @GetMapping("/totales")
    public ResponseEntity<MovimientoCaja> getTotalsMovCaja(
            @RequestParam("cajaId") Long cajaId,
            @RequestParam("fecha") @DateTimeFormat(iso = ISO.DATE) Date fecha,
            @RequestParam("situacion") String situacion) {
        return okOrNoContent(service.getTotalsMovCaja(new Caja(cajaId), fecha, situacion).orElse(null));
    }

    @GetMapping("/ultima-fecha")
    public ResponseEntity<java.sql.Date> findLastDateMov(@RequestParam("fecha") @DateTimeFormat(iso = ISO.DATE) Date fecha) {
        Date ultima = service.findLastDateMov(fecha);
        return okOrNoContent(ultima == null ? null : new java.sql.Date(ultima.getTime()));
    }

    @GetMapping("/totales/venta-contado")
    public Double totalVentaContado(@RequestParam("fecha") @DateTimeFormat(iso = ISO.DATE) Date fecha) {
        return service.totalVentaContado(fecha).orElse(0d);
    }

    @GetMapping("/totales/compra-contado")
    public Double totalCompraContado(@RequestParam("fecha") @DateTimeFormat(iso = ISO.DATE) Date fecha,
            @RequestParam("cajaId") Long cajaId) {
        return service.totalCompraContado(fecha, new Caja(cajaId)).orElse(0d);
    }

    @GetMapping("/totales/otros-ingresos")
    public Double totalOtrosIngresos(@RequestParam("fecha") @DateTimeFormat(iso = ISO.DATE) Date fecha,
            @RequestParam("cajaId") Long cajaId) {
        return service.totalOtrosIngresos(fecha, new Caja(cajaId)).orElse(0d);
    }

    @GetMapping("/totales/otros-egresos")
    public Double totalOtrosEgresos(@RequestParam("fecha") @DateTimeFormat(iso = ISO.DATE) Date fecha,
            @RequestParam("cajaId") Long cajaId) {
        return service.totalOtrosEgresos(fecha, new Caja(cajaId)).orElse(0d);
    }

    @GetMapping("/totales/entrada")
    public Double totalEntrada(@RequestParam("desde") @DateTimeFormat(iso = ISO.DATE) Date desde,
            @RequestParam("hasta") @DateTimeFormat(iso = ISO.DATE) Date hasta,
            @RequestParam("cajaId") Long cajaId) {
        return service.totalEntrada(desde, hasta, new Caja(cajaId)).orElse(0d);
    }

    @GetMapping("/totales/salida")
    public Double totalSalida(@RequestParam("desde") @DateTimeFormat(iso = ISO.DATE) Date desde,
            @RequestParam("hasta") @DateTimeFormat(iso = ISO.DATE) Date hasta,
            @RequestParam("cajaId") Long cajaId) {
        return service.totalSalida(desde, hasta, new Caja(cajaId)).orElse(0d);
    }

    private static <T> ResponseEntity<T> okOrNoContent(T body) {
        return body == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(body);
    }

    // ---- Escritura ----

    @PostMapping
    public ResponseEntity<MovimientoCaja> save(@RequestBody MovimientoCaja entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<MovimientoCaja>> saveAll(@RequestBody List<MovimientoCaja> entities) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entities));
    }

    @PutMapping("/{id}")
    public MovimientoCaja update(@PathVariable Long id, @RequestBody MovimientoCaja entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MovimientoCaja no encontrado: " + id));
        entity.setId(id);
        return service.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        MovimientoCaja entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MovimientoCaja no encontrado: " + id));
        service.remove(entity);
    }
}