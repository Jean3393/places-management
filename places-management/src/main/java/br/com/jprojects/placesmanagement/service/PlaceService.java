package br.com.jprojects.placesmanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.jprojects.placesmanagement.model.Place;
import br.com.jprojects.placesmanagement.repository.PlaceRepository;

@Service
public class PlaceService {
	
	private PlaceRepository placeRepository;
	
	@Autowired
	public PlaceService(PlaceRepository placeRepository) {
		this.placeRepository = placeRepository;
	}
	
	public void save(Place place) {
		placeRepository.save(place);
	}
	
	public Page<Place> findAll(Pageable pageable) {
		return placeRepository.findAll(pageable);
	}
	
	public Optional<Page<Place>> findByName(String name, Pageable pageable){
		return placeRepository.findByName(name, pageable);
	}
	
	public boolean existsById(Integer id) {
		return placeRepository.existsById(id);
	}

	public Place getPlaceById(Integer id) {
		return placeRepository.getReferenceById(id);
	}

}
