package py.com.prestosoftware.ui.table;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.PaisDTO;
import py.com.prestosoftware.ui.shared.DefaultTableModel;

@Component
public class PaisTableModel extends DefaultTableModel<PaisDTO> {

	private static final long serialVersionUID = 1L;
	
	@Override
    public String[] getColumnLabels() {
        return new String[]{
                "Nombre",
                "Activo"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	PaisDTO pais = entities.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return pais.getNombre();
            case 1:
                return pais.getActivo();
            default:
                return "";
        }
    }
}
