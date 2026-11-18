package py.com.prestosoftware.ui.controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Optional;

import jakarta.annotation.PostConstruct;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import py.com.prestosoftware.client.MarcaApiClient;
import py.com.prestosoftware.data.dtos.MarcaDTO;
import py.com.prestosoftware.data.dtos.ProductoDTO;
import py.com.prestosoftware.domain.validations.MarcaValidator;
import py.com.prestosoftware.domain.validations.ValidationError;
import py.com.prestosoftware.ui.forms.MarcaFrame;
import py.com.prestosoftware.ui.forms.MarcaPanel;
import py.com.prestosoftware.ui.search.MarcaInterfaz;
import py.com.prestosoftware.ui.search.ProductoInterfaz;
import py.com.prestosoftware.ui.shared.AbstractFrameController;
import py.com.prestosoftware.ui.shared.TableSearchPanel;
import py.com.prestosoftware.ui.table.MarcaTableModel;
import py.com.prestosoftware.util.Notifications;

@Controller
public class MarcaController extends AbstractFrameController {

    private MarcaFrame frame;
    private MarcaTableModel tableModel;
    private MarcaApiClient service;
    private MarcaValidator validator;
    private String origen;
  
    private MarcaDTO marca;
    @Autowired
    public MarcaController(MarcaFrame frame, MarcaTableModel tableModel, 
    		MarcaApiClient service, MarcaValidator validator) {
        this.frame = frame;
        this.tableModel = tableModel;
        this.service = service;
        this.validator = validator;
    }

    @PostConstruct
    private void prepareListeners() {
        TableSearchPanel searchPanel = frame.getSearchPanel();
        
        registerAction(frame.getFormPanel().getBtnGuardar(), (e) -> save());
        registerAction(frame.getFormPanel().getBtnCancelar(), (e) -> cleanInputs());
        registerAction(frame.getFormPanel().getBtnCerrar(), (e) -> closeWindow());
        
        registerAction(searchPanel.getSearchBtn(), (e) -> findAll(searchPanel.getTfSearchField().getText()));
        registerKeyEvent(searchPanel.getTfSearchField(), new KeyListener() {
        	@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					String name = searchPanel.getTfSearchField().getText();
					findAll(name);
				}
			}
		});
        
        registerSelectRow(frame.getTablePanel().getTable().getSelectionModel(), new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				setData();
			}
		});
    }

    @Override
    public void prepareAndOpenFrame() {
        loadMarcas();
        showFrame();
        getRowCount();
    }

    private void loadMarcas() {
        List<MarcaDTO> marcas = service.findAll();
        tableModel.clear();
        tableModel.addEntities(marcas);
    }
    
    public void getRowCount() {
    	long max = service.getRowMax();
    	frame.getFormPanel().setNewMarca(max + 1);
    }
    
    private void findAll(String name) {
    	List<MarcaDTO> marcas;
    	
    	if (name.isEmpty()) {
    		marcas = service.findAll();
		} else {
			marcas = service.findByNombre(name);		
		}
    	
        tableModel.clear();
        tableModel.addEntities(marcas);
    }

    private void save() {
        MarcaPanel formPanel = frame.getFormPanel();
        MarcaDTO marca = formPanel.getFormValue();
        Optional<ValidationError> errors = validator.validate(marca);
        if (errors.isPresent()) {
            ValidationError validationError = errors.get();
            Notifications.showFormValidationAlert(validationError.getMessage());
        } else {
            service.save(marca);
            loadMarcas();
            if(!origen.equalsIgnoreCase("MENU")) {
            	formPanel.getInterfaz().getEntity(marca);
            	setMarca(marca);
            	frame.dispose();	
            }
            
            
            cleanInputs();
        }
    }
    
    public void setInterfaz(MarcaInterfaz marcaInterfaz) {
    	frame.getFormPanel().setInterfaz(marcaInterfaz);
    }
    
    public MarcaInterfaz getInterfaz() {
		return frame.getFormPanel().getInterfaz();
	}
    
    private void showFrame() {
        frame.setVisible(true);
        frame.getFormPanel().getTfNombre().requestFocus();
    }

    public MarcaDTO getMarca() {
		return marca;
	}

	public void setMarca(MarcaDTO producto) {
		this.marca= producto;
	}
    
    private void cleanInputs() {
    	frame.getFormPanel().clearForm();
    	frame.getFormPanel().getTfNombre().requestFocus();
    	getRowCount();
    }
    
    private void closeWindow() {
    	frame.closeWindow();
    }
    
    public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	private void setData() {
        int selectedRow = frame.getTablePanel().getTable().getSelectedRow();
        
        if (selectedRow != -1) {
        	MarcaDTO marca = tableModel.getEntityByRow(selectedRow);
        	frame.getFormPanel().setFormValue(marca);   
        }
    }

}
