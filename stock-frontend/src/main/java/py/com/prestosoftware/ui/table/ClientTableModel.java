package py.com.prestosoftware.ui.table;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.ClienteDTO;
import py.com.prestosoftware.ui.helpers.Fechas;
import py.com.prestosoftware.ui.shared.DefaultTableModel;

@Component
public class ClientTableModel extends DefaultTableModel<ClienteDTO> {

	private static final long serialVersionUID = 1L;
	
	@Override
    public String[] getColumnLabels() {
        return new String[]{
        		"Codigo", "Nombre", "Razon Social", "C.I. Nro./R.U.C", "Telefono","Registro"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ClienteDTO client = entities.get(rowIndex);

        switch (columnIndex) {
	        case 0:
	            return client.getId();
            case 1:
                return client.getNombre();
            case 2:
                return client.getRazonSocial();
            case 3:
                return client.getCiruc();
            case 4:
                return client.getTelefono();
            case 5:
                if(client.getFechaRegistro() == null) {
                	return "";
                }else {
                	return Fechas.formatoDDMMAAAA(client.getFechaRegistro());
                }
            default:
                return "";
        }
    }
}
