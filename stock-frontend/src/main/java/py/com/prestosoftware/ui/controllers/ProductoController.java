package py.com.prestosoftware.ui.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.annotation.PostConstruct;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestClientResponseException;

import py.com.prestosoftware.client.CategoriaApiClient;
import py.com.prestosoftware.client.ColorApiClient;
import py.com.prestosoftware.client.DepositoApiClient;
import py.com.prestosoftware.client.GrupoApiClient;
import py.com.prestosoftware.client.ImpuestoApiClient;
import py.com.prestosoftware.client.ListaPrecioApiClient;
import py.com.prestosoftware.client.MarcaApiClient;
import py.com.prestosoftware.client.NcmApiClient;
import py.com.prestosoftware.client.ProductoApiClient;
import py.com.prestosoftware.client.SubgrupoApiClient;
import py.com.prestosoftware.client.TamanhoApiClient;
import py.com.prestosoftware.client.UnidadMedidaApiClient;
import py.com.prestosoftware.data.dtos.CategoriaDTO;
import py.com.prestosoftware.data.dtos.ColorDTO;
import py.com.prestosoftware.data.dtos.GrupoDTO;
import py.com.prestosoftware.data.dtos.ImpuestoDTO;
import py.com.prestosoftware.data.dtos.ListaPrecioDTO;
import py.com.prestosoftware.data.dtos.MarcaDTO;
import py.com.prestosoftware.data.dtos.NcmDTO;
import py.com.prestosoftware.data.dtos.ProductoDTO;
import py.com.prestosoftware.data.dtos.ProductoDeposito;
import py.com.prestosoftware.data.dtos.SubgrupoDTO;
import py.com.prestosoftware.data.dtos.TamanhoDTO;
import py.com.prestosoftware.data.dtos.UnidadMedidaDTO;
import py.com.prestosoftware.domain.validations.ProductoValidator;
import py.com.prestosoftware.domain.validations.ValidationError;
import py.com.prestosoftware.ui.forms.ProductoPanel;
import py.com.prestosoftware.ui.search.ProductoInterfaz;
import py.com.prestosoftware.ui.shared.AbstractFrameController;
import py.com.prestosoftware.ui.shared.DefaultTableModel;
import py.com.prestosoftware.ui.table.CategoriaComboBoxModel;
import py.com.prestosoftware.ui.table.ColorComboBoxModel;
import py.com.prestosoftware.ui.table.GrupoComboBoxModel;
import py.com.prestosoftware.ui.table.ImpuestoComboBoxModel;
import py.com.prestosoftware.ui.table.ListaPrecioComboBoxModel;
import py.com.prestosoftware.ui.table.MarcaComboBoxModel;
import py.com.prestosoftware.ui.table.NcmComboBoxModel;
import py.com.prestosoftware.ui.table.ProductTableModel;
import py.com.prestosoftware.ui.table.ProductoDepositoTableModel;
import py.com.prestosoftware.ui.table.SubgrupoComboBoxModel;
import py.com.prestosoftware.ui.table.TamanhoComboBoxModel;
import py.com.prestosoftware.ui.table.UnidadMedidaComboBoxModel;
import py.com.prestosoftware.util.Notifications;

@Controller
public class ProductoController extends AbstractFrameController {

    private ProductoApiClient productService;
    private ProductoValidator productValidator;
    private CategoriaApiClient categoriaService;
    private GrupoApiClient grupoService;
    private SubgrupoApiClient subGrupoApiClient;
    private NcmApiClient ncmService;
    private ImpuestoApiClient impuestoService;
    private DepositoApiClient depositoService;
    private MarcaApiClient marcaService;
    private TamanhoApiClient tamanhoService;
    private UnidadMedidaApiClient unidadMedidaService;
    private ColorApiClient colorService;
    private ListaPrecioApiClient listaPrecioService;
   // private ProductoInterfaz interfaz;
    
    private ProductoPanel productoPanel;
    private ProductTableModel productTableModel;
    private CategoriaComboBoxModel categoriaComboBoxModel;
    private GrupoComboBoxModel grupoComboBoxModel;
    private SubgrupoComboBoxModel subgrupoComboBoxModel;
    private NcmComboBoxModel ncmComboBoxModel;
    private ImpuestoComboBoxModel impuestoComboBoxModel;
    private MarcaComboBoxModel marcaComboBoxModel;
    private TamanhoComboBoxModel tamanhoComboBoxModel;
    private UnidadMedidaComboBoxModel umedidaComboBoxModel;
    private ColorComboBoxModel colorComboBoxModel;
    private ListaPrecioComboBoxModel listaComboBoxModel;
    private ProductoDepositoTableModel depositoTableModel;
    private String origen;
    private ProductoDTO producto;
    private List<ProductoDTO> productos;
    private DefaultTableModel table1;
    
