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
import py.com.prestosoftware.client.ClienteApiClient;
import py.com.prestosoftware.client.EmpresaApiClient;
import py.com.prestosoftware.client.ListaPrecioApiClient;
import py.com.prestosoftware.data.dtos.CiudadDTO;
import py.com.prestosoftware.data.dtos.ClienteDTO;
import py.com.prestosoftware.data.dtos.EmpresaDTO;
import py.com.prestosoftware.data.dtos.ListaPrecioDTO;
import py.com.prestosoftware.domain.validations.ClienteValidator;
import py.com.prestosoftware.domain.validations.ValidationError;
import py.com.prestosoftware.ui.forms.ClientePanel;
import py.com.prestosoftware.ui.shared.AbstractFrameController;
import py.com.prestosoftware.ui.table.CiudadComboBoxModel;
import py.com.prestosoftware.ui.table.ClientTableModel;
import py.com.prestosoftware.ui.table.EmpresaComboBoxModel;
import py.com.prestosoftware.ui.table.ListaPrecioComboBoxModel;
import py.com.prestosoftware.util.Notifications;

@Controller
public class ClienteController extends AbstractFrameController {

	private ClientePanel clientFrame;
	private ClientTableModel clientTableModel;
	private ClienteApiClient clientApiClient;
	private ClienteValidator clientValidator;

	private CiudadApiClient ciudadApiClient;
	private EmpresaApiClient empresaApiClient;
	private ListaPrecioApiClient listaPrecioApiClient;
	private CiudadComboBoxModel ciudadComboBoxModel;
	private EmpresaComboBoxModel empresaComboBoxModel;
	private ListaPrecioComboBoxModel listaPrecioComboBoxModel;

	@Autowired
	public ClienteController(ClientePanel clientFrame, ClientTableModel clientTableModel, ClienteApiClient clientApiClient,
			ClienteValidator clientValidator, CiudadApiClient ciudadApiClient, EmpresaApiClient empresaApiClient,
			ListaPrecioApiClient listaPrecioApiClient, CiudadComboBoxModel ciudadComboBoxModel,
			EmpresaComboBoxModel empresaComboBoxModel, ListaPrecioComboBoxModel listaPrecioComboBoxModel) {
		this.clientFrame = clientFrame;
		this.clientTableModel = clientTableModel;
		this.clientApiClient = clientApiClient;
		this.clientValidator = clientValidator;
		this.ciudadApiClient = ciudadApiClient;
		this.empresaApiClient = empresaApiClient;
		this.listaPrecioApiClient = listaPrecioApiClient;
		this.ciudadComboBoxModel = ciudadComboBoxModel;
		this.empresaComboBoxModel = empresaComboBoxModel;
		this.listaPrecioComboBoxModel = listaPrecioComboBoxModel;
	}

	@PostConstruct
	private void prepareListeners() {
		registerAction(clientFrame.getBtnGuardar(), (e) -> saveClient());
		registerAction(clientFrame.getBtnCancelar(), (e) -> cleanInputs());
		registerAction(clientFrame.getBtnCerrar(), (e) -> closeWindow());
		registerAction(clientFrame.getBtnNuevo(), (e) -> cleanInputs());

		registerAction(clientFrame.getBtnBuscar(), (e) -> findClients(clientFrame.getTfBuscador().getText()));
		registerKeyEvent(clientFrame.getTfBuscador(), new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String name = clientFrame.getTfBuscador().getText();
					findClients(name);
				}
			}
		});

		registerSelectRow(clientFrame.getTbCliente().getSelectionModel(), new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				setData();
			}
		});

		registerButtonEvent(clientFrame.getBtnGuardar(), new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					saveClient();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	}

	public ClientTableModel getClientTableModel() {
		return clientTableModel;
	}

	@Override
	public void prepareAndOpenFrame() {
		loadClients();
		loadCiudades();
		loadEmpresas();
		loadListaPrecios();
		showClientsFrame();
	}

	public void addNewCliente() {
		long id = clientApiClient.addNewClient();
		clientFrame.setNewClient(id);
	}

	private void loadClients() {
		List<ClienteDTO> clientes = clientApiClient.findAll();
		clientTableModel.clear();
		clientTableModel.addEntities(clientes);
	}

	private void loadCiudades() {
		List<CiudadDTO> ciudades = ciudadApiClient.findAll();
		ciudadComboBoxModel.clear();
		ciudadComboBoxModel.addElements(ciudades);
	}

	private void loadEmpresas() {
		List<EmpresaDTO> empresas = empresaApiClient.findAll();
		empresaComboBoxModel.clear();
		empresaComboBoxModel.addElements(empresas);
	}

	private void loadListaPrecios() {
		List<ListaPrecioDTO> listaPrecios = listaPrecioApiClient.findAll();
		listaPrecioComboBoxModel.clear();
		listaPrecioComboBoxModel.addElements(listaPrecios);
	}

	private void findClients(String name) {
		List<ClienteDTO> clientes;

		if (name.isEmpty()) {
			clientes = clientApiClient.findAll();
		} else {
			clientes = clientApiClient.findByNombre(name);
		}

		clientTableModel.clear();
		clientTableModel.addEntities(clientes);
	}

	private void showClientsFrame() {
		clientFrame.setVisible(true);
		clientFrame.getTfNombre().requestFocus();
	}

	private void saveClient() {
		ClienteDTO client = clientFrame.getClientFromForm();
		Optional<ValidationError> errors = clientValidator.validate(client);
		if (errors.isPresent()) {
			ValidationError validationError = errors.get();
			Notifications.showFormValidationAlert(validationError.getMessage());
		} else {
			try {
				clientApiClient.save(client);
				Notifications.showAlert("Cliente guardado correctamente");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Notifications.showAlert("Error al guardar el cliente: " + e.getMessage());
			}
			//int i= 10 /0 ;
			//System.out.println("div cero");
			cleanInputs();
			loadClients();
		}
	}

	private void cleanInputs() {
		clientFrame.clearForm();
		clientFrame.getTfNombre().requestFocus();
		addNewCliente();
	}

	private void closeWindow() {
		clientFrame.dispose();
	}

	private void setData() {
		int[] selectedRow = clientFrame.getTbCliente().getSelectedRows();
		if (selectedRow.length > 0) {
			Long selectedId = (Long) clientFrame.getTbCliente().getValueAt(selectedRow[0], 0);

			ClienteDTO cliente = clientTableModel.getEntities().stream()
					.filter(customer -> customer.getId().equals(selectedId.longValue())).findAny().orElse(null);

			clientFrame.setClientForm(cliente);
		}
	}

}
