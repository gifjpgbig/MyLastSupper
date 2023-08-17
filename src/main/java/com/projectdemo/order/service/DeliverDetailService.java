package com.projectdemo.order.service;

import java.util.List;
import java.util.Optional;

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
		//新增一個檢查:當該筆訂單已存在未棄單的外送員，就整個rollback
	public DeliverDetailBean addDD(String json) {
		JSONObject data = new JSONObject(json);
		DeliverDetailBean ddb = new DeliverDetailBean();
		//應該要新增一個判斷式: 在該筆資料已經存在的情形之下，就不讓程式在新增了
		//而是回傳null，還可以測試transactional能否正常使用
		if(ddRepo.checkTakable(data.getInt("orderid")) == null) {
			ddb.setDriverName(data.getString("driver"));
			ddb.setAddress(data.getString("address"));
			Integer dID = data.getInt("deliverID");
			Integer oID = data.getInt("orderid");
			ddb.setDeliverer(dRepo.findById(dID).get());
			ddb.setOrderList(olRepo.findById(oID).get());
			return ddRepo.save(ddb);
		}else {
			return null;
		}
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
	
	public List<DeliverDetailBean> findInProgressByDeliver(){
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

	public List<DeliverDetailBean> findDDByOrderId(Integer id) {
		return ddRepo.findDDByOrderId(id);
	}

}
