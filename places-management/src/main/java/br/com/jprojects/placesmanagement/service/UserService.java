package br.com.jprojects.placesmanagement.service;

import java.util.List;
import java.util.Optional;

import br.com.jprojects.placesmanagement.model.User;

public interface UserService {
	
	public User save(User user);
	
	public Optional<User> findByEmail(String email);
	
	public List<User> findAll();

}
