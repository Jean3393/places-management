package br.com.jprojects.placesmanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.jprojects.placesmanagement.model.Place;
import br.com.jprojects.placesmanagement.repository.PlaceRepository;
import br.com.jprojects.placesmanagement.service.PlaceService;

@Service
public class PlaceServiceImpl implements PlaceService {
	
	private PlaceRepository placeRepository;
	
	@Autowired
	public PlaceServiceImpl(PlaceRepository placeRepository) {
		this.placeRepository = placeRepository;
	}
	
	@Override
	public Place save(Place place) {
		return placeRepository.save(place);
	}
	
	@Override
	@Cacheable(value = "allPlacesCache", key = "#pageable")
	public Page<Place> findAll(Pageable pageable) {
		return placeRepository.findAll(pageable);
	}
	
	@Override
	public Optional<List<Place>> findByName(String name, Pageable pageable){
		return placeRepository.findByName(name, pageable);
	}
	
	@Override
	public boolean existsById(Integer id) {
		return placeRepository.existsById(id);
	}
	
	@Override
	@Cacheable(value = "placeIdCache", key = "#id")
	public Place getPlaceById(Integer id) {
		return placeRepository.findById(id).get();
	}

}
