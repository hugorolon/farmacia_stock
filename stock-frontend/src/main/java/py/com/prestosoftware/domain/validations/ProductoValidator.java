package py.com.prestosoftware.domain.validations;

import org.springframework.stereotype.Component;

import py.com.prestosoftware.data.dtos.ProductoDTO;

import java.util.Optional;

@Component
public class ProductoValidator extends ValidationSupport implements Validator<ProductoDTO> {

    private static final String REQUIRED_DATA_NOT_FILLED_OR_BAD_DATA = "Campo Obligatorio";

    @Override
    public Optional<ValidationError> validate(ProductoDTO product) {
    	String msg="";
        if (isNullOrEmptyString(product.getDescripcion())) {
        	msg="DescripciÃ³n de producto es ";
        	return Optional.of(new ValidationError(msg+REQUIRED_DATA_NOT_FILLED_OR_BAD_DATA));
        }
        
        return Optional.empty();
    }

}
