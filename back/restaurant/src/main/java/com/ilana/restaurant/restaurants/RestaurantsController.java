package com.ilana.restaurant.restaurants;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ilana.restaurant.config.ApiResponse;
import com.ilana.restaurant.service.Patcher;

@RestController
@RequestMapping(path = "/restaurants")
public class RestaurantsController {

	@Autowired
	private RestaurantsRepository restaurantsRepository;
	@Autowired
	private Patcher patcher;

	@GetMapping(path = "/all")
	public ResponseEntity<ApiResponse<List<RestaurantsDTO>>> getAllRestaurants() {

		List<Restaurants> allRestaurants = restaurantsRepository.findAll();

		List<RestaurantsDTO> restaurantDTOs = allRestaurants.stream().map(restaurant -> {
			RestaurantsDTO dto = new RestaurantsDTO();
			dto.setId(restaurant.getId());
			dto.setRestaurant_name(restaurant.getRestaurant_name());
			dto.setCity(restaurant.getCity());
			dto.setType(restaurant.getType());
			dto.setCoverPath(restaurant.getCoverPath());
			return dto;
		}).collect(Collectors.toList());

		return ResponseEntity.ok(new ApiResponse<>(restaurantDTOs));
	}

	@GetMapping(path = "/findbyid/{id}")
	public ResponseEntity<ApiResponse<RestaurantsDTO>> findById(@PathVariable Integer id) {
		Optional<Restaurants> optionalRestaurant = restaurantsRepository.findById(id);
		Restaurants restaurant = optionalRestaurant
				.orElseThrow(() -> new RuntimeException("no restaurant with id:" + id));

		RestaurantsDTO dto = new RestaurantsDTO();
		dto.setCity(restaurant.getCity());
		dto.setCoverPath(restaurant.getCoverPath());
		dto.setId(restaurant.getId());
		dto.setRestaurant_name(restaurant.getRestaurant_name());
		dto.setType(restaurant.getType());

		return ResponseEntity.ok(new ApiResponse<>(dto));

	}

	@GetMapping(path = "/findbycity/")
	public ResponseEntity<ApiResponse<List<RestaurantsDTO>>> findByCity(@RequestParam String city) {
		List<Restaurants> allRestaurants = restaurantsRepository.findByCity(city);
		if (allRestaurants.isEmpty()) {
			throw new RuntimeException("no match found");
		}

		List<RestaurantsDTO> restaurantDTOs = allRestaurants.stream().map(restaurant -> {
			RestaurantsDTO dto = new RestaurantsDTO();
			dto.setId(restaurant.getId());
			dto.setRestaurant_name(restaurant.getRestaurant_name());
			dto.setCity(restaurant.getCity());
			dto.setType(restaurant.getType());
			dto.setCoverPath(restaurant.getCoverPath());
			return dto;
		}).collect(Collectors.toList());

		return ResponseEntity.ok(new ApiResponse<>(restaurantDTOs));

	}

	@GetMapping(path = "/findbytype/")
	public ResponseEntity<ApiResponse<List<RestaurantsDTO>>> findByType(@RequestParam String type) {
		List<Restaurants> allRestaurants = restaurantsRepository.findByType(type);
		if (allRestaurants.isEmpty()) {
			throw new RuntimeException("no match found");
		}

		List<RestaurantsDTO> restaurantDTOs = allRestaurants.stream().map(restaurant -> {
			RestaurantsDTO dto = new RestaurantsDTO();
			dto.setId(restaurant.getId());
			dto.setRestaurant_name(restaurant.getRestaurant_name());
			dto.setCity(restaurant.getCity());
			dto.setType(restaurant.getType());
			dto.setCoverPath(restaurant.getCoverPath());
			return dto;
		}).collect(Collectors.toList());

		return ResponseEntity.ok(new ApiResponse<>(restaurantDTOs));

	}

	@GetMapping(path = "/findbycityandtype/")
	public ResponseEntity<ApiResponse<List<RestaurantsDTO>>> findByCityAndType(@RequestParam String city,
			@RequestParam String type) {
		List<Restaurants> allRestaurants = restaurantsRepository.findByCityAndType(city, type);
		if (allRestaurants.isEmpty()) {
			throw new RuntimeException("no match found");
		}

		List<RestaurantsDTO> restaurantDTOs = allRestaurants.stream().map(restaurant -> {
			RestaurantsDTO dto = new RestaurantsDTO();
			dto.setId(restaurant.getId());
			dto.setRestaurant_name(restaurant.getRestaurant_name());
			dto.setCity(restaurant.getCity());
			dto.setType(restaurant.getType());
			dto.setCoverPath(restaurant.getCoverPath());
			return dto;
		}).collect(Collectors.toList());

		return ResponseEntity.ok(new ApiResponse<>(restaurantDTOs));

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

	@PatchMapping(path = "/modify/{id}")
	public ResponseEntity<RestaurantsDTO> modifyRestaurant(@RequestBody Map<String, Object> updates,
			@PathVariable Integer id) {
		Optional<Restaurants> optionalRestaurant = restaurantsRepository.findById(id);
		Restaurants restaurant = optionalRestaurant
				.orElseThrow(() -> new RuntimeException("no restaurant with id:" + id));

		patcher.patchRequest(restaurant, id, updates);
		restaurantsRepository.save(restaurant);
		
		RestaurantsDTO dto = new RestaurantsDTO();
		dto.setId(restaurant.getId());
		dto.setRestaurant_name(restaurant.getRestaurant_name());
		dto.setCity(restaurant.getCity());
		dto.setType(restaurant.getType());
		dto.setCoverPath(restaurant.getCoverPath());
		return new ResponseEntity<>(dto, HttpStatus.OK);

	}

	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<String> deleteRestaurant(@PathVariable Integer id) {
		Optional<Restaurants> restaurantOptional = restaurantsRepository.findById(id);
		Restaurants restaurant = restaurantOptional.orElseThrow(() -> new RuntimeException("no restaurant with id:" + id));
	
		restaurantsRepository.delete(restaurant);
		return new ResponseEntity<>("Restaurant deleted", HttpStatus.OK);
	}

}
