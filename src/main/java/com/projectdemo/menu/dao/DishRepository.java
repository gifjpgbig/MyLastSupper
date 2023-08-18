package com.projectdemo.menu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projectdemo.menu.bean.DishBean;

public interface DishRepository extends JpaRepository<DishBean, Integer> {

//	找到menu後應該就要找到相關的dish
	@Query(value = "SELECT * FROM Dish AS d WHERE d.fk_menu_id = :menuId", nativeQuery = true)
	List<DishBean> findAllDishByMenuId(Integer menuId);

////	理論上沒有menu就不會有dish所以要刪除應該要找menuId
//	@Query(value="DELETE FROM Dish AS d WHERE d.fk_menu_id = :menuId", nativeQuery = true)
//	void deleteAllByMenuId(Integer menuId);
	
//	透過模糊搜尋菜單名字 所以要給餐廳的ID跟名字
	@Query(value = "SELECT * FROM Dish AS d WHERE d.fk_menu_id = :menuId AND d.name LIKE CONCAT('%', :dishName, '%')", nativeQuery = true)
	List<DishBean> findDishByName(@Param("dishName") String dishName, @Param("menuId") Integer menuId);

}