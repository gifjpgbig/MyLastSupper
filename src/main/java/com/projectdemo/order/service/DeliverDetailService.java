package com.projectdemo.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.projectdemo.order.bean.DeliverDetailBean;
import com.projectdemo.order.dao.DeliverDetailRepository;

@Configuration
public class DeliverDetailService {

	@Autowired
	private DeliverDetailRepository ddRepo;
	
	public DeliverDetailBean addDD(DeliverDetailBean dd) {
		return ddRepo.save(dd);
	}
	
	public DeliverDetailBean findDDById(Integer id) {
		return ddRepo.findById(id).get();
			
	}
	
	public void deleteById(Integer id) {
		ddRepo.deleteById(id);
	}
	
	public void updateById(Integer id, DeliverDetailBean dd) {
		ddRepo.save(dd);
	}
	
	
	
}
