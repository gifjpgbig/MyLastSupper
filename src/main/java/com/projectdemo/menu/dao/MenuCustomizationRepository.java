package com.projectdemo.menu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.projectdemo.menu.bean.MenuCustomizationBean;

public interface MenuCustomizationRepository extends JpaRepository<MenuCustomizationBean, Integer> {

	@Transactional
	@Modifying
	@Query(value="DELETE FROM menu_customization WHERE fk_dish_id = :dishId" , nativeQuery = true)
	void deleteAllMenuCustomizationByDishId(@Param("dishId") Integer dishId);
	
	@Query(value="SELECT * FROM menu_customization WHERE fk_dish_id = :dishId" , nativeQuery = true)
	List<MenuCustomizationBean> findMenuCustomizationByDishId(@Param("dishId") Integer dishId);
	
}