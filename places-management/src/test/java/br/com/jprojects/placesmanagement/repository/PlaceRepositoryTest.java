package br.com.jprojects.placesmanagement.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import br.com.jprojects.placesmanagement.model.Place;

@SpringBootTest
@ActiveProfiles("test")
class PlaceRepositoryTest {
	
	@Autowired
	private PlaceRepository underTest;

	@AfterEach
	void tearDown() throws Exception {
		underTest.deleteAll();
	}

	@Test
	void shouldReturnTrueWhenSearchingAPlaceByItsName() {
		Place place = new Place("Name", "Slug", "City", "State");
		underTest.save(place);
		
		Optional<List<Place>> test = underTest.findByName("Name", Pageable.ofSize(1));
		boolean expected = test.get().isEmpty();
		
		assertFalse(expected);
	}
	
	@Test
	void shouldReturnFalseWhenSearchingAPlaceByItsWrongName() {
		Optional<List<Place>> test = underTest.findByName("Name", Pageable.ofSize(1));
		boolean expected = test.get().isEmpty();
		
		assertTrue(expected);
	}

}
