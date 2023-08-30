package com.projectdemo.menu.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.menu.bean.DishBean;
import com.projectdemo.menu.bean.MenuBean;
import com.projectdemo.menu.service.DishService;
import com.projectdemo.menu.service.MenuService;
import com.projectdemo.shop.bean.ShopBean;
	
@RestController
@RequestMapping("/menu")
@CrossOrigin()
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private DishService dishService;
	
	@PostMapping("/getAllMenu")
	public String getMenuByDhopId(@RequestBody MenuBean menuBean) {
	    JSONObject responseJson = new JSONObject();
	    JSONArray array = new JSONArray();
	    ShopBean shop = menuBean.getShop();
	    List<MenuBean> menuList = menuService.findMenuByShopId(shop.getId());
	    for(MenuBean menu :menuList) {
	    	JSONObject menuJson = new JSONObject()
	    			.put("id", menu.getId())
	    			.put("name", menu.getName())
	    			.put("supply", menu.isSupply());
	    	array.put(menuJson);
	    }
	    responseJson.put("menus", array);
	    return responseJson.toString();
	}
	
	
	@PostMapping("/findAllMenu")
		public String findMenuByShopId(@RequestBody MenuBean menuBean) {
	    JSONObject responseJson = new JSONObject();
	    JSONArray array = new JSONArray();
	    ShopBean shop = menuBean.getShop();
//	    使用shopid找到全部的menu
	    List<MenuBean> menuList = menuService.findMenuByShopId(shop.getId());
	    List<Integer> menuIDList = new ArrayList<>();
	    for(MenuBean menu:menuList) {
	    	menuIDList.add(menu.getId());
	    }
	    
	    for(int i = 0;i<menuIDList.size();i++) {
	    	List<DishBean> dishList = dishService.findDishByMenuId(menuIDList.get(i));
	    	for (DishBean dish : dishList) {
            	
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
                        .put("fk_menu_id", menuIDList.get(i))
                        .put("picture", dish.getPicture());
                array.put(dishJson);           
	    }
	    responseJson.put("menus", array);
    } 
//    列印出全部資料
    return responseJson.toString();
	}	
	
	
	
	//使用PathVariable shopID找出全部的dish
	@GetMapping("/findMenuByShopIDToShoppingCart/{shopID}")
	public String findMenuByShopIDToShoppingCart(@PathVariable("shopID")Integer shopID) {
		JSONObject responseJson = new JSONObject();
	    JSONArray dishArray = new JSONArray();
	    JSONArray menuArray = new JSONArray();
	    
	    List<MenuBean> menuList = menuService.findMenuByShopId(shopID);
	    List<Integer> menuIDList = new ArrayList<>();
	    for(MenuBean menu:menuList) {
	    	menuIDList.add(menu.getId());
	    	JSONObject menuJson = new JSONObject()
	    			.put("id", menu.getId())
	    			.put("name", menu.getName());
	    	menuArray.put(menuJson);
	    }
	    responseJson.put("menus",menuArray);
	    
	    for(int i = 0;i<menuIDList.size();i++) {
	    	List<DishBean> dishList = dishService.findDishByMenuId(menuIDList.get(i));
	    	for (DishBean dish : dishList) {
//            	byte[] photo = dish.getPicture();
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
                        .put("fk_menu_id", menuIDList.get(i))
                        .put("picture",dish.getPicture());
//                if (photo != null && photo.length > 0) {
//    				String base64Photo = Base64.getEncoder().encodeToString(photo);
//    				dishJson.put("picture", base64Photo);
//    			} else {
//    				dishJson.put("picture", "");
//    			}
                dishArray.put(dishJson);           
	    }
	    responseJson.put("dishes", dishArray);
    } 
//    列印出全部資料
    return responseJson.toString();
	}
