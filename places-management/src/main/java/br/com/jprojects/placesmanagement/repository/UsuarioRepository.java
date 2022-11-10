package br.com.jprojects.placesmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jprojects.placesmanagement.model.User;

@Repository
public interface UsuarioRepository extends JpaRepository<User, Integer>{
	
	Optional<User> findByEmail(String email);
	
}
