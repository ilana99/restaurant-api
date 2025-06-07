package com.ilana.restaurant.service;

import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class Patcher {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public <T> T patchRequest(T instance, Integer instanceId, Map<String, Object> updates) {
		updates.forEach((key, value) -> {
			try {
				Field field = instance.getClass().getDeclaredField(key);
				field.setAccessible(true);
				field.set(instance, value);
			} catch (NoSuchFieldException | IllegalAccessException e) {
				e.printStackTrace();
			}
		});
		return instance;
	}

}