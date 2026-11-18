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

import py.com.prestosoftware.client.RolApiClient;
import py.com.prestosoftware.data.dtos.RolDTO;
import py.com.prestosoftware.domain.validations.RolValidator;
import py.com.prestosoftware.domain.validations.ValidationError;
import py.com.prestosoftware.ui.forms.RolFrame;
import py.com.prestosoftware.ui.forms.RolPanel;
import py.com.prestosoftware.ui.shared.AbstractFrameController;
import py.com.prestosoftware.ui.shared.TableSearchPanel;
import py.com.prestosoftware.ui.table.RolTableModel;
import py.com.prestosoftware.util.Notifications;

@Controller
public class RolController extends AbstractFrameController {

    private RolFrame frame;
    private RolTableModel tableModel;
    private RolApiClient service;
    private RolValidator validator;
   
    @Autowired
    public RolController(RolFrame frame,RolTableModel tableModel, 
    	RolApiClient service, RolValidator validator) {
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
        loadRoles();
        showFrame();
        getRowCount();
    }

    private void loadRoles() {
        List<RolDTO> roles = service.findAll();
        tableModel.clear();
        tableModel.addEntities(roles);
    }
    
    private void getRowCount() {
    	long max = service.getRoxMax();
    	frame.getFormPanel().setNewRol(max + 1);
    }
    
    private void findAll(String name) {
    	List<RolDTO> roles;
    	
    	if (name.isEmpty()) {
    		roles = service.findAll();
		} else {
			roles = service.findByNombre(name);		
		}
    	
        tableModel.clear();
        tableModel.addEntities(roles);
    }

    private void save() {
        RolPanel formPanel = frame.getFormPanel();
        RolDTO rol = formPanel.getFormValue();
        Optional<ValidationError> errors = validator.validate(rol);
        if (errors.isPresent()) {
            ValidationError validationError = errors.get();
            Notifications.showFormValidationAlert(validationError.getMessage());
        } else {
            service.save(rol);
            loadRoles();
            cleanInputs();
        }
    }
    
    private void showFrame() {
        frame.setVisible(true);
        frame.getFormPanel().getTfNombre().requestFocus();
    }

    private void cleanInputs() {
    	frame.getFormPanel().clearForm();
    	frame.getFormPanel().getTfNombre().requestFocus();
    	getRowCount();
    }
    
    private void closeWindow() {
    	frame.closeWindow();
    }
    
    private void setData() {
        int selectedRow = frame.getTablePanel().getTable().getSelectedRow();
        
        if (selectedRow != -1) {
        	RolDTO rol = tableModel.getEntityByRow(selectedRow);
        	frame.getFormPanel().setFormValue(rol);   
        }
    }

}
