package py.com.prestosoftware.domain.validations;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.PedidoDTO;

import java.util.Optional;

@Component
public class PedidoValidator extends ValidationSupport implements Validator<PedidoDTO> {

    private static final String REQUIRED_DATA_NOT_FILLED_OR_BAD_DATA = "Campos Obligatorios";

    @Override
    public Optional<ValidationError> validate(PedidoDTO p) {
        if (isNullOrEmptyString(p.getSituacion()) ||
                isNullValue(p.getProveedor().getId()) ||
                isNullValue(p.getFecha()) ||
                isNullOrEmptyString(p.getCondicion() + "") ||
                isEmptyList(p.getItems())) {
            return Optional.of(new ValidationError(REQUIRED_DATA_NOT_FILLED_OR_BAD_DATA));
        }

        return Optional.empty();
    }

}