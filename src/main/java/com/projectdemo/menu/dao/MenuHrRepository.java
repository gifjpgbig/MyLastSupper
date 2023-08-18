package com.projectdemo.menu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.menu.bean.MenuHrBean;



public interface MenuHrRepository extends JpaRepository<MenuHrBean, Integer> {

}
