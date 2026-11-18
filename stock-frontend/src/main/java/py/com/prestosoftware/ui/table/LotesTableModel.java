package py.com.prestosoftware.ui.table;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.LoteDTO;
import py.com.prestosoftware.ui.helpers.Fechas;
import py.com.prestosoftware.ui.shared.DefaultTableModel;

@Component
public class LotesTableModel extends DefaultTableModel<LoteDTO> {

	private static final long serialVersionUID = 1L;
	
	@Override
    public String[] getColumnLabels() {
        return new String[] { "PRODUCTO.", "VENCIMIENTO", "NRO. LOTE", "PRECIO COMPRA", "STOCK" };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LoteDTO item = entities.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return item.getIdProducto();
            case 1:
                return Fechas.dateUtilAStringDDMMAAAA(item.getFechaVencimiento());
            case 2:
                return item.getCodigoLote();    
            case 3:
                return item.getPrecioCompra();
            case 4:
                return item.getStockActual();
            default:
                return "";
        }
    }
}
