package br.com.jprojects.placesmanagement.dto.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class JwtUserDto {
	
	@Email(message = "Must enter a valid email address.")
	@NotBlank(message = "User email must not be null.")
	private String email;
	@NotBlank(message = "User password must not be null.")
	private String password;
	
	public JwtUserDto(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
