package br.com.jprojects.placesmanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jprojects.placesmanagement.dto.model.UserDto;
import br.com.jprojects.placesmanagement.dto.response.Response;
import br.com.jprojects.placesmanagement.exception.InvalidUserException;
import br.com.jprojects.placesmanagement.model.User;
import br.com.jprojects.placesmanagement.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

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
		
		Link link = WebMvcLinkBuilder.linkTo(UserController.class).slash(savedDto.getId()).withSelfRel();
		savedDto.add(link);

		response.setData(savedDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);

	}

}
