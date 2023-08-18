package com.projectdemo.menu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.projectdemo.menu.bean.MenuCustomizationOptionsBean;


public interface MenuCustomizationOptionsRepository extends JpaRepository<MenuCustomizationOptionsBean, Integer> {

	@Transactional
	@Modifying
	@Query(value="DELETE FROM menu_customization_options WHERE fk_menu_customization_id = :optionsId" , nativeQuery = true)
	void deleteAllMenuCustomizationOptionsByOptionsId(@Param("optionsId") Integer optionsId);
	
	@Query(value="SELECT * FROM menu_customization_options WHERE fk_menu_customization_id = :optionsId", nativeQuery = true)
	List<MenuCustomizationOptionsBean> findMenuCustomizationOptionsByOptionsId(@Param("optionsId") Integer optionsId);
}

