package com.projectdemo.menu.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.menu.bean.DishBean;
import com.projectdemo.menu.bean.MenuBean;
import com.projectdemo.menu.dao.DishRepository;
import com.projectdemo.menu.dao.MenuRepository;

@Service
public class DishService {
	@Autowired
	private DishRepository dishRepository;

	@Autowired
	private MenuRepository menuRepository;
	

	public DishBean creatDish(DishBean dishBean) {
		Integer id = dishBean.getMenu().getId();
		if (menuRepository.existsById(id)) {
			dishBean.onUpdate();
			return dishRepository.save(dishBean);
		}
		return null;
	}
	
	public List<DishBean> findDishByMenuId(Integer menuId) {
	    return dishRepository.findAllDishByMenuId(menuId);
	}
	
	public List<DishBean> findDishSimple(Integer shopId){
		List<MenuBean> menuList = menuRepository.findMenuByShopId(shopId);
        List<DishBean> dishList = new ArrayList<>();

        for (MenuBean menu : menuList) {
            List<DishBean> dishes = dishRepository.findAllDishByMenuId(menu.getId());
            dishList.addAll(dishes);
        }

        return dishList;
	}
	
	
	public List<DishBean> findDishByName(String dishName, Integer menuId) {
		return dishRepository.findDishByName(dishName, menuId);
	}

	public List<DishBean> findAllDish() {
		return dishRepository.findAll();
	}
	
	public DishBean findDishById(Integer id) {
		Optional<DishBean> findById = dishRepository.findById(id);
		return findById.orElse(null);
	}

//	public DishBean findAllDishById(Integer id) {
//		DishBean findAllDishByMenuId = dishRepository.findAllDishByMenuId(id);
//		if (findAllDishByMenuId != null) {
//			return findAllDishByMenuId;
//		}
//		return null;
//	}

	public DishBean updateDish(Integer id, DishBean dishBean) {
		Optional<DishBean> optional = dishRepository.findById(id);
		if (optional.isPresent()) {
			DishBean oldDish = optional.get();
			oldDish.setName(dishBean.getName());
			oldDish.setDescription(dishBean.getDescription());
			oldDish.setPrice(dishBean.getPrice());
			oldDish.setExtraInfo(dishBean.getExtraInfo());
			oldDish.setReview(dishBean.getReview());
			oldDish.setUpdateDate(LocalDateTime.now());
			oldDish.setDietaryRestrictions(dishBean.getDietaryRestrictions());
			oldDish.setLikes(dishBean.getLikes());
			oldDish.setDislikes(dishBean.getDislikes());
			oldDish.setLikerate(dishBean.getLikerate());
			oldDish.setSoldOut(dishBean.isSoldOut());

			return dishRepository.save(oldDish);
		}
		return null;
	}
	
	public DishBean updatePicture(Integer id , byte[] photo) {
		Optional<DishBean> optional = dishRepository.findById(id);
		if (optional.isPresent()) {
			DishBean oldDish = optional.get();
			oldDish.setPicture(photo);
			return dishRepository.save(oldDish);
		}
		return null;
	}
	
	public boolean deleteDish(Integer id) {
		if (dishRepository.findById(id).isPresent()) {
			dishRepository.deleteById(id);
			return true;
		}
		return false;
	}
}