package com.projectdemo.manage.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.manage.bean.CustomerServiceBean;
import com.projectdemo.manage.service.CustomerServiceService;

@RestController
@CrossOrigin()
public class CustomerServiceController {

	@Autowired
	private CustomerServiceService csService;
	
	@PostMapping("/manage/cs/add")
	public String add(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CustomerServiceBean addCDB = csService.addCDB(json);
		if(addCDB != null) {
			responseJson.put("message", "管理員新增成功！");
			responseJson.put("success", true);
		}else {
			responseJson.put("message", "管理員新增失敗！");
			responseJson.put("success", false);
		}
		return responseJson.toString();
	}
	
	@PostMapping("/manage/cs/login")
	public String login(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CustomerServiceBean cs = csService.checkLogin(json);
		if(cs != null) {
			responseJson.put("csName", cs.getName());
			responseJson.put("auth", cs.getAuthorizations());
			responseJson.put("message", "登入成功！");
			responseJson.put("success", true);
		}else {
			responseJson.put("message", "登入失敗！");
			responseJson.put("success", false);
		}
		return responseJson.toString();
	}	
	
}
