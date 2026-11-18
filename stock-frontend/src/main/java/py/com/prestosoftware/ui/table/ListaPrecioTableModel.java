package py.com.prestosoftware.ui.table;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.ListaPrecioDTO;
import py.com.prestosoftware.ui.shared.DefaultTableModel;

@Component
public class ListaPrecioTableModel extends DefaultTableModel<ListaPrecioDTO> {

	private static final long serialVersionUID = 1L;
	
	@Override
    public String[] getColumnLabels() {
        return new String[]{
                "Nombre",
                "Activo"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	ListaPrecioDTO listaPrecio = entities.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return listaPrecio.getNombre();
            case 1:
                return listaPrecio.getActivo();
            default:
                return "";
        }
    }
}
