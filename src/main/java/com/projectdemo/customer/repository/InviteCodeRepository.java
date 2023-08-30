package com.projectdemo.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.projectdemo.customer.bean.UserInviteCodeBean;

public interface InviteCodeRepository extends JpaRepository<UserInviteCodeBean, Integer> {
	
	
	//CRUD -READING
//	@Query(value = "select * from address where id = :id" , nativeQuery = true)
	@Query("SELECT u FROM UserInviteCodeBean u JOIN FETCH u.customer c WHERE u.id = :id")
	UserInviteCodeBean findInviteCodeByID(@Param("id")Integer id);
	
	@Query(value = "SELECT * FROM user_invite_code WHERE fk_customer_id = :cid",nativeQuery = true)
	UserInviteCodeBean findInviteCodeByCusID(@Param("cid") Integer cid);
	
	//CRUD -UPDATE
	@Transactional
	@Modifying
	@Query(value = "update user_invite_code set invite_quota = :inviteQuota where id = :id",nativeQuery = true)
	void updateInviteCode(@Param("id") Integer id,@Param("inviteQuota") Integer inviteQuota );
	

}
