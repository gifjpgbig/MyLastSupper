package com.projectdemo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.model.bean.customer.UserInviteCodeBean;

public interface InviteCodeRepository extends JpaRepository<UserInviteCodeBean, Integer> {

}
