package com.example.restaurant.restaurants;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.restaurant.restaurants.Restaurants;

public interface RestaurantsRepository extends CrudRepository<Restaurants, Integer> {

	List<Restaurants> findByCity(String city);
	
	Optional<Restaurants> findByType(String type);
	
	Optional<Restaurants> findByCityAndType(String city, String type);
	
}
