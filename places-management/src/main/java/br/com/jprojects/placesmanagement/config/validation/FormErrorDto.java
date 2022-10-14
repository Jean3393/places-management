package br.com.jprojects.placesmanagement.config.validation;

public class FormErrorDto {
	
	private String field;
	private String error;
	
	public FormErrorDto(String field, String error) {
		this.field = field;
		this.error = error;
	}
	
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

}
