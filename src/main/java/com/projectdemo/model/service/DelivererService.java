package com.projectdemo.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projectdemo.model.bean.deliver.DelivererBean;
import com.projectdemo.model.repository.DelivererRepository;

@Service
@Transactional
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
	public void updateDelerInfoById(Integer id, String name) {
	DelivererBean deliverer = delivererRepo.findById(id).orElse(null);
		if(deliverer!=null) {
			deliverer.onUpdate();
			deliverer.setName(name);
			delivererRepo.save(deliverer);
		}
	}
//	Delete
	public boolean deleteDelerById(Integer id) {
		if(id!=null && delivererRepo.existsById(id)) {
			delivererRepo.deleteById(id);
			return true;
		}
		return false;
	}
//	Name查帳號存在(還沒用到***)
	public boolean checkIfDelivererExist(String name) {
		DelivererBean dbDeliverer= delivererRepo.findByName2(name);
		if(dbDeliverer!=null) {
			return true;
		}else {
			return false;
		}
	}
//	ID查帳號存在
	public boolean exists(Integer id) {
		return delivererRepo.findById(id)!=null;
	}
//	外送員登入
	public DelivererBean login(String delername, String password) {
		DelivererBean deler=delivererRepo.findByName2(delername);
			if(deler!=null) {
				if(password!=null) {
					String fromDB = deler.getPassword();
					if(fromDB.equals(password)) {
						return deler;
					}
				}
			}
			return null;
	}
//	外送員改密碼
	public boolean changePassword(String delername,String oldPassword,String newPassword) {
		DelivererBean deler = this.login(delername, oldPassword);
		if(deler!=null) {
			if(newPassword!=null) {
				deler.onUpdate();
				deler.setPassword(newPassword);
				delivererRepo.save(deler);
				return true;
			}
		}
		return false;
	}
}
