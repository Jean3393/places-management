package br.com.jprojects.placesmanagement.dto.model;

import javax.validation.constraints.NotBlank;

public class JwtUserDto {
	
	@NotBlank(message = "User email must not be null.")
	private String username;
	@NotBlank(message = "User password must not be null.")
	private String password;
	
	public JwtUserDto(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
