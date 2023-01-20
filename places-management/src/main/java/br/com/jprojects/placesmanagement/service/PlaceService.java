package br.com.jprojects.placesmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.jprojects.placesmanagement.model.Place;

public interface PlaceService {
	
	public Place save(Place place);
	
	public Page<Place> findAll(Pageable pageable);
	
	public Optional<List<Place>> findByName(String name, Pageable pageable);
	
	public boolean existsById(Integer id);
	
	public Place getPlaceById(Integer id);

}
