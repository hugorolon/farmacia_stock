package py.com.prestosoftware.ui.table;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.MarcaDTO;
import py.com.prestosoftware.ui.shared.DefaultTableModel;

@Component
public class MarcaTableModel extends DefaultTableModel<MarcaDTO> {

	private static final long serialVersionUID = 1L;
	
	@Override
    public String[] getColumnLabels() {
        return new String[]{
                "Nombre",
                "Activo"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	MarcaDTO marca = entities.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return marca.getNombre();
            case 1:
                return marca.getActivo();
            default:
                return "";
        }
    }
}
