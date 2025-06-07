package com.ilana.restaurant.menu;

import java.util.List;

import com.ilana.restaurant.restaurants.Restaurants;
import com.ilana.restaurant.restaurants.RestaurantsDTO;

public class MenuDTO {
	private Integer restaurantId;
	private List<MenuItemDTO> menu;
	
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public List<MenuItemDTO> getMenu() {
		return menu;
	}
	public void setMenu(List<MenuItemDTO> menu) {
		this.menu = menu;
	}
	
	
	
}
