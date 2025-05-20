package com.ilana.restaurant.menuItem;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ilana.restaurant.menuItem.MenuItem;

public interface MenuItemRepository extends CrudRepository<MenuItem, Integer>{
	
	List<MenuItem> findByRestaurantId(Integer restaurantId);


}
