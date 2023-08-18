package com.projectdemo.menu.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.menu.bean.MenuCustomizationBean;
import com.projectdemo.menu.bean.MenuCustomizationOptionsBean;
import com.projectdemo.menu.service.MenuCustomizationOptionsService;

@RestController
@RequestMapping("/menuCustomizationOptions")
@CrossOrigin()
public class MenuCustomizationOptionsController {

	@Autowired
	private MenuCustomizationOptionsService menuCustomizationOptionsService;
	
	@PostMapping("/creatMenuCustomizationOptions")
	public String creatMenuCustomizationOptions(@RequestBody MenuCustomizationOptionsBean menuCustomizationOptionsBean) {
		JSONObject json = new JSONObject();
		MenuCustomizationOptionsBean creat = menuCustomizationOptionsService.creatMenuCustomizationOptions(menuCustomizationOptionsBean);
		if(creat != null) {
			json.put("result", true);
		} else {
			json.put("result", false);
		}
		return json.toString();
	}
	
	@PutMapping("/updateMenuCustomizationOptions")
	public String updateMenuCustomizationOptions(@RequestBody MenuCustomizationOptionsBean menuCustomizationOptionsBean) {
		JSONObject json = new JSONObject();
		MenuCustomizationOptionsBean update = menuCustomizationOptionsService.updateMenuCustomizationOptions(menuCustomizationOptionsBean.getId(),
				menuCustomizationOptionsBean);
		if (update != null) {
			json.put("result", true);
		}else {
			json.put("result", false);
			json.putOnce("message", "資料不存在");
		}
		 return json.toString();
	}
	
	
	
	@GetMapping("/findMenuCustomizationOptionsByOptionsId")
	public String findMenuCustomizationOptionsByOptionsId(@RequestBody MenuCustomizationOptionsBean menuCustomizationOptionsBean) {
//		先建立一個array準備把全部的加點項目放進去
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		
		MenuCustomizationBean mcBean = menuCustomizationOptionsBean.getMenuCustomization();
		List<MenuCustomizationOptionsBean> list = menuCustomizationOptionsService.findMenuCustomizationOptionsByOptionsId(mcBean.getId());
//		把找到的資料 透過迴圈方式放進去list裡面
		if (list != null && !list.isEmpty()) {
			for(MenuCustomizationOptionsBean bean : list) {
				JSONObject put = new JSONObject()
						.put("id", bean.getId())
						.put("option_money", bean.getOptionMoney())
						.put("option_name", bean.getOptionName())
						.put("fk_menu_customization_id", mcBean.getId());
				array.put(put);
			}
			json.put("MenuCustomizationOptions", array);
		}else {
			json.put("MenuCustomizationOptions", "查無資料");
		}
		return json.toString();
	}
	
	@DeleteMapping("/deleteMenuCustomizationOptionsByCustId")
	public String deleteMenuCustomizationOptionsByCustId(@RequestBody MenuCustomizationOptionsBean menuCustomizationOptionsBean) {
		JSONObject json = new JSONObject();
		if(menuCustomizationOptionsService.deleteMenuCustomizationOptionsByCustId(menuCustomizationOptionsBean.getMenuCustomization().getId())){
		json.put("result", true);
	}else {
		json.put("result", true);
	}
	return json.toString();
	}
	
	@DeleteMapping("/deleteMenuCustomizationOptionsById")
	public String deleteMenuCustomizationOptionsById(@RequestBody MenuCustomizationOptionsBean menuCustomizationOptionsBean) {
		JSONObject json = new JSONObject();
		if(menuCustomizationOptionsService.deleteMenuCustomizationOptions(menuCustomizationOptionsBean.getId())) {
			json.put("result", true);
		}else {
			json.put("result", true);
		}
		return json.toString();
	}
}
