package br.com.jprojects.placesmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jprojects.placesmanagement.dto.model.UserDto;
import br.com.jprojects.placesmanagement.dto.response.Response;
import br.com.jprojects.placesmanagement.model.User;
import br.com.jprojects.placesmanagement.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<Response<List<UserDto>>> getAll(){
		Response<List<UserDto>> response = new Response<>();
		
		List<User> users = userService.findAll();
		List<UserDto> dto = UserDto.listConverter(users);
		
		response.setData(dto);
		return ResponseEntity.ok(response);
	}

}
