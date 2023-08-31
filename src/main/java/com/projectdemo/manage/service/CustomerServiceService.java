package com.projectdemo.manage.service;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.projectdemo.manage.bean.CustomerServiceBean;
import com.projectdemo.manage.dao.CustomerServiceRepository;
import com.projectdemo.order.bean.DeliverDetailBean;
import com.projectdemo.shop.bean.ShopBean;

@Configuration
public class CustomerServiceService {

	@Autowired
	private CustomerServiceRepository csRepo;
	
	public CustomerServiceBean addCDB(CustomerServiceBean csb) {
		return csRepo.save(csb);
	}
	
	public CustomerServiceBean update(Integer id, CustomerServiceBean csb) {
		Optional<CustomerServiceBean> optional = csRepo.findById(id);
		if(optional.isPresent()) {
			CustomerServiceBean csbb = optional.get();
			csbb.setPhoto(csb.getPhoto());
			return csRepo.save(csbb);
		}
		return null;
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
	
	public CustomerServiceBean findById(Integer id) {
		Optional<CustomerServiceBean> optional = csRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	
	
}
