package com.projectdemo.customer.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.projectdemo.customer.bean.ShoppingCartBean;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartBean, Integer> {
	
	@EntityGraph(attributePaths = {"dish"})
    Optional<ShoppingCartBean> findById(Integer id);
	
	@Query(value = "SELECT * FROM shopping_cart where fk_customer_id = :cid", nativeQuery = true)
	List<ShoppingCartBean> findShoppingCartByCusID(@Param("cid")Integer cid);
	
	@Transactional
	@Modifying
	@Query(value = "update shopping_cart set amount = :amount where id = :id" , nativeQuery = true)
	void ShoppingCartUpdate(@Param("id")Integer id,@Param("amount") Integer amount);
}
