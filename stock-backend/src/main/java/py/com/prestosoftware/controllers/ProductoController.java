package py.com.prestosoftware.controllers;

import java.util.List;
import java.util.function.Function;

import jakarta.persistence.EntityManager;

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

import py.com.prestosoftware.data.models.Categoria;
import py.com.prestosoftware.data.models.Color;
import py.com.prestosoftware.data.models.Grupo;
import py.com.prestosoftware.data.models.Impuesto;
import py.com.prestosoftware.data.models.Marca;
import py.com.prestosoftware.data.models.Ncm;
import py.com.prestosoftware.data.models.Producto;
import py.com.prestosoftware.data.models.Subgrupo;
import py.com.prestosoftware.data.models.Tamanho;
import py.com.prestosoftware.data.models.UnidadMedida;
import py.com.prestosoftware.data.models.Usuario;
import py.com.prestosoftware.data.repository.ProductoRepository;
import py.com.prestosoftware.domain.services.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;
    private final ProductoRepository repository;
    private final EntityManager entityManager;

    public ProductoController(ProductoService service, ProductoRepository repository, EntityManager entityManager) {
        this.service = service;
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @GetMapping
    public List<Producto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Producto findById(@PathVariable Long id) {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado: " + id));
    }

    @GetMapping("/count")
    public long count() {
        return repository.count();
    }

    @PostMapping
    public ResponseEntity<Producto> save(@RequestBody Producto entity) {
        resolveAssociations(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
    }

    @PutMapping("/{id}")
    public Producto update(@PathVariable Long id, @RequestBody Producto entity) {
        service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado: " + id));
        entity.setId(id);
        resolveAssociations(entity);
        return service.update(entity);
    }

    private void resolveAssociations(Producto producto) {
        producto.setGrupo(resolveReference(
                producto.getGrupo(), Grupo::getId, Grupo::getNombre, Grupo.class, "grupo", true));
        producto.setSubgrupo(resolveReference(
                producto.getSubgrupo(), Subgrupo::getId, Subgrupo::getNombre, Subgrupo.class, "subgrupo", true));
        producto.setNcm(resolveReference(
                producto.getNcm(), Ncm::getId, Ncm::getNombre, Ncm.class, "NCM", true));
        producto.setCategoria(resolveReference(
                producto.getCategoria(), Categoria::getId, Categoria::getNombre, Categoria.class, "categoría", true));
        producto.setImpuesto(resolveReference(
                producto.getImpuesto(), Impuesto::getId, Impuesto::getNombre, Impuesto.class, "impuesto", true));
        producto.setMarca(resolveReference(
                producto.getMarca(), Marca::getId, Marca::getNombre, Marca.class, "marca", false));
        producto.setColor(resolveReference(
                producto.getColor(), Color::getId, Color::getNombre, Color.class, "color", false));
        producto.setTamanho(resolveReference(
                producto.getTamanho(), Tamanho::getId, Tamanho::getNombre, Tamanho.class, "tamaño", false));
        producto.setUnidadMedida(resolveReference(
                producto.getUnidadMedida(), UnidadMedida::getId, UnidadMedida::getNombre,
                UnidadMedida.class, "unidad de medida", true));
        producto.setUserCreate(resolveReference(
                producto.getUserCreate(), Usuario::getId, Usuario::getUsuario,
                Usuario.class, "usuario de registro", false));
        producto.setUserUpdate(resolveReference(
                producto.getUserUpdate(), Usuario::getId, Usuario::getUsuario,
                Usuario.class, "usuario de modificación", false));
    }

    private <T> T resolveReference(T reference, Function<T, Long> idGetter,
            Function<T, String> nameGetter, Class<T> type, String relationship, boolean required) {
        if (reference == null) {
            if (required) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "El producto debe incluir " + relationship);
            }
            return null;
        }

        Long id = idGetter.apply(reference);
        if (id == null) {
            if (!required && (nameGetter.apply(reference) == null || nameGetter.apply(reference).isBlank())) {
                return null;
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La relación " + relationship + " debe incluir un ID existente");
        }

        T persistedReference = entityManager.find(type, id);
        if (persistedReference == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    relationship + " no encontrado: " + id);
        }
        return persistedReference;
    }

    @PutMapping("/{id}/lote/activar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activarLote(@PathVariable Long id) {
        service.activarLote(id);
    }

    @PutMapping("/{id}/lote/inactivar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inactivarLote(@PathVariable Long id) {
        service.inactivarLote(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long id) {
        Producto entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado: " + id));
        repository.delete(entity);
    }
}