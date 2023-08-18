package com.projectdemo.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.model.bean.menu.DishBean;
import com.projectdemo.model.service.DishService;

@RestController
@CrossOrigin
public class DishController {
	
	@Autowired
	private DishService dishService;
	
	@PostMapping("/dish/create/{id}")
	public DishBean dishcreate(@RequestBody DishBean dish,@PathVariable Integer id) {
		return dishService.createDish(dish, id);
	}
	
	@PostMapping("/dish/findAll")
	public String findAllDish() {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		Integer count = 0;
		
		List<DishBean> dishes = dishService.findAllDish();
		for(DishBean dish : dishes) {
			JSONObject item = new JSONObject()
					.put("dishID", dish.getId())
					.put("description", dish.getDescription())
					.put("price", dish.getPrice())
					.put("dietaryRestrictions",dish.getDietaryRestrictions() );
			array = array.put(item);
			count += 1;
		}
		responseJson.put("count" , count);
		responseJson.put("list", array);
		return responseJson.toString();
	}
}
