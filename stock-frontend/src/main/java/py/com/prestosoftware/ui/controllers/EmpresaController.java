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
import py.com.prestosoftware.client.EmpresaApiClient;
import py.com.prestosoftware.data.dtos.EmpresaDTO;
import py.com.prestosoftware.domain.validations.EmpresaValidator;
import py.com.prestosoftware.domain.validations.ValidationError;
import py.com.prestosoftware.ui.forms.EmpresaFrame;
import py.com.prestosoftware.ui.forms.EmpresaPanel;
import py.com.prestosoftware.ui.shared.AbstractFrameController;
import py.com.prestosoftware.ui.shared.TableSearchPanel;
import py.com.prestosoftware.ui.table.EmpresaTableModel;
import py.com.prestosoftware.util.Notifications;

@Controller
public class EmpresaController extends AbstractFrameController {

    private EmpresaFrame frame;
    private EmpresaTableModel tableModel;
    private EmpresaApiClient apiClient;
    private EmpresaValidator validator;
   
    @Autowired
    public EmpresaController(EmpresaFrame frame, EmpresaTableModel tableModel, 
    						 EmpresaApiClient apiClient, EmpresaValidator validator) {
        this.frame = frame;
        this.tableModel = tableModel;
        this.apiClient = apiClient;
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
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					save();
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
        loadEmpresas();
        showFrame();
        getMax();
    }

    private void loadEmpresas() {
        List<EmpresaDTO> empresas = apiClient.findAll();
        tableModel.clear();
        tableModel.addEntities(empresas);
    }
    
    private void getMax() {
    	long max = apiClient.getRowCount();
    	frame.getFormPanel().setNewEmpresa(max + 1);
    }
    
    private void findAll(String name) {
    	List<EmpresaDTO> empresas;
    	
    	if (name.isEmpty()) {
    		empresas = apiClient.findAll();
		} else {
			empresas = apiClient.findByNombre(name);
		}
    	
        tableModel.clear();
        tableModel.addEntities(empresas);
    }

    private void save() {
        EmpresaPanel formPanel = frame.getFormPanel();
        EmpresaDTO empresa = formPanel.getFormValue();
        Optional<ValidationError> errors = validator.validate(empresa);
        if (errors.isPresent()) {
            ValidationError validationError = errors.get();
            Notifications.showFormValidationAlert(validationError.getMessage());
        } else {
            apiClient.save(empresa);
            loadEmpresas();
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
        	EmpresaDTO empresa = tableModel.getEntityByRow(selectedRow);
        	frame.getFormPanel().setFormValue(empresa);   
        }
    }

}
