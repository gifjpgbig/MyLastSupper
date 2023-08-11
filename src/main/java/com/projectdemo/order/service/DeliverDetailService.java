package com.projectdemo.order.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.projectdemo.deliver.dao.DelivererRepository;
import com.projectdemo.order.bean.DeliverDetailBean;
import com.projectdemo.order.bean.OrderDetailBean;
import com.projectdemo.order.dao.DeliverDetailRepository;
import com.projectdemo.order.dao.OrderListRepository;

@Configuration
public class DeliverDetailService {

	@Autowired
	private DeliverDetailRepository ddRepo;
	
	@Autowired
	private DelivererRepository dRepo;

	@Autowired
	private OrderListRepository olRepo;
	
	
	
	//每當外送員成功接到一筆訂單，就針對orderlist的deliverDetail新增一筆資料
	//這支service程式被應用在OrderListController
	
	public DeliverDetailBean addDD(String json) {
		JSONObject data = new JSONObject(json);
		DeliverDetailBean ddb = new DeliverDetailBean();
		//應該要新增一個判斷式: 在該筆資料已經存在的情形之下，就不讓程式在新增了，而是回傳錯誤，還可以測試transactional能否正常使用
		ddb.setDriverName(data.getString("driver"));
		ddb.setAddress(data.getString("address"));
		Integer dID = data.getInt("deliverID");
		Integer oID = data.getInt("orderid");
		
		
		ddb.setDeliverer(dRepo.findById(dID).get());
		ddb.setOrderList(olRepo.findById(oID).get());
		
		return ddRepo.save(ddb);
	}

	public DeliverDetailBean findDDById(Integer id) {
		return ddRepo.findById(id).get();
	}

	public void deleteById(Integer id) {
		ddRepo.deleteById(id);
	}

	public void updateById(Integer id, DeliverDetailBean dd) {
		ddRepo.save(dd);
	}

	public List<DeliverDetailBean> findDDByOrderId(Integer id) {
		return ddRepo.findDDByOrderId(id);
	}

}
