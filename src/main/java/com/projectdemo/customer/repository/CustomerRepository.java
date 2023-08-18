package com.projectdemo.customer.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.projectdemo.customer.bean.CustomerBean;

public interface CustomerRepository extends JpaRepository<CustomerBean, Integer> {
	//Read
	//<------------------------------------------------------------------------------------------------>
	@Query(value = "SELECT * FROM customer ORDER BY CASE WHEN :sortOrder = 'asc' THEN id END ASC, "
			+ "CASE WHEN :sortOrder = 'desc' THEN id END DESC", nativeQuery = true)
	List<CustomerBean> findAllCustomer(@Param("sortOrder")String sortOrder);
	
	@Query(value = "select * from customer where name = :name" , nativeQuery = true)
	CustomerBean findCustomerByName(@Param("name") String name);
	
	@Query(value = "SELECT * FROM customer WHERE account LIKE %:account%", nativeQuery = true)
    List<CustomerBean> findCustomersByAccountContaining(@Param("account") String account);
	
	@Query(value = "SELECT * FROM customer WHERE　account = :account AND password = :password", nativeQuery = true)
	CustomerBean CustomerLogin(@Param("account") String account, @Param("password") String password);
	
	
	
	
	//update
	//<------------------------------------------------------------------------------------------>
    @Modifying
    @Transactional
    @Query(value = "UPDATE customer SET name = :name, email = :email, "
    + "birthday = :birthday, phone = :phone , password = :password "
    + " WHERE id = :id",nativeQuery = true)
    void updateCustomerByID(@Param("id") Integer id, @Param("name") String name, 
    						@Param("email") String email, @Param("birthday") LocalDate birthday, 
    						@Param("phone") String phone, @Param("password") String password
    						);
    
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE customer SET photo = :photo   WHERE id = :id", nativeQuery = true)
    void updatePhotoByID(@Param("id") Integer id , @Param("photo") byte[] photoBytes);

}

