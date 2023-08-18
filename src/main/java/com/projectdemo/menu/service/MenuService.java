package com.projectdemo.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.menu.bean.MenuBean;
import com.projectdemo.menu.dao.MenuRepository;


@Service
public class MenuService {
	
	@Autowired
	private MenuRepository menuRepository;
	
	public MenuBean creatMenu(MenuBean menuBean) {
		return menuRepository.save(menuBean);
	}
	
	public MenuBean getMenuById(Integer id) {
		return menuRepository.findById(id).orElse(null);
	}
	
	public List<MenuBean> getMenuAll() {
		return menuRepository.findAll();
	}
	
	public MenuBean updateMenu(MenuBean menuBean) {
		if(menuRepository.existsById(menuBean.getId())) {
			return menuRepository.save(menuBean);
		}
		return null;
	}
	
	public boolean deleteMenu(Integer id) {
		if(menuRepository.findById(id).isPresent()) {
			menuRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
