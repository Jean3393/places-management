package br.com.jprojects.placesmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class PlacesManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlacesManagementApplication.class, args);
	}

}
