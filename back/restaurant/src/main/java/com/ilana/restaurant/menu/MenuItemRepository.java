package com.ilana.restaurant.menu;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ilana.restaurant.menu.MenuItem;

public interface MenuItemRepository extends CrudRepository<MenuItem, Integer>{
	
	List<MenuItem> findByRestaurantId(Integer restaurantId);


}
