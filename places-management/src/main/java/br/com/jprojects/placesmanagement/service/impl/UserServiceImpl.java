package br.com.jprojects.placesmanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jprojects.placesmanagement.model.User;
import br.com.jprojects.placesmanagement.repository.UserRepository;
import br.com.jprojects.placesmanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository repository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.repository = userRepository;
	}

	@Override
	public User save(User user) {
		return repository.save(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

}
