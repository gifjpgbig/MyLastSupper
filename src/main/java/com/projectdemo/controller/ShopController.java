package com.projectdemo.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projectdemo.model.bean.shop.ShopBean;
import com.projectdemo.model.service.ShopService;


@RestController
@CrossOrigin()
public class ShopController {

	@Autowired
	private ShopService shopService;
	//<----------------------------------------------------------------------------------->
	//Create
	@PostMapping("/shop/create")
	public ShopBean createShop(@RequestBody ShopBean shop) {
		return shopService.createShop(shop);
	}

	//<----------------------------------------------------------------------------------->
	//Read
	@GetMapping("/shop/findAll")
	public String findAllShop(){
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		Integer count = 0;
		
		List<ShopBean> AllShop = shopService.findAllShop();
		
		for(ShopBean shop: AllShop) {
			JSONObject item = new JSONObject()
					.put("id", shop.getId())
					.put("name", shop.getName())
					.put("phone",shop.getPhone())
					.put("address",shop.getAddress())
					.put("openStatus", shop.getOpenStatus())
					.put("email", shop.getEmail());
			byte[] photoBytes = shop.getPhoto();
			if(photoBytes != null && photoBytes.length > 0 ) {
				String base64Photo  = Base64.getEncoder().encodeToString(photoBytes);
				item.put("photo", base64Photo);
			} else {
				item.put("photo","");
			}
			array = array.put(item);
			count +=1;
		}
		responseJson.put("count", count);
		responseJson.put("list" , array);
		return responseJson.toString();
	}
	
	
//	@PostMapping("shop/findFavorites/")
//	public String findFavoritesShop(@RequestBody List<Integer> shopIDList) {
//		JSONObject responseJson = new JSONObject();
//		responseJson.put("message", "更新照片成功");
//		responseJson.put("success", true);
//		return responseJson.toString();
//	}
	
//	@PostMapping("shop/findByID/{id}")
//	public String findShopByID(@PathVariable Integer id) {
//		JSONObject responseJson = new JSONObject();
//		
//		ShopBean shop = shopService.findShopByID(id);
//
//		responseJson.put("message", "尋找商家成功");
//		responseJson.put("success", true);
//		responseJson.put("list",shop);
//		return responseJson.toString();
//	}
	
	@PostMapping("shop/findByID/{id}")
	public ResponseEntity<ShopBean> findShopByID(@PathVariable Integer id) {
		ShopBean shop = shopService.findShopByID(id);
		if (shop != null) {
	        return ResponseEntity.status(HttpStatus.OK)
	            .body(shop);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .build();
	    }
	}
	
//	@PostMapping("shop/findByID/{id}")
//	public ShopBean findShopByID(@PathVariable Integer id) {
//		ShopBean shop = shopService.findShopByID(id);
//		return shop;
//	}
	
//<----------------------------------------------------------------------------------->
	//Update
	
	
	@PutMapping("/shop/updatePhoto/{id}")
	public String updateShopPhoto(@RequestParam("photoFile") MultipartFile photoFile,@PathVariable Integer id) throws IOException {
		byte[] photoBytes = photoFile.getBytes();
		JSONObject responseJson = new JSONObject();
		shopService.updatePhotoByID(id, photoBytes);
		responseJson.put("message", "更新照片成功");
		responseJson.put("success", true);
		return responseJson.toString();
	}
	


}