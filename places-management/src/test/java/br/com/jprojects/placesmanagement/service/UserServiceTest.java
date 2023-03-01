package br.com.jprojects.placesmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.jprojects.placesmanagement.enumeration.RoleEnum;
import br.com.jprojects.placesmanagement.model.User;
import br.com.jprojects.placesmanagement.repository.UserRepository;

@SpringBootTest
class UserServiceTest {
	
	@Autowired
	private UserService underTest;
	
	@MockBean
	private UserRepository repository;
	
	@Test
	void testSave() {
		//given
		User user = new User(1, "name", "password", "test@email.com", RoleEnum.ROLE_USER);
		
		//when
		underTest.save(user);
		
		//then
		ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
		
		verify(repository).save(userArgumentCaptor.capture());
		
		User userCaptured = userArgumentCaptor.getValue();
		
		assertEquals(userCaptured, user);		
		
	}

	@Test
	void testFindByEmail() {
		underTest.findByEmail("");
		
		verify(repository).findByEmail("");
	}

	@Test
	void testFindAll() {
		underTest.findAll();
		
		verify(repository).findAll();
	}

}
