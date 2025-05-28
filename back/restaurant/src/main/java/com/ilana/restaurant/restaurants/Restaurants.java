package com.ilana.restaurant.restaurants;

import java.util.List;

import com.ilana.restaurant.menu.MenuItem;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Restaurants {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Nonnull
	private String restaurant_name;
	@Nonnull
	private String city;
	private String type;
	private String coverPath;
	@OneToMany(mappedBy = "restaurant")
	private List<MenuItem> menu;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRestaurant_name() {
		return restaurant_name;
	}
	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCoverPath() {
		return coverPath;
	}
	public void setCoverPath(String coverPath) {
		this.coverPath = coverPath;
	}
	public List<MenuItem> getMenu() {
		return menu;
	}
	public void setMenu(List<MenuItem> menu) {
		this.menu = menu;
	}
	
	

}
