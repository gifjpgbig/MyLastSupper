package com.projectdemo.menu.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.menu.bean.MenuBean;
import com.projectdemo.menu.dao.MenuRepository;
import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.service.ShopService;

@Service
public class MenuService {

	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private ShopService shopService;
	
	
//	透過商店的ID搜尋到商店內部的全部menu
	public List<MenuBean> findMenuByShopId(Integer shopId) {
		return menuRepository.findMenuByShopId(shopId);
	}
	
//	使用shopId去找到有相同的menuId
	public List<Integer> findMenuIdsByShopId(Integer shopId) {
	    return menuRepository.findMenuIdsByShopId(shopId);
	}

//	透過模糊搜尋菜單名字 所以要給餐廳的ID跟名字
	public List<MenuBean> findMenuByName(String menuName, Integer shopId) {
		return menuRepository.findMenuByName(menuName, shopId);
	}

	public MenuBean creatMenu(MenuBean menuBean) {
		ShopBean shop = shopService.findById(menuBean.getShop().getId());
        if (shop != null) {
            menuBean.setShop(shop);
            menuBean.onUpdate();
            return menuRepository.save(menuBean);
        } else {
            // 处理店铺不存在的情况

            return null;
        }
	}

	public MenuBean getMenuById(Integer id) {
		Optional<MenuBean> findById = menuRepository.findById(id);
		if(findById.isPresent()) {
			return findById.get();
		}else {
			return null;
		}
	}

	public MenuBean updateMenu(Integer id ,MenuBean menuBean) {
		Optional<MenuBean> optional = menuRepository.findById(id);
		if(optional.isPresent()) {
			MenuBean oldMenu = optional.get();
			oldMenu.setName(menuBean.getName());
			oldMenu.setUpdateDate(LocalDateTime.now());
			return menuRepository.save(oldMenu);
		}
		return null;
	}

	public boolean deleteMenu(Integer id) {
		if (menuRepository.findById(id).isPresent()) {
			menuRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public boolean menuExists(Integer menuId) {
	    return menuRepository.existsById(menuId);
	}
}
