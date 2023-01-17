package br.com.jprojects.placesmanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.jprojects.placesmanagement.model.Place;
import br.com.jprojects.placesmanagement.repository.PlaceRepository;

@SpringBootTest
class PlaceServiceTest {
	
	@Autowired
	private PlaceService service;
	
	@MockBean
	private PlaceRepository repository;

	@Test
	void saveTest() {
		Place place = new Place(anyString(), "Bar", anyString(), anyString());
		
		when(repository.save(Mockito.any(Place.class))).thenReturn(place);
		
		Place testPlace = service.save(new Place());
		
		assertNotNull(testPlace);
		assertEquals("Bar", testPlace.getSlug());
	}
	
	@Test
	void findAllTest() {
		List<Place> places = new ArrayList<>();
		Pageable pageable = Pageable.ofSize(1);
		Page<Place> page = new PageImpl<>(places, pageable, 1);
		
		when(repository.findAll(Mockito.any(Pageable.class))).thenReturn(page);
		
		Page<Place> testPlaces = service.findAll(pageable);
		
		assertNotNull(testPlaces);
		assertEquals(testPlaces.getSize(), 1);
	}

}
