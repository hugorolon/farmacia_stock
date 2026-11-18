package py.com.prestosoftware.domain.validations;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.UnidadMedidaDTO;

import java.util.Optional;

@Component
public class UnidadMedidaValidator extends ValidationSupport implements Validator<UnidadMedidaDTO> {

    private static final String REQUIRED_DATA_NOT_FILLED_OR_BAD_DATA = "Campos Obligatorios";

    @Override
    public Optional<ValidationError> validate(UnidadMedidaDTO u) {
        if (isNullOrEmptyString(u.getNombre())) {
            return Optional.of(new ValidationError(REQUIRED_DATA_NOT_FILLED_OR_BAD_DATA));
        }
        
        return Optional.empty();
    }

}
