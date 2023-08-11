package com.projectdemo.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.bean.ShopCategoryBean;
import com.projectdemo.shop.dao.ShopCategoryRepository;
import com.projectdemo.shop.dao.ShopRepository;

@Service
public class ShopCategoryService {

	@Autowired
	private ShopCategoryRepository shopCategoryRepository;
	
	@Autowired
	private ShopRepository shopRepository;
	
	public ShopCategoryBean add(ShopCategoryBean bean) {
		return shopCategoryRepository.save(bean);
	}
	
	public ShopCategoryBean findById(Integer id) {
		Optional<ShopCategoryBean> optional = shopCategoryRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public List<ShopCategoryBean> findAllByShopId(Integer id) {
		Optional<ShopBean> optional = shopRepository.findById(id);
		if(optional.isPresent()) {
			return shopCategoryRepository.findAllByShop(optional.get());
		}
		return null;
	}
	
	public ShopCategoryBean update(Integer id, ShopCategoryBean bean) {
		Optional<ShopCategoryBean> optional = shopCategoryRepository.findById(id);
		if(optional.isPresent()) {
			ShopCategoryBean old = optional.get();
			old.setName(bean.getName());
			
			return shopCategoryRepository.save(old);
		}
		return null;
	}
	
	public boolean delete(Integer id) {
		try {
			shopCategoryRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return false;
	}
}
