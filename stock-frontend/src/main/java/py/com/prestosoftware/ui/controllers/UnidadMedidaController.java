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

import py.com.prestosoftware.client.UnidadMedidaApiClient;
import py.com.prestosoftware.data.dtos.UnidadMedidaDTO;
import py.com.prestosoftware.domain.validations.UnidadMedidaValidator;
import py.com.prestosoftware.domain.validations.ValidationError;
import py.com.prestosoftware.ui.forms.UnidadMedidaFrame;
import py.com.prestosoftware.ui.forms.UnidadMedidaPanel;
import py.com.prestosoftware.ui.shared.AbstractFrameController;
import py.com.prestosoftware.ui.shared.TableSearchPanel;
import py.com.prestosoftware.ui.table.UnidadMedidaTableModel;
import py.com.prestosoftware.util.Notifications;

@Controller
public class UnidadMedidaController extends AbstractFrameController {

    private UnidadMedidaFrame frame;
    private UnidadMedidaTableModel tableModel;
    private UnidadMedidaApiClient service;
    private UnidadMedidaValidator validator;
   
    @Autowired
    public UnidadMedidaController(UnidadMedidaFrame frame, UnidadMedidaTableModel tableModel, 
    		UnidadMedidaApiClient service, UnidadMedidaValidator validator) {
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
        loadUnidadMedidas();
        showFrame();
        getMax();
    }

    private void loadUnidadMedidas() {
        List<UnidadMedidaDTO> umedidas = service.findAll();
        tableModel.clear();
        tableModel.addEntities(umedidas);
    }
    
    private void getMax() {
    	long max = service.getRowCount();
    	frame.getFormPanel().setNewUm(max + 1);
    }
    
    private void findAll(String name) {
    	List<UnidadMedidaDTO> umedidas;
    	
    	if (name.isEmpty()) {
    		umedidas = service.findAll();
		} else {
			umedidas = service.findByNombre(name);		
		}
    	
        tableModel.clear();
        tableModel.addEntities(umedidas);
    }

    private void save() {
        UnidadMedidaPanel formPanel = frame.getFormPanel();
        UnidadMedidaDTO umedida = formPanel.getFormValue();
        Optional<ValidationError> errors = validator.validate(umedida);
        if (errors.isPresent()) {
            ValidationError validationError = errors.get();
            Notifications.showFormValidationAlert(validationError.getMessage());
        } else {
            service.save(umedida);
            loadUnidadMedidas();
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
    	getMax();
    }
    
    private void closeWindow() {
    	frame.closeWindow();
    }
    
    private void setData() {
        int selectedRow = frame.getTablePanel().getTable().getSelectedRow();
        
        if (selectedRow != -1) {
        	UnidadMedidaDTO umedida = tableModel.getEntityByRow(selectedRow);
        	frame.getFormPanel().setFormValue(umedida);   
        }
    }

}