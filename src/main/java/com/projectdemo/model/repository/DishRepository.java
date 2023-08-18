package com.projectdemo.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projectdemo.model.bean.menu.DishBean;

public interface DishRepository extends JpaRepository<DishBean, Integer>{
	
}
