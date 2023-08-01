package com.projectdemo.shop.controller;

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

import com.projectdemo.shop.bean.OpenHrBean;
import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.service.OpenHrService;

@RestController
@RequestMapping("/shop/openhr")
public class OpenHrController {

	@Autowired
	private OpenHrService openHrService;
	
	@PostMapping("/add")
	public String addCategory(@RequestBody OpenHrBean bean) {
		JSONObject json = new JSONObject();
		OpenHrBean add = openHrService.add(bean);
		if (add != null) {
			json.put("success", true);
		} else {
			json.put("success", false);
		}
		return json.toString();
	}
	
	@GetMapping("/{id}")
	public String findById(@PathVariable Integer id) {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		OpenHrBean find = openHrService.findById(id);

		if (find != null) {
			ShopBean fk_shop = find.getShop();
			JSONObject item = new JSONObject()
					.put("id", find.getId())
					.put("MonOpen", find.getMonOpen())
					.put("MonClose", find.getMonClose())
					.put("tueOpen", find.getTueOpen())
					.put("tueClose", find.getTueClose())
					.put("wedOpen", find.getWedOpen())
					.put("wedClose", find.getWedClose())
					.put("thrOpen", find.getThrOpen())
					.put("thrClose", find.getThrClose())
					.put("friOpen", find.getFriOpen())
					.put("friClose", find.getFriClose())
					.put("satOpen", find.getSatOpen())
					.put("satClose", find.getSatClose())
					.put("sunOpen", find.getSunOpen())
					.put("sunClose", find.getSunClose());
			JSONObject shop = new JSONObject().put("shopName", fk_shop.getName()).put("shopId", fk_shop.getId());
			array = array.put(item).put(shop);
			json.put("prepTime", array);
		}
		else {
			json.put("message", "invalid id");
		}
		return json.toString();
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Integer id, @RequestBody OpenHrBean bean) {
		JSONObject json = new JSONObject();
		OpenHrBean update = openHrService.update(id, bean);
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
		OpenHrBean bean = openHrService.findById(id);
		if (bean != null) {
			boolean delete = openHrService.delete(id);
			json.put("success", delete);
		} else {
			json.put("success", false);
			json.put("message", "id doesn't exist");
		}
		return json.toString();
	}
}
