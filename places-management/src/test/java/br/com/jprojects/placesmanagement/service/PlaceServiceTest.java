package br.com.jprojects.placesmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;

import br.com.jprojects.placesmanagement.model.Place;
import br.com.jprojects.placesmanagement.repository.PlaceRepository;

@SpringBootTest
class PlaceServiceTest {
	
	@Autowired
	private PlaceService service;
	
	@MockBean
	private PlaceRepository repository;
	
	@Mock
	private Pageable pageable;

	
	@Test
	void saveTest() {
		//given
		Place place = new Place("Name", "Bar", "City", "State");
		
		//when
		service.save(place);
		
		//then
		ArgumentCaptor<Place> placeArgumentCaptor = ArgumentCaptor.forClass(Place.class);
		
		verify(repository).save(placeArgumentCaptor.capture());
		
		Place placeCaptured = placeArgumentCaptor.getValue();
		
		assertEquals(place, placeCaptured);
	}
	
	@Test
	void findAllTest() {
		//when
		service.findAll(pageable);
		
		//then
		verify(repository).findAll(pageable);
	}
	
	@Test
	void findByNameTest() {
		service.findByName("", pageable);
		
		verify(repository).findByName("", pageable);
	}

}