    @Autowired
    public ProductoController(ProductoPanel productFrame, ProductTableModel productTableModel, ProductoApiClient productService, 
    	ProductoValidator productValidator, CategoriaApiClient categoriaService, GrupoApiClient grupoService, ListaPrecioApiClient listaPrecioService,
    	NcmApiClient ncmService, ImpuestoApiClient impuestoService, MarcaApiClient marcaService, TamanhoApiClient tamanhoService, SubgrupoApiClient subGrupoApiClient,
    	UnidadMedidaApiClient unidadMedidaService, ColorApiClient colorService, DepositoApiClient depositoService, CategoriaComboBoxModel categoriaComboBoxModel,
    	GrupoComboBoxModel grupoComboBoxModel, NcmComboBoxModel ncmComboBoxModel, ImpuestoComboBoxModel impuestoComboBoxModel,
    	MarcaComboBoxModel marcaComboBoxModel, TamanhoComboBoxModel tamanhoComboBoxModel, UnidadMedidaComboBoxModel unidadMedidaComboBoxModel, 
    	ColorComboBoxModel colorComboBoxModel, ListaPrecioComboBoxModel listaComboBoxModel, SubgrupoComboBoxModel subgrupoComboBoxModel, ProductoDepositoTableModel depositoTableModel) {
        this.productoPanel = productFrame;  
        this.productTableModel = productTableModel;
        this.depositoTableModel=depositoTableModel;
        this.productService = productService;
        this.productValidator = productValidator;
        this.categoriaService = categoriaService;
        this.grupoService = grupoService;
        this.ncmService = ncmService;
        this.impuestoService = impuestoService;
        this.marcaService = marcaService;
        this.tamanhoService = tamanhoService;
        this.unidadMedidaService = unidadMedidaService;
        this.colorService = colorService;
        this.listaPrecioService = listaPrecioService;
        this.subGrupoApiClient = subGrupoApiClient;
        this.depositoService = depositoService;
        this.categoriaComboBoxModel = categoriaComboBoxModel;
        this.grupoComboBoxModel = grupoComboBoxModel;
        this.ncmComboBoxModel = ncmComboBoxModel;
        this.impuestoComboBoxModel = impuestoComboBoxModel;
        this.marcaComboBoxModel = marcaComboBoxModel;
        this.tamanhoComboBoxModel = tamanhoComboBoxModel;
        this.umedidaComboBoxModel = unidadMedidaComboBoxModel;
        this.colorComboBoxModel = colorComboBoxModel;
        this.listaComboBoxModel = listaComboBoxModel;
        this.subgrupoComboBoxModel = subgrupoComboBoxModel;
    }

