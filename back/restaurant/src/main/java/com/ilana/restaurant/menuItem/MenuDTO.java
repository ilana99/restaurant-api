package com.ilana.restaurant.menuItem;

import java.util.List;

import com.ilana.restaurant.restaurants.Restaurants;
import com.ilana.restaurant.restaurants.RestaurantsDTO;

public class MenuDTO {
	private RestaurantsDTO restaurant;
	private List<MenuItemDTO> menu;
	
	public RestaurantsDTO getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(RestaurantsDTO restaurant) {
		this.restaurant = restaurant;
	}
	public List<MenuItemDTO> getMenu() {
		return menu;
	}
	public void setMenu(List<MenuItemDTO> menu) {
		this.menu = menu;
	}
	
	
	
}
