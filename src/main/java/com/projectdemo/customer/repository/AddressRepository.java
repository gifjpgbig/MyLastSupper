package com.projectdemo.customer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projectdemo.customer.bean.AddressBean;

public interface AddressRepository extends JpaRepository<AddressBean, Integer> {
	
	@EntityGraph(attributePaths = {"customer"})
    Optional<AddressBean> findById(Integer id);
	
	
//	@Query(value = "select * from address where id = :id" , nativeQuery = true)
	@Query("SELECT a FROM AddressBean a JOIN FETCH a.customer c LEFT JOIN FETCH c.userInvite WHERE a.id = :id")
	AddressBean findAddressByID(@Param("id")Integer id);
}
