package com.projectdemo.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.menu.bean.MenuCustomizationOptionsBean;
import com.projectdemo.menu.dao.MenuCustomizationOptionsRepository;


@Service
public class MenuCustomizationOptionsService {
	@Autowired
	private MenuCustomizationOptionsRepository menuCustomizationOptionsRepository;

	public MenuCustomizationOptionsBean creatDish(MenuCustomizationOptionsBean menuCustomizationOptionsBean) {
		return menuCustomizationOptionsRepository.save(menuCustomizationOptionsBean);
	}

	public MenuCustomizationOptionsBean getDishById(Integer id) {
		return menuCustomizationOptionsRepository.findById(id).orElse(null);
	}

	public List<MenuCustomizationOptionsBean> getDishAll() {
		return menuCustomizationOptionsRepository.findAll();
	}

	public MenuCustomizationOptionsBean updateDish(MenuCustomizationOptionsBean menuCustomizationOptionsBean) {
		if (menuCustomizationOptionsRepository.existsById(menuCustomizationOptionsBean.getId())) {
			return menuCustomizationOptionsRepository.save(menuCustomizationOptionsBean);
		}
		return null;
	}

	public boolean deleteDish(Integer id) {
		if (menuCustomizationOptionsRepository.findById(id).isPresent()) {
			menuCustomizationOptionsRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
