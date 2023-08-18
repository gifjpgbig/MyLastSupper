package com.projectdemo.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.customer.bean.UserInviteCodeBean;
import com.projectdemo.customer.service.InviteCodeService;


@RestController
public class InviteCodeController {
	@Autowired
	private InviteCodeService inviteCodeService;

	@PostMapping("/inviteCode/create/{id}")
	public UserInviteCodeBean inviteCodeCreate(@RequestBody UserInviteCodeBean userInviteCode,
			@PathVariable Integer id) {
		return inviteCodeService.InviteCodeCreate(userInviteCode, id);
	}

	@GetMapping("/inviteCode/read/{id}")
	public void inviteCodeRead(@PathVariable Integer id) {
		inviteCodeService.findInviteCodeByID(id);
	}

	@PutMapping("/inviteCode/update/{id}")
	public String inviteCodeUpdate(@PathVariable Integer id) {
		return inviteCodeService.InviteCodeUpdate(id);
	}

	@DeleteMapping("/inviteCode/delete/{id}")
	public String deleteInviteCodeByID(@PathVariable Integer id) {
	    return inviteCodeService.deleteInviteCodeByID(id);
	}
}
