package py.com.prestosoftware.ui.table;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.PagarProveedorDTO;
import py.com.prestosoftware.ui.shared.DefaultTableModel;

@Component
public class PagarProveedorTableModel extends DefaultTableModel<PagarProveedorDTO> {

	private static final long serialVersionUID = 1L;
	
	@Override
    public String[] getColumnLabels() {
        return new String[] { "NUMERO", "FECHA", "PROVEEDOR", "VALOR", "DESCUENTO", "RECARGO","MONTO NETO"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	PagarProveedorDTO item = entities.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        switch (columnIndex) {
            case 0:
                return item.getPprNumero();
            case 1:
                return sdf.format(item.getFecha());
            case 2:
                return item.getPprEntidad();
            case 3:
                return item.getPprValor();
            case 4:
                return item.getPprDescuento();
            case 5:
                return item.getPprRecargo();
            case 6:
                return item.getPprMonto();    
            default:
                return "";
        }
    }
}
