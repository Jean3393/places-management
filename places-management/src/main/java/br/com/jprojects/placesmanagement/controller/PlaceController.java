package br.com.jprojects.placesmanagement.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.jprojects.placesmanagement.dto.model.PlaceDto;
import br.com.jprojects.placesmanagement.dto.response.Response;
import br.com.jprojects.placesmanagement.exception.InvalidPlaceException;
import br.com.jprojects.placesmanagement.exception.PlaceNotFoundException;
import br.com.jprojects.placesmanagement.model.Place;
import br.com.jprojects.placesmanagement.service.PlaceService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/places")
public class PlaceController {

	@Autowired
	private PlaceService placeService;

	@GetMapping(params = "name")
	@ApiOperation(value = "Route to find a place by it's name on the API")
	public ResponseEntity<Response<List<PlaceDto>>> getByName(@RequestParam(value = "name") String name,
			@PageableDefault(page = 0, size = 10) Pageable pageable) throws PlaceNotFoundException {

		Response<List<PlaceDto>> response = new Response<>();

		Optional<List<Place>> place = placeService.findByName(name, pageable);
		if (place.get().isEmpty()) {
			throw new PlaceNotFoundException("No place was found with the name: " + name);
		}

		List<PlaceDto> placeDto = PlaceDto.listConverter(place.get());
		
		placeDto.forEach(dto -> {
			Link link = WebMvcLinkBuilder.linkTo(PlaceController.class).slash(dto.getId()).withSelfRel();
			dto.add(link);
		});
		
		response.setData(placeDto);
		return ResponseEntity.ok(response);

	}

	@GetMapping
	@ApiOperation(value = "Route to find all places of the API")
	public ResponseEntity<Response<Page<PlaceDto>>> getAll(@PageableDefault(page = 0, size = 10) Pageable pageable) {

		Response<Page<PlaceDto>> response = new Response<>();

		Page<Place> places = placeService.findAll(pageable);
		Page<PlaceDto> dto = PlaceDto.pageConverter(places);
		
		dto.forEach(placeDto -> {
			Link link = WebMvcLinkBuilder.linkTo(PlaceController.class).slash(placeDto.getId()).withSelfRel();
			placeDto.add(link);
		});

		response.setData(dto);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	@ApiOperation(value = "Route to create places")
	public ResponseEntity<Response<PlaceDto>> save(@RequestBody @Valid PlaceDto dto, BindingResult result,
			UriComponentsBuilder uriBuilder) throws InvalidPlaceException {

		Response<PlaceDto> response = new Response<>();

		if (result.hasErrors()) {
			throw new InvalidPlaceException(result);
		}

		Place place = dto.convertDtoToEntity();
		Place savedPlace = placeService.save(place);

		PlaceDto savedDto = new PlaceDto(savedPlace);

		URI uri = uriBuilder.path("/api/places/{id}").buildAndExpand(place.getId()).toUri();
		
		Link link = WebMvcLinkBuilder.linkTo(PlaceController.class).slash(savedDto.getId()).withSelfRel();
		savedDto.add(link);

		response.setData(savedDto);
		return ResponseEntity.created(uri).body(response);

	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Route to find a place by it's id on the API")
	public ResponseEntity<Response<PlaceDto>> getById(@PathVariable Integer id) throws PlaceNotFoundException {

		Response<PlaceDto> response = new Response<>();

		if (!placeService.existsById(id)) {
			throw new PlaceNotFoundException("No place was found with the id: " + id);
		}

		Place place = placeService.getPlaceById(id);
		PlaceDto dto = new PlaceDto(place);
		
		Link link = WebMvcLinkBuilder.linkTo(PlaceController.class).slash(place.getId()).withSelfRel();
		dto.add(link);

		response.setData(dto);
		return ResponseEntity.ok(response);

	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Route to update a place")
	@Transactional
	public ResponseEntity<Response<PlaceDto>> update(@PathVariable Integer id, @RequestBody @Valid PlaceDto dto)
			throws PlaceNotFoundException {

		Response<PlaceDto> response = new Response<>();

		if (!placeService.existsById(id)) {
			throw new PlaceNotFoundException("No place was found with the id: " + id);
		}

		dto.setId(id);

		Place placeToUpdate = placeService.getPlaceById(id);
		placeToUpdate.updatePlace(dto);
		placeService.save(placeToUpdate);
		PlaceDto updatedDto = new PlaceDto(placeToUpdate);
		
		Link link = WebMvcLinkBuilder.linkTo(PlaceController.class).slash(updatedDto.getId()).withSelfRel();
		updatedDto.add(link);

		response.setData(updatedDto);
		return ResponseEntity.ok(response);
	}

}
