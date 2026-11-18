package py.com.prestosoftware.domain.validations;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.VentaTemporalDTO;

import java.util.Optional;

@Component
public class VentaTemporalValidator extends ValidationSupport implements Validator<VentaTemporalDTO> {

    private static final String REQUIRED_DATA_NOT_FILLED_OR_BAD_DATA = "Campos Obligatorios o No tiene items cargados";

    @Override
    public Optional<ValidationError> validate(VentaTemporalDTO venta) {
        if (
                isNullValue(venta.getCliente().getId()) ||
                isNullOrEmptyString(venta.getCondicion() + "") ||
                isEmptyList(venta.getItems())) {
            return Optional.of(new ValidationError(REQUIRED_DATA_NOT_FILLED_OR_BAD_DATA));
        }

        return Optional.empty();
    }

}
