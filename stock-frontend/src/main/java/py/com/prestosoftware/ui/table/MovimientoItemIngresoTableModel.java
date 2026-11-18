package py.com.prestosoftware.ui.table;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.MovimientoItemIngresoDTO;
import py.com.prestosoftware.ui.shared.DefaultTableModel;

@Component
public class MovimientoItemIngresoTableModel extends DefaultTableModel<MovimientoItemIngresoDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1632730735672928703L;

	
	@Override
    public String[] getColumnLabels() {
        return new String[] {"INGRESO", "DESCRIP.", "MONTO"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	MovimientoItemIngresoDTO item = entities.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
            	return item.getMiiIngreso();
            case 1:
            	return item.getMiiDescripcion();
            case 2:
                return item.getMiiMonto();
            
            default:
                return "";
        }
    }
}
