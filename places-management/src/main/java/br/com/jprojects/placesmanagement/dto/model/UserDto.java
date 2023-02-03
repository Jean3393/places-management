package br.com.jprojects.placesmanagement.dto.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import br.com.jprojects.placesmanagement.model.User;

public class UserDto {
	
	private Integer id;
	@NotBlank(message = "Name cannot be null.")
	private String name;
	@NotBlank(message = "Password cannot be null.")
	@Length(min = 6, message = "Password must contain at least 6 characters.")
	private String password;
	@NotBlank(message = "Email cannot be null.")
	@Email(message = "Invalid email.")
	private String email;
	@NotBlank(message = "The user role cannot be null.")
	@Pattern(regexp = "^(ROLE_ADMIN|ROLE_USER)$", message = "Use only 'ROLE_ADMIN' or 'ROLE_USER' values.")
	private String role;

	public UserDto(String name, String password, String email, String role) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.role = role;
	}
	
	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.role = user.getRole().toString();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public static List<UserDto> listConverter(List<User> users) {
		List<UserDto> userList = new ArrayList<>();
		users.stream().forEach(user -> userList.add(new UserDto(user)));
		
		return userList;
	}

}