//	    將menu資料透過for迴圈放進去menuJson裡面
//	    if (menuList != null && !menuList.isEmpty()) {
//	        for (MenuBean menu : menuList) {
//	            JSONObject menuJson = new JSONObject()
//	                    .put("id", menu.getId())
//	                    .put("name", menu.getName())
//	                    .put("supply", menu.isSupply());
//	            
//	             使用menuid找到有相關聯的dish
//	            List<DishBean> dishList = dishService.findDishByMenuId(menu.getId());
//	            if (!dishList.isEmpty()) {
//	                JSONArray dishesArray = new JSONArray();
//	            如果有資料 將dish裡面的資料透過迴圈放進去dishesArray裡面
//	                for (DishBean dish : dishList) {
//	                	byte[] photo = dish.getPicture();
//	                    JSONObject dishJson = new JSONObject()
//	                            .put("id", dish.getId())
//	                            .put("name", dish.getName())
//	                            .put("description", dish.getDescription())
//	                            .put("price", dish.getPrice())
//	                            .put("extraInfo", dish.getExtraInfo())
//	                            .put("review", dish.getReview())
//	                            .put("createDate", dish.getCreateDate())
//	                            .put("updateDate", dish.getUpdateDate())
//	                            .put("likes", dish.getLikes())
//	                            .put("dislikes", dish.getDislikes())
//	                            .put("likerate", dish.getLikerate())
//	                            .put("soldOut", dish.isSoldOut())
//	                            .put("fk_menu_id", menu.getId());
//	                    
//	                    if (photo != null && photo.length > 0) {
//	        				String base64Photo = Base64.getEncoder().encodeToString(photo);
//	        				dishJson.put("picture", base64Photo);
//	        			} else {
//	        				dishJson.put("picture", "");
//	        			}
//	                    dishesArray.put(dishJson);
//	                }
//	                將所有dish資料放進變數dishes裡面
//	                menuJson.put("dishes", dishesArray);
//	            }
//	            將放完資料的menuJson放進array裡面
//	            array.put(menuJson);
//	        }
//	        將整個資料放進變數menu裡面
	        
	
	@GetMapping("/findMenuByName")
	public String findAllMenuByName(@RequestBody MenuBean menuBean) {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		
		ShopBean shop = menuBean.getShop();
		List<MenuBean> findMenu = menuService.findMenuByName(menuBean.getName(), shop.getId());
		
		if(findMenu != null && !findMenu.isEmpty()) {
			for(MenuBean menu : findMenu) {
				JSONObject menuJson = new JSONObject()
						.put("id", menu.getId())
						.put("name", menu.getName())
						.put("supply", menu.isSupply())
						.put("fk_shop_id", shop.getId());
				array.put(menuJson);
			}
		}else {
			json.put("message", "查無資料");
		}
		json.put("menu", array);
		return json.toString();
	}
	
	@PutMapping("/updateMenu")
	public String updateMenu(@RequestBody MenuBean menuBean) {
		JSONObject json = new JSONObject();
		MenuBean update = menuService.updateMenu(menuBean.getId(), menuBean);
		if(update != null) {
			json.put("result", "成功");
		}else {
			json.put("result", "失敗");
		}
		return json.toString();	
	
	}
	
	@PostMapping("/creatMenu")
	public String creatMenu(@RequestBody MenuBean menuBean) {
		JSONObject json = new JSONObject();
		MenuBean creatMenu = menuService.creatMenu(menuBean);
		if(creatMenu != null) {
			json.put("result", "成功");
		}else {
			json.put("result", "失敗");
		}
		return json.toString();	
		
	}
	
//	這邊要注意前端輸入的資料id的名稱要確認
//	下面是不同的寫法
//	@DeleteMapping("/deletemenu")
//	public boolean deleteMenuById(@RequestBody Map<String, Object> requestMap) {
//		Integer id = (Integer) requestMap.get("id");
//		System.out.println(id);
//		return menuService.deleteMenu(id);
//	}
	@DeleteMapping("/deleteMenu")
	public String deleteMenuById(@RequestBody MenuBean menuBean) {
		if(menuService.deleteMenu(menuBean.getId())) {
			return "成功" ;
		}
		return "失敗" ;
	}
	
	
}
