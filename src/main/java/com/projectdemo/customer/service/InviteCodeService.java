package com.projectdemo.customer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.customer.bean.CustomerBean;
import com.projectdemo.customer.bean.UserInviteCodeBean;
import com.projectdemo.customer.repository.CustomerRepository;
import com.projectdemo.customer.repository.InviteCodeRepository;

@Service
public class InviteCodeService {

	@Autowired
	private InviteCodeRepository inviteCodeRepository;
	@Autowired
	private CustomerRepository customerRepository;

	public UserInviteCodeBean InviteCodeCreate(UserInviteCodeBean userInvitecode, Integer id) {
		Optional<CustomerBean> customerOptional = customerRepository.findById(id);
		if (customerOptional.isPresent()) {
			userInvitecode.setCustomer(customerOptional.get());
			return inviteCodeRepository.save(userInvitecode);
		}
		return null;
	}
	
	public void findInviteCodeByID(Integer id) {
		inviteCodeRepository.findInviteCodeByID(id);
	}

	public String InviteCodeUpdate(Integer id) {
		UserInviteCodeBean findInviteCodeByID = inviteCodeRepository.findInviteCodeByID(id);
		Integer inviteQuota = findInviteCodeByID.getInviteQuota() - 1;
		if(inviteQuota >=0) {
			inviteCodeRepository.updateInviteCode(id, inviteQuota);
			return "更新成功";
		} else {
			return "你已無Quota";
		}
	}
	
	public String deleteInviteCodeByID(Integer id) {
		if (inviteCodeRepository.existsById(id)) {
	        inviteCodeRepository.deleteById(id);
	        return "刪除成功";
	    } else {
	        return "刪除失敗，該資料不存在";
	    }
	}

}
