package com.example.restaurant.restaurants;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.menuItem.MenuItem;

@RestController
@RequestMapping(path = "/restaurants")
public class RestaurantsController {

	@Autowired
	private RestaurantsRepository restaurantsRepository;
	private RestaurantsDTO restaurantsDTO;
	
	@GetMapping(path = "/all")
	public @ResponseBody List<RestaurantsDTO> getAllRestaurants() {
		Iterable<Restaurants> allRestaurants = restaurantsRepository.findAll();
		List<RestaurantsDTO> restaurantDTOs = new ArrayList<>();
		
		for (Restaurants restaurant : allRestaurants) {
			RestaurantsDTO dto = new RestaurantsDTO();
		    dto.setId(restaurant.getId());
		    dto.setRestaurant_name(restaurant.getRestaurant_name());
		    dto.setCity(restaurant.getCity());
		    dto.setType(restaurant.getType());
		    restaurantDTOs.add(dto);
		}
		
		return restaurantDTOs;
		
	}

	@GetMapping(path = "/findbyid/{id}")
	public @ResponseBody Optional<Restaurants> findById(@PathVariable Integer id) {
		return restaurantsRepository.findById(id);
	}

	@GetMapping(path = "/findbycity/")
	public @ResponseBody List<Restaurants> findByCity(@RequestParam String city) {
		return restaurantsRepository.findByCity(city);
	}

	@GetMapping(path = "/findbycityandtype/")
	public @ResponseBody Optional<Restaurants> findByCityAndType(@RequestParam String city, @RequestParam String type) {
		return restaurantsRepository.findByCityAndType(city, type);
	}



	@PostMapping(path = "/add")
	public ResponseEntity<Restaurants> addRestaurant(@RequestBody Restaurants restaurants) {
		String name = restaurants.getRestaurant_name();
		String city = restaurants.getCity();
		String type = restaurants.getType();

		Restaurants n = new Restaurants();
		n.setRestaurant_name(name);
		n.setCity(city);
		n.setType(type);

		Restaurants addedRestaurant = restaurantsRepository.save(n);

		return new ResponseEntity<Restaurants>(addedRestaurant, HttpStatus.CREATED);
	}


}
