package br.com.jprojects.placesmanagement.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jprojects.placesmanagement.dto.PlaceDto;
import br.com.jprojects.placesmanagement.model.Place;
import br.com.jprojects.placesmanagement.service.PlaceService;

@RestController
@RequestMapping("api/places")
public class PlaceController {
	
	@Autowired
	private PlaceService placeService;
	
	@GetMapping(params = "name")
	public ResponseEntity<List<PlaceDto>> getByName(@RequestParam(value = "name") String name,@PageableDefault(page = 0, size = 10) Pageable pageable) throws Exception{
		
		Optional<List<Place>> place = placeService.findByName(name, pageable);
		if(place.get().isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		List<PlaceDto> placeDto = PlaceDto.listConverter(place.get());
		return ResponseEntity.ok(placeDto);
		
	}
	
	@GetMapping
	public ResponseEntity<Page<PlaceDto>> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable){
		Page<Place> places = placeService.findAll(pageable);
		Page<PlaceDto> dto = PlaceDto.pageConverter(places);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<PlaceDto> save(@RequestBody @Valid PlaceDto dto, UriComponentsBuilder uriBuilder){
		Place place = dto.convertDtoToEntity();
		Place savedPlace = placeService.save(place);
		
		PlaceDto savedDto = new PlaceDto(savedPlace);
		
		URI uri = uriBuilder.path("/api/places/{id}").buildAndExpand(place.getId()).toUri();
		return ResponseEntity.created(uri).body(savedDto);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PlaceDto> getById(@PathVariable Integer id) {
		if(placeService.existsById(id)) {
			Place place = placeService.getPlaceById(id);
			return ResponseEntity.ok(new PlaceDto(place));
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PlaceDto> update(@PathVariable Integer id,@RequestBody @Valid PlaceDto dto) {
		if(placeService.existsById(id)) {
			dto.setId(id);
			
			Place placeToUpdate = placeService.getPlaceById(id);
			placeToUpdate.setUpdatedAt(LocalDateTime.now());
			placeService.save(placeToUpdate);
			PlaceDto updatedDto = new PlaceDto(placeToUpdate);
			
			return ResponseEntity.ok(updatedDto);
		}
		
		return ResponseEntity.notFound().build();
	}

}
