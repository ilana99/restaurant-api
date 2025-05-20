package com.ilana.restaurant.restaurants;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ilana.restaurant.restaurants.Restaurants;

public interface RestaurantsRepository extends CrudRepository<Restaurants, Integer> {

	List<Restaurants> findByCity(String city);
	
	List<Restaurants> findByType(String type);
	
	Optional<Restaurants> findByCityAndType(String city, String type);
	
}
