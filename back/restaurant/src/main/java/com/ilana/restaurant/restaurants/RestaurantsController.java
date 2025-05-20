package com.ilana.restaurant.restaurants;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ilana.restaurant.menuItem.MenuItem;
import com.ilana.restaurant.service.Patcher;


@RestController
@RequestMapping(path = "/restaurants")
public class RestaurantsController {

	@Autowired
	private RestaurantsRepository restaurantsRepository;
	@Autowired
	private Patcher patcher;
	
	@GetMapping(path = "/all")
	public List<RestaurantsDTO> getAllRestaurants() {
		Iterable<Restaurants> allRestaurants = restaurantsRepository.findAll();
		List<RestaurantsDTO> restaurantDTOs = new ArrayList<>();
		
		for (Restaurants restaurant : allRestaurants) {
			RestaurantsDTO dto = new RestaurantsDTO();
		    dto.setId(restaurant.getId());
		    dto.setRestaurant_name(restaurant.getRestaurant_name());
		    dto.setCity(restaurant.getCity());
		    dto.setType(restaurant.getType());
		    dto.setCoverPath(restaurant.getCoverPath());
		    restaurantDTOs.add(dto);
		}
		
		return restaurantDTOs;
		
	}

	@GetMapping(path = "/findbyid/{id}")
	public  Optional<Restaurants> findById(@PathVariable Integer id) {
		return restaurantsRepository.findById(id);
	}

	@GetMapping(path = "/findbycity/")
	public  List<Restaurants> findByCity(@RequestParam String city) {
		return restaurantsRepository.findByCity(city);
	}
	
	@GetMapping(path = "/findbytype/")
	public  List<Restaurants> findByType(@RequestParam String type) {
		return restaurantsRepository.findByType(type);
	}

	@GetMapping(path = "/findbycityandtype/")
	public  Optional<Restaurants> findByCityAndType(@RequestParam String city, @RequestParam String type) {
		return restaurantsRepository.findByCityAndType(city, type);
	}



	@PostMapping(path = "/add")
	public ResponseEntity<Restaurants> addRestaurant(@RequestBody RestaurantsDTO restaurantsDTO) {
		String name = restaurantsDTO.getRestaurant_name();
		String city = restaurantsDTO.getCity();
		String type = restaurantsDTO.getType();
		String cover = restaurantsDTO.getCoverPath();

		Restaurants restaurant = new Restaurants();
		restaurant.setRestaurant_name(name);
		restaurant.setCity(city);
		restaurant.setType(type);
		restaurant.setCoverPath(cover);

		Restaurants addedRestaurant = restaurantsRepository.save(restaurant);

		return new ResponseEntity<Restaurants>(addedRestaurant, HttpStatus.CREATED);
	}
	
	@PatchMapping(path="/modify/{id}")
	public  ResponseEntity<Restaurants> modifyRestaurant(@RequestBody Map<String, Object> updates, @PathVariable Integer id) {
		Optional<Restaurants> restaurantOptional = restaurantsRepository.findById(id);
		
		if (restaurantOptional.isPresent()) {
			Restaurants restaurant = restaurantOptional.get();
			patcher.patchRequest(restaurant, id, updates);
			restaurantsRepository.save(restaurant);
			return new ResponseEntity<>(restaurant, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping(path="/delete/{id}")
	public  ResponseEntity<String> deleteRestaurant(@PathVariable Integer id) {
		Optional<Restaurants> restaurantOptional = restaurantsRepository.findById(id);
		if (restaurantOptional.isPresent()) {
			Restaurants restaurant = restaurantOptional.get();
			restaurantsRepository.delete(restaurant);
			return new ResponseEntity<>("deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("not found", HttpStatus.OK);
		}
	}
	 


}
