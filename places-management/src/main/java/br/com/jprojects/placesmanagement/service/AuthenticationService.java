package br.com.jprojects.placesmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.jprojects.placesmanagement.dto.model.JwtUserDto;
import br.com.jprojects.placesmanagement.dto.model.TokenDto;

@Service
public class AuthenticationService {

	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;

	@Autowired
	public AuthenticationService(JwtService jwtService, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
	}

	public TokenDto authenticate(JwtUserDto dto) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						dto.getUsername(), 
						dto.getPassword()
				)
		);
		
		UserDetails user = userDetailsService.loadUserByUsername(dto.getUsername());
		String token = jwtService.generateToken(user);
		
		return new TokenDto(token);
	}

}
