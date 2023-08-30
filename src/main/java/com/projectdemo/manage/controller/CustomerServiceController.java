package com.projectdemo.manage.controller;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projectdemo.manage.bean.CustomerServiceBean;
import com.projectdemo.manage.service.CustomerServiceService;
import com.projectdemo.shop.bean.ShopBean;

@RestController
@CrossOrigin()
public class CustomerServiceController {

	@Autowired
	private CustomerServiceService csService;

	@PostMapping("/manage/cs/add")
	public String add(@RequestPart String json, @RequestPart("photoFile") MultipartFile photoFile) {
		JSONObject responseJson = new JSONObject();
		
		
		JSONObject data = new JSONObject(json);
		

		try {
			byte[] photo = photoFile.getBytes();
			CustomerServiceBean csb = new CustomerServiceBean();
			csb.setAccount(data.getString("account"));
			csb.setAuthorizations(data.getString("auth"));
			csb.setName(data.getString("name"));
			csb.setPassword(data.getString("password"));
			csb.setPhoto(photo);
		
			CustomerServiceBean addCDB = csService.addCDB(csb);
			if(addCDB != null) {
				responseJson.put("message", "管理員新增成功！");
				responseJson.put("success", true);
			}else {
				responseJson.put("message", "管理員新增失敗！");
				responseJson.put("success", false);
			}
		} catch (IOException e) {
			responseJson.put("message", "相片規格不符合");
			responseJson.put("success", false);
			e.printStackTrace();
		} 
		
		return responseJson.toString();
	}
	
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
	
	

	@PostMapping("/manage/cs/login")
	public String login(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CustomerServiceBean cs = csService.checkLogin(json);
		if (cs != null) {
			responseJson.put("csName", cs.getName());
			responseJson.put("auth", cs.getAuthorizations());
			responseJson.put("message", "登入成功！");
			responseJson.put("success", true);
		} else {
			responseJson.put("message", "登入失敗！");
			responseJson.put("success", false);
		}
		return responseJson.toString();
	}

}
