package py.com.prestosoftware.ui.transactions;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.JTextComponent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.com.prestosoftware.client.AperturaCierreCajaApiClient;
import py.com.prestosoftware.client.CajaApiClient;
import py.com.prestosoftware.client.ClienteApiClient;
import py.com.prestosoftware.client.ItemCuentaAPagarApiClient;
import py.com.prestosoftware.client.ItemPagarProveedorApiClient;
import py.com.prestosoftware.client.MovimientoCajaApiClient;
import py.com.prestosoftware.client.MovimientoEgresoApiClient;
import py.com.prestosoftware.client.MovimientoItemEgresoApiClient;
import py.com.prestosoftware.client.PagarProveedorApiClient;
import py.com.prestosoftware.client.ProcesoPagoProveedoresApiClient;
import py.com.prestosoftware.client.ProveedorApiClient;
import py.com.prestosoftware.data.dtos.AperturaCierreCajaDTO;
import py.com.prestosoftware.data.dtos.CajaDTO;
import py.com.prestosoftware.data.dtos.ClienteDTO;
import py.com.prestosoftware.data.dtos.ItemPagarProveedorDTO;
import py.com.prestosoftware.data.dtos.MonedaDTO;
import py.com.prestosoftware.data.dtos.MovimientoCajaDTO;
import py.com.prestosoftware.data.dtos.MovimientoEgresoDTO;
import py.com.prestosoftware.data.dtos.MovimientoItemEgresoDTO;
import py.com.prestosoftware.data.dtos.PagarProveedorDTO;
import py.com.prestosoftware.data.dtos.ProcesoPagoProveedoresDTO;
import py.com.prestosoftware.data.dtos.ProveedorDTO;
import py.com.prestosoftware.ui.helpers.CellRendererOperaciones;
import py.com.prestosoftware.ui.helpers.FormatearValor;
import py.com.prestosoftware.ui.helpers.GlobalVars;
import py.com.prestosoftware.ui.search.PagarProveedorDialog;
import py.com.prestosoftware.ui.search.PagarProveedorInterfaz;
import py.com.prestosoftware.ui.search.ConsultaProveedor;
import py.com.prestosoftware.ui.search.ProveedorInterfaz;
import py.com.prestosoftware.ui.table.DetalleCuentaProveedorTableModel;
import py.com.prestosoftware.ui.viewmodel.DetalleAPagarProveedorView;
import py.com.prestosoftware.util.Notifications;

@Service
public class PagarProveedorPanel extends JDialog implements PagarProveedorInterfaz, ProveedorInterfaz {

	private static final long serialVersionUID = 1L;

	private static final int COBRO_CLIENTE_CODE = 1;
	private static final int ENTIDAD_CODE = 2;
	private JTable tbDetallePagarProveedor;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnCerrar;
	private JButton btnVer;
	private JLabel lblSituacion;
	private JLabel lblNota;
	private JTextField tfNota;
	private JTextField tfFecha;
	private JTextField tfDocumento;
	private JTextField tfEntidad;
	private JLabel lblNroRecibo;
	private JLabel lblFecha;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JButton btnAnula;
	private JTextField tfNombreEntidad;
	private JCheckBox chkCobraTodos;
	private JTextField tfSaldo;
	private JTextField tfDescuentos;
	private JTextField tfRecargos;
	private JTextField tfMontoACobrar;
	private JTextField tfObs;
	private JTextField tfTotalACobrar;
	private JLabel lblMontoACobrar;

	private DetalleCuentaProveedorTableModel itemTableModel;
	@Autowired
	private CajaApiClient cajaService;
	@Autowired
	private AperturaCierreCajaApiClient movCajaApiClient;
	@Autowired
	private PagarProveedorApiClient pagarProveedorApiClient;
	@Autowired
	private ProcesoPagoProveedoresApiClient procesoPagarProveedorApiClient;
	@Autowired
	private ItemPagarProveedorApiClient itemPagarProveedorApiClient;
	@Autowired
	private ItemCuentaAPagarApiClient itemCuentaAPagarApiClient;
	@Autowired
	private MovimientoEgresoApiClient movimientoEgresoApiClient;
	@Autowired
	private MovimientoItemEgresoApiClient movimientoItemEgresoApiClient;
	@Autowired
	private ProveedorApiClient proveedorService;
	@Autowired
	private MovimientoCajaApiClient pagoService;
	private PagarProveedorDTO pagarProveedorSeleccionado;
	private Double totalCalculado;
	private PagarProveedorDialog pagarProveedorDialog;
	private ConsultaProveedor proveedorDialog;

