package br.com.jprojects.placesmanagement.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

public class InvalidPlaceException extends BindException {
	
	private static final long serialVersionUID = -1291044470717222856L;
	

	public InvalidPlaceException(BindingResult bindingResult) {
		super(bindingResult);
	}

}
