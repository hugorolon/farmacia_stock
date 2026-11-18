package py.com.prestosoftware.ui.table;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.MovimientoCajaDTO;
import py.com.prestosoftware.ui.helpers.FormatearValor;
import py.com.prestosoftware.ui.shared.DefaultTableModel;

@Component
public class EntradaCajaTableModel extends DefaultTableModel<MovimientoCajaDTO> {

	private static final long serialVersionUID = 1L;
	
	@Override
    public String[] getColumnLabels() {
        return new String[] { "MONEDA", "EFECTIVO", "CAMBIO", "CONSOLIDADO"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	MovimientoCajaDTO item = entities.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return item.getMoneda().getNombre();
            case 1:
                return FormatearValor.doubleAString(item.getNotaValor() != null ? 
                		item.getNotaValor():0);
            case 2:
                return item.getCotizacion();
            case 3:
                return item.getConsolidado();
            default:
                return "";
        }
    }
}
