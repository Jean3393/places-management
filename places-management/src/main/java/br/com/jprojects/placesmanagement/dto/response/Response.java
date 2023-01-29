package br.com.jprojects.placesmanagement.dto.response;

import java.time.LocalDateTime;

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

}
