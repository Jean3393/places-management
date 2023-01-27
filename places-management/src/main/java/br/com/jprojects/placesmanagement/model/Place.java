package br.com.jprojects.placesmanagement.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.modelmapper.ModelMapper;

import br.com.jprojects.placesmanagement.dto.PlaceDto;

@Entity
public class Place {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	private String slug;
	private String city;
	private String state;
	private LocalDateTime createdAt = LocalDateTime.now();
	private LocalDateTime updatedAt;
	
	
	public Place() {
	}
	
	public Place(String name, String slug, String city, String state) {
		this.name = name;
		this.slug = slug;
		this.city = city;
		this.state = state;
	}
	
	public Place(Integer id, String name, String slug, String city, String state) {
		this.id = id;
		this.name = name;
		this.slug = slug;
		this.city = city;
		this.state = state;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public PlaceDto convertEntityToDto() {
		return new ModelMapper().map(this, PlaceDto.class);
	}
	
	public void updatePlace(PlaceDto dto) {
		setId(dto.getId());
		setName(dto.getName());
		setSlug(dto.getSlug());
		setCity(dto.getCity());
		setState(dto.getState());
		setUpdatedAt(LocalDateTime.now());
	}

}
