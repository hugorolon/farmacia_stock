package py.com.prestosoftware.ui.table;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.DepartamentoDTO;
import py.com.prestosoftware.ui.shared.DefaultTableModel;

@Component
public class DepartamentoTableModel extends DefaultTableModel<DepartamentoDTO> {

	private static final long serialVersionUID = 1L;
	
	@Override
    public String[] getColumnLabels() {
        return new String[] {"Nombre","Activo"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	DepartamentoDTO departamento = entities.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return departamento.getNombre();
            case 1:
                return departamento.getActivo();
            default:
                return "";
        }
    }
}
