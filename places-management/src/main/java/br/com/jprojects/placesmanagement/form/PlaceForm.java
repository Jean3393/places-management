package br.com.jprojects.placesmanagement.form;

import br.com.jprojects.placesmanagement.model.Place;

public class PlaceForm {
	
	private String name;
	private String slug;
	private String city;
	private String state;
	
	public PlaceForm(String name, String slug, String city, String state) {
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

	public Place converter() {
		return new Place(name, slug, city, state);
	}

}
