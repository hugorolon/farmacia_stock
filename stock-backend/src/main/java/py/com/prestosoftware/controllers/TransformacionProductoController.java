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

import py.com.prestosoftware.data.models.TransformacionProducto;
import py.com.prestosoftware.data.repository.TransformacionProductoRepository;
import py.com.prestosoftware.domain.services.TransformacionProductoService;
import py.com.prestosoftware.data.models.Deposito;
import py.com.prestosoftware.data.models.Producto;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/api/transformacion-productos")
public class TransformacionProductoController {

    private final TransformacionProductoService service;
    private final TransformacionProductoRepository repository;

    public TransformacionProductoController(TransformacionProductoService service, TransformacionProductoRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping
    public List<TransformacionProductoSummary> findAll() {
        List<TransformacionProductoSummary> result = new ArrayList<>();
        for (Object[] row : repository.findSummaryRows()) {
            result.add(TransformacionProductoSummary.from(row));
        }
        return result;
    }

    public static class TransformacionProductoSummary {
        private Long id;
        private Date fecha;
        private Integer cantidad;
        private Integer situacion;
        private String usuario;
        private String obs;
        private Producto productoOrigen;
        private Deposito depositoOrigen;
        private Deposito depositoDestino;

        public static TransformacionProductoSummary from(Object[] row) {
            TransformacionProductoSummary result = new TransformacionProductoSummary();
            result.id = ((Number) row[0]).longValue();
            result.fecha = (Date) row[1];
            result.cantidad = row[2] == null ? null : ((Number) row[2]).intValue();
            result.situacion = row[3] == null ? null : ((Number) row[3]).intValue();
            result.usuario = (String) row[4];
            result.obs = (String) row[5];
            if (row[6] != null) {
                Producto producto = new Producto();
                producto.setId(((Number) row[6]).longValue());
                producto.setDescripcion(row[7] == null ? "Producto no disponible" : (String) row[7]);
                result.productoOrigen = producto;
            }
            if (row[8] != null) {
                Deposito deposito = new Deposito();
                deposito.setId(((Number) row[8]).longValue());
                result.depositoOrigen = deposito;
            }
            if (row[9] != null) {
                Deposito deposito = new Deposito();
                deposito.setId(((Number) row[9]).longValue());
                result.depositoDestino = deposito;
            }
            return result;
        }

        public Long getId() { return id; }
        public Date getFecha() { return fecha; }
        public Integer getCantidad() { return cantidad; }
        public Integer getSituacion() { return situacion; }
        public String getUsuario() { return usuario; }
        public String getObs() { return obs; }
        public Producto getProductoOrigen() { return productoOrigen; }
        public Deposito getDepositoOrigen() { return depositoOrigen; }
        public Deposito getDepositoDestino() { return depositoDestino; }
    }

    @GetMapping("/{id}")
    public TransformacionProducto findById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TransformacionProducto no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<TransformacionProducto> save(@RequestBody TransformacionProducto entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(entity));
    }

    @PutMapping("/{id}")
    public TransformacionProducto update(@PathVariable Long id, @RequestBody TransformacionProducto entity) {
        repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TransformacionProducto no encontrado: " + id));
        entity.setId(id);
        return repository.save(entity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        TransformacionProducto entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "TransformacionProducto no encontrado: " + id));
        repository.delete(entity);
    }
}