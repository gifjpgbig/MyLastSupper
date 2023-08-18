package com.projectdemo.menu.controller;

import java.io.IOException;
import java.util.Base64;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projectdemo.menu.bean.DishBean;
import com.projectdemo.menu.bean.MenuBean;
import com.projectdemo.menu.bean.MenuCustomizationBean;
import com.projectdemo.menu.bean.MenuCustomizationOptionsBean;
import com.projectdemo.menu.service.DishService;
import com.projectdemo.menu.service.MenuCustomizationOptionsService;
import com.projectdemo.menu.service.MenuCustomizationService;
import com.projectdemo.menu.service.MenuService;

@RestController
@RequestMapping("/dish")
@CrossOrigin()
public class DishController {

	@Autowired
	private DishService dishService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private MenuCustomizationService menuCustomizationService;

	@Autowired
	private MenuCustomizationOptionsService menuCustomizationOptionsService;

//	使用shopid找到全部的dish
	@GetMapping("/findDishSimple")
	public String findDishSimple(@RequestBody MenuBean menuBean) {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();

		// 從 menuBean 中取得 shopId
		Integer shopId = menuBean.getShop().getId();
		List<Integer> menuIds = menuService.findMenuIdsByShopId(shopId);

		if (!menuIds.isEmpty()) {
			for (Integer menuId : menuIds) {
				// 使用 MenuId 查詢所有的 DishBean
				List<DishBean> dishList = dishService.findDishByMenuId(menuId);

				for (DishBean dish : dishList) {
					// 將每個 DishBean 轉換成 JSONObject，放進 JSONArray 中
					byte[] photo = dish.getPicture();
					JSONObject dishJson = new JSONObject()
							.put("id", dish.getId())
							.put("name", dish.getName())
							.put("description", dish.getDescription())
							.put("price", dish.getPrice())
							.put("extraInfo", dish.getExtraInfo())
							.put("review", dish.getReview())
							.put("createDate", dish.getCreateDate())
							.put("updateDate", dish.getUpdateDate())
							.put("likes", dish.getLikes())
							.put("dislikes", dish.getDislikes())
							.put("likerate", dish.getLikerate())
							.put("soldOut", dish.isSoldOut())
							.put("fk_menu_id", dish.getMenu().getId());
					if (photo != null && photo.length > 0) {
						String base64Photo = Base64.getEncoder().encodeToString(photo);
						dishJson.put("picture", base64Photo);
					} else {
						dishJson.put("picture", "");
					}
					array.put(dishJson);
				}
			}
			// 將 JSONArray 放進最外層的 JSONObject
			json.put("dishesimple", array);
		} else {
			json.put("message", "查無菜單");
		}

		return json.toString();
	}

//	使用dishId找到全部的資料
	@PostMapping("/findDishById")
	public String findDishById(@RequestBody DishBean dishBean) {
		JSONObject finaljson = new JSONObject();
		// 取得 Dish的全部資料
		DishBean dish = dishService.findDishById(dishBean.getId());
		if (dish != null) {
			MenuBean menu = dish.getMenu();
			MenuBean menuById = menuService.getMenuById(menu.getId());
			byte[] photo = dish.getPicture();
			JSONObject item = new JSONObject()
					.put("id", dish.getId())
					.put("name", dish.getName())
					.put("description", dish.getDescription())
					.put("price", dish.getPrice())
					.put("extraInfo", dish.getExtraInfo())
					.put("review", dish.getReview())
					.put("createDate", dish.getCreateDate())
					.put("updateDate", dish.getUpdateDate())
					.put("likes", dish.getLikes())
					.put("dislikes", dish.getDislikes())
					.put("likerate", dish.getLikerate())
					.put("soldOut", dish.isSoldOut())
					.put("fk_menu_id", menu.getId())
					.put("dietaryRestrictions", dish.getDietaryRestrictions())
					.put("shopid", menuById.getShop().getId());
			if (photo != null && photo.length > 0) {
				String base64Photo = Base64.getEncoder().encodeToString(photo);
				item.put("picture", base64Photo);
			} else {
				item.put("picture", "");
			}

			// 取得該餐點內的選項
			List<MenuCustomizationBean> menuCustomizations = menuCustomizationService
					.findAllMenuCustomizationByDishId(dish.getId());
			if (!menuCustomizations.isEmpty()) {
				JSONArray menuCustomizationsArray = new JSONArray();
				for (MenuCustomizationBean mcBean : menuCustomizations) {
					Integer mcId = mcBean.getId();

					// 取得該選項內的全部品項
					List<MenuCustomizationOptionsBean> optionsList = menuCustomizationOptionsService
							.findMenuCustomizationOptionsByOptionsId(mcId);
					JSONArray optionsArray = new JSONArray();
					for (MenuCustomizationOptionsBean option : optionsList) {
//	                	將品項放進json裡面
						JSONObject optionJson = new JSONObject().put("id", option.getId())
								.put("option_name", option.getOptionName()).put("option_money", option.getOptionMoney())
								.put("fk_menu_customization_id", mcId);

						optionsArray.put(optionJson);
					}
//	                將選項放進JSON裡面
					JSONObject mcJson = new JSONObject().put("id", mcBean.getId())
							.put("customization_name", mcBean.getCustomizationName())
							.put("max_selection", mcBean.getMaxSelection())
							.put("min_selection", mcBean.getMinSelection()).put("repeatable", mcBean.isRepeatable())
							.put("fk_dish_id", dish.getId()).put("options", optionsArray);

					menuCustomizationsArray.put(mcJson);
				}
//	            將取得的全部選項與品項 都放進menu_customizations的變數裡面
				item.put("menu_customizations", menuCustomizationsArray);
			}
//	        將所取得的全部資料 放到dish的變數內
			finaljson.put("dish", item);
			finaljson.put("message", true);
		} else {
			finaljson.put("dish", "查無菜單");
			finaljson.put("message", false);
		}

		return finaljson.toString();
	}

