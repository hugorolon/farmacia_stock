package py.com.prestosoftware.ui.table;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.DepositoDTO;
import py.com.prestosoftware.ui.shared.DefaultTableModel;

@Component
public class DepositoTableModel extends DefaultTableModel<DepositoDTO> {

	private static final long serialVersionUID = 1L;
	
	@Override
    public String[] getColumnLabels() {
        return new String[] { "NOMBRE", "EMPRESA", "ACTIVO" };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	DepositoDTO dep = entities.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return dep.getNombre();
            case 1:
                return dep.getEmpresa().getNombre(); 
            case 2:
                return dep.getActivo() == 1 ? "SI":"NO";
            default:
                return "";
        }
    }
}
