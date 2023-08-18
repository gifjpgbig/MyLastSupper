package com.projectdemo.menu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.menu.bean.MenuCustomizationOptionsBean;


public interface MenuCustomizationOptionsRepository extends JpaRepository<MenuCustomizationOptionsBean, Integer> {

}
