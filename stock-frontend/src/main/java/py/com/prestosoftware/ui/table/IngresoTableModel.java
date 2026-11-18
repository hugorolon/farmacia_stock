package py.com.prestosoftware.ui.table;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.IngresoDTO;
import py.com.prestosoftware.ui.shared.DefaultTableModel;

@Component
public class IngresoTableModel extends DefaultTableModel<IngresoDTO> {

	private static final long serialVersionUID = 1L;
	
	@Override
    public String[] getColumnLabels() {
        return new String[] { "CODIGO", "DESCRIPCION", "ABREVIATURA" };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	IngresoDTO item = entities.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return item.getId();
            case 1:
            	return item.getIngDescripcion();
            case 2:
            	return item.getIngAbreviatura();
            default:
                return "";
        }
    }
}
