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

import com.projectdemo.menu.bean.MenuBean;
import com.projectdemo.menu.bean.MenuCategoryBean;
import com.projectdemo.menu.service.MenuCategoryService;
import com.projectdemo.menu.service.MenuService;

@RestController
@RequestMapping("/menuCategory")
@CrossOrigin()
public class MenuCategoryController {

	@Autowired
	private MenuCategoryService menuCategoryService;
	
	@Autowired
	private MenuService menuService;
	
	@GetMapping("/findMenuCategoryByName")
	public String findMenuCategoryByName(@RequestBody MenuCategoryBean menuCategoryBean) {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
//		透過名字搜尋到全部有類似名稱的全部資料
		List<MenuCategoryBean> find = menuCategoryService.findMenuCategoryByName(menuCategoryBean.getName());
//		判斷資料內是否有東西
		if(find != null && !find.isEmpty()) {
			for (MenuCategoryBean mcBean : find) {
//				如果有資料 將這些資料用迴圈放進去
				JSONObject mcJson = new JSONObject()
						.put("id", mcBean.getId())
						.put("name", mcBean.getName());
//				這邊是用資料內的menuid去呼叫menuservice裡面的功能 去尋找資料
				MenuBean menuById = menuService.getMenuById(mcBean.getMenu().getId());
				if(menuById != null) {
//					如果裡面有資料 將資料放進menu的json裡面
					JSONObject menuArray = new JSONObject()
							.put("id", menuById.getId())
		                    .put("name", menuById.getName())
		                    .put("fk_shop_id", menuById.getShop().getId())
		                    .put("supply", menuById.isSupply());
//					將這個menuJson放進mcJson裡面的變數menu裡面
					mcJson.put("menu", menuArray);
				}
//				最後將整個資料印出來
				array.put(mcJson);
			}
		}else {
			json.put("message", "查無資料");
		}
		json.put("MenuCategory", array);
		return json.toString();
	}
	
	
	
	@PutMapping("/updateMenuCategory")
	public String updateMenuCategory(@RequestBody MenuCategoryBean menuCategoryBean) {
		JSONObject json = new JSONObject();
		MenuCategoryBean update = menuCategoryService.updateMenuCategory(menuCategoryBean.getId(), menuCategoryBean);
		if (update != null) {
			json.put("result", true);
		} else {
			json.put("result", false);
			json.putOnce("message", "資料不存在");
		}
		return json.toString();
	}
	
	

	
	@PostMapping("/creatMenuCategory")
	public String creatMenuCategory(@RequestBody MenuCategoryBean menuCategoryBean) {
		JSONObject json = new JSONObject();
		MenuCategoryBean creat = menuCategoryService.creatMenuCategory(menuCategoryBean);
		if(creat != null) {
			json.put("result", true);
		} else {
			json.put("result", false);
		}
		return json.toString();
	}
	
	@DeleteMapping("deleteMenuCategory")
	public String deleteMenuCategoryById(@RequestBody MenuCategoryBean menuCategoryBean) {
	    JSONObject json = new JSONObject();
	    if (menuCategoryService.deleteAllMenuCategoryByMenuId(menuCategoryBean.getMenu().getId())) {
	        json.put("result", "成功");
	    } else {
	        json.put("result", "失敗");
	    }
	    return json.toString();
	}
	
}
