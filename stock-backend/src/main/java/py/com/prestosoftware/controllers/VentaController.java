package py.com.prestosoftware.controllers;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;

import py.com.prestosoftware.data.dtos.VentaDTO;
import py.com.prestosoftware.data.models.Cliente;
import py.com.prestosoftware.data.models.Venta;
import py.com.prestosoftware.data.repository.VentaRepository;
import py.com.prestosoftware.domain.services.VentaService;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService service;
    private final VentaRepository repository;
    private final ObjectMapper objectMapper;

    public VentaController(VentaService service, VentaRepository repository, ObjectMapper objectMapper) {
        this.service = service;
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<Venta> findAll() {
        List<Venta> result = new ArrayList<>();
        for (Object[] row : repository.findSummaryRows()) {
            Venta venta = new Venta();
            venta.setId(((Number) row[0]).longValue());
            venta.setFecha((Date) row[1]);
            venta.setClienteNombre(row[2] == null ? "" : (String) row[2]);
            venta.setTotalGeneral(row[3] == null ? null : ((Number) row[3]).doubleValue());
            venta.setCondicion(((Number) row[4]).intValue());
            venta.setSituacion(row[5] == null ? null : (String) row[5]);
            result.add(venta);
        }
        return result;
    }

    @GetMapping("/{id}")
    public VentaDTO findById(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrado: " + id));
    }

    @GetMapping("/{id}/detalles")
    public List<Object[]> getDetallesById(@PathVariable Long id) {
        List<Object[]> detalles = service.retriveVentaDetalleByIdVenta(id);
        return detalles;
    }

    @GetMapping("/count")
    public long count() {
        return repository.getMaxId();
    }

    @GetMapping("/timbrado/nro")
    public Long getNroTimbrado() {
        Long nro = service.getNroTimbrado();
        return nro == null ? 0L : nro;
    }

    @PutMapping("/{id}/timbrado")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveTimbrado(@PathVariable Long id, @RequestParam("nro") Long nroTimbrado) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrado: " + id));
        service.saveTimbrado(id.toString(), nroTimbrado.toString());
    }

    @GetMapping("/filtro")
    public List<Venta> filtro(@RequestParam("desde") long desde,
            @RequestParam("hasta") long hasta,
            @RequestParam(value = "situacion", defaultValue = "") String situacion,
            @RequestParam(value = "formaPago", defaultValue = "0") int formaPago) {
        Date fechaDesde = new Date(desde);
        Date fechaHasta = new Date(hasta);

        List<Venta> ventas;
        if (situacion.isEmpty() && formaPago == 0) {
            ventas = repository.getVentasFiltroSinSituacionSinForma(fechaDesde, fechaHasta);
        } else if (situacion.isEmpty()) {
            ventas = repository.getVentasFiltroSinSituacion(fechaDesde, fechaHasta, formaPago);
        } else if (formaPago == 0) {
            ventas = repository.getVentasFiltroSinForma(fechaDesde, fechaHasta, situacion);
        } else {
            ventas = repository.getVentasFiltro(fechaDesde, fechaHasta, situacion, formaPago);
        }

        List<Venta> result = new ArrayList<>();
        for (Venta venta : ventas) {
            Venta summary = new Venta();
            summary.setId(venta.getId());
            summary.setFecha(venta.getFecha());
            summary.setClienteNombre(venta.getClienteNombre());
            summary.setTotalGeneral(venta.getTotalGeneral());
            summary.setCondicion(venta.getCondicion());
            summary.setSituacion(venta.getSituacion());
            result.add(summary);
        }
        return result;
    }

    
    @PostMapping
    public ResponseEntity<Venta> save(@RequestBody Map<String, Object>  request) {
        int lanzamientoCaja = objectMapper.convertValue(request.get("lanzCaja"), Integer.class);
        String condicion = objectMapper.convertValue(request.get("condicionPago"), String.class);
        Venta venta = objectMapper.convertValue(request.get("venta"), Venta.class);
        Cliente clienteNuevo = objectMapper.convertValue(request.get("cliente"), Cliente.class);
        System.out.println("VentaController.save: LanzamientoCaja: " + lanzamientoCaja + ", Condicion: " + condicion + ", Venta: " + venta);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(lanzamientoCaja, venta, clienteNuevo, condicion));
    }

    @PutMapping("/{id}")
    public Venta update(@PathVariable Long id, @RequestBody Venta entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        Venta entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Venta no encontrado: " + id));
        repository.delete(entity);
    }
}