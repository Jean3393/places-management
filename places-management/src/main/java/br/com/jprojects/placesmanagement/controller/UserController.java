package br.com.jprojects.placesmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jprojects.placesmanagement.dto.model.UserDto;
import br.com.jprojects.placesmanagement.dto.response.Response;
import br.com.jprojects.placesmanagement.exception.InvalidUserException;
import br.com.jprojects.placesmanagement.model.User;
import br.com.jprojects.placesmanagement.service.UserService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public ResponseEntity<Response<List<UserDto>>> getAll() {
		Response<List<UserDto>> response = new Response<>();

		List<User> users = userService.findAll();
		List<UserDto> dto = UserDto.listConverter(users);

		response.setData(dto);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<UserDto>> register(@RequestBody @Valid UserDto dto, BindingResult result,
			UriComponentsBuilder uriBuilder) throws InvalidUserException {
		Response<UserDto> response = new Response<>();

		if (result.hasErrors()) {
			throw new InvalidUserException(result);
		}
		
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));

		User user = dto.convertDtoToEntity();
		User savedUser = userService.save(user);

		UserDto savedDto = new UserDto(savedUser);

		response.setData(savedDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

}