    @PostConstruct
    private void prepareListeners() {
        registerAction(productoPanel.getBtnGuardar(), (e) -> save());
        registerAction(productoPanel.getBtnCancelar(), (e) -> cleanInputs());
        registerAction(productoPanel.getBtnCerrar(), (e) -> closeWindow());
        registerAction(productoPanel.getBtnNuevo(), (e) -> cleanInputs());
  
        registerAction(productoPanel.getBtnBuscador(), (e) -> findByName(productoPanel.getTfBuscador().getText()));
        
        registerSelectRow(productoPanel.getTbProducto().getSelectionModel(), (e) -> setData());
        
        registerKeyEvent(productoPanel.getTfBuscador(), new KeyListener() {
        	@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					String name = productoPanel.getTfBuscador().getText();
					findByName(name.toUpperCase());
				} else if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
					productoPanel.dispose();
			    } else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			    	productoPanel.getTbProducto().requestFocus();
			    } 
			}

			@Override
			public void keyReleased(KeyEvent e) {
				JTextField textField = (JTextField) e.getSource();
				String text = textField.getText();
				textField.setText(text.toUpperCase());
				DefaultTableModel table1 = (DefaultTableModel) productoPanel.getTbProducto().getModel();
				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table1);
				productoPanel.getTbProducto().setRowSorter(tr);
				tr.setRowFilter(RowFilter.regexFilter("(?i)" + textField.getText()));
			}
		});
        
        registerKeyEvent(productoPanel.getTfProductoId(), new KeyListener() {
        	@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					String id = productoPanel.getTfProductoId().getText();
					 findById(id);
				}
			}
		});
        
        registerButtonEvent(productoPanel.getBtnNuevo(), new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					cleanInputs();
				}
			}
		});
        
        registerSelectRow(productoPanel.getTbProducto().getSelectionModel(), new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				setData();	
			}
		});
    }

    @Override
    public void prepareAndOpenFrame() {
    	try {
            loadProducts();
            loadCategorias();
            loadGrupos();
            loadNcms();
            loadImpuestos();
            loadMarcas();
            loadUnidadDeMedidas();
            loadColors();
            loadTamanhos();
            loadListaPrecios();
            //loadSubGrupos();
            showProductFrame();
            
            productoPanel.getBtnNuevo().requestFocus();			
		} catch (Exception e) {
			// TODO: handle exception
		}

    }

    private void loadProducts() {
        this.productos = productService.findAllByNombre();
        productoPanel.getProductTableModel().clear();
        productoPanel.getProductTableModel().addEntities(this.productos);
    }

    private void loadCategorias() {
        List<CategoriaDTO> categorias = categoriaService.findAll();
        categoriaComboBoxModel.clear();
        categoriaComboBoxModel.addElements(categorias);
    }
    
    private void loadGrupos() {
        List<GrupoDTO> grupos = grupoService.findAll();
        grupoComboBoxModel.clear();
        grupoComboBoxModel.addElements(grupos);
    }
    
    private void loadSubGrupos() {
    	List<SubgrupoDTO> subgrupos = subGrupoApiClient.findAll();
    	subgrupoComboBoxModel.clear();
        subgrupoComboBoxModel.addElements(subgrupos);
    }
    
    private void loadNcms() {
        List<NcmDTO> ncms = ncmService.findAll();
        ncmComboBoxModel.clear();
        ncmComboBoxModel.addElements(ncms);
    }
    
    private void loadImpuestos() {
        List<ImpuestoDTO> impuestos = impuestoService.findAll();
        impuestoComboBoxModel.clear();
        impuestoComboBoxModel.addElements(impuestos);
    }
    
    
    private List<ProductoDeposito>  getStockProductosByDeposito(ProductoDTO p) {
    	try {
    		List<ProductoDeposito> listProductoDeposito= new ArrayList<ProductoDeposito>();
    		if (p != null) {
    			String deposito = depositoService.findById(1L).get().getNombre();
    			if (p.getDepO1() != null) {
    				ProductoDeposito dep01 = new ProductoDeposito(deposito, p.getDepO1());
    				listProductoDeposito.add(dep01);
    				
    				String deposito2 = depositoService.findById(2L).get().getNombre();
    				if (p.getDepO2() != null) {
    					ProductoDeposito dep02 = new ProductoDeposito(deposito2, p.getDepO2());
    					listProductoDeposito.add(dep02);
    					
    					String deposito3 = depositoService.findById(3L).get().getNombre();
    					if (p.getDepO3() != null) {
    						ProductoDeposito dep03 = new ProductoDeposito(deposito3, p.getDepO3());
    						listProductoDeposito.add(dep03);
    						
    						String deposito4 = depositoService.findById(4L).get().getNombre();
    						if (p.getDepO4() != null) {
    							ProductoDeposito dep04 = new ProductoDeposito(deposito4, p.getDepO4());
    							listProductoDeposito.add(dep04);
    							
    							String deposito5 = depositoService.findById(5L).get().getNombre();
    							if (p.getDepO5() != null) {
    								ProductoDeposito dep05 = new ProductoDeposito(deposito5, p.getDepO5());
    								listProductoDeposito.add(dep05);
    							}	
    						}
    					}
    				}
    			}	
    		}
    		return listProductoDeposito;	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
    
    private void loadMarcas() {
        List<MarcaDTO> marcas = marcaService.findAll();
        marcaComboBoxModel.clear();
        marcaComboBoxModel.addElements(marcas);
    }
    
    private void loadUnidadDeMedidas() {
        List<UnidadMedidaDTO> umedidas = unidadMedidaService.findAll();
        umedidaComboBoxModel.clear();
        umedidaComboBoxModel.addElements(umedidas);
    }
    
    private void loadColors() {
        List<ColorDTO> colores = colorService.findAll();
        colorComboBoxModel.clear();
        colorComboBoxModel.addElements(colores);
    }
    
    private void loadListaPrecios() {
    	List<ListaPrecioDTO> listaPrecios = listaPrecioService.findAll();
        listaComboBoxModel.clear();
        listaComboBoxModel.addElements(listaPrecios);
    }
    
    private void loadTamanhos() {
        List<TamanhoDTO> tamanhos = tamanhoService.findAll();
        tamanhoComboBoxModel.clear();
        tamanhoComboBoxModel.addElements(tamanhos);
    }
    
//    public void addNewProduct() {
//    	long id = productService.addNewProduct();
//    	productoPanel.setNewProducto(id + 1);
//    }
    
    private void findByName(String name) {
    	try {
    		if (name.isEmpty()) {
        		productos = productService.findAll();
    		} else {
    			productos = productService.findByNombre(name);		
    		}
        	productoPanel.getProductTableModel().clear();
        	productoPanel.getProductTableModel().addEntities(productos);
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

    private void findById(String id) {
    	try {
    		Optional<ProductoDTO> producto=Optional.of(new ProductoDTO());
        	if (!id.equalsIgnoreCase("0") ||Integer.valueOf(id).intValue()>0) {
        		producto = productService.findById(Long.valueOf(id));
    		}
        	productoPanel.setProductForm(producto.get());  
        	List<ProductoDeposito> listProductoDeposito= getStockProductosByDeposito(producto.get());
        	depositoTableModel.clear();
        	depositoTableModel.addEntities(listProductoDeposito);
		} catch (Exception e) {
			// TODO: handle exception
		}
    }

    
    private void showProductFrame() {
    	productoPanel.setVisible(true);
        productoPanel.getTfNombre().requestFocus();
    }

    public void setInterfaz(ProductoInterfaz productoInterfaz) {
    	productoPanel.setInterfaz(productoInterfaz);
    }
    
    public ProductoInterfaz getInterfaz() {
		return productoPanel.getInterfaz();
	}

    
	public List<ProductoDTO> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoDTO> productos) {
		this.productos = productos;
	}

	private void save() {
        ProductoDTO product = productoPanel.getProductForm();
        Optional<ValidationError> errors = productValidator.validate(product);
        
        if (errors.isPresent()) {
            ValidationError validationError = errors.get();
            Notifications.showFormValidationAlert(validationError.getMessage());
        } else {
        	try {
        		product = productService.save(product);
				Notifications.showAlert("Producto guardado con éxito!");
        	} catch (RestClientResponseException ex) {
        		String response = ex.getResponseBodyAsString();
        		Notifications.showAlert("No se pudo guardar el producto. " +
        				(response == null || response.trim().isEmpty()
        						? ex.getStatusText()
        						: response));
        		return;
        	}
            if(origen.equalsIgnoreCase("MENU")) {
            	loadProducts();
            	
            }else {
            	productoPanel.getInterfaz().getEntity(product);
            	setProducto(product);
            	productoPanel.dispose();	
            }
            cleanInputs();	
            productoPanel.getMarcaComboBoxModel().setSelectedItem(product.getMarca());
        }
    }

    private void cleanInputs() {
    	try {
        	productoPanel.clearForm();
        	productoPanel.getTfNombre().requestFocus();
        	//addNewProduct();	
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    private void closeWindow() {
    	productoPanel.dispose();;
    }
    
    private void setData() {
    	try {
    		int[] selectedRow = productoPanel.getTbProducto().getSelectedRows();
            if (selectedRow.length>0 && selectedRow[0] != -1) {
            	Long selectedId = (Long) productoPanel.getTbProducto().getValueAt(selectedRow[0], 0);
            	//Producto product =(Producto) this.getTable1().getEntityByRow(selectedRow);
            	ProductoDTO product= this.productos.stream().filter(pro -> pro.getId().equals(selectedId.longValue()))
      				  .findAny()
      				  .orElse(null);
            	productoPanel.setProductForm(product);  
            }
		} catch (Exception e) {
			// TODO: handle exception
		}   
    }
    
   
	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public ProductoDTO getProducto() {
		return producto;
	}

	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}

	public DefaultTableModel getTable1() {
		return table1;
	}

	public void setTable1(DefaultTableModel table1) {
		this.table1 = table1;
	}

	public ProductTableModel getProductTableModel() {
		return productTableModel;
	}

	public void setProductTableModel(ProductTableModel productTableModel) {
		this.productTableModel = productTableModel;
	}

    
}
