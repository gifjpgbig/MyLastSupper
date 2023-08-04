package com.projectdemo.order.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.order.bean.OrderListBean;
import com.projectdemo.order.service.OrderListService;

@RestController
@CrossOrigin()
public class OrderListController {

	@Autowired
	private OrderListService olService;

	// 找到全部的訂單
	@PostMapping("/order/find")
	public String find(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();

		System.out.println(json);
		JSONObject countJson = new JSONObject(json);
		long count = olService.count(countJson);
		responseJson.put("count", count);
		
		Page<OrderListBean> orders = olService.findAll(json);
		JSONArray array = new JSONArray();
		if (orders != null && !orders.isEmpty()) {
			for (OrderListBean order : orders) {
				Integer cusID = 0;
				Integer shopID = 0;
				if (order.getCustomer().getCustomerID() != null) {
					cusID = order.getCustomer().getCustomerID();
				}
				if (order.getShop().getId() != null) {
					shopID = order.getShop().getId();
				}
				JSONObject item = new JSONObject()
						.put("id", order.getId())
						.put("address", order.getAddress())
						.put("status", order.getStatus())
						.put("customerID", cusID)
						.put("shopID", shopID);
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

	// 用客戶id去搜尋
	@PostMapping("/order/findByCustomerId/{id}")
	public String findBycustomerId(@PathVariable Integer id) {
		JSONObject responseJson = new JSONObject();
		List<OrderListBean> orders = olService.findOrderByCustomerId(id);
		JSONArray array = new JSONArray();
		if (orders != null && !orders.isEmpty()) {
			for (OrderListBean order : orders) {
				JSONObject item = new JSONObject()
						.put("id", order.getId())
						.put("address", order.getAddress())
						.put("status", order.getStatus())
						.put("customerID", order.getCustomer().getCustomerID())
						.put("customer", order.getCustomer().toString());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

	@PostMapping("/order/findByShopId/{id}")
	public List<OrderListBean> findByShopId(@PathVariable Integer id) {
		return olService.findOrderByShopId(id);
	}

	// 給vue的馬老ajax controller
	@PostMapping("/order/insert1")
	public String insert1(@RequestBody OrderListBean ol) {
		JSONObject responseJson = new JSONObject();
//		JSONObject obj = new JSONObject(json);
//		Integer id = obj.isNull("id") ? null : obj.getInt("id");

//		if(productAjaxService.exists(id)) {
//			responseJson.put("message", "資料已存在");
//			responseJson.put("success", false);
//		} else {
		OrderListBean order = olService.addOrder(ol);
		if (order != null) {
			responseJson.put("message", "新增成功");
			responseJson.put("success", true);
		} else {
			responseJson.put("message", "新增失敗");
			responseJson.put("success", false);
		}
//		}
		return responseJson.toString();

	}
//	@PostMapping("/order/insert1")
//	public OrderListBean insert1(@RequestBody OrderListBean ol) {
//		return olService.addOrder(ol);
//	}

	@PostMapping("/order/findById/{id}")
	public OrderListBean findById(@PathVariable Integer id) {
		return olService.findOrderById(id);
	}

	@PutMapping("/order/update1/{id}")
	public void update1(@PathVariable Integer id, @RequestBody OrderListBean ol) {
		olService.updateOrderById(id, ol);
	}

	// 改變訂單狀態
	@PutMapping("/order/update/status/{id}")
	public String updateStatus(@PathVariable Integer id, @RequestBody OrderListBean ol) {
		System.out.println(ol.toString());
		System.out.println(ol.getStatus());

		JSONObject responseJson = new JSONObject();
		if (!olService.exists(id)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		} else {
			OrderListBean result = olService.updateStatusById(id, ol);
			if (result != null) {
				responseJson.put("message", "資料更新成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "資料更新失敗");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();
	}

	// 改變客戶針對訂單的評論、店家的評價、餐點的評論
	@PutMapping("/order/update/reviews/{id}")
	public void updateReviews(@PathVariable Integer id, @RequestBody OrderListBean ol) {
		olService.updateReviewsById(id, ol);
	}

	// 改變店家回覆客戶的評論
	@PutMapping("/order/update/reply/{id}")
	public void updateReply(@PathVariable Integer id, @RequestBody OrderListBean ol) {
		olService.updateReplyById(id, ol);
	}

	@DeleteMapping("/order/delete1/{id}")
	public String delete1(@PathVariable Integer id) {
		JSONObject responseJson = new JSONObject();
		if (!olService.exists(id)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		} else {
			boolean remove = olService.deleteODById(id);
			if (remove != false) {
				responseJson.put("message", "資料刪除成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "資料刪除失敗");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();
	}

}
