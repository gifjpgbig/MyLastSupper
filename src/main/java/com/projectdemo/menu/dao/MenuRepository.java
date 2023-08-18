package com.projectdemo.menu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.menu.bean.MenuBean;

public interface MenuRepository extends JpaRepository<MenuBean, Integer>{

}
