package br.com.jprojects.placesmanagement.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.jprojects.placesmanagement.dto.PlaceDto;
import br.com.jprojects.placesmanagement.model.Place;
import br.com.jprojects.placesmanagement.service.PlaceService;

@SpringBootTest
@AutoConfigureMockMvc
class PlaceControllerTest {

	static final String URL = "/api/places";
	static final Integer ID = 1;
	static final String NAME = "Name";
	static final String SLUG = "Slug";
	static final String CITY = "City";
	static final String STATE = "State";

	@Autowired
	MockMvc MockMvc;

	@MockBean
	private PlaceService service;

	@Mock
	private Pageable pageable;

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetByName() throws Exception {
		Place place = getMockPlace();
		List<Place> list = new ArrayList<>();
		list.add(place);
		pageable = Pageable.ofSize(10);
		Optional<List<Place>> optionalPlace = Optional.of(list);
		when(service.findByName("Name", pageable)).thenReturn(optionalPlace);

		MockMvc.perform(MockMvcRequestBuilders.get(URI.create(URL.concat("?name=Name"))))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testGetAll() throws Exception {
		List<Place> list = new ArrayList<>();
		list.add(getMockPlace());
		pageable = Pageable.ofSize(10);
		Page<Place> page = new PageImpl<>(list, pageable, 0);
		when(service.findAll(pageable)).thenReturn(page);

		MockMvc.perform(MockMvcRequestBuilders.get(URI.create(URL)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testSave() throws Exception {
		Place place = getMockPlace();
		when(service.save(any(Place.class))).thenReturn(place);

		MockMvc.perform(
				MockMvcRequestBuilders.post(URI.create(URL)).content(getJsonPayload(ID, NAME, SLUG, CITY, STATE))
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
//		.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void testGetById() throws Exception {
		Place place = getMockPlace();
		when(service.existsById(anyInt())).thenReturn(true);
		when(service.getPlaceById(anyInt())).thenReturn(place);

		MockMvc.perform(MockMvcRequestBuilders.get(URI.create(URL + "/1")))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	void testUpdate() throws Exception {
		Place updatedPlace = getMockPlace();
		updatedPlace.setCity("Updated City");
		when(service.existsById(anyInt())).thenReturn(true);
		when(service.getPlaceById(anyInt())).thenReturn(updatedPlace);
		when(service.save(any(Place.class))).thenReturn(updatedPlace);

		MockMvc.perform(MockMvcRequestBuilders.put(URI.create(URL.concat("/1")))
				.content(getJsonPayload(ID, NAME, SLUG, CITY, STATE)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

	}

	private Place getMockPlace() {
		return new Place(ID, NAME, SLUG, CITY, STATE);
	}

	private String getJsonPayload(Integer id, String name, String slug, String city, String state)
			throws JsonProcessingException {
		Place place = new Place(id, name, slug, city, state);
		PlaceDto dto = new PlaceDto(place);

		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

		return mapper.writeValueAsString(dto);
	}

}
