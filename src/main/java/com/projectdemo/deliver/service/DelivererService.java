package com.projectdemo.deliver.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projectdemo.deliver.bean.DelivererBean;
import com.projectdemo.deliver.dao.DelivererRepository;

@Service
public class DelivererService {

	@Autowired
	private DelivererRepository delivererRepo;

//	CRUD:C用到
	public DelivererBean addDeliverer(DelivererBean deler) {
		return delivererRepo.save(deler);
	}
//	查詢 id
	public DelivererBean findDelerById(Integer id) {
		Optional<DelivererBean> optional = delivererRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
//	查詢 Name
//	public DelivererBean findDelerByName(String name) {
//		return delivererRepo.findByName2(name);
//	}
//	查詢 Name調整
	public DelivererBean findDelerByName(String name) {
		DelivererBean findName = delivererRepo.findByName2(name);
		if(findName!=null) {
			return findName;
		}else {
		return null;
		}
	}
//	查詢 NameLike(模糊搜尋)
	public List<DelivererBean> findDelerByNameLike(String name) {
		List<DelivererBean> list = delivererRepo.findByNameLike(name);
		
		if(list.isEmpty()) {
			return null;
		}else {
			return list;
		}
	}
//	Update以id改Name加入updateTime
	@Transactional
	public void updateDelerInfoById(Integer id, String name) {
	DelivererBean deliverer = delivererRepo.findById(id).orElse(null);
		if(deliverer!=null) {
//			deliverer.onUpdate();
			deliverer.setName(name);
			delivererRepo.save(deliverer);
		}
	}

//	delete原始
//	public void deleteDelerById(Integer id) {
//		delivererRepo.deleteById(id);
//	}
//	delete調整
	public void deleteDelerById(Integer id) {
		if(id!=null) {
			delivererRepo.deleteById(id);
		}
	}
//	確認帳號是否存在(還沒用到***)
	public boolean checkIfDelivererExist(String name) {
		DelivererBean dbDeliverer= delivererRepo.findByName2(name);
		if(dbDeliverer!=null) {
			return true;
		}else {
			return false;
		}
	}
	
}