	@Autowired
	public PagarProveedorPanel(PagarProveedorApiClient pagarProveedorApiClient,
			DetalleCuentaProveedorTableModel itemTableModel, ItemPagarProveedorApiClient itemPagarProveedorApiClient,
			ItemCuentaAPagarApiClient itemCuentaAPagarApiClient, ClienteApiClient clienteService, CajaApiClient cajaService,
			PagarProveedorDialog pagarProveedorDialog, MovimientoEgresoApiClient movimientoEgresoApiClient,
			MovimientoItemEgresoApiClient movimientoItemEgresoApiClient, ConsultaProveedor proveedorDialog,
			MovimientoCajaApiClient pagoService, AperturaCierreCajaApiClient movCajaApiClient,
			ProcesoPagoProveedoresApiClient procesoPagarProveedorApiClient, ProveedorApiClient proveedorService) {
		this.pagarProveedorApiClient = pagarProveedorApiClient;
		this.itemTableModel = itemTableModel;
		this.cajaService = cajaService;
		this.pagarProveedorDialog = pagarProveedorDialog;
		this.proveedorService = proveedorService;
		this.pagoService = pagoService;
		this.movCajaApiClient = movCajaApiClient;
		this.proveedorDialog = proveedorDialog;
		this.procesoPagarProveedorApiClient = procesoPagarProveedorApiClient;
		this.movimientoEgresoApiClient = movimientoEgresoApiClient;
		this.movimientoItemEgresoApiClient = movimientoItemEgresoApiClient;
		this.itemPagarProveedorApiClient = itemPagarProveedorApiClient;
		this.itemCuentaAPagarApiClient = itemCuentaAPagarApiClient;

		setSize(866, 488);
		setTitle("PAGAR PROVEEDOR");
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);

