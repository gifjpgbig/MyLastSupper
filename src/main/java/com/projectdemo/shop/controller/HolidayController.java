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

import com.projectdemo.shop.bean.HolidayBean;
import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.service.HolidayService;

@RestController
@RequestMapping("/shop/holiday")
@CrossOrigin()
public class HolidayController {

	@Autowired
	private HolidayService holidayService;
	
	@PostMapping("/add")
	public String addHoliday(@RequestBody HolidayBean bean) {
		JSONObject json = new JSONObject();
		HolidayBean add = holidayService.add(bean);
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
		HolidayBean find = holidayService.findById(id);

		if (find != null) {
			ShopBean fk_shop = find.getShop();
			JSONObject item = new JSONObject()
					.put("id", find.getId())
					.put("restDate", find.getRestDate())
					.put("wholeDay", find.isWholeDay())
					.put("startTime", find.getStartTime())
					.put("endTime", find.getEndTime());
			JSONObject shop = new JSONObject().put("shopName", fk_shop.getName()).put("shopId", fk_shop.getId());
			array = array.put(item).put(shop);
			json.put("holiday", array);
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
		List<HolidayBean> list = holidayService.findAllByShopId(id);
		if (list != null && !list.isEmpty()) {
			for (HolidayBean bean : list) {
				JSONObject item = new JSONObject()
						.put("id", bean.getId())
						.put("restDate", bean.getRestDate())
						.put("wholeDay", bean.isWholeDay())
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
	
	@PutMapping("/{id}")
	public String update(@PathVariable Integer id, @RequestBody HolidayBean bean) {
		JSONObject json = new JSONObject();
		HolidayBean update = holidayService.update(id, bean);
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
		HolidayBean bean = holidayService.findById(id);
		if (bean != null) {
			boolean delete = holidayService.delete(id);
			json.put("success", delete);
		} else {
			json.put("success", false);
			json.put("message", "id doesn't exist");
		}
		return json.toString();
	}
	
	@GetMapping("/active/{id}")
	public String findActiveHolidays(@PathVariable Integer id) {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		
		List<HolidayBean> list = holidayService.findActiveHolidays(id);
		if (list != null && !list.isEmpty()) {
			for (HolidayBean bean : list) {
				JSONObject item = new JSONObject()
						.put("id", bean.getId())
						.put("restDate", bean.getRestDate())
						.put("wholeDay", bean.isWholeDay())
						.put("startTime", bean.getStartTime())
						.put("endTime", bean.getEndTime());
				array.put(item);
			}
			json.put("list", array);
			json.put("success", true);
		} else {
			json.put("success", false);
		}
		return json.toString();
	}
}
