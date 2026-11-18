package py.com.prestosoftware.ui.table;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.MovimientoEgresoDTO;
import py.com.prestosoftware.ui.shared.DefaultTableModel;
import java.util.Date;

@Component
public class MovimientoEgresoTableModel extends DefaultTableModel<Object[]> {

	private static final long serialVersionUID = 1L;
	
	@Override
    public String[] getColumnLabels() {
        return new String[] { "ID.", "FECHA", "CAJA", "DOCUMENTO", "ENTIDAD", "TOTAL"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    	Object[] item = entities.get(rowIndex);
    	
        switch (columnIndex) {
            case 0:
                return item[0].toString();
            case 1:
            	return formatDate(item[1]);
            case 2:
                return (item[2]==null?'1':item[2].toString());//.getMegCaja();
            case 3:
                return item[3].toString();//.getMegDocumento();
            case 4:
                return item[4].toString();//.getMegEntidad();
            case 5:
                return item[5];
            case 6:
                return item[6];    
            default:
                return "";
        }
    }

    private String formatDate(Object value) {
        if (value == null) {
            return "";
        }

        SimpleDateFormat displayFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (value instanceof Date) {
            return displayFormat.format((Date) value);
        }

        if (value instanceof String) {
            SimpleDateFormat apiFormat = new SimpleDateFormat("yyyy-MM-dd");
            apiFormat.setLenient(false);
            ParsePosition position = new ParsePosition(0);
            Date date = apiFormat.parse((String) value, position);
            if (date != null && position.getIndex() == ((String) value).length()) {
                return displayFormat.format(date);
            }
        }

        throw new IllegalArgumentException("Formato de fecha no compatible: " + value);
    }
}
