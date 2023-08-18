package com.projectdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.model.bean.menu.MenuBean;
import com.projectdemo.model.service.MenuService;

@RestController
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@PostMapping("/menu/create")
	public MenuBean createMenu(@RequestBody MenuBean menu , @RequestParam("id")Integer id) {
		return menuService.createMenu(menu, id);
	}
}
