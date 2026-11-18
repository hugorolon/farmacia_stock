package py.com.prestosoftware.domain.validations;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.CategoriaDTO;

import java.util.Optional;

@Component
public class CategoriaValidator extends ValidationSupport implements Validator<CategoriaDTO> {

    private static final String REQUIRED_DATA_NOT_FILLED_OR_BAD_DATA = "Campos Obligatorios";

    @Override
    public Optional<ValidationError> validate(CategoriaDTO c) {
        if (isNullOrEmptyString(c.getNombre())) {
            return Optional.of(new ValidationError(REQUIRED_DATA_NOT_FILLED_OR_BAD_DATA));
        }
        
        return Optional.empty();
    }

}
