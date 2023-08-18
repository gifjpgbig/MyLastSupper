package com.projectdemo.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.shop.bean.PrepTimeBean;
import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.dao.PrepTimeRepository;
import com.projectdemo.shop.dao.ShopRepository;

@Service
public class PrepTimeService {

	@Autowired
	private PrepTimeRepository prepTimeRepository;
	
	@Autowired
	private ShopRepository shopRepository;
	
	public PrepTimeBean add(PrepTimeBean bean) {
		return prepTimeRepository.save(bean);
	}
	
	public PrepTimeBean findById(Integer id) {
		Optional<PrepTimeBean> optional = prepTimeRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public List<PrepTimeBean> findAllByShopId(Integer id) {
		Optional<ShopBean> optional = shopRepository.findById(id);
		if(optional.isPresent()) {
			return prepTimeRepository.findAllByShop(optional.get());
		}
		return null;
	}
	
	public PrepTimeBean update(Integer id, PrepTimeBean bean) {
		Optional<PrepTimeBean> optional = prepTimeRepository.findById(id);
		if(optional.isPresent()) {
			PrepTimeBean old = optional.get();
			old.setPrep(bean.getPrep());
			old.setDay(bean.getDay());
			old.setStartTime(bean.getStartTime());
			old.setEndTime(bean.getEndTime());
			
			return prepTimeRepository.save(old);
		}
		return null;
	}
	
	public boolean delete(Integer id) {
		try {
			prepTimeRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return false;
	}
	
	public List<PrepTimeBean> findAllByShopOrdered(Integer id) {
		Optional<ShopBean> optional = shopRepository.findById(id);
		if(optional.isPresent()) {
			return prepTimeRepository.findAllByShopOrdered(id);
		}
		return null;
	}
}
