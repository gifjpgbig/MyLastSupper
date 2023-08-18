package com.projectdemo.menu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.menu.bean.MenuBean;
import com.projectdemo.menu.bean.MenuCategoryBean;
import com.projectdemo.menu.dao.MenuCategoryRepository;

@Service
public class MenuCategoryService {
	@Autowired
	private MenuCategoryRepository menuCategoryRepository;

	@Autowired
	private MenuService menuService;
	
	public List<MenuCategoryBean> findMenuCategoryByName(String name){
		return menuCategoryRepository.findMenuCategoryByName(name);
	}

	
	
	public MenuCategoryBean creatMenuCategory(MenuCategoryBean menuCategoryBean) {
		Integer menuId = menuCategoryBean.getMenu().getId();
		if (menuId != null) {
			MenuBean menu = menuService.getMenuById(menuId);
			if (menu != null) {
				menuCategoryBean.setMenu(menu);
				return menuCategoryRepository.save(menuCategoryBean);
			}
		}
		return null;
	}

	public MenuCategoryBean getMenuCategoryById(Integer id) {
		return menuCategoryRepository.findById(id).orElse(null);
	}

	public List<MenuCategoryBean> getMenuCategoryAll() {
		return menuCategoryRepository.findAll();
	}

	public MenuCategoryBean updateMenuCategory(Integer id , MenuCategoryBean menuCategoryBean) {
		Optional<MenuCategoryBean> Optional = menuCategoryRepository.findById(id);
		if(Optional.isPresent()) {
			MenuCategoryBean oldBean = Optional.get();
			oldBean.setName(menuCategoryBean.getName());
			return menuCategoryRepository.save(oldBean);
		}
		return null;
	}
	
	public boolean findMenuCategoryByMenuId(Integer menuId){
		if(menuCategoryRepository.findMenuCategoryByMenuId(menuId).isEmpty()) {
			return false;
		}else {
			return true;
		}
	}

	public boolean deleteAllMenuCategoryByMenuId(Integer menuId) {
		boolean menuCategories = findMenuCategoryByMenuId(menuId);
		if (menuCategories) {
			menuCategoryRepository.deleteAllMenuCategoryByMenuId(menuId);
			return true;
		}
		return false;
	}
	
	
}
