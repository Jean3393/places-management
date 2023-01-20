package br.com.jprojects.placesmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jprojects.placesmanagement.model.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer>{
	
	Optional<List<Place>> findByName(String name, Pageable pageable);

}