	@PutMapping("/updatePicture/{id}")
	public String updateDishPicture(@RequestParam("photoFile") MultipartFile photoFile , @PathVariable Integer id) throws IOException {
	    if (photoFile != null) {
	        byte[] bytes = photoFile.getBytes();
	        DishBean update = dishService.updatePicture(id, bytes);
	        JSONObject json = new JSONObject();
	        if (update != null) {
	            json.put("result", true);
	        } else {
	            json.put("result", false);
	            json.putOnce("message", "資料不存在");
	        }
	        return json.toString();
	    } else {
	        // Handle the case where photoFile is null
	        JSONObject json = new JSONObject();
	        json.put("result", false);
	        json.putOnce("message", "照片未上傳");
	        return json.toString();
	    }
	}
	
//	更新Dish
	@PutMapping("/updateDish")
	public String updateDish(@RequestBody DishBean dishBean)  {
		JSONObject json = new JSONObject();
//		String base64 = dishBean.getPicture();
//		先判斷裡面有沒有東西 有東西就把資料放進去
		DishBean update = dishService.updateDish(dishBean.getId(), dishBean);
		if (update != null) {
			
			json.put("result", true);
		} else {
			json.put("result", false);
			json.putOnce("message", "資料不存在");
		}
		return json.toString();
	}

	@PostMapping("/creatDish")
	public String creatDish(@RequestBody DishBean dishBean) {
		JSONObject json = new JSONObject();
		DishBean creatDish = dishService.creatDish(dishBean);
		if (creatDish != null) {
			json.put("result", true);
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	@DeleteMapping("/deleteDish")
	public String deleteDishById(@RequestBody DishBean dishBean) {
		Integer dishId = dishBean.getId();
		JSONObject json = new JSONObject();
		DishBean findDishById = dishService.findDishById(dishId);
//		先找到選項的全部資料 如果有 就先刪除掉選項 如果沒有就直接執行刪除
		List<MenuCustomizationBean> findMC = menuCustomizationService.findAllMenuCustomizationByDishId(dishId);
		if (!findMC.isEmpty()) {
			for (MenuCustomizationBean mc : findMC) {
				menuCustomizationOptionsService.deleteMenuCustomizationOptionsByCustId(mc.getId());
			}
			menuCustomizationService.deleteAllMenuCustomizationByDishId(dishId);
		}
//		這邊就是不論前面有沒有找到選項 都要執行刪除 但要先判定有沒有這筆資料
		if (findDishById != null) {
			dishService.deleteDish(dishId);
			json.put("result", true);
			json.put("message", "刪除成功");
		} else {
			json.put("result", false);
			json.put("message", "刪除失敗");
		}

		return json.toString();
	}

}