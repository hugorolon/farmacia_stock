
package py.com.prestosoftware.ui.table;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.DevolucionDTO;
import py.com.prestosoftware.ui.shared.DefaultTableModel;

@Component
public class DevolucionesTableModel extends DefaultTableModel<DevolucionDTO> {

	private static final long serialVersionUID = 1L;
	
	@Override
    public String[] getColumnLabels() {
        return new String[]{
        		"COD.", "FECHA", "ID", "CLIENTE/PROVEEDOR", "TOTAL", "CONDICION", "ESTADO"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	DevolucionDTO v = entities.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        switch (columnIndex) {
	        case 0:
	            return v.getId();
            case 1:
                return sdf.format(v.getFecha());
            case 2:
                return v.getRefId();
            case 3:
            	return v.getReferencia();
            case 4:
                return v.getTotalGeneral();
            case 5:
                return v.getTipo();
            case 6:
                return v.getSituacion();
            default:
                return "";
        }
    }
}
