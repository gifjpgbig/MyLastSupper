package com.projectdemo.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.projectdemo.customer.bean.AddressBean;

public interface AddressRepository extends JpaRepository<AddressBean, Integer> {
	
	@EntityGraph(attributePaths = {"customer"})
    Optional<AddressBean> findById(Integer id);
	
	@Query(value = "SELECT * FROM address WHERE fk_customer_id = :cid",nativeQuery = true)
	List<AddressBean> findAddressByCusID(@Param("cid")Integer cid);
	
//	@Query(value = "select * from address where id = :id" , nativeQuery = true)
	@Query("SELECT a FROM AddressBean a JOIN FETCH a.customer c LEFT JOIN FETCH c.userInvite WHERE a.id = :id")
	AddressBean findAddressByID(@Param("id")Integer id);
	
	@Modifying
    @Transactional
    @Query(value = "UPDATE address SET building = :building , delivery_instructions = :deliveryInstructions"
    		+ " , delivery_option = :deliveryOption , floor_No = :floorNo , location = :location  WHERE id = :id", nativeQuery = true)
    void addressUpdate(@Param("id") Integer id , @Param("building") String building , 
    		@Param("deliveryInstructions") String deliveryInstructions, @Param("deliveryOption")String deliveryOption,
    		@Param("floorNo") String floorNo , @Param("location") String location);
}
