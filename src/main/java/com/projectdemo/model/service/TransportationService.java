package com.projectdemo.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projectdemo.model.bean.deliver.TransportationBean;
import com.projectdemo.model.repository.TransportationRepository;

@Service
@Transactional
public class TransportationService {

	@Autowired
	private TransportationRepository tranRepo;
	
	public TransportationBean addTrans(TransportationBean tran) {
		return tranRepo.save(tran);
	}
	
	public TransportationBean findTranById(Integer id) {
		Optional<TransportationBean> optional = tranRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;	
	}
//	Update以id
	public void updateTransById(Integer id, String type, String brand, String license) {
		tranRepo.updateById(id, type, brand, license);
	}
	
	public  void deleteTransById(Integer id) {
		tranRepo.deleteById(id);
	}
}
