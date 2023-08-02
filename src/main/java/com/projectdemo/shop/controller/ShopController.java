package com.projectdemo.controller;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.projectdemo.model.bean.shop.ShopBean;
import com.projectdemo.model.service.ShopService;

@RestController
@RequestMapping("/shop")
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
			JSONObject object = new JSONObject(bean);
			array.put(object);
		}
		json.put("list", array);
		return json.toString();
	}

	@GetMapping("/{id}")
	public String findById(@PathVariable Integer id) {
		JSONObject json = new JSONObject();
		ShopBean shop = shopService.findById(id);
		if(shop != null) {
			json.put("shop", new JSONObject(shop));			
		}
		else {
			json.put("message", "invalid id");
		}
		return json.toString();
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
