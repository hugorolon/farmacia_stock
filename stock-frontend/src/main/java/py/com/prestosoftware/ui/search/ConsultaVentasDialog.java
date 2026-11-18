package py.com.prestosoftware.ui.search;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import py.com.prestosoftware.client.VentaApiClient;
import py.com.prestosoftware.ui.helpers.CellRendererOperaciones;
import py.com.prestosoftware.ui.table.ConsultaVentaTableModel;
import py.com.prestosoftware.ui.viewmodel.ConsultaNota;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Component
public class ConsultaVentasDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JTable table;
	private JScrollPane scrollPane;
	
	private VentaApiClient service;
	private ConsultaVentaTableModel tableModel;
	private ProductoInterfaz interfaz;
	
	private List<ConsultaNota> notas;

	@Autowired
	public ConsultaVentasDialog(VentaApiClient service, ConsultaVentaTableModel tableModel, 
			ConsultaPrecioProducto consultaPrecioProducto) {
		this.service = service;
		this.tableModel = tableModel;
		
		setTitle("CONSULTA DE VENTAS POR PRODUCTO");
		setSize(700, 350);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel pnlBuscador = new JPanel();
		getContentPane().add(pnlBuscador, BorderLayout.NORTH);
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable(tableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultRenderer(Object.class, new CellRendererOperaciones());
		scrollPane.setViewportView(table);
		
		JPanel pnlBotonera = new JPanel();
		getContentPane().add(pnlBotonera, BorderLayout.SOUTH);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					//aceptar();
				}
			}
		});
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//aceptar();
			}
		});
		pnlBotonera.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					dispose();
				}
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		pnlBotonera.add(btnCancelar);
		
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ventana = this.getSize(); 
		this.setLocation((pantalla.width - ventana.width) / 2, (pantalla.height - ventana.height) / 2);
	}
	
	public ProductoInterfaz getInterfaz() {
		return interfaz;
	}
	
	public void setInterfaz(ProductoInterfaz interfaz) {
		this.interfaz = interfaz;
	}
	
	public void setProductoId(Long productoId) {
		if (productoId > 0) {
			notas = new java.util.ArrayList<>();
			for (Object[] row : service.retriveVentasByProductoId(productoId)) {
				ConsultaNota nota = new ConsultaNota();
				if (row.length > 0) nota.setOperacion(row[0] == null ? null : row[0].toString());
				if (row.length > 1 && row[1] instanceof java.util.Date) nota.setFecha((java.util.Date) row[1]);
				if (row.length > 2) nota.setNota(row[2] == null ? null : row[2].toString());
				if (row.length > 3) nota.setNotaNro(row[3] == null ? null : Long.valueOf(row[3].toString()));
				if (row.length > 4) nota.setNotaRefCod(row[4] == null ? null : Long.valueOf(row[4].toString()));
				if (row.length > 5) nota.setNotaRef(row[5] == null ? null : row[5].toString());
				if (row.length > 6) nota.setPrecioProducto(row[6] == null ? null : Double.valueOf(row[6].toString()));
				if (row.length > 7) nota.setCantidad(row[7] == null ? null : Double.valueOf(row[7].toString()));
				notas.add(nota);
			}
		}
		
        tableModel.clear();
        tableModel.addEntities(notas);
        table.requestFocus();
	}

}
