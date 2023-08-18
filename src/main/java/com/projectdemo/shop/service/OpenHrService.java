package com.projectdemo.shop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.shop.bean.OpenHrBean;
import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.dao.OpenHrRepository;
import com.projectdemo.shop.dao.ShopRepository;

@Service
public class OpenHrService {
	
	@Autowired
	private OpenHrRepository openHrRepository;
	
	@Autowired
	private ShopRepository shopRepository;
	
	public OpenHrBean add(OpenHrBean bean) {
		Integer id = bean.getShop().getId();
		if(shopRepository.existsById(id)) {
			return openHrRepository.save(bean);			
		}
		return null;
	}
	
	public OpenHrBean findById(Integer id) {
		Optional<OpenHrBean> optional = openHrRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public OpenHrBean update(Integer id, OpenHrBean bean) {
		Optional<OpenHrBean> optional = openHrRepository.findById(id);
		if(optional.isPresent()) {
			OpenHrBean old = optional.get();
			old.setMonOpen(bean.getMonOpen());
			old.setMonClose(bean.getMonClose());
			old.setTueOpen(bean.getTueOpen());
			old.setTueClose(bean.getTueClose());
			old.setWedOpen(bean.getWedOpen());
			old.setWedClose(bean.getWedClose());
			old.setThrOpen(bean.getThrOpen());
			old.setThrClose(bean.getThrClose());
			old.setFriOpen(bean.getFriOpen());
			old.setFriClose(bean.getFriClose());
			old.setSatOpen(bean.getSatOpen());
			old.setSatClose(bean.getSatClose());
			old.setSunOpen(bean.getSunOpen());
			old.setSunClose(bean.getSunClose());
			
			return openHrRepository.save(old);
		}
		return null;
	}
	
	public boolean delete(Integer id) {
		try {
			openHrRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return false;
	}
	
	public OpenHrBean findByShop(Integer id) {
		Optional<ShopBean> optional = shopRepository.findById(id);
		if(optional.isPresent()) {
			return openHrRepository.findByShop(optional.get());
		}
		else {
			return null;
		}
	}
}
