package br.com.jprojects.placesmanagement.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
	
	private T data;
	private Object errors;
	
	public void setData(T data) {
		this.data = data;
	}
	public T getData() {
		return data;
	}
	public void setErrors(Object errors) {
		this.errors = errors;
	}
	public Object getErrors() {
		return this.errors;
	}
	
	public void addErrorMessageToResponse(String msgError) {
		ResponseError error = new ResponseError();
		error.setDetails(msgError);
		error.setTimestamp(LocalDateTime.now());
		
		setErrors(error);
	}
	
	public void addFieldErrorMessagesToResponse(List<FieldError> fieldErrors) {
		List<ResponseError> responseErrors = new ArrayList<>();
		
		fieldErrors.forEach(fieldError -> {
			ResponseError error = new ResponseError();
			error.setDetails(fieldError.getDefaultMessage());
			error.setTimestamp(LocalDateTime.now());
			
			responseErrors.add(error);
		});
		
		setErrors(responseErrors);
	}

}
