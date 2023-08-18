package com.projectdemo.shop.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.bean.ShopCategoryBean;
import com.projectdemo.shop.service.ShopCategoryService;

@RestController
@RequestMapping("/shop/category")
@CrossOrigin()
public class ShopCategoryController {

	@Autowired
	private ShopCategoryService shopCategoryService;
	
	@PostMapping("/add")
	public String addCategory(@RequestBody ShopCategoryBean bean) {
		JSONObject json = new JSONObject();
		ShopCategoryBean add = shopCategoryService.add(bean);
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
		ShopCategoryBean find = shopCategoryService.findById(id);

		if (find != null) {
			ShopBean fk_shop = find.getShop();
			JSONObject item = new JSONObject()
					.put("id", find.getId())
					.put("name", find.getName());
			JSONObject shop = new JSONObject().put("shopName", fk_shop.getName()).put("shopId", fk_shop.getId());
			array = array.put(item).put(shop);
			json.put("shopCategory", array);
		}
		else {
			json.put("message", "invalid id");
		}
		return json.toString();
	}
	
	@GetMapping("/all/{id}")
	public String findAllByShop(@PathVariable Integer id) {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		List<ShopCategoryBean> list = shopCategoryService.findAllByShopId(id);
		if (list != null && !list.isEmpty()) {
			for (ShopCategoryBean bean : list) {
				JSONObject item = new JSONObject()
						.put("id", bean.getId())
						.put("name", bean.getName());
				array.put(item);
			}
			json.put("list", array);
		} else {
			json.put("message", "invalid id");
		}
		return json.toString();
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Integer id, @RequestBody ShopCategoryBean bean) {
		JSONObject json = new JSONObject();
		ShopCategoryBean update = shopCategoryService.update(id, bean);
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
		ShopCategoryBean bean = shopCategoryService.findById(id);
		if (bean != null) {
			boolean delete = shopCategoryService.delete(id);
			json.put("success", delete);
		} else {
			json.put("success", false);
			json.put("message", "id doesn't exist");
		}
		return json.toString();
	}
}
