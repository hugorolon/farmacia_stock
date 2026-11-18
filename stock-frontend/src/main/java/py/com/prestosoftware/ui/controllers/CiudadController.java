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

import py.com.prestosoftware.client.CiudadApiClient;
import py.com.prestosoftware.client.DepartamentoApiClient;
import py.com.prestosoftware.data.dtos.CiudadDTO;
import py.com.prestosoftware.data.dtos.DepartamentoDTO;
import py.com.prestosoftware.domain.validations.CiudadValidator;
import py.com.prestosoftware.domain.validations.ValidationError;
import py.com.prestosoftware.ui.forms.CiudadFrame;
import py.com.prestosoftware.ui.forms.CiudadPanel;
import py.com.prestosoftware.ui.search.CiudadInterfaz;
import py.com.prestosoftware.ui.shared.AbstractFrameController;
import py.com.prestosoftware.ui.shared.TableSearchPanel;
import py.com.prestosoftware.ui.table.CiudadTableModel;
import py.com.prestosoftware.ui.table.DepartamentoComboBoxModel;
import py.com.prestosoftware.util.Notifications;

@Controller
public class CiudadController extends AbstractFrameController {

    private CiudadFrame frame;
    private CiudadTableModel tableModel;
    private CiudadValidator validator;
    private CiudadApiClient service;
    private DepartamentoComboBoxModel depComboModel;
    private DepartamentoApiClient depApiClient;
    private String origen;
    private CiudadDTO ciudad;
   
    @Autowired
    public CiudadController(CiudadFrame frame, CiudadTableModel tableModel, 
    						CiudadApiClient service, CiudadValidator validator,
    						DepartamentoApiClient depApiClient, DepartamentoComboBoxModel depComboModel) {
        this.frame = frame;
        this.tableModel = tableModel;
        this.service = service;
        this.validator = validator;
        this.depApiClient = depApiClient;
        this.depComboModel = depComboModel;
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
    
    public CiudadTableModel getTableModel() {
		return tableModel;
	}

    @Override
    public void prepareAndOpenFrame() {
        loadCiudades();
        loadDeps();
        showFrame();
        addNewCiudad();
    }

    private void loadCiudades() {
    	List<CiudadDTO> ciudades = service.findAll();
        tableModel.clear();
        tableModel.addEntities(ciudades);
    }
    
    private void loadDeps() {
    	List<DepartamentoDTO> deps = depApiClient.findAll();
    	depComboModel.clear();
    	depComboModel.addElements(deps);
    }

    private void addNewCiudad() {
    	long ciudadId = service.addNewCiudad();
    	frame.getFormPanel().setNewCotizacion(ciudadId + 1);
    }
   
    private void findAll(String name) {
    	List<CiudadDTO> ciudades;
    	
    	if (name.isEmpty()) {
    		ciudades = service.findAll();
		} else {
			ciudades = service.findByNombre(name);		
		}
    	
        tableModel.clear();
        tableModel.addEntities(ciudades);
    }

    private void showFrame() {
        frame.setVisible(true);
        frame.getFormPanel().getTfNombre().requestFocus();
    }

    private void save() {
        CiudadPanel formPanel = frame.getFormPanel();
        CiudadDTO ciudad = formPanel.getFormValue();
        Optional<ValidationError> errors = validator.validate(ciudad);
        if (errors.isPresent()) {
            ValidationError validationError = errors.get();
            Notifications.showFormValidationAlert(validationError.getMessage());
        } else {
        	if(!origen.equalsIgnoreCase("MENU")) {
            	formPanel.getInterfaz().getEntity(ciudad);
            	setCiudad(ciudad);
            	frame.dispose();	
            }
        	
            service.save(ciudad);
            tableModel.fireTableDataChanged();
            cleanInputs();
            loadCiudades();
        }
    }

    private void cleanInputs() {
    	frame.getFormPanel().clearForm();
    	frame.getFormPanel().getTfNombre().requestFocus();
    	addNewCiudad();
    }
    
    private void closeWindow() {
    	frame.closeWindow();
    }
    
    
    public CiudadDTO getCiudad() {
		return ciudad;
	}

	public void setCiudad(CiudadDTO ciudad) {
		this.ciudad = ciudad;
	}

	public void setInterfaz(CiudadInterfaz ciudadInterfaz) {
    	frame.getFormPanel().setInterfaz(ciudadInterfaz);
    }
    
    public CiudadInterfaz getInterfaz() {
		return frame.getFormPanel().getInterfaz();
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
        	CiudadDTO ciudad = tableModel.getEntityByRow(selectedRow);
        	frame.getFormPanel().setFormValue(ciudad);   
        }
    }

}
