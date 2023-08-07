package com.projectdemo.order.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.order.bean.OrderDetailBean;
import com.projectdemo.order.service.OrderDetailService;

@RestController
public class OrderDetailController {

	@Autowired
	private OrderDetailService odService;
	
	
	@GetMapping("/order/detail/find1/{id}")
	public OrderDetailBean find1(@PathVariable Integer id) {
		return odService.findODById(id);
	}

	
	@GetMapping("/order/detail/find2/{id}")
	public List<OrderDetailBean> find2(@PathVariable Integer id) {
		List<OrderDetailBean> findOrderDetailById = odService.findOrderDetailById(id);
//		System.out.println(findOrderDetailById.getDish().getId());
//		System.out.println(findOrderDetailById.getOrderList().getId());
		return findOrderDetailById;
	}

	@PostMapping("/order/detail/findAllByOrderId")
	public String findAllByOrderId(@PathVariable Integer id) {
		JSONObject responseJson = new JSONObject();
		List<OrderDetailBean> orders = odService.findOrderDetailById(id);
		
		JSONArray array = new JSONArray();
		if(orders != null && !orders.isEmpty()) {
			for(OrderDetailBean order : orders) {
				Integer dishID = 0;
				Integer detailID = 0;
				if (order.getDish().getId() != null) {
					dishID = order.getDish().getId();
				}
				if (order.getOrderList().getId() != null) {
					detailID = order.getOrderList().getId();
				}
				
				JSONObject item = new JSONObject()
						.put("id", order.getId())
						.put("amount", order.getAmount())
						.put("customization", order.getCustomization())
						.put("dishID", dishID)
						.put("detailID", detailID);
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
		
	}
	
	
	@PostMapping("/order/detail/insert1")
	public OrderDetailBean insert1(@RequestBody OrderDetailBean od) {
		return odService.addOD(od);
	}
	
	@PutMapping("/order/detail/update1/{id}")
	public OrderDetailBean update1(@PathVariable Integer id, @RequestBody OrderDetailBean od) {
		return odService.updateODBById(id, od);
	}
	
	
	@DeleteMapping("/order/detail/delete1")
	public void delete1(@RequestParam("ODid") Integer id) {
		odService.deleteODById(id);
	}
	
}
