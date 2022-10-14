package br.com.jprojects.placesmanagement.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.jprojects.placesmanagement.model.Place;

public class PlaceDto {
	
	private int id;
	private String name;
	private String slug;
	private String city;
	private String state;
	
	public PlaceDto(Place place) {
		this.id = place.getId();
		this.slug = place.getSlug();
		this.name = place.getName();
		this.state = place.getState();
		this.city = place.getCity();
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
	public static List<PlaceDto> converter(List<Place> places) {
		List<PlaceDto> placesList = places.stream().map(PlaceDto::new).collect(Collectors.toList());
		return placesList;
	}
	
	

}
