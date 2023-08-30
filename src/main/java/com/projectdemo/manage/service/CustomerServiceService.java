package com.projectdemo.manage.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.projectdemo.manage.bean.CustomerServiceBean;
import com.projectdemo.manage.dao.CustomerServiceRepository;
import com.projectdemo.order.bean.DeliverDetailBean;

@Configuration
public class CustomerServiceService {

	@Autowired
	private CustomerServiceRepository csRepo;
	
	public CustomerServiceBean addCDB(String json) {
		JSONObject data = new JSONObject(json);
		CustomerServiceBean csb = new CustomerServiceBean();
		csb.setAccount(data.getString("account"));
		csb.setAuthorizations(data.getString("auth"));
		csb.setName(data.getString("name"));
		csb.setPassword(data.getString("password"));
		
		
		CustomerServiceBean addCsb = csRepo.save(csb);
		if(addCsb != null) {
			return addCsb;
		}else {
			return null;
		}
	}
	
	
	public CustomerServiceBean checkLogin(String json) {
		JSONObject data = new JSONObject(json);
		CustomerServiceBean csb = new CustomerServiceBean();
		CustomerServiceBean foundCS = csRepo.findCustomerServiceByAccount(data.getString("account"));
		if(foundCS != null) {
			if(foundCS.getPassword().equals(data.getString("password"))) {
				return foundCS;
			}
		}
		return null;
	}
	
	
	
	
}
