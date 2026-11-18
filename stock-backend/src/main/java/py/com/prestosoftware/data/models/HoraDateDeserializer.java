package py.com.prestosoftware.data.models;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class HoraDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String value = parser.getText();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setLenient(false);
        try {
            return formatter.parse(value);
        } catch (ParseException exception) {
            throw context.weirdStringException(value, Date.class,
                    "La hora debe tener el formato HH:mm:ss");
        }
    }
}
