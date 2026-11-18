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

import py.com.prestosoftware.client.ImpuestoApiClient;
import py.com.prestosoftware.data.dtos.ImpuestoDTO;
import py.com.prestosoftware.domain.validations.ImpuestoValidator;
import py.com.prestosoftware.domain.validations.ValidationError;
import py.com.prestosoftware.ui.forms.ImpuestoFrame;
import py.com.prestosoftware.ui.forms.ImpuestoPanel;
import py.com.prestosoftware.ui.shared.AbstractFrameController;
import py.com.prestosoftware.ui.shared.TableSearchPanel;
import py.com.prestosoftware.ui.table.ImpuestoTableModel;
import py.com.prestosoftware.util.Notifications;

@Controller
public class ImpuestoController extends AbstractFrameController {

    private ImpuestoFrame frame;
    private ImpuestoTableModel tableModel;
    private ImpuestoApiClient service;
    private ImpuestoValidator validator;
   
    @Autowired
    public ImpuestoController(ImpuestoFrame frame, ImpuestoTableModel tableModel, 
					    	  ImpuestoApiClient service, ImpuestoValidator validator) {
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
        loadImpuestos();
        showFrame();
        getRowCount();
    }

    private void loadImpuestos() {
        List<ImpuestoDTO> impuestos = service.findAll();
        tableModel.clear();
        tableModel.addEntities(impuestos);
    }
    
    private void getRowCount() {
    	long id = service.getRowCount();
    	frame.getFormPanel().setNewImpuesto(id + 1);
    }
    
    private void findAll(String name) {
    	List<ImpuestoDTO> impuestos;
    	
    	if (name.isEmpty()) {
    		impuestos = service.findAll();
		} else {
			impuestos = service.findByNombre(name);		
		}
    	
        tableModel.clear();
        tableModel.addEntities(impuestos);
    }

    private void save() {
        ImpuestoPanel formPanel = frame.getFormPanel();
        ImpuestoDTO impuesto = formPanel.getFormValue();
        Optional<ValidationError> errors = validator.validate(impuesto);
        if (errors.isPresent()) {
            ValidationError validationError = errors.get();
            Notifications.showFormValidationAlert(validationError.getMessage());
        } else {
            service.save(impuesto);
            loadImpuestos();
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
        	ImpuestoDTO impuesto = tableModel.getEntityByRow(selectedRow);
        	frame.getFormPanel().setFormValue(impuesto);   
        }
    }

}