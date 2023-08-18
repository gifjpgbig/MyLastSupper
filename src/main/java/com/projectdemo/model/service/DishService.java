package com.projectdemo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.model.bean.menu.DishBean;
import com.projectdemo.model.bean.menu.MenuBean;
import com.projectdemo.model.repository.DishRepository;
import com.projectdemo.model.repository.MenuRepository;

@Service
public class DishService {
	
	@Autowired
	private DishRepository dishRepository;
	@Autowired
	private MenuRepository menuRepository;
	
	public DishBean createDish(DishBean dish,Integer id) {
		Optional<MenuBean> menuOptional = menuRepository.findById(id);
		if(menuOptional.isPresent()) {
			MenuBean menuBean = menuOptional.get();
			dish.setMenu(menuBean);
			return dishRepository.save(dish);
		} else {
			return null;
		}
	}
	
	public List<DishBean> findAllDish() {
		return dishRepository.findAll();
	}
}
