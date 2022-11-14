package br.com.jprojects.placesmanagement.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.jprojects.placesmanagement.model.User;
import br.com.jprojects.placesmanagement.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService{
	
	@Autowired
	private UserRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = usuarioRepository.findByEmail(email);
		
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new UsernameNotFoundException("Invalid data.");
	}

}
