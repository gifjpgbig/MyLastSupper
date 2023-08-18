package com.projectdemo.shop.controller;


import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.bean.ShopDTO;
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
	

	//Code based on teacher's, uses RequestParam
	//Never tested so don't know if it works
	@PostMapping("/addWithPhoto")
	public String addShopWithPhoto(@RequestPart String jsonPayload, @RequestPart("photoFile") MultipartFile photoFile) {
		JSONObject json = new JSONObject();
		JSONObject payload = new JSONObject(jsonPayload);
		try {
			byte[] photo = photoFile.getBytes();
			
			ShopBean shopBean = new ShopBean();
			shopBean.setName(payload.getString("name"));
			shopBean.setAccount(payload.getString("account"));
			shopBean.setPassword(payload.getString("password"));
			shopBean.setEmail(payload.getString("email"));
			shopBean.setPhone(payload.getString("phone"));
			shopBean.setDistrict(payload.getString("district"));
			shopBean.setAddress(payload.getString("address"));
			shopBean.setBank(payload.getString("bank"));
			
			shopBean.setPhoto(photo);
			ShopBean shop = shopService.addShop(shopBean);
			if (shop != null) {
				json.put("success", true);
			} else {
				json.put("success", false);
			}
		} catch (IOException e) {
			json.put("message", "invalid photo");
			e.printStackTrace();
		}
		return json.toString();
	}
	
	//Add shop and photo separately
	@PostMapping("/addPhoto/{id}")
	public String addShopPhoto(@PathVariable("id") Integer id, @RequestBody MultipartFile photoFile) {
		JSONObject json = new JSONObject();
		ShopBean shopBean = shopService.findById(id);
		try {
			byte[] photo = photoFile.getBytes();
			shopBean.setPhoto(photo);
			ShopBean update = shopService.update(id, shopBean);
			if (update != null) {
				json.put("success", true);
			} else {
				json.put("success", false);
			}
		} catch (IOException e) {
			json.put("message", "invalid photo");
			e.printStackTrace();
		}
		return json.toString();
	}
	
	//Get photo separately
	@GetMapping("/findPhoto/{id}") //shop id
	public String getPhoto(@PathVariable Integer id) {
		JSONObject json = new JSONObject();
		ShopBean shop = shopService.findById(id);
		
		if(shop == null) {
			return null;
		}
		
		byte[] photo = shop.getPhoto();
		if(photo != null) {
			String base64Image = Base64.getEncoder().encodeToString(photo);
			json.put("success", true);
			json.put("photo", base64Image);
		}
		else {
			json.put("success", false);
		}
		return json.toString();
	}
	
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

//	@GetMapping("/{id}")
//	public String findById(@PathVariable Integer id) {
//		JSONObject json = new JSONObject();
//		ShopBean shop = shopService.findById(id);
//		if(shop != null) {
//			json.put("shop", new JSONObject(shop));			
//		}
//		else {
//			json.put("message", "invalid id");
//		}
//		return json.toString();
//	}

	private ShopDTO convertToShopDTO(ShopBean bean) {
		ShopDTO dto = new ShopDTO();
		
		// Shop columns
		dto.setId(bean.getId());
		dto.setName(bean.getName());
		dto.setAccount(bean.getAccount());
		dto.setPassword(bean.getPassword());
		dto.setEmail(bean.getEmail());
		dto.setPhone(bean.getPhone());
		dto.setDistrict(bean.getDistrict());
		dto.setAddress(bean.getAddress());
		dto.setLatitude(bean.getLatitude());
		dto.setLongitude(bean.getLongitude());
		dto.setReview(bean.getReview());
		dto.setBank(bean.getBank());
		dto.setOpenStatus(bean.isOpenStatus());
		dto.setCdate(bean.getCdate());
		dto.setUdate(bean.getUdate());
		
		// Open Hour
		if(bean.getOpenhrBean() != null) {
			dto.setOpenhrId(bean.getOpenhrBean().getId());
		}
		
		// Photo
		byte[] photo = bean.getPhoto();
		if(photo != null) {
			String base64Image = Base64.getEncoder().encodeToString(photo);
			dto.setPhoto(base64Image);
		}
		
		// Prep Time
		List<PrepTimeBean> prepTime = bean.getPrepTime();
		ArrayList<Integer> prepTimeList = new ArrayList<Integer>();
		for(PrepTimeBean prep : prepTime) {
			prepTimeList.add(prep.getId());
		}
		dto.setPrepTimeId(prepTimeList);
		
		// Shopping Cart
		List<ShoppingCartBean> shoppingCart = bean.getShoppingCart();
		ArrayList<Integer> shoppingCartList = new ArrayList<Integer>();
		for(ShoppingCartBean cart : shoppingCart) {
			shoppingCartList.add(cart.getId());
		}
		dto.setShoppingCartId(shoppingCartList);
		
		// Canned Message
		List<CannedMessageBean> cannedMessage = bean.getCannedMessage();
		ArrayList<Integer> cannedMessageList = new ArrayList<Integer>();
		for(CannedMessageBean msg : cannedMessage) {
			cannedMessageList.add(msg.getId());
		}
		dto.setCannedMessageId(cannedMessageList);
		
		// Shop Category
		List<ShopCategoryBean> shopCategory = bean.getShopCategory();
		ArrayList<Integer> shopCategoryList = new ArrayList<Integer>();
		for(ShopCategoryBean cat : shopCategory) {
			shopCategoryList.add(cat.getId());
		}
		dto.setShopCategoryId(shopCategoryList);
		
		// Holiday
		List<HolidayBean> holiday = bean.getHoliday();
		ArrayList<Integer> holidayList = new ArrayList<Integer>();
		for(HolidayBean hol : holiday) {
			holidayList.add(hol.getId());
		}
		dto.setHolidayId(holidayList);
		
		// Menu
		List<MenuBean> menu = bean.getMenu();
		ArrayList<Integer> menuList = new ArrayList<Integer>();
		for(MenuBean men : menu) {
			menuList.add(men.getId());
		}
		dto.setMenuId(menuList);
		
		// Order List
		List<OrderListBean> orderList = bean.getOrderList();
		ArrayList<Integer> orderArrayList = new ArrayList<Integer>();
		for(OrderListBean list: orderList) {
			orderArrayList.add(list.getId());
		}
		dto.setOrderListId(orderArrayList);
		
		// Shop History Message
		List<ShopHistoryMessageBean> shopHistoryMessage = bean.getShopHistoryMessage();
		ArrayList<Integer> shopHistoryList = new ArrayList<Integer>();
		for(ShopHistoryMessageBean hist : shopHistoryMessage) {
			shopHistoryList.add(hist.getId());
		}
		dto.setShopHistoryMessageId(shopHistoryList);
		
		// Favorites
		List<FavoritesBean> favorites = bean.getFavorites();
		ArrayList<Integer> favoritesList = new ArrayList<Integer>();
		for(FavoritesBean fav : favorites) {
			favoritesList.add(fav.getId());
		}
		dto.setFavoritesId(favoritesList);
		
		return dto;
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
