package com.projectdemo.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.menu.bean.MenuCategoryBean;
import com.projectdemo.menu.dao.MenuCategoryRepository;


@Service
public class MenuCategoryService {
	@Autowired
	private MenuCategoryRepository menuCategoryRepository;

	public MenuCategoryBean creatDish(MenuCategoryBean menuCategoryBean) {
		return menuCategoryRepository.save(menuCategoryBean);
	}

	public MenuCategoryBean getDishById(Integer id) {
		return menuCategoryRepository.findById(id).orElse(null);
	}

	public List<MenuCategoryBean> getDishAll() {
		return menuCategoryRepository.findAll();
	}

	public MenuCategoryBean updateDish(MenuCategoryBean menuCategoryBean) {
		if (menuCategoryRepository.existsById(menuCategoryBean.getId())) {
			return menuCategoryRepository.save(menuCategoryBean);
		}
		return null;
	}

	public boolean deleteDish(Integer id) {
		if (menuCategoryRepository.findById(id).isPresent()) {
			menuCategoryRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
