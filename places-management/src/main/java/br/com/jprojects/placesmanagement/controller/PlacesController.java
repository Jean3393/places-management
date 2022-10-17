package br.com.jprojects.placesmanagement.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jprojects.placesmanagement.dto.PlaceDto;
import br.com.jprojects.placesmanagement.dto.UpdatedPlaceDto;
import br.com.jprojects.placesmanagement.form.PlaceForm;
import br.com.jprojects.placesmanagement.form.UpdatedPlaceForm;
import br.com.jprojects.placesmanagement.model.Place;
import br.com.jprojects.placesmanagement.repository.PlaceRepository;

@RestController
@RequestMapping("places")
public class PlacesController {
	
	@Autowired
	private PlaceRepository placeRepository;
	
	@GetMapping
	public ResponseEntity<List<PlaceDto>> getPlaces(){
		List<Place> places = placeRepository.findAll();
		List<PlaceDto> placesDto = PlaceDto.converter(places);
		
		return ResponseEntity.ok(placesDto);
	}
	
	@PostMapping
	public ResponseEntity<PlaceDto> register(@RequestBody @Valid PlaceForm form, UriComponentsBuilder uriBuilder){
		Place place = form.converter();
		placeRepository.save(place);
		
		URI uri = uriBuilder.path("/places/{id}").buildAndExpand(place.getId()).toUri();
		return ResponseEntity.created(uri).body(new PlaceDto(place));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PlaceDto> getPlace(@PathVariable Integer id) {
		if(placeRepository.existsById(id)) {
			Place place = placeRepository.getReferenceById(id);
			return ResponseEntity.ok(new PlaceDto(place));
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UpdatedPlaceDto> update(@PathVariable Integer id,@RequestBody @Valid UpdatedPlaceForm form) {
		if(placeRepository.existsById(id)) {
			Place place = form.converter(id, placeRepository);
			UpdatedPlaceDto dto = new UpdatedPlaceDto(place);
			
			return ResponseEntity.ok(dto);
		}
		
		return ResponseEntity.notFound().build();
	}

}
