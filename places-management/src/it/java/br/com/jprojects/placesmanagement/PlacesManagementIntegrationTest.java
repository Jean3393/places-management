package br.com.jprojects.placesmanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import br.com.jprojects.placesmanagement.dto.model.UserDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
public class PlacesManagementIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Order(1)
	public void createUser() {
		UserDto userDto = new UserDto("New User", "userPassword", "test@email.com", "ROLE_USER");

		HttpEntity<UserDto> entityRequest = new HttpEntity<UserDto>(userDto);

		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange("http://localhost:" + this.port + "/api/users", HttpMethod.POST, entityRequest, String.class);
		
		assertEquals(201, responseEntity.getStatusCodeValue());

	}

}
