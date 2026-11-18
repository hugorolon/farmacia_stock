package py.com.prestosoftware.domain.validations;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.CiudadDTO;

import java.util.Optional;

@Component
public class CiudadValidator extends ValidationSupport implements Validator<CiudadDTO> {

    private static final String REQUIRED_DATA_NOT_FILLED_OR_BAD_DATA = "Campos Obligatorios";

    @Override
    public Optional<ValidationError> validate(CiudadDTO ciudad) {
        if (isNullOrEmptyString(ciudad.getNombre())) {
            return Optional.of(new ValidationError(REQUIRED_DATA_NOT_FILLED_OR_BAD_DATA));
        }
        
        return Optional.empty();
    }

}
