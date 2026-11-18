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

import py.com.prestosoftware.client.EmpaqueApiClient;
import py.com.prestosoftware.data.dtos.EmpaqueDTO;
import py.com.prestosoftware.domain.validations.EmpaqueValidator;
import py.com.prestosoftware.domain.validations.ValidationError;
import py.com.prestosoftware.ui.forms.EmpaqueFrame;
import py.com.prestosoftware.ui.forms.EmpaquePanel;
import py.com.prestosoftware.ui.shared.AbstractFrameController;
import py.com.prestosoftware.ui.shared.TableSearchPanel;
import py.com.prestosoftware.ui.table.EmpaqueTableModel;
import py.com.prestosoftware.util.Notifications;

@Controller
public class EmpaqueController extends AbstractFrameController {

    private EmpaqueFrame frame;
    private EmpaqueTableModel tableModel;
    private EmpaqueApiClient service;
    private EmpaqueValidator validator;
   
    @Autowired
    public EmpaqueController(EmpaqueFrame frame, EmpaqueTableModel tableModel, 
    							EmpaqueApiClient service, EmpaqueValidator validator) {
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
        
        registerButtonEvent(frame.getFormPanel().getBtnGuardar(), new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					save();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) { }
			
			@Override
			public void keyPressed(KeyEvent e) { }
		});
        
        registerSelectRow(frame.getTablePanel().getTable().getSelectionModel(), new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				setData();
			}
		});
    }
    
    //preguntar
    public EmpaqueTableModel getTableModel() {
		return tableModel;
	}

    @Override
    public void prepareAndOpenFrame() {
        loadPaises();
        addNewPais();
        showFrame();
    }
    
    //preguntar
    private void addNewPais() {
    	long cantPais = service.addNewPais();
    	frame.getFormPanel().setNewPais(cantPais + 1);
    }

    private void loadPaises() {
        List<EmpaqueDTO> empaques = service.findAll();
        tableModel.clear();
        tableModel.addEntities(empaques);
    }
    
    //preguntar por que no tiene esto, pero si tiene en Deposito Controller
//    private void getMax() {
//    	long max = service.getRowCount();
//    	frame.getFormPanel().setNewDeposito(max + 1);
//    }
    
    private void findAll(String name) {
    	List<EmpaqueDTO> empaques;
    	
    	if (name.isEmpty()) {
    		empaques = service.findAll();
		} else {
			empaques = service.findByNombre(name);		
		}
    	
        tableModel.clear();
        tableModel.addEntities(empaques);
    }

    private void save() {
        EmpaquePanel formPanel = frame.getFormPanel();
        EmpaqueDTO emp = formPanel.getFormValue();
        Optional<ValidationError> errors = validator.validate(emp);
        if (errors.isPresent()) {
            ValidationError validationError = errors.get();
            Notifications.showFormValidationAlert(validationError.getMessage());
        } else {
            service.save(emp);
            frame.getFormPanel().clearForm();
            loadPaises();
            addNewPais();
        }
    }
    
    private void showFrame() {
        frame.setVisible(true);
        frame.getFormPanel().getTfNombre().requestFocus();
    }

    private void cleanInputs() {
    	frame.getFormPanel().clearForm();
    	addNewPais();
    }
    
    private void closeWindow() {
    	frame.closeWindow();
    }
    
    private void setData() {
        int selectedRow = frame.getTablePanel().getTable().getSelectedRow();
        
        if (selectedRow != -1) {
        	EmpaqueDTO emp = tableModel.getEntityByRow(selectedRow);
        	frame.getFormPanel().setFormValue(emp);   
        }
    }

}
