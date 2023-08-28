package com.projectdemo.order.controller;


import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.order.bean.DeliverDetailBean;
import com.projectdemo.order.bean.OrderProgressDTO;
import com.projectdemo.order.service.DeliverDetailService;

@RestController
@CrossOrigin()
public class DeliverDetailController {

	@Autowired
	private DeliverDetailService ddService;
	
//	@PostMapping("/order/deliver/insert1")
//	public DeliverDetailBean insert1(@RequestBody DeliverDetailBean dd) {
//		return ddService.addDD(dd);
//	}
	
	
	@PostMapping("/order/deliver/find1/{id}")
	public DeliverDetailBean find1(@PathVariable Integer id) {
		return ddService.findDDById(id);
	}


	//讓外送員查看自己接到的訂單
		//這個邏輯是，查看自己已接單的訂單
		//請注意，資料型態是object
	@PostMapping("/order/deliver/findInProgressByDeliver/{id}")
	public String findInProgressByDeliver(@PathVariable Integer id) {
		JSONObject responseJson = new JSONObject();
//		List<OrderProgressDTO> myOrder = ddService.findInProgressByDeliver();
		List<Object[]> myOrder = ddService.findInProgressByDeliver();
		JSONArray array = new JSONArray();
//		for(OrderProgressDTO order: myOrder) {
		for(Object order: myOrder) {
			JSONObject item = new JSONObject();
			item.put("order", order);
			System.out.println(order.toString());
			System.out.println(order);
			array.put(item);
		}
		responseJson.put("list", array);
		return responseJson.toString();
//		{
//		    "list": [
//		        {
//		            "order": [
//		                "萬華產業園區幸福路一段update成功!",
//		                null,
//		                null,
//		                null,
//		                "酷狗路熊空街我沒K",
//		                "酷狗餐廳"
//		            ]
//		        },
//		        {
//		            "order": [
//		                "萬華產業園區test",
//		                null,
//		                null,
//		                null,
//		                "酷狗路熊空街我沒K",
//		                "酷狗餐廳"
//		            ]
//		        },
//		        ]
//		}
	}
	
	
	
	//讓外送員完成訂單
		//這個邏輯是，把訂單的狀態改變成為已完成
		//讓客服人員可以查看曾經接過這張訂單的所有外送員
	@PostMapping("/order/deliver/findAllByOrderId/{id}")
	public String findAllByOrderId(@PathVariable Integer id) {
		JSONObject responseJson = new JSONObject();
		
		List<DeliverDetailBean> details = ddService.findDDByOrderId(id);
		JSONArray array = new JSONArray();
		if(details != null && !details.isEmpty()) {
			for(DeliverDetailBean detail: details) {
				Integer deliverID = 0;
				Integer orderID = 0;
				if (detail.getDeliverer().getId() != null) {
					deliverID = detail.getDeliverer().getId();
				}
				if (detail.getOrderList().getId() != null) {
					orderID = detail.getOrderList().getId();
				}
				JSONObject item = new JSONObject()
						.put("id", detail.getId())
						.put("address", detail.getAddress())
						.put("arriveTime", detail.getArriveTime())
						.put("deliverTime", detail.getDeliverTime())
						.put("driverName", detail.getDriverName())
						.put("isCancel", detail.isCancel())
						.put("reason", detail.getCancelReason());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();		
	}
	
	
	
	@PutMapping("/order/deliver/update1/{id}")
	public void update1(@PathVariable Integer id, @RequestBody DeliverDetailBean dd) {
		ddService.updateById(id, dd);
	}
	
	
	@DeleteMapping("/order/deliver/delete1")
	public void delete1(@RequestParam("DDid") Integer id) {
		ddService.deleteById(id);
	}
	
	
	
}
