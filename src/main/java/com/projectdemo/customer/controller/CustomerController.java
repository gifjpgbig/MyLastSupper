package com.projectdemo.customer.controller;

import java.io.IOException;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projectdemo.customer.bean.CustomerBean;
import com.projectdemo.customer.service.CustomerService;

@RestController
@CrossOrigin()
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	//Create
	//<-------------------------------------------------------------------------->
	@PostMapping("/customer/create")
	public String createCustomer(@RequestBody CustomerBean customer) {
		customerService.createCustomer(customer);
		JSONObject responseJson = new JSONObject();
		responseJson.put("message", "新增成功");
		responseJson.put("success", true);
		return responseJson.toString();
	}
	
	
	//Read
	//<-------------------------------------------------------------------------->
	@GetMapping("/customer/findByName")
	public CustomerBean findCustomerByName(@RequestParam("name") String name) {
		return customerService.findCustomerByName(name);
	}
	
	@PostMapping("/customer/findAll")
	public String findAll(@RequestBody String datas){
		JSONObject requestJson = new JSONObject(datas);
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		Integer count = 0;
		
		String sortOrder = requestJson.getString("sortOrder");
		List<CustomerBean> Customers = customerService.findAllCustomer(sortOrder);
		
		for(CustomerBean customer : Customers) {
			JSONObject item = new JSONObject()
					.put("id", customer.getCustomerID())
					.put("name", customer.getName())
					.put("email", customer.getEmail())
					.put("birthday", customer.getBirthday())
					.put("phone", customer.getPhone());
			array = array.put(item);
			count+=1;
		}
		responseJson.put("count", count);
		responseJson.put("list" , array);
		return responseJson.toString();
	}
	
	@GetMapping("/customer/findByAccount")
	public String findByAccount(@RequestParam("Searchaccount") String account){
		List<CustomerBean> Customers = customerService.findCustomerByAccount(account);
		JSONArray array = new JSONArray();
		JSONObject responseJson = new JSONObject();
		Integer count = 0;
		for(CustomerBean customer : Customers) {
			JSONObject item = new JSONObject()
					.put("id", customer.getCustomerID())
					.put("name", customer.getName())
					.put("email", customer.getEmail())
					.put("birthday", customer.getBirthday())
					.put("phone", customer.getPhone());
			array = array.put(item);
			count+=1;
		}
		responseJson.put("count", count);
		responseJson.put("list" , array);
		return responseJson.toString();
	}
	
	@PostMapping("/customer/find{id}")
	public String findCustomerById(@PathVariable Integer id) {
		JSONObject responseJson = new JSONObject();
		JSONObject item = new JSONObject();
		CustomerBean customer = customerService.findCustomerById(id);
		item.put("id",customer.getCustomerID());
		item.put("name", customer.getName());
		item.put("email", customer.getEmail());
		item.put("birthday", customer.getBirthday());
		item.put("phone", customer.getPhone());
		item.put("password", customer.getPassword());
		byte[] photoBytes = customer.getPhoto();
		if(photoBytes != null && photoBytes.length > 0 ) {
			String base64Photo  = Base64.getEncoder().encodeToString(photoBytes);
			item.put("photo", base64Photo);
		} else {
			item.put("photo","");
		}
		responseJson.put("list",item);
		return responseJson.toString();
	}
	
	@PostMapping("/customer/login")
	public String login(@RequestBody CustomerBean customer) {
		JSONObject responseJson = new JSONObject();
		JSONObject item = new JSONObject();
		
		String account = customer.getAccount();
		String password = customer .getPassword();
		CustomerBean loginInCustomer = customerService.CustomerLogin(account,password);
		item.put("id",loginInCustomer.getCustomerID());
		item.put("name", loginInCustomer.getName());
		item.put("email", loginInCustomer.getEmail());
		item.put("birthday", loginInCustomer.getBirthday());
		item.put("phone", loginInCustomer.getPhone());
		item.put("password", loginInCustomer.getPassword());
		byte[] photoBytes = loginInCustomer.getPhoto();
		if(photoBytes != null && photoBytes.length > 0 ) {
			String base64Photo  = Base64.getEncoder().encodeToString(photoBytes);
			item.put("photo", base64Photo);
		} else {
			item.put("photo","");
		}
		responseJson.put("list",item);
		responseJson.put("message", "登入成功");
		responseJson.put("success", true);
		return responseJson.toString();
	}
	
	
	
	//update
	//<-------------------------------------------------------------------------->
	@PutMapping("/customer/put/{id}")
	public String updateCustomerByID(@RequestBody CustomerBean customer,@PathVariable Integer id) {
		JSONObject responseJson = new JSONObject();
		String name = customer.getName();
		String email = customer.getEmail();
		LocalDate birthday = customer.getBirthday();
		String phone = customer.getPhone();
		String password = customer.getPassword();
		customerService.updateCustomerByID(id, name, email, birthday, phone, password);
		responseJson.put("message", "修改成功");
		responseJson.put("success" ,true);
		return responseJson.toString();
	}
	
	@PutMapping("/customer/updatePhoto/{id}")
	public String updatePhoto(@RequestParam("photoFile") MultipartFile photoFile,@PathVariable Integer id) throws IOException {
		byte[] photoBytes = photoFile.getBytes();
		JSONObject responseJson = new JSONObject();
		customerService.updatePhotoByID(id, photoBytes);
		responseJson.put("message", "更新照片成功");
		responseJson.put("success", true);
		return responseJson.toString();
	}
	
	//delete
	//<-------------------------------------------------------------------------->
	@DeleteMapping("/customer/delete/{id}")
	public String deleteCustomerByID(@PathVariable Integer id) {
		JSONObject responseJson = new JSONObject();
		customerService.deleteCustomerByID(id);
		responseJson.put("message", "刪除客戶資料成功");
		responseJson.put("success", true);
		return responseJson.toString();
	}
}
