package py.com.prestosoftware.ui.table;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.UsuarioDTO;
import py.com.prestosoftware.ui.shared.DefaultTableModel;

@Component
public class UsuarioTableModel extends DefaultTableModel<UsuarioDTO> {

	private static final long serialVersionUID = 1L;
	
	@Override
    public String[] getColumnLabels() {
        return new String[]{
                "USUARIO", "EMPRESA", "ROL", "ACTIVO"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	UsuarioDTO usuario = entities.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return usuario.getUsuario();
            case 1:
                return usuario.getEmpresa().getNombre();
            case 2:
                return usuario.getRol().getNombre();
            case 3:
                return usuario.getActivo() == 1 ? "SI":"NO";
            default:
                return "";
        }
    }
}
