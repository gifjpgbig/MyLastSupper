package com.projectdemo.menu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projectdemo.menu.bean.MenuBean;

public interface MenuRepository extends JpaRepository<MenuBean, Integer> {

//	透過商店的ID搜尋到商店內部的全部menu
	@Query(value = "SELECT * FROM Menu AS m WHERE m.fk_shop_id = :shopId", nativeQuery = true)
	List<MenuBean> findMenuByShopId(Integer shopId);

//	透過模糊搜尋菜單名字 所以要給餐廳的ID跟名字
	@Query(value = "SELECT * FROM Menu AS m WHERE m.fk_shop_id = :shopId AND m.name LIKE CONCAT('%', :menuName, '%')", nativeQuery = true)
	List<MenuBean> findMenuByName(@Param("menuName") String menuName, @Param("shopId") Integer shopId);
	
//	使用shopId去找到有相同的menuId
	@Query(value = "SELECT m.id FROM Menu AS m WHERE m.fk_shop_id = :shopId", nativeQuery = true)
	List<Integer> findMenuIdsByShopId(Integer shopId);
	
}