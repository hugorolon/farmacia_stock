package py.com.prestosoftware.controllers;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * Devuelve 400 con un mensaje legible cuando una relación enviada por el cliente
 * referencia un registro inexistente (ver SafeJpaRepository), en lugar de un 500 genérico.
 */
@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler({ EntityNotFoundException.class, ObjectRetrievalFailureException.class })
	public ResponseEntity<Map<String, Object>> handleEntityNotFound(RuntimeException ex,
			HttpServletRequest request) {
		// Spring traduce EntityNotFoundException a JpaObjectRetrievalFailureException: usar la causa original.
		Throwable cause = ex instanceof EntityNotFoundException || ex.getCause() == null ? ex : ex.getCause();
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", OffsetDateTime.now().toString());
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());
		body.put("message", cause.getMessage());
		body.put("path", request.getRequestURI());
		return ResponseEntity.badRequest().body(body);
	}
}
