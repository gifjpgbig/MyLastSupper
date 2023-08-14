package com.projectdemo.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projectdemo.model.bean.deliver.DelivererBean;

public interface DelivererRepository extends JpaRepository<DelivererBean, Integer> {

//	用位置對
	@Query("from DelivererBean where name= ?1")
	DelivererBean findByName(String name);
	
//	指定名字
	@Query("from DelivererBean where name= :n")
	DelivererBean findByName2(@Param("n") String name);

//	模糊查詢(多筆)
	@Query("from DelivererBean where name like %:n%")
	List<DelivererBean> findByNameLike(@Param("n") String name);

//CRUD:U 用id改Name
//	@Transactional寫在service層
	@Modifying
	@Query("update DelivererBean set name = :name where id = :id")
	void updateNameById(@Param("id") Integer id, @Param("name") String newName);
	
//	修改密碼
	@Modifying
	@Query("update DelivererBean set password = :password where id=:id")
	void updatePasswordByID(@Param("id") Integer id,@Param("password")String password);
	
}
