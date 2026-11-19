package py.com.prestosoftware.ui.reports;

import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import org.springframework.stereotype.Component;

import net.miginfocom.swing.MigLayout;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import py.com.prestosoftware.ui.helpers.GlobalVars;
import py.com.prestosoftware.ui.helpers.Util;
import py.com.prestosoftware.util.ConnectionUtils;
import py.com.prestosoftware.util.Notifications;
import py.com.prestosoftware.util.ReportesPath;

/**
 * Informes de control de lotes (farmacia):
 * - Lotes vencidos
 * - Lotes por vencer en los proximos N dias
 * - Stock por lote
 * - Movimientos de lotes (trazabilidad: que compra ingreso y a que cliente se vendio cada lote)
 */
@Component
public class InformeLotesDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private static final String TIPO_VENCIDOS = "Lotes vencidos con stock";
	private static final String TIPO_POR_VENCER = "Lotes por vencer";
	private static final String TIPO_STOCK = "Stock por lote";
	private static final String TIPO_MOVIMIENTOS = "Movimientos de lotes (trazabilidad)";

	private JComboBox<String> cbTipo;
	private JComboBox<String> cbOrden;
	private JTextField tfDias;
	private JTextField tfProductoId;
	private JTextField tfLote;
	private JFormattedTextField tfDesde;
	private JFormattedTextField tfHasta;
	private JCheckBox chkSinStock;

	public InformeLotesDialog() {
		setupUI();
		Util.setupScreen(this);
	}

	private void setupUI() {
		setTitle("INFORME DE LOTES Y VENCIMIENTOS");
		setSize(620, 400);
		getContentPane().setLayout(new MigLayout("", "[grow]", "[][][grow][]"));

		Font font = new Font("Tahoma", Font.PLAIN, 14);

		JPanel pnlTipo = new JPanel(new MigLayout("", "[120px][grow]", "[][]"));
		pnlTipo.setBorder(new TitledBorder(null, "INFORME", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlTipo, "cell 0 0,growx");

		JLabel lblTipo = new JLabel("Tipo de informe");
		lblTipo.setFont(font);
		pnlTipo.add(lblTipo, "cell 0 0");
		cbTipo = new JComboBox<String>(new DefaultComboBoxModel<String>(
				new String[] { TIPO_POR_VENCER, TIPO_VENCIDOS, TIPO_STOCK, TIPO_MOVIMIENTOS }));
		cbTipo.setFont(font);
		cbTipo.addActionListener(e -> actualizarCampos());
		pnlTipo.add(cbTipo, "cell 1 0,growx");

		JLabel lblOrden = new JLabel("Ordenado por");
		lblOrden.setFont(font);
		pnlTipo.add(lblOrden, "cell 0 1");
		cbOrden = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] { "Vencimiento", "Producto" }));
		cbOrden.setFont(font);
		pnlTipo.add(cbOrden, "cell 1 1,growx");

		JPanel pnlFiltros = new JPanel(new MigLayout("", "[120px][100px][80px][grow]", "[][][][]"));
		pnlFiltros.setBorder(new TitledBorder(null, "FILTROS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(pnlFiltros, "cell 0 1,growx");

		JLabel lblDias = new JLabel("Vencen en (dias)");
		lblDias.setFont(font);
		pnlFiltros.add(lblDias, "cell 0 0");
		tfDias = new JTextField("90");
		tfDias.setFont(font);
		tfDias.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {
				Util.validateNumero(e);
			}
		});
		pnlFiltros.add(tfDias, "cell 1 0,growx");

		JLabel lblProducto = new JLabel("Cod. producto");
		lblProducto.setFont(font);
		pnlFiltros.add(lblProducto, "cell 0 1");
		tfProductoId = new JTextField();
		tfProductoId.setFont(font);
		tfProductoId.setToolTipText("Vacio = todos los productos");
		tfProductoId.addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyTyped(java.awt.event.KeyEvent e) {
				Util.validateNumero(e);
			}
		});
		pnlFiltros.add(tfProductoId, "cell 1 1,growx");

		JLabel lblLote = new JLabel("Nro. lote");
		lblLote.setFont(font);
		pnlFiltros.add(lblLote, "cell 2 1,alignx right");
		tfLote = new JTextField();
		tfLote.setFont(font);
		tfLote.setToolTipText("Vacio = todos los lotes");
		pnlFiltros.add(tfLote, "cell 3 1,growx");

		JLabel lblDesde = new JLabel("Desde / Hasta");
		lblDesde.setFont(font);
		pnlFiltros.add(lblDesde, "cell 0 2");
		tfDesde = new JFormattedTextField(crearFormatoFecha());
		tfDesde.setFont(font);
		pnlFiltros.add(tfDesde, "cell 1 2,growx");
		tfHasta = new JFormattedTextField(crearFormatoFecha());
		tfHasta.setFont(font);
		pnlFiltros.add(tfHasta, "cell 2 2 2 1,width 100!");

		chkSinStock = new JCheckBox("Incluir lotes sin stock");
		chkSinStock.setFont(font);
		pnlFiltros.add(chkSinStock, "cell 0 3 4 1");

		JPanel pnlBotonera = new JPanel();
		getContentPane().add(pnlBotonera, "cell 0 3,growx");

		JButton btnPrevisualizar = new JButton("Pre visualizar");
		btnPrevisualizar.setFont(font);
		btnPrevisualizar.addActionListener(e -> generar(false));
		pnlBotonera.add(btnPrevisualizar);

		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setFont(font);
		btnImprimir.addActionListener(e -> generar(true));
		pnlBotonera.add(btnImprimir);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(font);
		btnCancelar.addActionListener(e -> dispose());
		pnlBotonera.add(btnCancelar);
	}

	/** Valores por defecto cada vez que se abre el informe. */
	public void inicializar() {
		cbTipo.setSelectedItem(TIPO_POR_VENCER);
		tfDias.setText("90");
		tfProductoId.setText("");
		tfLote.setText("");
		chkSinStock.setSelected(false);
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		tfHasta.setText(f.format(cal.getTime()));
		cal.set(Calendar.DAY_OF_MONTH, 1);
		tfDesde.setText(f.format(cal.getTime()));
		actualizarCampos();
	}

	private void actualizarCampos() {
		String tipo = String.valueOf(cbTipo.getSelectedItem());
		boolean movimientos = TIPO_MOVIMIENTOS.equals(tipo);
		tfDias.setEnabled(TIPO_POR_VENCER.equals(tipo));
		tfDesde.setEnabled(movimientos);
		tfHasta.setEnabled(movimientos);
		chkSinStock.setEnabled(TIPO_STOCK.equals(tipo));
		cbOrden.setEnabled(!movimientos);
	}

	private MaskFormatter crearFormatoFecha() {
		try {
			MaskFormatter mf = new MaskFormatter("##/##/####");
			mf.setPlaceholderCharacter('_');
			return mf;
		} catch (ParseException e) {
			return null;
		}
	}

	private Date parseFecha(String texto) {
		if (texto == null || texto.contains("_") || texto.trim().length() != 10)
			return null;
		SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
		f.setLenient(false);
		try {
			return f.parse(texto.trim());
		} catch (ParseException e) {
			return null;
		}
	}

	private static String escaparSql(String valor) {
		return valor.replace("'", "''");
	}

	/** Filtro opcional por producto y por nro. de lote (alias de lotes: l). */
	private String filtrosComunes() {
		StringBuilder sb = new StringBuilder();
		String productoId = tfProductoId.getText().trim();
		if (!productoId.isEmpty() && productoId.matches("\\d+"))
			sb.append(" AND l.id_producto = ").append(productoId);
		String lote = tfLote.getText().trim();
		if (!lote.isEmpty())
			sb.append(" AND upper(l.codigo_lote) LIKE '%").append(escaparSql(lote.toUpperCase())).append("%'");
		return sb.toString();
	}

	private void generar(boolean imprimir) {
		String tipo = String.valueOf(cbTipo.getSelectedItem());
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("empresa", GlobalVars.EMPRESA);
		String reporte;

		if (TIPO_MOVIMIENTOS.equals(tipo)) {
			Date desde = parseFecha(tfDesde.getText());
			Date hasta = parseFecha(tfHasta.getText());
			if (desde == null || hasta == null || desde.after(hasta)) {
				Notifications.showAlert("Verifique el rango de fechas (dd/mm/aaaa).");
				tfDesde.requestFocus();
				return;
			}
			SimpleDateFormat iso = new SimpleDateFormat("yyyy-MM-dd");
			String sql = "SELECT CAST(l.id AS bigint) AS lote_id, CAST(m.producto_id AS bigint) AS producto_id, p.descripcion AS producto, "
					+ "l.codigo_lote, l.fecha_vencimiento, CAST(COALESCE(l.stock_actual,0) AS double precision) AS stock_actual, "
					+ "m.fecha, m.tipo, CAST(m.referencia_id AS bigint) AS referencia, "
					+ "CASE WHEN m.tipo IN ('VENTA','ANULA_VENTA') THEN v.cliente_nombre ELSE c.proveedor_nombre END AS tercero, "
					+ "CAST(CASE WHEN m.cantidad > 0 THEN m.cantidad ELSE 0 END AS double precision) AS entrada, "
					+ "CAST(CASE WHEN m.cantidad < 0 THEN -m.cantidad ELSE 0 END AS double precision) AS salida "
					+ "FROM lote_movimientos m "
					+ "JOIN lotes l ON l.id = m.lote_id "
					+ "JOIN productos p ON p.id = m.producto_id "
					+ "LEFT JOIN ventas v ON v.id = m.referencia_id AND m.tipo IN ('VENTA','ANULA_VENTA') "
					+ "LEFT JOIN compras c ON c.id = m.referencia_id AND m.tipo IN ('COMPRA','ANULA_COMPRA') "
					+ "WHERE CAST(m.fecha AS date) BETWEEN '" + iso.format(desde) + "' AND '" + iso.format(hasta) + "' "
					+ filtrosComunes()
					+ " ORDER BY p.descripcion, l.id, m.fecha, m.id";
			parametros.put("sql", sql);
			parametros.put("titulo", "Movimientos de lotes");
			parametros.put("subtitulo", "Periodo: " + tfDesde.getText() + " al " + tfHasta.getText());
			reporte = "reportLoteMovimientos.jrxml";
		} else {
			int dias = 90;
			try {
				dias = Integer.parseInt(tfDias.getText().trim());
			} catch (NumberFormatException e) {
				tfDias.setText("90");
			}
			String where;
			String titulo;
			String subtitulo;
			if (TIPO_VENCIDOS.equals(tipo)) {
				where = " AND CAST(l.fecha_vencimiento AS date) < CURRENT_DATE AND COALESCE(l.stock_actual,0) > 0";
				titulo = "Lotes vencidos con stock";
				subtitulo = "Stock que debe retirarse de la venta";
			} else if (TIPO_POR_VENCER.equals(tipo)) {
				where = " AND CAST(l.fecha_vencimiento AS date) BETWEEN CURRENT_DATE AND CURRENT_DATE + " + dias
						+ " AND COALESCE(l.stock_actual,0) > 0";
				titulo = "Lotes por vencer";
				subtitulo = "Vencen en los proximos " + dias + " dias";
			} else {
				where = chkSinStock.isSelected() ? "" : " AND COALESCE(l.stock_actual,0) > 0";
				titulo = "Stock por lote";
				subtitulo = chkSinStock.isSelected() ? "Incluye lotes sin stock" : "Solo lotes con stock";
			}
			String orden = "Producto".equals(String.valueOf(cbOrden.getSelectedItem()))
					? " ORDER BY p.descripcion, l.fecha_vencimiento, l.id"
					: " ORDER BY l.fecha_vencimiento, p.descripcion, l.id";
			String sql = "SELECT CAST(l.id AS bigint) AS lote_id, CAST(l.id_producto AS bigint) AS producto_id, "
					+ "p.descripcion AS producto, p.principio_activo, l.codigo_lote, l.fecha_vencimiento, "
					+ "CAST(COALESCE(l.stock_actual,0) AS double precision) AS stock, "
					+ "CAST(COALESCE(l.precio_compra,0) AS double precision) AS precio_compra, "
					+ "CAST(CAST(l.fecha_vencimiento AS date) - CURRENT_DATE AS integer) AS dias, "
					+ "CASE WHEN CAST(l.fecha_vencimiento AS date) < CURRENT_DATE THEN 'VENCIDO' "
					+ "WHEN CAST(l.fecha_vencimiento AS date) <= CURRENT_DATE + " + dias + " THEN 'POR VENCER' "
					+ "ELSE 'VIGENTE' END AS estado "
					+ "FROM lotes l JOIN productos p ON p.id = l.id_producto "
					+ "WHERE 1 = 1" + where + filtrosComunes() + orden;
			System.out.println("SQL: " + sql);

			parametros.put("sql", sql);
			parametros.put("titulo", titulo);
			parametros.put("subtitulo", subtitulo);
			reporte = "reportLotes.jrxml";
		}

		try (Connection conn = ConnectionUtils.getConnection()) {
			String ruta = new File(ReportesPath.resolver(), reporte).getAbsolutePath();
			JasperDesign jasperDesign = JRXmlLoader.load(ruta);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);

			if (imprimir) {
				JasperPrintManager.printReport(jasperPrint, true);
			} else {
				JFrame frame = new JFrame();
				frame.setTitle(String.valueOf(parametros.get("titulo")));
				frame.setBounds(100, 100, 900, 650);
				frame.getContentPane().add(new JRViewer(jasperPrint));
				frame.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Notifications.showAlert("No se pudo generar el informe: " + e.getMessage());
		}
	}
}
