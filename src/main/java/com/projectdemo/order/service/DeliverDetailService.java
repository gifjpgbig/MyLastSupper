package com.projectdemo.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import com.projectdemo.order.bean.DeliverDetailBean;
import com.projectdemo.order.bean.OrderDetailBean;
import com.projectdemo.order.bean.OrderProgressDTO;
import com.projectdemo.order.dao.DeliverDetailRepository;

@Configuration
public class DeliverDetailService {

	@Autowired
	private DeliverDetailRepository ddRepo;
	
	public DeliverDetailBean addDD(DeliverDetailBean dd) {
		return ddRepo.save(dd);
	}
	

	//外送員接單的延伸功能，緊急狀況由客服人員註銷已接單的外送員詳細
	//OrderListService.java 還要搭配更新訂單的狀態
	//讓外送員的該筆訂單吃土，也可以再延伸去算外送員的良率
	//再由良率延伸去停權或是其他管理措施
	//OrderListController.java
	public DeliverDetailBean terminate(String json) {
		JSONObject data = new JSONObject(json);
		DeliverDetailBean ddb = ddRepo.findById(data.getInt("deliver_detail_id")).get();
		if(ddb != null) {
			ddb.setCancel(true);
			ddb.setCancelReason(data.getString("reason"));
			return ddRepo.save(ddb);
		}else {
			return null;
		}
	}
	
	public List<Object[]> findInProgressByDeliver(){
//	public List<OrderProgressDTO> findInProgressByDeliver(){
		return ddRepo.findInProgressByDeliver();
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
	
	
	
}
