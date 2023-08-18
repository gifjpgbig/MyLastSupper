package com.projectdemo.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.model.bean.menu.MenuBean;
import com.projectdemo.model.bean.shop.ShopBean;
import com.projectdemo.model.repository.MenuRepository;
import com.projectdemo.model.repository.ShopRepository;

@Service
public class MenuService {
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private ShopRepository shopRepository;
	
	public MenuBean createMenu(MenuBean menu , Integer id) {
		Optional<ShopBean> shopOptional = shopRepository.findById(id);
		if(shopOptional.isPresent()) {
			ShopBean shop = shopOptional.get();
			menu.setShop(shop);
			return menuRepository.save(menu);
		} else {
			System.out.println("未抓取到shop ID");
			return null;
		}
		
	}
}
