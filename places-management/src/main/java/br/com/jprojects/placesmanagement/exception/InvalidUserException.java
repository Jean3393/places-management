package br.com.jprojects.placesmanagement.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

public class InvalidUserException extends BindException {
	
	private static final long serialVersionUID = 4867527161144859465L;

	public InvalidUserException(BindingResult bindingResult) {
		super(bindingResult);
	}	

}
