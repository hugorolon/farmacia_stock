package py.com.prestosoftware.ui.table;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.TransferenciaProductoDetalleDTO;
import py.com.prestosoftware.ui.shared.DefaultTableModel;

@Component
public class TransferenciaTableModel extends DefaultTableModel<TransferenciaProductoDetalleDTO> {

	private static final long serialVersionUID = 1L;
	
	@Override
    public String[] getColumnLabels() {
        return new String[] { "CODIGO", "DESCRIPCION", "CANTIDAD" };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	TransferenciaProductoDetalleDTO item = entities.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return item.getProductoId();
            case 1:
            	return item.getProducto();
            case 2:
            	return item.getCantidad();
            default:
                return "";
        }
    }
}
