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

	public UserInviteCodeBean InviteCodeCreate(Integer id) {
		Optional<CustomerBean> customerOptional = customerRepository.findById(id);
		UserInviteCodeBean userInviteCode = new UserInviteCodeBean();
		if (customerOptional.isPresent()) {
			userInviteCode.setCustomer(customerOptional.get());
			userInviteCode.setAmount(id);
			Integer code = 123456 + id;
			userInviteCode.setCode("InviteCode" + String.valueOf(code));
			userInviteCode.setInvitedQuota(200);
			userInviteCode.setInviteQuota(150);
			return inviteCodeRepository.save(userInviteCode);
		}
		return null;
	}
	
	
	public UserInviteCodeBean findInviteCodeByCusID(Integer cid) {
		return inviteCodeRepository.findInviteCodeByCusID(cid);
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
