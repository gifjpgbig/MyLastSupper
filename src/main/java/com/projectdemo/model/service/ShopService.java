package com.projectdemo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.model.bean.shop.ShopBean;
import com.projectdemo.model.repository.ShopRepository;

@Service
public class ShopService {
	
	@Autowired
	private ShopRepository shopRepository;
	
	public ShopBean createShop(ShopBean shop) {
		return shopRepository.save(shop);
	}
	
	public List<ShopBean> findAllShop(){
		return shopRepository.findAll();
	}
	
	public void updatePhotoByID(Integer id,byte[] photoBytes) {
		shopRepository.updatePhotoByID(id,photoBytes);
	}
	
	public ShopBean findShopByID(Integer id) {
		return shopRepository.findById(id).get();
	}
	

}
