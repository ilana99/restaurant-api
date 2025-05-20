package com.ilana.restaurant.user;

import org.springframework.data.repository.CrudRepository;

import com.ilana.restaurant.user.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}
