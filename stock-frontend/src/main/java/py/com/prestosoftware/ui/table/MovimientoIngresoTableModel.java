package py.com.prestosoftware.ui.table;

import java.text.SimpleDateFormat;
import java.text.ParsePosition;
import java.util.Date;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.MovimientoIngresoDTO;
import py.com.prestosoftware.ui.shared.DefaultTableModel;

@Component
public class MovimientoIngresoTableModel extends DefaultTableModel<Object[]> {

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
                return item[2].toString();
            case 3:
                return item[3].toString();
            case 4:
                return item[4].toString();
            case 5:
                return item[5];    
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
