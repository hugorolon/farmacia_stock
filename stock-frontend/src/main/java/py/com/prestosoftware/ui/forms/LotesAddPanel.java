package py.com.prestosoftware.ui.forms;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.miginfocom.swing.MigLayout;
import py.com.prestosoftware.client.LoteApiClient;
import py.com.prestosoftware.data.dtos.LoteDTO;
import py.com.prestosoftware.ui.helpers.Fechas;
import py.com.prestosoftware.ui.helpers.Util;
import py.com.prestosoftware.ui.search.LoteInterfaz;

@Component
public class LotesAddPanel extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField tfVencimiento;
	private JButton btnGuardar, btnCancelar;
	private JLabel label_3;
	private LoteApiClient loteService;

	private LoteInterfaz interfaz;

	@Autowired
	public LotesAddPanel(LoteApiClient loteService) {
		this.loteService = loteService;
		setSize(1070, 421);

		initComponents();

		Util.setupScreen(this);
	}

	private void initComponents() {
		getContentPane().setLayout(new MigLayout("", "[88px][1px][694px][4px][217.00px]", "[29px][233.00px][39.00][]"));
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));

		getContentPane().add(tabbedPane, "cell 0 1 5 2,grow");

		JPanel pnlLotes = new JPanel();
		tabbedPane.addTab("Lotes", null, pnlLotes, null);

		JButton btnAddLote = new JButton("+"); //$NON-NLS-1$ //$NON-NLS-2$
		btnAddLote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//showDialog(CIUDAD_CODE);
			}
		});
		btnAddLote.setBounds(591, 10, 46, 30);
		pnlLotes.add(btnAddLote);

		JLabel lblVencimiento = new JLabel(ResourceBundle.getBundle("py.com.prestosoftware.ui.forms.messages")
				.getString("ProveedorPanel.lblDireccin.text"));
		lblVencimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVencimiento.setBounds(333, 8, 98, 30);

		tfVencimiento = new JFormattedTextField(getFormatoFecha());
		tfVencimiento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfVencimiento.setBounds(441, 8, 140, 30);
		tfVencimiento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!tfVencimiento.getText().isEmpty()) {
						btnAddLote.requestFocus();
					}
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					btnAddLote.requestFocus();
				}
			}
		});
		
		tfVencimiento.setColumns(10);
		pnlLotes.setLayout(null);
		pnlLotes.add(lblVencimiento);
		pnlLotes.add(tfVencimiento);

		label_3 = new JLabel("*");
		label_3.setBounds(185, 4, 18, 30);
		label_3.setVerticalAlignment(SwingConstants.BOTTOM);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Dialog", Font.BOLD, 20));
		pnlLotes.add(label_3);
		
		JLabel lblCodigo = new JLabel(ResourceBundle.getBundle("py.com.prestosoftware.ui.forms.messages").getString("LotesAddPanel.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(10, 8, 58, 30);
		pnlLotes.add(lblCodigo);
		
		tfCodigo = new JTextField();
		tfCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfCodigo.setBounds(79, 16, 96, 19);
		pnlLotes.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		

		
		JPanel pnlBotonera = new JPanel();
		getContentPane().add(pnlBotonera, "cell 0 3 5 1,grow");

		btnGuardar = new JButton(ResourceBundle.getBundle("py.com.prestosoftware.ui.forms.messages") //$NON-NLS-1$
				.getString("ProveedorPanel.btnGuardar.text")); //$NON-NLS-1$
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aceptar();
			}
		});
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlBotonera.add(btnGuardar);

		btnCancelar = new JButton(ResourceBundle.getBundle("py.com.prestosoftware.ui.forms.messages") //$NON-NLS-1$
				.getString("ProveedorPanel.btnCancelar.text")); //$NON-NLS-1$
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlBotonera.add(btnCancelar);
	}

	private MaskFormatter formatoFecha;
	private JTextField tfCodigo;

	private MaskFormatter getFormatoFecha() {
		try {
			if (formatoFecha == null) {
				formatoFecha = new MaskFormatter("##/##/####");
				formatoFecha.setPlaceholderCharacter('_');
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return formatoFecha;
	}
	
	public void setLotesForm(LoteDTO lote) {
		tfVencimiento.setText(Fechas.dateUtilAStringDDMMAAAA(lote.getFechaVencimiento()));
	}

	public LoteDTO getLotesFrom() {
		LoteDTO lote= new LoteDTO();
		lote.setFechaVencimiento(Fechas.stringDDMMAAAAADateUtil(tfVencimiento.getText()));

		return lote;
	}

	public LoteInterfaz getInterfaz() {
		return interfaz;
	}

	public void setInterfaz(LoteInterfaz interfaz) {
		this.interfaz = interfaz;
	}

	private void aceptar() {
		LoteDTO lote = this.getLotesFrom();
		java.util.Optional<LoteDTO> loteRec = loteService.findById(lote.getId());
		clearForm();
		if (loteRec.isPresent()) {
			interfaz.getEntity(loteRec.get());
		}
		dispose();
	}

	public void addNewLote() {
		long Id = loteService.getRowCount();
		this.setNewLote(Id + 1);
	}

	public void clearForm() {
		tfVencimiento.setText("");
	}
	
	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setNewLote(long id) {
		tfCodigo.setText(String.valueOf(id));
	}
}
