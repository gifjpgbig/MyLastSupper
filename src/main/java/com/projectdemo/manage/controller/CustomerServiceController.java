package com.projectdemo.manage.controller;

import java.io.IOException;
import java.util.Base64;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projectdemo.manage.bean.CustomerServiceBean;
import com.projectdemo.manage.service.CustomerServiceService;
import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.service.ShopService;

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
			if (addCDB != null) {
				responseJson.put("message", "管理員新增成功！");
				responseJson.put("success", true);
			} else {
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

	@PostMapping("/manage/cs/addPhoto/{id}")
	public String addShopPhoto(@PathVariable("id") Integer id, @RequestBody MultipartFile photoFile) {
		JSONObject json = new JSONObject();
		CustomerServiceBean csb = new CustomerServiceBean();
		try {
			byte[] photo = photoFile.getBytes();
			csb.setPhoto(photo);
			CustomerServiceBean update = csService.update(id, csb);
			if (update != null) {
				json.put("success", true);
				json.put("message", "照片更新成功");
			} else {
				json.put("success", false);
				json.put("message", "照片更新失敗");
			}
		} catch (IOException e) {
			json.put("message", "invalid photo");
			json.put("success", false);
			e.printStackTrace();
		}
		return json.toString();
	}

	/**
	 * GET PHOTO BY CUSSERVICE ID
	 * 
	 * @param id cusService ID
	 * @return Base64 photo
	 */
	
	@GetMapping("/manage/cs/findPhoto/{id}")
	public String getPhoto(@PathVariable Integer id) {
		JSONObject json = new JSONObject();
		CustomerServiceBean csb = csService.findById(id);
		if (csb == null) {
			return null;
		}
		
		byte[] photo = csb.getPhoto();
		if (photo != null) {
			String base64Image = Base64.getEncoder().encodeToString(photo);
			json.put("success", true);
			json.put("photo", base64Image);
		} else {
			json.put("success", false);
		}
		
		return json.toString();
	}

	@PostMapping("/manage/cs/login")
	public String login(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CustomerServiceBean cs = csService.checkLogin(json);
		if (cs != null) {
			responseJson.put("csID", cs.getId());
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
