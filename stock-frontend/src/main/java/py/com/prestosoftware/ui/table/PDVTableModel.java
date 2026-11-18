package py.com.prestosoftware.ui.table;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.VentaDetalleDTO;
import py.com.prestosoftware.ui.shared.DefaultTableModel;

@Component
public class PDVTableModel extends DefaultTableModel<VentaDetalleDTO> {

	private static final long serialVersionUID = 1L;
	
	@Override
    public String[] getColumnLabels() {
        return new String[] { "CODIGO", "DESCRIPCIÓN", "PRECIO UNIT.", "CANTIDAD" };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VentaDetalleDTO item = entities.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return item.getProductoId();
            case 1:
            	return item.getProducto();
            case 2:
            	return item.getPrecio();
            case 3:
                return item.getCantidad();
            default:
                return "";
        }
    }
}
