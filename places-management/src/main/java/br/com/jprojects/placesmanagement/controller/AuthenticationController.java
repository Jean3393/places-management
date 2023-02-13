package br.com.jprojects.placesmanagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jprojects.placesmanagement.dto.model.JwtUserDto;
import br.com.jprojects.placesmanagement.dto.model.TokenDto;
import br.com.jprojects.placesmanagement.dto.response.Response;
import br.com.jprojects.placesmanagement.service.AuthenticationService;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping
	public ResponseEntity<Response<TokenDto>> authenticate(@RequestBody @Valid JwtUserDto dto) {
		
		Response<TokenDto> response = new Response<>();
		
		TokenDto tokenDto = authenticationService.authenticate(dto);
		
		response.setData(tokenDto);
		return ResponseEntity.ok(response);
		
	}

}
