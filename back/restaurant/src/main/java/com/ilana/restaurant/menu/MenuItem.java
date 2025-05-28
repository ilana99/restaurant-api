package com.ilana.restaurant.menu;


import com.ilana.restaurant.restaurants.Restaurants;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MenuItem {

	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurants restaurant;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String item;
	private Integer price;
	private String description;
	private String imagePath;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setItemId(Integer id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Restaurants getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurants restaurant) {
		this.restaurant = restaurant;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
		
	
}
