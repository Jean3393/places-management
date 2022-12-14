package br.com.jprojects.placesmanagement.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationErrorHandler {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<FormErrorDto> handle(MethodArgumentNotValidException exception){
		List<FormErrorDto> dto = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getFieldErrors();
		
		fieldErrors.forEach(e -> {
			FormErrorDto error = new FormErrorDto(e.getField(), e.getDefaultMessage());
			dto.add(error);
		});
		
		return dto;
	}

}
