package py.com.prestosoftware.ui.table;

import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.TransformacionProductoDTO;
import py.com.prestosoftware.ui.shared.DefaultTableModel;

@Component
public class TransformacionProductoTableModel extends DefaultTableModel<TransformacionProductoDTO> {

	private static final long serialVersionUID = 1L;
	
	@Override
    public String[] getColumnLabels() {
        return new String[] { "ID", "FECHA", "PRODUCTO ORIGEN", "CANTIDAD", "SITUACION" };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	TransformacionProductoDTO tp = entities.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        switch (columnIndex) {
            case 0:
                return tp.getId();
            case 1:
                return sdf.format(tp.getFecha());
            case 2:
                if (tp.getProductoOrigen() == null) {
                    return "Producto no disponible";
                }
            	return tp.getProductoOrigen().getId()+" - "+tp.getProductoOrigen().getDescripcion();
            case 3:
            	return tp.getCantidad();	
            case 4:
            	return tp.getSituacion() == 1 ? "VIGENTE":"ELIMINADO";
            default:
                return "";
        }
    }
}
