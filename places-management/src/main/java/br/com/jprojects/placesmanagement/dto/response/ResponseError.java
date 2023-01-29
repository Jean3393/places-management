package br.com.jprojects.placesmanagement.dto.response;

import java.time.LocalDateTime;

public class ResponseError {
	
	private String details;
	private LocalDateTime timestamp;
	
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
