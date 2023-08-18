package com.projectdemo.shop.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.shop.bean.CannedMessageBean;
import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.dao.CannedMessageRepository;
import com.projectdemo.shop.dao.ShopRepository;

@Service
public class CannedMessageService {

	@Autowired
	CannedMessageRepository cannedMessageRepository;
	
	@Autowired
	ShopRepository shopRepository;
	
	public CannedMessageBean addCannedMessage(CannedMessageBean bean) {
		return cannedMessageRepository.save(bean);
	}
	
	public CannedMessageBean findById(Integer id) {
		Optional<CannedMessageBean> optional = cannedMessageRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public List<CannedMessageBean> findAllByShopId(Integer id) {
		Optional<ShopBean> optional = shopRepository.findById(id);
		if(optional.isPresent()) {
			return cannedMessageRepository.findAllByShop(optional.get());
		}
		return null;
	}
	
	public CannedMessageBean update(Integer id, CannedMessageBean msg) {
		Optional<CannedMessageBean> optional = cannedMessageRepository.findById(id);
		if(optional.isPresent()) {
			CannedMessageBean oldMsg = optional.get();
			oldMsg.setMessageUDate(LocalDateTime.now());
			oldMsg.setMoneyRange(msg.getMoneyRange());
			oldMsg.setScoreRange(msg.getScoreRange());
			oldMsg.setMessageSendTime(msg.getMessageSendTime());
			oldMsg.setMessageText(msg.getMessageText());
			
			return cannedMessageRepository.save(oldMsg);
		}
		return null;
	}
	
	public boolean delete(Integer id) {
		try {
			cannedMessageRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<CannedMessageBean> findAllByShopOrdered(Integer id) {
		Optional<ShopBean> optional = shopRepository.findById(id);
		if(optional.isPresent()) {
			return cannedMessageRepository.findAllByShopOrdered(id);
		}
		return null;
	}
}
