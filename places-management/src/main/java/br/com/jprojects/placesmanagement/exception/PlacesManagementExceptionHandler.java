package br.com.jprojects.placesmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.jprojects.placesmanagement.dto.response.Response;

@ControllerAdvice
public class PlacesManagementExceptionHandler<T> {
	
	@ExceptionHandler(value = { PlaceNotFoundException.class })
	protected ResponseEntity<Response<T>> handlePlaceNotFoundException(PlaceNotFoundException exception) {
		Response<T> response = new Response<>();
		
		response.addErrorMessageToResponse(exception.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(value = { InvalidPlaceException.class, InvalidUserException.class })
	protected ResponseEntity<Response<T>> handleInvalidEntityException(BindException exception) {
		Response<T> response = new Response<>();
		
		response.addFieldErrorMessagesToResponse(exception.getFieldErrors());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	protected ResponseEntity<Response<T>> handleUsernameNotFoundException(UsernameNotFoundException exception){
		Response<T> response = new Response<>();
		
		response.addErrorMessageToResponse(exception.getLocalizedMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

}
