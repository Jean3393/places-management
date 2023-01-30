package br.com.jprojects.placesmanagement.dto.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import br.com.jprojects.placesmanagement.model.Place;

public class PlaceDto {
	
	private int id;
	@NotBlank(message = "Insert a name for the place.")
	private String name;
	@NotBlank(message = "Insert the slug of the place.")
	private String slug;
	@NotBlank(message = "Insert the name of the city the place is in.")
	private String city;
	@NotBlank(message = "Insert the name of the state of the city.")
	private String state;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	public PlaceDto(String name, String slug, String city, String state) {
		this.name = name;
		this.slug = slug;
		this.city = city;
		this.state = state;
	}
	
	public PlaceDto(Place place) {
		this.id = place.getId();
		this.slug = place.getSlug();
		this.name = place.getName();
		this.state = place.getState();
		this.city = place.getCity();
		this.createdAt = place.getCreatedAt();
		this.updatedAt = place.getUpdatedAt();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public static List<PlaceDto> listConverter(List<Place> places) {
		List<PlaceDto> placesList = new ArrayList<>();
		places.forEach(l ->  placesList.add(new PlaceDto(l)));
		return placesList;
	}
	public static Page<PlaceDto> pageConverter(Page<Place> places) {
		Page<PlaceDto> placesList = places.map(PlaceDto::new);
		return placesList;
	}
	public Place convertDtoToEntity() {
		return new ModelMapper().map(this, Place.class);
	}

}
