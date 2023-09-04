package com.projectdemo.manage.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.projectdemo.manage.bean.CustomerServiceBean;
import com.projectdemo.manage.dao.CustomerServiceRepository;

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
	
	
	public CustomerServiceBean isExists(String uid) {
		CustomerServiceBean csb = csRepo.findCSBByUID(uid);
		if(csb != null) {
			return csb;
		}else {
			return null;
		}
	}
	
	public CustomerServiceBean createNewBean(CustomerServiceBean csb) {
		csb.setAuthorizations("guest");
		csb.setFirstLogin(true);
		return csRepo.save(csb);
	}
	
	public CustomerServiceBean updateApplyText(CustomerServiceBean csb, String apply) {
		csb.setApplyText(apply);
		return csRepo.save(csb);
	}

	public CustomerServiceBean updateReplyText(CustomerServiceBean csb, String reply) {
		csb.setReplyText(reply);
		return csRepo.save(csb);
	}
	
	public CustomerServiceBean updateAuth(CustomerServiceBean csb, String auth) {
		csb.setAuthorizations(auth);
		return csRepo.save(csb);
	}
	
	public CustomerServiceBean updateLoginStatus(CustomerServiceBean csb) {
		csb.setFirstLogin(false);
		return csRepo.save(csb);
	}
	
	public List<CustomerServiceBean> findManager(){
		return csRepo.findByAuth("Manager");
	}
	
	public List<CustomerServiceBean> findUser(){
		return csRepo.findByAuth("user");
	}
	
	public List<CustomerServiceBean> findGuest(){
		return csRepo.findByAuth("guest");
	}	
}
