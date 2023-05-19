package br.com.jprojects.placesmanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.json.JSONException;
import org.json.JSONObject;
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

import br.com.jprojects.placesmanagement.dto.model.JwtUserDto;
import br.com.jprojects.placesmanagement.dto.model.PlaceDto;
import br.com.jprojects.placesmanagement.dto.model.UserDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test")
public class PlacesManagementIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String token;

	@Test
	@Order(1)
	public void createUser() {
		UserDto userDto = new UserDto("New User", "userPassword", "test@email.com", "ROLE_USER");

		HttpEntity<UserDto> entityRequest = new HttpEntity<UserDto>(userDto);

		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange("http://localhost:" + this.port + "/api/users", HttpMethod.POST, entityRequest, String.class);

		assertEquals(201, responseEntity.getStatusCodeValue());

	}

	@Test
	@Order(2)
	public void authenticatingUser() throws JSONException {
		JwtUserDto dto = new JwtUserDto("test@email.com", "userPassword");

		HttpEntity<JwtUserDto> entityRequest = new HttpEntity<JwtUserDto>(dto);

		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange("http://localhost:" + this.port + "/api/auth", HttpMethod.POST, entityRequest, String.class);

		JSONObject object = new JSONObject(responseEntity.getBody());

		token = object.getJSONObject("data").getString("token");

		assertEquals(200, responseEntity.getStatusCodeValue());
		assertNotNull(token);

	}

	@Test
	@Order(3)
	public void savePlace() {
		PlaceDto dto = new PlaceDto("Place One", "Nice Place", "City One", "State One");

		HttpEntity<PlaceDto> entityRequest = new HttpEntity<PlaceDto>(dto);

		ResponseEntity<String> responseEntity = this.restTemplate.exchange(
				"http://localhost:" + this.port + "/api/places", HttpMethod.POST, entityRequest, String.class);

		assertEquals(201, responseEntity.getStatusCodeValue());
		
	}

	@Test
	@Order(4)
	public void getAllPlaces() throws JSONException {

		PlaceDto dto1 = new PlaceDto("Place Two", "Great Place", "City Two", "State Two");
		PlaceDto dto2 = new PlaceDto("Place Three", "Amazing Place", "City Three", "State Three");

		HttpEntity<PlaceDto> entityRequest1 = new HttpEntity<PlaceDto>(dto1);
		HttpEntity<PlaceDto> entityRequest2 = new HttpEntity<PlaceDto>(dto2);

		this.restTemplate.exchange("http://localhost:" + this.port + "/api/places", HttpMethod.POST, entityRequest1,
				String.class);

		this.restTemplate.exchange("http://localhost:" + this.port + "/api/places", HttpMethod.POST, entityRequest2,
				String.class);

		HttpEntity<String> entityRequest = new HttpEntity<String>("");
		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange("http://localhost:" + this.port + "/api/places", HttpMethod.GET, entityRequest, String.class);
		
		JSONObject object = new JSONObject(responseEntity.getBody());
		int numberOfSavedPlaces = object.getJSONObject("data").getInt("totalElements");
		
		assertEquals(3, numberOfSavedPlaces);

	}
	
	@Test
	@Order(5)
	public void updateAPlace() throws JSONException {
		String newSlug = "Astonishing Updated Place";
		PlaceDto dto = new PlaceDto("Place Three", newSlug, "City Three", "State Three");
		
		HttpEntity<PlaceDto> entityRequest = new HttpEntity<PlaceDto>(dto);
		ResponseEntity<String> responseEntity = this.restTemplate
				.exchange("http://localhost:" + this.port + "/api/places/3", HttpMethod.PUT, entityRequest, String.class);
		
		JSONObject object = new JSONObject(responseEntity.getBody());
		String updatedSlug = object.getJSONObject("data").getString("slug");
		
		assertEquals(newSlug, updatedSlug);
	}

}
