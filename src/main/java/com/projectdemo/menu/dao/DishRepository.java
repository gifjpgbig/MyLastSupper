package com.projectdemo.menu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.menu.bean.DishBean;

public interface DishRepository extends JpaRepository<DishBean, Integer>{

}