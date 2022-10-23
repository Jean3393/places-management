package br.com.jprojects.placesmanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jprojects.placesmanagement.model.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer>{
	
	Page<Place> findByName(String name, Pageable pageable);

}
