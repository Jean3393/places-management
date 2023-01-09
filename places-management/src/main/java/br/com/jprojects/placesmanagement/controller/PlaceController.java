package br.com.jprojects.placesmanagement.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import br.com.jprojects.placesmanagement.service.PlaceService;

@RestController
@RequestMapping("api/places")
public class PlaceController {
	
	@Autowired
	private PlaceService placeService;
	
	@GetMapping("/{name}")
	public ResponseEntity<Page<PlaceDto>> getPlaceByName(@PathVariable String name,@PageableDefault(page = 0, size = 10) Pageable pageable) throws Exception{
		
		Optional<Page<Place>> place = placeService.findByName(name, pageable);
		if(place.isEmpty()) {
			throw new Exception("No place was found with this name.");
		}
		
		Page<PlaceDto> placeDto = PlaceDto.converter(place.get());
		return ResponseEntity.ok(placeDto);
		
	}
	
	@GetMapping
	public ResponseEntity<Page<PlaceDto>> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable){
		Page<Place> places = placeService.findAll(pageable);
		Page<PlaceDto> dto = PlaceDto.converter(places);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<PlaceDto> register(@RequestBody @Valid PlaceForm form, UriComponentsBuilder uriBuilder){
		Place place = form.converter();
		placeService.save(place);
		
		URI uri = uriBuilder.path("/api/places/{id}").buildAndExpand(place.getId()).toUri();
		return ResponseEntity.created(uri).body(new PlaceDto(place));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PlaceDto> getPlace(@PathVariable Integer id) {
		if(placeService.existsById(id)) {
			Place place = placeService.getPlaceById(id);
			return ResponseEntity.ok(new PlaceDto(place));
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UpdatedPlaceDto> update(@PathVariable Integer id,@RequestBody @Valid UpdatedPlaceForm form) {
		if(placeService.existsById(id)) {
			Place place = form.converter(id, placeService);
			UpdatedPlaceDto dto = new UpdatedPlaceDto(place);
			
			return ResponseEntity.ok(dto);
		}
		
		return ResponseEntity.notFound().build();
	}

}
