package br.com.jprojects.placesmanagement.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.jprojects.placesmanagement.enumeration.RoleEnum;
import br.com.jprojects.placesmanagement.model.User;

@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryTest {
	
	@Autowired
	private UserRepository underTest;
	
	@AfterEach
	void tearDown() throws Exception {
		underTest.deleteAll();
	}
	
	@Test
	void shouldReturnTrueWhenSearchingForAnUserByItsEmail() {
		String email = "test@email.com";
		User user = new User(1, "Name", "password", email, RoleEnum.ROLE_USER);
		underTest.save(user);
		
		Optional<User> test = underTest.findByEmail(email);
		boolean expected = test.isEmpty();
		
		assertFalse(expected);
	}
	
	@Test
	void shouldReturnFalseWhenSearchingForAnUserByItsWrongEmail() {
		String email = "test@email.com";
		
		Optional<User> test = underTest.findByEmail(email);
		boolean expected = test.isEmpty();
		
		assertTrue(expected);
	}

}
