package com.projectdemo.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.menu.bean.MenuHrBean;
import com.projectdemo.menu.dao.MenuHrRepository;


@Service
public class MenuHrService {
	
	@Autowired
	private MenuHrRepository menuHrRepository;

	public MenuHrBean creatDish(MenuHrBean menuHrBean) {
		return menuHrRepository.save(menuHrBean);
	}

	public MenuHrBean getDishById(Integer id) {
		return menuHrRepository.findById(id).orElse(null);
	}

	public List<MenuHrBean> getDishAll() {
		return menuHrRepository.findAll();
	}

	public MenuHrBean updateDish(MenuHrBean menuHrBean) {
		if (menuHrRepository.existsById(menuHrBean.getId())) {
			return menuHrRepository.save(menuHrBean);
		}
		return null;
	}

	public boolean deleteDish(Integer id) {
		if (menuHrRepository.findById(id).isPresent()) {
			menuHrRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
