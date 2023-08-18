package com.projectdemo.shop.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.service.ShopService;

@RestController
@RequestMapping("/shop")
public class ShopController {

	@Autowired
	private ShopService shopService;

	@PostMapping("/add")
	public String addShop(@RequestBody ShopBean shopBean) {
		JSONObject json = new JSONObject();
		ShopBean shop = shopService.addShop(shopBean);
		if (shop != null) {
			json.put("success", true);
		} else {
			json.put("success", false);
		}
		return json.toString();
	}
	
	//TODO: add shop photo
	
	@GetMapping("/all")
	public String findAll() {
		JSONObject json = new JSONObject();
		List<ShopBean> list = shopService.findAll();
		JSONArray array = new JSONArray();
		for (ShopBean bean : list) {
			JSONObject object = new JSONObject(bean);
			array.put(object);
		}
		json.put("list", array);
		return json.toString();
	}

	@GetMapping("/{id}")
	public String findById(@PathVariable Integer id) {
		JSONObject json = new JSONObject();
		ShopBean shop = shopService.findById(id);
		if(shop != null) {
			json.put("shop", new JSONObject(shop));			
		}
		else {
			json.put("message", "invalid id");
		}
		return json.toString();
	}

	@PutMapping("/{id}")
	public String update(@PathVariable Integer id, @RequestBody ShopBean shopBean) {
		JSONObject json = new JSONObject();
		ShopBean update = shopService.update(id, shopBean);
		if (update != null) {
			json.put("success", true);
		} else {
			json.put("success", false);
			json.put("message", "id doesn't exist");
		}
		return json.toString();
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Integer id) {
		JSONObject json = new JSONObject();
		ShopBean shop = shopService.findById(id);
		if(shop != null) {
			boolean delete = shopService.delete(id);
			json.put("success", delete);
		}
		else {
			json.put("success", false);
			json.put("message", "id doesn't exist");
		}
		return json.toString();
	}
}
