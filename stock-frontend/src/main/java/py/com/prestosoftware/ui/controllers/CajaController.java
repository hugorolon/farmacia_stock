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

import py.com.prestosoftware.client.CajaApiClient;
import py.com.prestosoftware.client.EmpresaApiClient;
import py.com.prestosoftware.data.dtos.CajaDTO;
import py.com.prestosoftware.data.dtos.EmpresaDTO;
import py.com.prestosoftware.domain.validations.CajaValidator;
import py.com.prestosoftware.domain.validations.ValidationError;
import py.com.prestosoftware.ui.forms.CajaFrame;
import py.com.prestosoftware.ui.forms.CajaPanel;
import py.com.prestosoftware.ui.shared.AbstractFrameController;
import py.com.prestosoftware.ui.shared.TableSearchPanel;
import py.com.prestosoftware.ui.table.CajaTableModel;
import py.com.prestosoftware.ui.table.EmpresaComboBoxModel;
import py.com.prestosoftware.util.Notifications;

@Controller
public class CajaController extends AbstractFrameController {

    private CajaFrame frame;
    private CajaTableModel tableModel;
    private CajaApiClient apiClient;
    private CajaValidator validator;
    
    private EmpresaApiClient empresaApiClient;
    private EmpresaComboBoxModel empresaComboBoxModel;
   
    @Autowired
    public CajaController(CajaFrame frame, CajaTableModel tableModel, 
    		CajaApiClient apiClient, CajaValidator validator,
    		EmpresaApiClient empresaApiClient, EmpresaComboBoxModel empresaComboBoxModel) {
        this.frame = frame;
        this.tableModel = tableModel;
        this.apiClient = apiClient;
        this.validator = validator;
        this.empresaApiClient = empresaApiClient;
        this.empresaComboBoxModel = empresaComboBoxModel;
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
        loadCajas();
        loadEmpresas();
        showFrame();
        getMax();
    }

    private void loadCajas() {
        List<CajaDTO> cajas = apiClient.findAll();
        tableModel.clear();
        tableModel.addEntities(cajas);
    }
    
    private void loadEmpresas() {
        List<EmpresaDTO> empresas = empresaApiClient.findAll();
        empresaComboBoxModel.clear();
        empresaComboBoxModel.addElements(empresas);
    }
    
    private void getMax() {
    	long max = apiClient.getRowCount();
    	frame.getFormPanel().setNewCaja(max + 1);
    }
    
    private void findAll(String name) {
    	List<CajaDTO> cajas;
    	
    	if (name.isEmpty()) {
    		cajas = apiClient.findAll();
		} else {
			cajas = apiClient.findByNombre(name);		
		}
    	
        tableModel.clear();
        tableModel.addEntities(cajas);
    }

    private void save() {
        CajaPanel formPanel = frame.getFormPanel();
        CajaDTO caja = formPanel.getFormValue();
        Optional<ValidationError> errors = validator.validate(caja);
        if (errors.isPresent()) {
            ValidationError validationError = errors.get();
            Notifications.showFormValidationAlert(validationError.getMessage());
        } else {
            apiClient.save(caja);
            loadCajas();
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
        	CajaDTO caja = tableModel.getEntityByRow(selectedRow);
        	frame.getFormPanel().setFormValue(caja);   
        }
    }

}
