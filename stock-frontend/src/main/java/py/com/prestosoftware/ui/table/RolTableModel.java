package py.com.prestosoftware.ui.table;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.RolDTO;
import py.com.prestosoftware.ui.shared.DefaultTableModel;

@Component
public class RolTableModel extends DefaultTableModel<RolDTO> {

	private static final long serialVersionUID = 1L;
	
	@Override
    public String[] getColumnLabels() {
        return new String[]{ "Nombre", "Activo" };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	RolDTO rol = entities.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return rol.getNombre();
            case 1:
                return rol.getActivo();
            default:
                return "";
        }
    }
}
