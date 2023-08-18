package com.projectdemo.shop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.projectdemo.customer.bean.FavoritesBean;
import com.projectdemo.customer.bean.ShoppingCartBean;
import com.projectdemo.manage.bean.ShopHistoryMessageBean;
import com.projectdemo.menu.bean.MenuBean;
import com.projectdemo.order.bean.OrderListBean;
import com.projectdemo.shop.bean.CannedMessageBean;
import com.projectdemo.shop.bean.HolidayBean;
import com.projectdemo.shop.bean.PrepTimeBean;
import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.bean.ShopCategoryBean;
import com.projectdemo.shop.bean.ShopDTO;
import com.projectdemo.shop.service.ShopService;

@RestController
@RequestMapping("/shop")
@CrossOrigin()
public class ShopController {
	
	@Autowired
	private ShopService shopService;

	//First create Shop, then add photo
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
	public String addShopWithPhoto(@RequestParam("shopBean") ShopBean shopBean, @RequestParam("photoFile") MultipartFile photoFile) {
		JSONObject json = new JSONObject();
		try {
			byte[] photo = photoFile.getBytes();
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
	public ResponseEntity<byte[]> getPhoto(@PathVariable Integer id) {
		ShopBean shop = shopService.findById(id);
		
		if(shop == null) {
			return null;
		}
		
		byte[] photo = shop.getPhoto();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(photo, headers, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public String findAll() {
		JSONObject json = new JSONObject();
		List<ShopBean> list = shopService.findAll();
		JSONArray array = new JSONArray();
		for (ShopBean bean : list) {
			ShopDTO shopDTO = convertToShopDTO(bean);
			JSONObject object = new JSONObject(shopDTO);
			array.put(object);
		}
		json.put("list", array);
		return json.toString();
	}
	
	//查詢單筆商店
	@GetMapping("/{id}")
	public String findById(@PathVariable Integer id) {
		JSONObject json = new JSONObject();
		ShopBean shop = shopService.findById(id);
		if(shop != null) {
			// Use DTO to create suitable JSON object
			ShopDTO shopDTO = convertToShopDTO(shop);
//			json.put("shop", new JSONObject(shop));
			json.put("shop", new JSONObject(shopDTO));
			json.put("success", true);
		}
		else {
			json.put("success", false);
			json.put("message", "invalid id");
		}
		return json.toString();
	}
	
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
		dto.setOpenStatus(bean.getOpenStatus());
		dto.setCdate(bean.getCdate());
		dto.setUdate(bean.getUdate());
		dto.setOpenhrId(bean.getOpenhrBean().getId());
		
		
		//photo
		byte[] photo = bean.getPhoto();
		if(photo != null) {
			String base64Image = Base64.getEncoder().encodeToString(photo);
			dto.setPhoto(base64Image);
		}
		
		
		//openhr null exception
		if(bean.getOpenhrBean() != null) {
			dto.setOpenhrId(bean.getOpenhrBean().getId());
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
	
	@GetMapping("/name")
	public String findByName(@RequestParam("name") String name) {
		JSONObject json = new JSONObject();
		if(name != null) {
			ShopBean shopBean = shopService.findByName(name);
			if(shopBean != null) {
				ShopDTO shopDTO = convertToShopDTO(shopBean);
				json.put("shop", new JSONObject(shopDTO));
			}
			else {
				json.put("message", "No results found");
			}
		}
		else {
			json.put("message", "No results found");
		}
		
		return json.toString();
	}
	
	@GetMapping("/nameFuzzy")
	public String findByNameFuzzy(@RequestParam("name") String name) {
		JSONObject json = new JSONObject();
		if(name != null) {
			JSONArray array = new JSONArray();
			List<ShopBean> list = shopService.findFuzzy(name);
			if(! list.isEmpty()) {
				for (ShopBean bean : list) {
					ShopDTO shopDTO = convertToShopDTO(bean);
					JSONObject object = new JSONObject(shopDTO);
					array.put(object);
				}
				json.put("list", array);
			}
			else {
				json.put("message", "No results found");
			}
		}
		else {
			json.put("message", "No results found");
		}
		
		return json.toString();
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Map<String, String> loginData) {
		JSONObject json = new JSONObject();
		String username = loginData.get("account");
		String password = loginData.get("password");
		
		boolean loginValidate = shopService.loginValidate(username, password);
		if(loginValidate) {
			json.put("validate", true);
		}
		else {
			json.put("validate", false);
		}
		return json.toString();
	}
}
