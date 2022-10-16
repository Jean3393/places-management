package br.com.jprojects.placesmanagement.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import br.com.jprojects.placesmanagement.model.Place;
import br.com.jprojects.placesmanagement.repository.PlaceRepository;

public class UpdatedPlaceForm {
	
	@NotBlank
	private String name;
	@NotBlank
	private String slug;
	@NotBlank
	private String city;
	@NotBlank
	private String state;
	private LocalDateTime update = LocalDateTime.now();

	public UpdatedPlaceForm(String name, String slug, String city, String state) {
		this.name = name;
		this.slug = slug;
		this.city = city;
		this.state = state;
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
	
	public LocalDateTime getUpdate() {
		return update;
	}

	public void setUpdate(LocalDateTime update) {
		this.update = update;
	}
	
	public Place converter(Integer id, PlaceRepository repository) {
		Place place = repository.getReferenceById(id);
		place.setName(this.name);
		place.setSlug(this.slug);
		place.setCity(this.city);
		place.setState(state);
		place.setUpdatedAt(this.update);
		return place;
	}

}
