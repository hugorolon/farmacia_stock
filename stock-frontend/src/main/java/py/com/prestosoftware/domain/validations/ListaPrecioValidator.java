package py.com.prestosoftware.domain.validations;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.ListaPrecioDTO;

import java.util.Optional;

@Component
public class ListaPrecioValidator extends ValidationSupport implements Validator<ListaPrecioDTO> {

    private static final String REQUIRED_DATA_NOT_FILLED_OR_BAD_DATA = "Campos Obligatorios";

    @Override
    public Optional<ValidationError> validate(ListaPrecioDTO lista) {
        if (isNullOrEmptyString(lista.getNombre())) {
            return Optional.of(new ValidationError(REQUIRED_DATA_NOT_FILLED_OR_BAD_DATA));
        }
        
        return Optional.empty();
    }

}
