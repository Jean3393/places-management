package br.com.jprojects.placesmanagement.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.jprojects.placesmanagement.model.Place;

public class PlaceDto {
	
	private int id;
	private String name;
	private String slug;
	private String city;
	private String state;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
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
	public static Page<PlaceDto> converter(Page<Place> places) {
		Page<PlaceDto> placesList = places.map(PlaceDto::new);
		return placesList;
	}

}