		initComponents();
	}

	@SuppressWarnings("serial")
	private void initComponents() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 76, 844, 330);
		getContentPane().add(tabbedPane);

		JPanel pnlProducto = new JPanel();
		tabbedPane.addTab("PAGAR A PROVEEDORES", null, pnlProducto, null);
		pnlProducto.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 11, 823, 64);
		pnlProducto.add(panel_1);
		panel_1.setLayout(null);

		tfFecha = new JTextField();
		tfFecha.setEditable(false);
		tfFecha.setBounds(10, 32, 77, 30);
		panel_1.add(tfFecha);
		tfFecha.setColumns(10);

		tfDocumento = new JTextField();
		tfDocumento.setBounds(97, 32, 86, 30);
		panel_1.add(tfDocumento);
		tfDocumento.setColumns(10);

		tfEntidad = new JTextField();
		tfEntidad.setHorizontalAlignment(SwingConstants.RIGHT);
		tfEntidad.setBounds(193, 32, 63, 30);
		panel_1.add(tfEntidad);
		tfEntidad.setFont(new Font("Arial", Font.PLAIN, 14));
		tfEntidad.setColumns(10);
		tfEntidad.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tfEntidad.selectAll();
			}
		});
		tfEntidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_F4) {
					showDialog(ENTIDAD_CODE);
				} else if (e.getKeyCode() == KeyEvent.VK_F11) {
					abandonarNota();
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!tfEntidad.getText().isEmpty()) {
						findEntidad(tfEntidad.getText());
					} else {
						showDialog(ENTIDAD_CODE);
					}
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					chkCobraTodos.requestFocus();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// Util.validateNumero(e);
			}
		});

		JLabel lblEntidad = new JLabel("PROVEEDOR");
		lblEntidad.setBounds(193, 0, 63, 30);
		panel_1.add(lblEntidad);

		lblNroRecibo = new JLabel("NRO. RECIBO");
		lblNroRecibo.setBounds(97, 0, 110, 30);
		panel_1.add(lblNroRecibo);

		lblFecha = new JLabel("FECHA");
		lblFecha.setBounds(10, 0, 63, 30);
		panel_1.add(lblFecha);

		tfNombreEntidad = new JTextField();
		tfNombreEntidad.setEditable(false);
		tfNombreEntidad.setHorizontalAlignment(SwingConstants.RIGHT);
		tfNombreEntidad.setFont(new Font("Arial", Font.PLAIN, 14));
		tfNombreEntidad.setColumns(10);
		tfNombreEntidad.setBounds(261, 32, 276, 30);
		panel_1.add(tfNombreEntidad);

		chkCobraTodos = new JCheckBox("PAGAR TODOS");
		chkCobraTodos.setBounds(583, 36, 128, 23);
		chkCobraTodos.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (chkCobraTodos.isSelected()) {
					cargaSaldo();
					calculateItem();
					itemTableModel.fireTableDataChanged();
					tfSaldo.setText(tfTotalACobrar.getText());
					tfMontoACobrar.setText(tfTotalACobrar.getText());
				} else {
					descargaSaldo();
					itemTableModel.fireTableDataChanged();
					tfSaldo.setText("0");
					tfMontoACobrar.setText("0");
				}

			}

			private void cargaSaldo() {
				for (DetalleAPagarProveedorView ie : itemTableModel.getEntities()) {
					ie.setCobro(ie.getIcp_monto1());
				}
			}

			private void descargaSaldo() {
				for (DetalleAPagarProveedorView ie : itemTableModel.getEntities()) {
					ie.setCobro(0d);
				}
			}
		});
		panel_1.add(chkCobraTodos);

		panel_2 = new JPanel();
		panel_2.setBounds(6, 97, 823, 207);
		pnlProducto.add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollDetallePagarProveedors = new JScrollPane();
		scrollDetallePagarProveedors.setBounds(10, 11, 803, 105);
		panel_2.add(scrollDetallePagarProveedors);

		tbDetallePagarProveedor = new JTable(itemTableModel) {
			public boolean isCellEditable(int fila, int columna) {
				if (columna == 8)
					return true;
				return false;
			}

			public void changeSelection(int row, int column, boolean toggle, boolean extend) {
				super.changeSelection(row, column, toggle, extend);

				if (editCellAt(row, column)) {
					Component editor = getEditorComponent();
					editor.requestFocusInWindow();
					((JTextComponent) editor).selectAll();
				}
			}
		};

		tbDetallePagarProveedor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbDetallePagarProveedor.setDefaultRenderer(Object.class, new CellRendererOperaciones());
		tbDetallePagarProveedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getItemSelected();
			}
		});
		tbDetallePagarProveedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) {
					getItemSelected();
					calculateItem();
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					calculateItem();
					// btnGuardar.requestFocus();
				} else if (e.getKeyCode() == KeyEvent.VK_TAB) {
					calculateItem();
				}
				itemTableModel.fireTableDataChanged();
			}
		});

		tbDetallePagarProveedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getItemSelected();
				calculateItem();
			}
		});

		tbDetallePagarProveedor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				calculateItem();
			}
		});
		tbDetallePagarProveedor.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent arg0) {
				calculateItem();
			}

			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		scrollDetallePagarProveedors.setViewportView(tbDetallePagarProveedor);

		JLabel lblTotalMontoIngreso = new JLabel("TOTALES");
		lblTotalMontoIngreso.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotalMontoIngreso.setBounds(394, 115, 91, 30);
		panel_2.add(lblTotalMontoIngreso);

		tfSaldo = new JTextField();
		tfSaldo.setEditable(false);
		tfSaldo.setText("");
		tfSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
		tfSaldo.setColumns(10);
		tfSaldo.setBounds(679, 115, 134, 30);
		panel_2.add(tfSaldo);

		JLabel lblDescuentos = new JLabel("DESCUENTOS");
		lblDescuentos.setBounds(116, 156, 79, 14);
		panel_2.add(lblDescuentos);

		tfDescuentos = new JTextField();
		tfDescuentos.setHorizontalAlignment(SwingConstants.RIGHT);
		tfDescuentos.setBounds(116, 171, 163, 30);
		tfDescuentos.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tfDescuentos.selectAll();
			}
		});
		tfDescuentos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!tfDescuentos.getText().isEmpty() && !tfDescuentos.getText().toString().equalsIgnoreCase("0")) {
						tfRecargos.setText("0");
						calculateMontoTotal(0);
					} else {
						Notifications.showAlert("Digite el monto a descontar");
						tfDescuentos.requestFocus();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					tfRecargos.requestFocus();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
		panel_2.add(tfDescuentos);
		tfDescuentos.setColumns(10);

		tfRecargos = new JTextField();
		tfRecargos.setHorizontalAlignment(SwingConstants.RIGHT);
		tfRecargos.setColumns(10);
		tfRecargos.setBounds(289, 171, 163, 30);
		tfRecargos.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tfRecargos.selectAll();
			}
		});
		tfRecargos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!tfRecargos.getText().isEmpty() && !tfRecargos.getText().toString().equalsIgnoreCase("0")) {
						calculateMontoTotal(1);
						tfDescuentos.setText("0");
					} else {
						Notifications.showAlert("Digite el monto a recargar");
						tfRecargos.requestFocus();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					tfRecargos.requestFocus();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});
		panel_2.add(tfRecargos);

		JLabel lblRecargos = new JLabel("RECARGOS");
		lblRecargos.setBounds(289, 156, 79, 14);
		panel_2.add(lblRecargos);

		tfMontoACobrar = new JTextField();
		tfMontoACobrar.setEditable(false);
		tfMontoACobrar.setHorizontalAlignment(SwingConstants.RIGHT);
		tfMontoACobrar.setColumns(10);
		tfMontoACobrar.setBounds(460, 171, 163, 30);
		panel_2.add(tfMontoACobrar);

		lblMontoACobrar = new JLabel("MONTO A PAGAR");
		lblMontoACobrar.setBounds(460, 156, 125, 14);
		panel_2.add(lblMontoACobrar);

		tfObs = new JTextField();
		tfObs.setHorizontalAlignment(SwingConstants.LEFT);
		tfObs.setColumns(10);
		tfObs.setBounds(633, 171, 180, 30);
		panel_2.add(tfObs);

		JLabel lblOBS = new JLabel("OBS.");
		lblOBS.setBounds(633, 156, 79, 14);
		panel_2.add(lblOBS);

		tfTotalACobrar = new JTextField();
		tfTotalACobrar.setText("");
		tfTotalACobrar.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTotalACobrar.setEditable(false);
		tfTotalACobrar.setColumns(10);
		tfTotalACobrar.setBounds(544, 115, 134, 30);
		panel_2.add(tfTotalACobrar);

		lblNewLabel = new JLabel("DETALLES DE LA CUENTA CON EL PROVEEDOR");
		lblNewLabel.setBounds(6, 75, 253, 27);
		pnlProducto.add(lblNewLabel);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Seleccione", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(6, 6, 844, 69);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);

		JPanel pnlCliente = new JPanel();
		pnlCliente.setBounds(6, 18, 828, 45);
		panel_3.add(pnlCliente);
		pnlCliente.setLayout(null);

		lblNota = new JLabel("Nota:");
		lblNota.setBounds(6, 7, 27, 30);
		pnlCliente.add(lblNota);

		tfNota = new JTextField();
		tfNota.setEditable(false);
		tfNota.setColumns(10);
		tfNota.setBounds(33, 7, 51, 30);
		pnlCliente.add(tfNota);

		btnVer = new JButton("VER");
		btnVer.setFont(new Font("Dialog", Font.BOLD, 18));
		btnVer.setBounds(90, 7, 21, 30);
		pnlCliente.add(btnVer);
		btnVer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					showDialog(COBRO_CLIENTE_CODE);
				}
			}
		});
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDialog(COBRO_CLIENTE_CODE);
			}
		});

		lblSituacion = new JLabel("Nuevo");
		lblSituacion.setBounds(113, 15, 59, 14);
		pnlCliente.add(lblSituacion);

		JPanel panel = new JPanel();
		panel.setBounds(6, 408, 844, 35);
		getContentPane().add(panel);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					save();
				} else {
					if (e.getKeyCode() == KeyEvent.VK_F4) {
						save();
					} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						abandonarNota();
					}
				}

			}
		});
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});
		panel.add(btnGuardar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					clearForm();
					newMov();
				}
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearForm();
				newMov();
			}
		});

		btnAnula = new JButton("Eliminar");
		btnAnula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				elimina();
			}
		});
		panel.add(btnAnula);
		panel.add(btnCancelar);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				dispose();
			}
		});
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnCerrar);

		btnAnula.setVisible(false);
		btnGuardar.setVisible(true);

		TableCellRenderer render = new TableCellRenderer() {

			public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,

					boolean hasFocus, int row, int column) {

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				DecimalFormat formatea = new DecimalFormat("###,###");
				String valor = "";
				if (column == 2 || column == 4)
					valor = sdf.format(value);
				else if (column == 7)
					valor = formatea.format(value);
				else
					valor = value.toString();

				JLabel lbl = new JLabel(valor);

				// lbl.setHorizontalAlignment(SwingConstants.CENTER); //alina a laizquierda
				if (column == 2 || column == 4) { // color de fondo
					// lbl.setOpaque(true);
					lbl.setHorizontalAlignment(SwingConstants.CENTER); // alina a laizquierda
				}

				if (column == 7) { // color de fondo
					// lbl.setOpaque(true);
					lbl.setHorizontalAlignment(SwingConstants.RIGHT); // alina a laizquierda
					lbl.setForeground(Color.BLUE); // fuente azul
					lbl.setBackground(Color.GRAY);
				}
				return lbl;
			}

		};
		tbDetallePagarProveedor.getColumnModel().getColumn(0).setPreferredWidth(100);
		tbDetallePagarProveedor.getColumnModel().getColumn(2).setCellRenderer(render);
		tbDetallePagarProveedor.getColumnModel().getColumn(4).setCellRenderer(render);
		// tbDetallePagarProveedor.getColumnModel().getColumn(7).setCellRenderer(render);
		clearForm();
		// newMov();

	}

	protected void elimina() {
		btnAnula.setVisible(false);
		btnGuardar.setVisible(true);
		this.getPagarProveedorSeleccionado().setPprSituacion(1);
		eliminaPagarProveedor();
		clearForm();
		newMov();
	}

	public void setCliente(Optional<ClienteDTO> cliente) {
		if (cliente.isPresent()) {
			tfNombreEntidad.setText(cliente.get().getNombre());
		}
	}

	private void getItemSelected() {
		int selectedRow = tbDetallePagarProveedor.getSelectedRow();

		if (selectedRow != -1) {
			/*
			 * DetallePagarProveedorView item =
			 * itemTableModel.g.getEntityByRow(selectedRow);
			 * tfIngresoID.setText(String.valueOf(item.getMiiIngreso()));
			 * tfMontoIngreso.setText(FormatearValor.doubleAString(item.getMiiMonto()));
			 * Optional<Ingreso> ingresoSeleccionado
			 * =ingresoService.findById(Long.valueOf(item.getMiiIngreso()));
			 * tfDescripcionIngreso.setText(String.valueOf(ingresoSeleccionado.get().
			 * getIngDescripcion()));
			 */
		}
	}

	public PagarProveedorDTO getFormValue() {
		try {
			PagarProveedorDTO t = new PagarProveedorDTO();
			t.setFecha(new Date());
			t.setHora(new Date());
			t.setPprSituacion(0);
			t.setPprDocumento(tfDocumento.getText());
			t.setPprTipoEntidad(3);
			t.setPprEntidad(Integer.valueOf(tfEntidad.getText()));
			t.setPprNumero(Integer.valueOf(tfNota.getText()));
			t.setPprValor(totalCalculado);
			Double descuento = FormatearValor.desformatearValor(tfDescuentos.getText());
			Double interes = FormatearValor.desformatearValor(tfDescuentos.getText());
			t.setPprMonto(totalCalculado - descuento + interes);
			t.setPprDescuento(descuento);
			t.setPprRecargo(interes);
			return t;
		} catch (Exception e) {
			throw new IllegalArgumentException("No se pudo construir el pago a proveedor", e);
		}
	}

	public void clearItem() {

	}

	public void clearForm() {
		tfFecha.setText("");
		tfDocumento.setText("");
		tfEntidad.setText("");
		tfNombreEntidad.setText("");
		tfTotalACobrar.setText("0");
		tfMontoACobrar.setText("0");
		tfDescuentos.setText("0");
		tfRecargos.setText("0");
		tfSaldo.setText("0");
		tfObs.setText("");
		itemTableModel.clear();
		itemTableModel.fireTableDataChanged();
	}

	private void showDialog(int code) {
		switch (code) {
		case COBRO_CLIENTE_CODE:
			pagarProveedorDialog.setInterfaz(this);
			pagarProveedorDialog.loadPagarProveedors("");
			pagarProveedorDialog.setVisible(true);
			break;
		case ENTIDAD_CODE:
			proveedorDialog.setInterfaz(this);
			proveedorDialog.getProveedores("");
			proveedorDialog.setVisible(true);
			break;
		default:
			break;
		}
	}

	private void save() {
		Integer respuesta = JOptionPane.showConfirmDialog(null, "CONFIRMA");
		if (respuesta == 0) {
			try {
				if (validateCabezera() && validateItems(itemTableModel.getEntities())) {
					// Cobro clientes
					PagarProveedorDTO t = getFormValue();
					PagarProveedorDTO v = pagarProveedorApiClient.save(t);
					if (v != null) {
						// detalle cobro cliente
						List<ItemPagarProveedorDTO> detalles = new ArrayList<ItemPagarProveedorDTO>();
						for (DetalleAPagarProveedorView item : itemTableModel.getEntities()) {
							if (item.getCobro().doubleValue() > 0) {
								ItemPagarProveedorDTO itemPagarProveedor = new ItemPagarProveedorDTO();
								itemPagarProveedor.setIppNumero(v.getPprNumero());
								itemPagarProveedor.setIppMonto(item.getCobro());
								itemPagarProveedor.setIppSecuenciaCuenta(item.getIcp_Secuencia());
								if (item.getCobro().doubleValue() + item.getPagado().doubleValue() == item
										.getIcp_monto1().doubleValue()) {
									itemCuentaAPagarApiClient.cambiaEstadoSituacion(1,
											Long.valueOf(item.getIcp_Secuencia()));
								}
								detalles.add(itemPagarProveedor);
							}
						}
						itemPagarProveedorApiClient.saveDetalles(detalles);

						// MovimientoEgreso
						MovimientoEgresoDTO movimientoEgreso = new MovimientoEgresoDTO();
						movimientoEgreso.setFecha(new Date());
						movimientoEgreso.setHora(new Date());
						movimientoEgreso.setMegCaja(1);
						movimientoEgreso.setMegDocumento(v.getPprDocumento());
						movimientoEgreso.setMegEntidad(tfEntidad.getText());
						movimientoEgreso.setMegProceso(v.getPprNumero());
						movimientoEgreso.setMegTipoProceso(2);
						movimientoEgreso.setMegSituacion(0);
						movimientoEgreso.setMegTipoEntidad(8);
						MovimientoEgresoDTO movNew = movimientoEgresoApiClient.save(movimientoEgreso);

						MovimientoItemEgresoDTO movimientoItemEgreso = new MovimientoItemEgresoDTO();
						movimientoItemEgreso.setMieNumero(movNew.getMegNumero());
						movimientoItemEgreso.setMieMonto(totalCalculado);
						movimientoItemEgreso.setMieEgreso(2);
						movimientoItemEgreso.setMieDescripcion("Pago a Proveedores");
						movimientoItemEgresoApiClient.save(movimientoItemEgreso);

						// Proceso cobro cliente
						ProcesoPagoProveedoresDTO p = new ProcesoPagoProveedoresDTO();
						p.setPppPago(v.getPprNumero());
						p.setPppIngresoEgreso(2);
						p.setPppTipoproceso(32);
						p.setPppFlag(1);
						p.setPppProceso(movNew.getMegNumero());
						procesoPagarProveedorApiClient.save(p);

						// MovimientoCaja
						// Apertura de caja caso no este abierto
						lanzamientoCaja();
						// actualiza ingreso en movimiento Caja
						Optional<CajaDTO> caja = cajaService.findById(1L);
						MovimientoCajaDTO movCaja = new MovimientoCajaDTO();
						movCaja.setCaja(caja.get());
						movCaja.setFecha(new Date());
						movCaja.setMoneda(new MonedaDTO(1l));
						movCaja.setNotaNro(v.getPprNumero().toString());
						movCaja.setNotaReferencia(v.getPprNumero().toString());
						movCaja.setNotaValor(totalCalculado);
						movCaja.setPlanCuentaId(1);
						movCaja.setTipoOperacion("S");
						movCaja.setUsuario(GlobalVars.USER_ID);
						movCaja.setValorM01(totalCalculado);
						movCaja.setObs("Egreso en caja 01 ");
						movCaja.setSituacion("PAGADO");
						pagoService.save(movCaja);

					}
					Notifications.showAlert("Pago a Proveedor registrado con exito.!");
					newMov();
				}
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this,
						"No se pudo guardar el pago: " + e.getMessage(),
						"Error",
						JOptionPane.ERROR_MESSAGE);
			}

		} else {
			tfEntidad.requestFocus();
		}
	}

	private void lanzamientoCaja() {
		Optional<CajaDTO> caja = cajaService.findById(1l);
		if (caja.isPresent()) {
			CajaDTO ca = caja.get();
			Optional<AperturaCierreCajaDTO> movCaja = movCajaApiClient.findByCajaAndFechaApertura(ca, new Date());
			if (!movCaja.isPresent()) {
				AperturaCierreCajaDTO newMov = movCajaApiClient.save(new AperturaCierreCajaDTO(ca, new Date(), 0d));
			}
		}
	}

	private void eliminaPagarProveedor() {
		try {
			PagarProveedorDTO p = pagarProveedorApiClient
					.findById(Integer.valueOf(this.getPagarProveedorSeleccionado().getPprNumero().toString()));
			if (p != null) {
				List<ItemPagarProveedorDTO> listaItemCobro = itemPagarProveedorApiClient.findByIppNumero(
						Integer.valueOf(this.getPagarProveedorSeleccionado().getPprNumero().toString()));
				for (ItemPagarProveedorDTO itemPagarProveedor : listaItemCobro) {
					// if(item.getCobro().doubleValue()+item.getPagado().doubleValue()==item.getIca_monto1().doubleValue())
					// {
					itemCuentaAPagarApiClient.cambiaEstadoSituacion(0,
							Long.valueOf(itemPagarProveedor.getIppSecuenciaCuenta()));

					// ***Esta linea no removió***//
					itemPagarProveedorApiClient.remove(itemPagarProveedor);
				}
				pagarProveedorApiClient.remove(p);
				MovimientoEgresoDTO movimientoEgreso = movimientoEgresoApiClient.findByMegProceso(p.getPprNumero());
				List<MovimientoItemEgresoDTO> listaMovItemIngreso = movimientoItemEgresoApiClient
						.findByCabId(movimientoEgreso.getMegNumero());
				for (MovimientoItemEgresoDTO movimientoItemEgreso : listaMovItemIngreso) {
					movimientoItemEgresoApiClient.remove(movimientoItemEgreso);
				}
				movimientoEgresoApiClient.remove(movimientoEgreso);

				ProcesoPagoProveedoresDTO procesoPagarProveedor = procesoPagarProveedorApiClient
						.findByPppPago(p.getPprNumero());
				procesoPagarProveedorApiClient.remove(procesoPagarProveedor);

				MovimientoCajaDTO movCaja = pagoService.findByNotaNro(p.getPprNumero().toString());
				pagoService.remove(movCaja);
			}

		} catch (Exception e) {
			Notifications
					.showAlert("Ocurrió un error al eliminar el registro de pago al proveedor, verifique documento!!");
		}

	}

	private boolean validateCabezera() {
		if (totalCalculado.intValue() == 0) {
			Notifications.showAlert("Debe cargar por lo menos un item del cobro al cliente");
			return false;
		}
		return true;
	}

	private boolean validateItems(List<DetalleAPagarProveedorView> items) {
		if (items.isEmpty()) {
			Notifications.showAlert("Ningun Item Ingreso encontrado !");
			return false;
		}
		for (DetalleAPagarProveedorView e : items) {
//			Optional<Ingreso> ingreso = ingresoService.findById(Long.valueOf(e.getMiiIngreso()));
//
//			if (!ingreso.isPresent()) {
//				// verificar la cantidad
//					// sinStock = true;
//				Notifications.showAlert("Ningun Ingreso válido encontrado !");
//					return false;
//			}
		}
		return true;
	}

	private Boolean isValidItem() {
		if (tfEntidad.getText().isEmpty()) {
			Notifications.showAlert("Digite el Id del Cliente");
			tfEntidad.requestFocus();
			return false;
		}
		return true;
	}

	public void newMov() {
		clearForm();

		SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
		tfFecha.setText(sd.format(new Date()));
		// Optional<Usuario> usuario= usuarioService.findById(GlobalVars.USER_ID);
//		tfEntidad.setText(GlobalVars.USER_ID.toString());
//		tfNombreEntidad.setText(GlobalVars.USER);
		long max = pagarProveedorApiClient.getRowCount();
		tfNota.setText(String.valueOf(max + 1));
		lblSituacion.setText("NUEVO");
		btnGuardar.setVisible(true);
		btnAnula.setVisible(false);
		itemTableModel.clear();
		tfEntidad.requestFocus();
		habilitaEdicion();
	}

	@Override
	public void getEntity(PagarProveedorDTO pagarProveedor) {
		if (pagarProveedor != null) {
			setPagarProveedorSeleccionado(pagarProveedor);
			desabilitaEdicion();
			if (pagarProveedor.getPprSituacion() == 0) {
				btnAnula.setVisible(true);
			} else {
				btnAnula.setVisible(false);
			}
			btnGuardar.setVisible(false);
			tfNota.setText(pagarProveedor.getPprNumero().toString());
			lblSituacion.setText(pagarProveedor.getPprSituacion() == 0 ? "VIGENTE" : "ELIMINADO");
			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
			tfFecha.setText(sd.format(pagarProveedor.getFecha()));
			tfDocumento.setText("" + pagarProveedor.getPprDocumento());
			tfEntidad.setText(pagarProveedor.getPprEntidad().toString());
			Optional<ProveedorDTO> proveedor = null;
			proveedor = proveedorService.findById(Long.valueOf(pagarProveedor.getPprEntidad()));
			if (proveedor.isPresent()) {
				tfNombreEntidad.setText(proveedor.get().getNombre());
			}
			itemTableModel.clear();
			List<Object[]> listaItemPagarProveedor = pagarProveedorApiClient.findProveedorPagadoView(
					Long.valueOf(pagarProveedor.getPprEntidad()), pagarProveedor.getPprNumero());
			List<DetalleAPagarProveedorView> listaCasteado = castDetallePagarProveedor(listaItemPagarProveedor, 1);
			itemTableModel.addEntities(listaCasteado);
			calculateItem();
		}

	}

	private void desabilitaEdicion() {
		tfEntidad.setEnabled(false);
		chkCobraTodos.setEnabled(false);
		lblMontoACobrar.setText("TOTAL COBRADO");
	}

	private void habilitaEdicion() {
		tfEntidad.setEnabled(true);
		chkCobraTodos.setEnabled(true);
		lblMontoACobrar.setText("MONTO A COBRAR");
	}

	@Override
	public void getEntity(ProveedorDTO proveedor) {
		if (proveedor != null) {
			tfEntidad.setText(proveedor.getId().toString());
			tfNombreEntidad.setText(proveedor.getNombre());
			itemTableModel.clear();
			List<Object[]> listMII = pagarProveedorApiClient.findDetallePagarProveedorView(proveedor.getId());
			List<DetalleAPagarProveedorView> listaCasteado = castDetallePagarProveedor(listMII, 0);
			itemTableModel.addEntities(listaCasteado);
			calculateItem();
		}

	}

	private void findEntidad(String id) {
		Optional<ProveedorDTO> proveedor = null;
		proveedor = proveedorService.findById(Long.valueOf(id));
		if (proveedor.isPresent()) {
			tfNombreEntidad.setText(proveedor.get().getNombre());
			itemTableModel.clear();
			List<Object[]> listMII = pagarProveedorApiClient.findDetallePagarProveedorView(proveedor.get().getId());
			List<DetalleAPagarProveedorView> listaCasteado = castDetallePagarProveedor(listMII, 0);
			itemTableModel.addEntities(listaCasteado);
			calculateItem();
			chkCobraTodos.requestFocus();
		} else {
			Notifications.showAlert("No existe cliente informado. Verifique por favor.!");
		}
	}

	private void calculateItem() {
		totalCalculado = itemTableModel.getEntities().stream().mapToDouble(i -> i.getCobro()).sum();
		tfMontoACobrar.setText(FormatearValor.doubleAString(totalCalculado));
		tfSaldo.setText(FormatearValor.doubleAString(totalCalculado));
		tfTotalACobrar.setText(FormatearValor
				.doubleAString(itemTableModel.getEntities().stream().mapToDouble(i -> i.getIcp_monto1()).sum()));
	}

	private List<DetalleAPagarProveedorView> castDetallePagarProveedor(List<Object[]> listMII, int edicion) {
		List<DetalleAPagarProveedorView> lista = new ArrayList<DetalleAPagarProveedorView>();
		for (Object[] objects : listMII) {
			DetalleAPagarProveedorView det = new DetalleAPagarProveedorView();
			det.setCap_numero((Integer) objects[0]);
			det.setCap_fecha1(toDate(objects[1]));
			det.setCap_boleta(objects[2].toString());
			// det.setCap_entidad((Long)objects[3]);
			det.setNombre(objects[4].toString());
			det.setCap_monto1((Double) objects[5]);
			det.setCap_proceso((Integer) objects[6]);
			det.setIcp_vencimiento1(toDate(objects[7]));
			det.setIcp_monto1((Double) objects[8] - (Double) objects[10]);
			det.setIcp_documento((String) objects[9]);
			det.setPagado((Double) objects[10]);
			det.setNombre_ingreso((String) objects[11]);
			if (edicion == 0)
				det.setCobro(0d);
			else
				det.setCobro((Double) objects[13]);
			det.setIcp_Secuencia((Integer) objects[12]);
			//if (det.getIcp_monto1() > 0)
			lista.add(det);
		}

		return lista;

	}

	private Date toDate(Object value) {
		if (value == null) {
			return null;
		}
		if (value instanceof Date) {
			return (Date) value;
		}

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(value.toString());
		} catch (Exception e) {
			throw new IllegalArgumentException("Fecha invalida: " + value, e);
		}
	}

	private void calculateMontoTotal(int origen) {
		try {
			Double aCobrar = itemTableModel.getEntities().stream().mapToDouble(i -> i.getCobro()).sum();
			if (aCobrar > 0) {
				Double totalCta = FormatearValor.stringToDouble(tfSaldo.getText());
				Double descuento = 0d;
				Double recargo = 0d;
				if (!tfDescuentos.getText().isEmpty() && !tfDescuentos.getText().toString().equalsIgnoreCase("0")) {
					descuento = FormatearValor.stringToDouble(tfDescuentos.getText());
					tfDescuentos.setText(FormatearValor.doubleAString(descuento));
					btnGuardar.requestFocus();
				}
				if (!tfRecargos.getText().isEmpty() && !tfRecargos.getText().toString().equalsIgnoreCase("0")) {
					recargo = FormatearValor.stringToDouble(tfRecargos.getText());
					tfRecargos.setText(FormatearValor.doubleAString(recargo));
					btnGuardar.requestFocus();
				}
				totalCalculado = totalCta - descuento + recargo;
				tfMontoACobrar.setText(FormatearValor.doubleAString(totalCalculado));
				if (origen == 0)
					tfRecargos.requestFocus();
				else
					tfMontoACobrar.requestFocus();

			} else {
				tfRecargos.setText("0");
				tfDescuentos.setText("0");
				Notifications.showAlert("Debe cargar primero los datos a pagar.!");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void abandonarNota() {
		Integer respuesta = JOptionPane.showConfirmDialog(this, "ABANDONAR MOVIMIENTO.?", "AVISO",
				JOptionPane.OK_CANCEL_OPTION);

		if (respuesta == 0) {
			clearForm();
		} else {
			tfEntidad.requestFocus();
		}
	}

	public PagarProveedorDTO getPagarProveedorSeleccionado() {
		return pagarProveedorSeleccionado;
	}

	public void setPagarProveedorSeleccionado(PagarProveedorDTO pagarProveedorSeleccionado) {
		this.pagarProveedorSeleccionado = pagarProveedorSeleccionado;
	}

	public Double getTotalCalculado() {
		return totalCalculado;
	}

	public void setTotalCalculado(Double totalCalculado) {
		this.totalCalculado = totalCalculado;
	}

	public JLabel getLblMontoACobrar() {
		return lblMontoACobrar;
	}

	public void setLblMontoACobrar(JLabel lblMontoACobrar) {
		this.lblMontoACobrar = lblMontoACobrar;
	}

}
