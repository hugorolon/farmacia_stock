package py.com.prestosoftware.ui.table;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.UsuarioRolDTO;
import py.com.prestosoftware.ui.shared.DefaultTableModel;

@Component
public class UsuarioRolTableModel extends DefaultTableModel<UsuarioRolDTO> {

	private static final long serialVersionUID = 1L;
	
	@Override
    public String[] getColumnLabels() {
        return new String[]{"ID", "USUARIO", "ROL"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	UsuarioRolDTO usuario = entities.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return usuario.getId();
            case 1:
                return usuario.getUsuario().getUsuario();
            case 2:
                return usuario.getRol().getNombre();
            default:
                return "";
        }
    }
}
