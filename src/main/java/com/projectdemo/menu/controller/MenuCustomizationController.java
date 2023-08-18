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

import com.projectdemo.menu.bean.DishBean;
import com.projectdemo.menu.bean.MenuCustomizationBean;
import com.projectdemo.menu.bean.MenuCustomizationOptionsBean;
import com.projectdemo.menu.service.MenuCustomizationOptionsService;
import com.projectdemo.menu.service.MenuCustomizationService;

@RestController
@RequestMapping("/menuCustomization")
@CrossOrigin()
public class MenuCustomizationController {

	@Autowired
	private MenuCustomizationService menuCustomizationService;
	
	@Autowired
	private MenuCustomizationOptionsService menuCustomizationOptionsService;
	
	
	@PostMapping("/creatMenuCustomization")
	public String creatMenuCustomization(@RequestBody MenuCustomizationBean menuCustomizationBean) {
		JSONObject json = new JSONObject();
		MenuCustomizationBean creat = menuCustomizationService.creatMenuCustomization(menuCustomizationBean);
		if(creat != null ) {
			json.put("result", true);
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

//	更新資料理論上不應該影響原本就存在的品項
	@PutMapping("/updateMenuCustomization")
	public String updateMenuCustomization(@RequestBody MenuCustomizationBean menuCustomizationBean) {
		JSONObject json = new JSONObject();
		MenuCustomizationBean update = menuCustomizationService.updateMenuCustomization(menuCustomizationBean.getId(),
				menuCustomizationBean);
//		先確認這個ID裡面有資料才可以做更新 所以前端必須要將ID放進去搜尋
		if (update != null) {
			json.put("result", true);
		}else {
			json.put("result", false);
			json.putOnce("message", "資料不存在");
		}
		 return json.toString();

	}
	
//	透過dishid去找到該品項裡面有什麼加點項目
	@PostMapping("/findAllMenuCustomizationByDishId")
	public String findAllMenuCustomizationByDishId(@RequestBody MenuCustomizationBean menuCustomizationBean) {
//		先建立一個array準備把全部的加點項目放進去
		JSONObject finaljson = new JSONObject();
		JSONArray array = new JSONArray();
//		建立dishbean把資料內的dishid放進去
		DishBean dish = menuCustomizationBean.getDish();
		List<MenuCustomizationBean> list = menuCustomizationService.findAllMenuCustomizationByDishId(dish.getId());
//		把找到的資料 透過迴圈方式放進去list裡面
		if (list != null && !list.isEmpty()) {
			for (MenuCustomizationBean mcBean : list) {
//				先找出選項的id
				Integer mcId = mcBean.getId();
//				用選項的ID去找到全部的品項
				List<MenuCustomizationOptionsBean> optionsList = menuCustomizationOptionsService.findMenuCustomizationOptionsByOptionsId(mcId);
//				將全部品項放進array裡面
				JSONArray optionsArray = new JSONArray();
				for (MenuCustomizationOptionsBean option : optionsList) {
	                JSONObject optionJson = new JSONObject()
	                        .put("id", option.getId())
	                        .put("option_name", option.getOptionName())
	                        .put("option_money", option.getOptionMoney())
	                        .put("fk_menu_customization_id", mcId);
	                optionsArray.put(optionJson);
	            }
//				建立選項的array
				JSONObject mcJson = new JSONObject()
						.put("id", mcBean.getId())
						.put("customization_name", mcBean.getCustomizationName())
						.put("max_selection", mcBean.getMaxSelection())
						.put("min_selection", mcBean.getMinSelection())
						.put("repeatable", mcBean.isRepeatable())
						.put("fk_dish_id", dish.getId())
//						將品項的array放進去選項內
						.put("options", optionsArray);
				array.put(mcJson);
			}
//			將所有資料放進變數MenuCustomization裡面
			finaljson.put("MenuCustomization", array);
		} else {
			finaljson.put("MenuCustomization", "查無資料");
		}
//		將所有選項顯示出來 包含品項
		return finaljson.toString();
	}

//	可能只要刪除某個加點項目 這個方法就是
	@DeleteMapping("/deleteMenuCustomizationId")
	public String deleteMenuCustomizationId(@RequestBody MenuCustomizationBean menuCustomizationBean) {
		JSONObject json = new JSONObject();
//		如果ID內有資料就刪除 回傳true
		if(menuCustomizationService.deleteMenuCustomizationId(menuCustomizationBean.getId())) {
//			確認裡面有東西刪除後 在進去品項內刪除掉全部品項 最後再回傳成功
			menuCustomizationOptionsService.deleteMenuCustomizationOptionsByCustId(menuCustomizationBean.getId());
			json.put("result", "成功");
		} else {
			json.put("result", "失敗");
		}
		return json.toString();
	}
	
//	這個是如果刪除掉dish的話也要同時把所有的加點資料刪除
	@DeleteMapping("/deleteAllMenuCustomizationByDishId")
	public String deleteAllMenuCustomizationByDishId(@RequestBody MenuCustomizationBean menuCustomizationBean) {
		JSONObject json = new JSONObject();
		List<MenuCustomizationBean> find = menuCustomizationService.findAllMenuCustomizationByDishId(menuCustomizationBean.getDish().getId());
//		找到全部有相同dishid的menuCustomizationid後 放進一個List裡面
		List<Integer> mcIds = menuCustomizationService.findMenuCustomizationIdsByDishId(menuCustomizationBean.getDish().getId());
		if (find != null && !find.isEmpty()) {
//			用迴圈方式取得全部的id後 執行刪除品項的功能
			for(Integer mcId : mcIds) {
				menuCustomizationOptionsService.deleteMenuCustomizationOptionsByCustId(mcId);
			}
//			刪除掉所有品項後 最後在刪除掉加點的項目 不然會出bug
			menuCustomizationService.deleteAllMenuCustomizationByDishId(menuCustomizationBean.getDish().getId());
			json.put("result", "成功");
		} else {
			json.put("result", "失敗");
		}
		return json.toString();
	}

}
