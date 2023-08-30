package com.projectdemo.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.customer.bean.UserInviteCodeBean;
import com.projectdemo.customer.service.InviteCodeService;

@RestController
@CrossOrigin()
public class InviteCodeController {
	@Autowired
	private InviteCodeService inviteCodeService;

// <----------------------------------------------------------------------------------------
//	create
	@PostMapping("/inviteCode/create/{id}")
	public UserInviteCodeBean inviteCodeCreate(@PathVariable Integer id) {
		return inviteCodeService.InviteCodeCreate(id);
	}

// <----------------------------------------------------------------------------------------
//	read
	
	@GetMapping("/inviteCode/findInviteCodeByCusID/{cid}")
	public UserInviteCodeBean findInviteCodeByCusID(@PathVariable("cid") Integer cid) {
		return inviteCodeService.findInviteCodeByCusID(cid);
	}

// <----------------------------------------------------------------------------------------
//	update
	@PutMapping("/inviteCode/update/{id}")
	public String inviteCodeUpdate(@PathVariable Integer id) {
		return inviteCodeService.InviteCodeUpdate(id);
	}

// <----------------------------------------------------------------------------------------
//	delete
	@DeleteMapping("/inviteCode/delete/{id}")
	public String deleteInviteCodeByID(@PathVariable Integer id) {
		return inviteCodeService.deleteInviteCodeByID(id);
	}
}
