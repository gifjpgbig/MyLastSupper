package com.projectdemo.menu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.menu.bean.MenuCategoryBean;


public interface MenuCategoryRepository extends JpaRepository<MenuCategoryBean, Integer> {

}
