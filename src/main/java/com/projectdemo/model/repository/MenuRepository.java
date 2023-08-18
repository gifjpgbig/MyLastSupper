package com.projectdemo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.model.bean.menu.MenuBean;

public interface MenuRepository extends JpaRepository<MenuBean, Integer>{

}
