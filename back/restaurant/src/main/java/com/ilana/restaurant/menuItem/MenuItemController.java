package com.example.restaurant.menuItem;

import java.util.List;
import java.util.Optional;
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

import com.example.restaurant.restaurants.Restaurants;
import com.example.restaurant.restaurants.RestaurantsRepository;

@RestController
@RequestMapping(path = "/menu")
public class MenuItemController {

	@Autowired
	private MenuItemRepository menuItemRepository;
	@Autowired
	private RestaurantsRepository restaurantsRepository;



	@PostMapping(path = "/add-item/")
	public @ResponseBody ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem,
			@RequestParam Integer restaurantId) {
		String item = menuItem.getItem();
		Integer price = menuItem.getPrice();
		String description = menuItem.getDescription();

		MenuItem n = new MenuItem();
		n.setItem(item);
		n.setPrice(price);
		n.setDescription(description);
		n.setRestaurantId(restaurantId);
		MenuItem addedItem = menuItemRepository.save(n);

		return new ResponseEntity<MenuItem>(addedItem, HttpStatus.CREATED);
	}

	@GetMapping(path="all/")
	public @ResponseBody ResponseEntity<Iterable<MenuItem>> getMenu(@RequestParam Integer restaurantId) {
		List<MenuItem> menuItems = menuItemRepository.findByRestaurantId(restaurantId);

		if (!menuItems.isEmpty()) {
			return new ResponseEntity<>(menuItems, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
