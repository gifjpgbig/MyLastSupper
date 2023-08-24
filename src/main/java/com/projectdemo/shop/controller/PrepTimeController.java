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

import com.projectdemo.shop.bean.PrepTimeBean;
import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.service.PrepTimeService;

@RestController
@RequestMapping("/shop/preptime")
@CrossOrigin()
public class PrepTimeController {

	@Autowired
	private PrepTimeService prepTimeService;
	
	/**
	 * CREATE
	 * 
	 * @param bean
	 * @return
	 */
	@PostMapping("/add")
	public String addCategory(@RequestBody PrepTimeBean bean) {
		JSONObject json = new JSONObject();
		PrepTimeBean add = prepTimeService.add(bean);
		if (add != null) {
			json.put("success", true);
		} else {
			json.put("success", false);
		}
		return json.toString();
	}
	
	/**
	 * FIND BY ID
	 * 
	 * @param id PrepTime ID
	 * @return PrepTime, Shop ID & name
	 */
	@GetMapping("/{id}")
	public String findById(@PathVariable Integer id) {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		PrepTimeBean find = prepTimeService.findById(id);

		if (find != null) {
			ShopBean fk_shop = find.getShop();
			JSONObject item = new JSONObject()
					.put("id", find.getId())
					.put("prep", find.getPrep())
					.put("day", find.getDay())
					.put("startTime", find.getStartTime())
					.put("endTime", find.getEndTime());
			JSONObject shop = new JSONObject().put("shopName", fk_shop.getName()).put("shopId", fk_shop.getId());
			array = array.put(item).put(shop);
			json.put("prepTime", array);
		}
		else {
			json.put("message", "invalid id");
		}
		return json.toString();
	}
	
	/**
	 * FIND ALL PrepTime BY SHOP ID
	 * 
	 * @param id Shop ID
	 * @return PrepTime array
	 */
	@GetMapping("/all/{id}")
	public String findAllByShop(@PathVariable Integer id) {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		List<PrepTimeBean> list = prepTimeService.findAllByShopId(id);
		if (list != null && !list.isEmpty()) {
			for (PrepTimeBean bean : list) {
				JSONObject item = new JSONObject()
						.put("id", bean.getId())
						.put("prep", bean.getPrep())
						.put("day", bean.getDay())
						.put("startTime", bean.getStartTime())
						.put("endTime", bean.getEndTime());
				array.put(item);
			}
			json.put("list", array);
		} else {
			json.put("message", "invalid id");
		}
		return json.toString();
	}
	
	/**
	 * UPDATE 
	 * 
	 * @param id PrepTime ID
	 * @param bean
	 * @return
	 */
	@PutMapping("/{id}")
	public String update(@PathVariable Integer id, @RequestBody PrepTimeBean bean) {
		JSONObject json = new JSONObject();
		PrepTimeBean update = prepTimeService.update(id, bean);
		if (update != null) {
			json.put("success", true);
		} else {
			json.put("success", false);
			json.put("message", "id doesn't exist");
		}
		return json.toString();
	}
	
	/**
	 * DELETE
	 * 
	 * @param id PrepTime ID
	 * @return
	 */
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Integer id) {
		JSONObject json = new JSONObject();
		PrepTimeBean bean = prepTimeService.findById(id);
		if (bean != null) {
			boolean delete = prepTimeService.delete(id);
			json.put("success", delete);
		} else {
			json.put("success", false);
			json.put("message", "id doesn't exist");
		}
		return json.toString();
	}
	
	/**
	 * FIND ALL PrepTime BY SHOP ID, ORDERED BY DAY & START TIME
	 * 
	 * @param id Shop ID
	 * @return PrepTime array
	 */
	@GetMapping("/allOrdered/{id}")
	public String findAllByShopOrdered(@PathVariable("id") Integer id) {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		List<PrepTimeBean> list = prepTimeService.findAllByShopOrdered(id);
		
		if (list != null && !list.isEmpty()) {
			for (PrepTimeBean bean : list) {
				JSONObject item = new JSONObject()
						.put("id", bean.getId())
						.put("prep", bean.getPrep())
						.put("day", bean.getDay())
						.put("startTime", bean.getStartTime())
						.put("endTime", bean.getEndTime());
				array.put(item);
			}
			json.put("list", array);
			json.put("success", true);
		} else {
			json.put("success", false);
			json.put("message", "invalid id");
		}
		
		return json.toString();
	}
}
