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
import py.com.prestosoftware.client.UsuarioRolApiClient;
import py.com.prestosoftware.data.dtos.RolDTO;
import py.com.prestosoftware.data.dtos.UsuarioDTO;
import py.com.prestosoftware.data.dtos.UsuarioRolDTO;
import py.com.prestosoftware.client.UsuarioApiClient;
import py.com.prestosoftware.domain.validations.UsuarioRolValidator;
import py.com.prestosoftware.domain.validations.ValidationError;
import py.com.prestosoftware.ui.forms.UsuarioRolFrame;
import py.com.prestosoftware.ui.forms.UsuarioRolPanel;
import py.com.prestosoftware.ui.shared.AbstractFrameController;
import py.com.prestosoftware.ui.shared.TableSearchPanel;
import py.com.prestosoftware.ui.table.RolComboBoxModel;
import py.com.prestosoftware.ui.table.UsuarioComboBoxModel;
import py.com.prestosoftware.ui.table.UsuarioRolTableModel;
import py.com.prestosoftware.util.Notifications;

@Controller
public class UsuarioRolController extends AbstractFrameController {

    private UsuarioRolFrame frame;
    private UsuarioRolTableModel tableModel;
    private UsuarioRolApiClient service;
    private UsuarioRolValidator validator;
    
    private RolApiClient rolService;
    private UsuarioApiClient usuarioService;
    private RolComboBoxModel rolComboBoxModel;
   
    @Autowired
    public UsuarioRolController(UsuarioRolFrame frame, UsuarioRolTableModel tableModel, UsuarioRolApiClient service, 
    		UsuarioRolValidator validator, RolApiClient rolService,  UsuarioApiClient usuarioService, RolComboBoxModel rolComboBoxModel) {
        this.frame = frame;
        this.tableModel = tableModel;
        this.service = service;
        this.validator = validator;
        this.rolService = rolService;
        this.usuarioService = usuarioService;
        this.rolComboBoxModel = rolComboBoxModel;
    }

    /**
     * @wbp.parser.entryPoint
     */
    @PostConstruct
    private void prepareListeners() {
        
        registerAction(frame.getFormPanel().getBtnGuardar(), (e) -> save());
        registerAction(frame.getFormPanel().getBtnCancelar(), (e) -> cleanInputs());
        registerAction(frame.getFormPanel().getBtnCerrar(), (e) -> closeWindow());
        
        registerSelectRow(frame.getTablePanel().getTable().getSelectionModel(), new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				setData();
			}
		});
    }

    /**
     * @wbp.parser.entryPoint
     */
    @Override
    public void prepareAndOpenFrame() {
        loadUsuarioRoles();
        loadRoles();
        loadUsuarios();
        showFrame();
        getMax();
    }

    private void loadUsuarioRoles() {
        List<UsuarioRolDTO> usuarioRoles = service.findAll();
        tableModel.clear();
        tableModel.addEntities(usuarioRoles);
    }
    
    private void loadRoles() {
        List<RolDTO> roles = rolService.findAll();
        rolComboBoxModel.clear();
        rolComboBoxModel.addElements(roles);
    }
    
    private void loadUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.findAll();
       // usuComboBoxModel.addElements(usuarios);
    }
    private void getMax() {
    	long max = service.getRowCount();
    	frame.getFormPanel().setNewUsuarioRol(max + 1);
    }
    
    private void findAll(String name) {
    	List<UsuarioRolDTO> usuarioRoles;
    	
    	if (name.isEmpty()) {
    		usuarioRoles = service.findAll();
		} else {
			usuarioRoles = service.findByUsuarioRol(name);		
		}
    	
        tableModel.clear();
        tableModel.addEntities(usuarioRoles);
    }

    private void save() {
        UsuarioRolPanel formPanel = frame.getFormPanel();
        UsuarioRolDTO usuarioRol = formPanel.getFormValue();
        Optional<ValidationError> errors = validator.validate(usuarioRol);
        if (errors.isPresent()) {
            ValidationError validationError = errors.get();
            Notifications.showFormValidationAlert(validationError.getMessage());
        } else {
            service.save(usuarioRol);
           loadUsuarioRoles();
           cleanInputs();
        }
    }
    
    private void showFrame() {
        frame.setVisible(true);
        //frame.getFormPanel().getCbUsu().requestFocus();
    }

    private void cleanInputs() {
    	frame.getFormPanel().clearForm();
    	//frame.getFormPanel().getCbUsu().requestFocus();
    	getMax();
    }
    
    private void closeWindow() {
    	frame.closeWindow();
    }
    
    private void setData() {
        int selectedRow = frame.getTablePanel().getTable().getSelectedRow();
        
        if (selectedRow != -1) {
        	UsuarioRolDTO usuarioRol = tableModel.getEntityByRow(selectedRow);
        	frame.getFormPanel().setFormValue(usuarioRol);   
        }
    }

}
