package com.ilana.restaurant.menu;

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
import org.springframework.web.servlet.resource.NoResourceFoundException;


import com.ilana.restaurant.config.ApiResponse;
import com.ilana.restaurant.restaurants.Restaurants;
import com.ilana.restaurant.restaurants.RestaurantsDTO;
import com.ilana.restaurant.restaurants.RestaurantsRepository;
import com.ilana.restaurant.service.Patcher;


@RestController
@RequestMapping(path = "/menu")
public class MenuItemController {

	@Autowired
	private Patcher patcher;
	@Autowired
	private MenuItemRepository menuItemRepository;
	@Autowired
	private RestaurantsRepository restaurantsRepository;
	
	@GetMapping(path="/{id}")
	public ResponseEntity<ApiResponse<MenuDTO>> getMenu(@PathVariable Integer restaurantId)  {
	
		Optional<Restaurants> optionalRestaurant = restaurantsRepository.findById(restaurantId);
		Restaurants restaurant = optionalRestaurant.orElseThrow(() -> 
			new RuntimeException("no restaurant with id:" + restaurantId));
		
		List<MenuItem> menu = restaurant.getMenu();
	
		
		List<MenuItemDTO> ListmenuItemDTO = menu.stream()
				.map(menuItem -> {
					MenuItemDTO menuItemDTO = new MenuItemDTO();
					menuItemDTO.setDescription(menuItem.getDescription());
					menuItemDTO.setId(menuItem.getId());
					menuItemDTO.setImagePath(menuItem.getImagePath());
					menuItemDTO.setPrice(menuItem.getPrice());
					menuItemDTO.setItem(menuItem.getItem());
					return menuItemDTO;
				})
				.collect(Collectors.toList());
		
		MenuDTO menuDTO = new MenuDTO();
		menuDTO.setMenu(ListmenuItemDTO);
		menuDTO.setRestaurantId(restaurant.getId());
		
		return ResponseEntity.ok(new ApiResponse<>(menuDTO));
	
		
	}

	@PostMapping
	public ResponseEntity<ApiResponse<MenuItemDTO>> addItem(@RequestBody MenuItem menuItem,
			@RequestParam Integer restaurantId) {
		Optional<Restaurants> optionalRestaurant = restaurantsRepository.findById(restaurantId);
		Restaurants restaurant = optionalRestaurant.orElseThrow(() -> 
			new RuntimeException("no restaurant with id:" + restaurantId));
		
		String item = menuItem.getItem();
		Integer price = menuItem.getPrice();
		String description = menuItem.getDescription();
		String image = menuItem.getImagePath();
		
		MenuItem n = new MenuItem();
		n.setItem(item);
		n.setPrice(price);
		n.setDescription(description);
		n.setRestaurant(restaurant);
		n.setImagePath(image);
		menuItemRepository.save(n);
		
		MenuItemDTO dto = new MenuItemDTO();
		dto.setDescription(n.getDescription());
		dto.setId(n.getId());
		dto.setPrice(n.getPrice());
		dto.setItem(n.getItem());
		dto.setImagePath(n.getImagePath());
		
		return ResponseEntity.ok(new ApiResponse<>(dto)); 
		
	}

	
	
	@DeleteMapping
	public ResponseEntity<ApiResponse<String>> deleteItem(@RequestParam Integer restaurantId, @RequestParam Integer itemId) {

		Optional<Restaurants> optionalRestaurant = restaurantsRepository.findById(restaurantId);
		Restaurants restaurant = optionalRestaurant.orElseThrow(() -> 
			new RuntimeException("no restaurant with id:" + restaurantId));
		
		Optional<MenuItem> itemOptional = menuItemRepository.findById(itemId);
		MenuItem item = itemOptional.orElseThrow(() -> new RuntimeException("no item with id:" + itemId));
		
	
		
		if (restaurant.getId() == item.getRestaurant().getId()) {
			menuItemRepository.delete(item);
		} else {
			throw new RuntimeException("error");
		}
		
		
		return ResponseEntity.ok(new ApiResponse<>("item deleted"));
		
	}
	
	@PatchMapping
	public ResponseEntity<MenuItemDTO> modifyItem(@RequestBody Map<String, Object> updates, @RequestParam Integer restaurantId, @RequestParam Integer itemId) {

		
		Optional<Restaurants> optionalRestaurant = restaurantsRepository.findById(restaurantId);
		Restaurants restaurant = optionalRestaurant.orElseThrow(() -> 
			new RuntimeException("no restaurant with id:" + restaurantId));
		
		Optional<MenuItem> itemOptional = menuItemRepository.findById(itemId);
		MenuItem item = itemOptional.orElseThrow(() -> new RuntimeException("no item with id:" + itemId));
		
		if (restaurant.getId() == item.getRestaurant().getId()) {
			patcher.patchRequest(item, itemId, updates);
			menuItemRepository.save(item);
			
			MenuItemDTO dto = new MenuItemDTO();
			dto.setDescription(item.getDescription());
			dto.setId(item.getId());
			dto.setImagePath(item.getImagePath());
			dto.setItem(item.getItem());
			dto.setPrice(item.getPrice());
			
			return ResponseEntity.ok(dto);
			
		} else {
			throw new RuntimeException("error");
		}
	}

}
