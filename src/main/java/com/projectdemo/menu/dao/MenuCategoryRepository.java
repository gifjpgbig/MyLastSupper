package com.projectdemo.menu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.projectdemo.menu.bean.MenuCategoryBean;


public interface MenuCategoryRepository extends JpaRepository<MenuCategoryBean, Integer> {

	
	@Query(value="SELECT * FROM menu_category AS mc WHERE mc.name LIKE CONCAT('%', :menuCategoryName, '%')", nativeQuery = true)
	List<MenuCategoryBean> findMenuCategoryByName(@Param("menuCategoryName") String menuName);
	
	@Query(value="SELECT * FROM menu_category AS mc WHERE mc.fk_menu_id = :menuId", nativeQuery = true)
	List<MenuCategoryBean> findMenuCategoryByMenuId(@Param("menuId") Integer menuId);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM menu_category WHERE fk_menu_id = :menuId" , nativeQuery = true)
	void deleteAllMenuCategoryByMenuId(@Param("menuId") Integer menuId);
	
}
