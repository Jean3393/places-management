package br.com.jprojects.placesmanagement.dto.model;

import java.time.LocalDateTime;

import br.com.jprojects.placesmanagement.model.Place;

public class UpdatedPlaceDto {
	
	private int id;
	private String name;
	private String slug;
	private String city;
	private String state;
	private LocalDateTime updated;
	
	public UpdatedPlaceDto(Place place) {
		this.id = place.getId();
		this.slug = place.getSlug();
		this.name = place.getName();
		this.state = place.getState();
		this.city = place.getCity();
		this.updated = place.getUpdatedAt();
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
	public LocalDateTime getUpdated() {
		return updated;
	}
	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

}
