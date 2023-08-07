package com.projectdemo.shop.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.dao.ShopRepository;

@Configuration
public class ShopService {

	@Autowired
	private ShopRepository shopRepository;
	
	public ShopBean addShop(ShopBean shopBean) {
		return shopRepository.save(shopBean);
	}
	
	public List<ShopBean> findAll() {
		return shopRepository.findAll();
	}
	
	public ShopBean findById(Integer id) {
		Optional<ShopBean> optional = shopRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public ShopBean update(Integer id, ShopBean shopBean) {
		Optional<ShopBean> optional = shopRepository.findById(id);
		if(optional.isPresent()) {
			ShopBean oldShop = optional.get();
			oldShop.setName(shopBean.getName());
			oldShop.setAccount(shopBean.getAccount());
			oldShop.setPassword(shopBean.getPassword());
			oldShop.setEmail(shopBean.getEmail());
			oldShop.setPhone(shopBean.getPhone());
			oldShop.setPhoto(shopBean.getPhoto());
			oldShop.setDistrict(shopBean.getDistrict());
			oldShop.setAddress(shopBean.getAddress());
			oldShop.setLatitude(shopBean.getLatitude());
			oldShop.setLongitude(shopBean.getLongitude());
			oldShop.setReview(shopBean.getReview());			
			oldShop.setBank(shopBean.getBank());
			oldShop.setOpenStatus(shopBean.getOpenStatus());
			oldShop.setUdate(LocalDateTime.now());
			
			return shopRepository.save(oldShop);
		}
		return null;
	}
	
	public boolean delete(Integer id) {
		try {
			shopRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
