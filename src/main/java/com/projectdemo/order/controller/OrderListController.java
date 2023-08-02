package com.projectdemo.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.order.bean.OrderListBean;
import com.projectdemo.order.service.OrderListService;

@RestController
public class OrderListController {

	@Autowired
	private OrderListService olService;
	
	
	@PostMapping("/order/findByCustomerId/{id}")
	public List<OrderListBean> findBycustomerId(@PathVariable Integer id){
		return olService.findOrderByCustomerId(id);
	}

	@PostMapping("/order/findByShopId/{id}")
	public List<OrderListBean> findByShopId(@PathVariable Integer id){
		return olService.findOrderByShopId(id);
	}
	
	
	@PostMapping("/order/insert1")
	public OrderListBean insert1(@RequestBody OrderListBean ol) {
		return olService.addOrder(ol);
	}
	
	
	@PostMapping("/order/findById/{id}")
	public OrderListBean find(@PathVariable Integer id) {
		return olService.findOrderById(id);
	}
	
	@PutMapping("/order/update1/{id}")
	public void update1(@PathVariable Integer id, @RequestBody OrderListBean ol) {
		olService.updateOrderById(id, ol);
	}
	//改變訂單狀態
	@PutMapping("/order/update/status/{id}")
	public void updateStatus(@PathVariable Integer id, @RequestBody OrderListBean ol) {
		olService.updateStatusById(id, ol);
	}
	//改變客戶針對訂單的評論、店家的評價、餐點的評論
	@PutMapping("/order/update/reviews/{id}")
	public void updateReviews(@PathVariable Integer id, @RequestBody OrderListBean ol) {
		olService.updateReviewsById(id, ol);
	}
	//改變店家回覆客戶的評論
	@PutMapping("/order/update/reply/{id}")
	public void updateReply(@PathVariable Integer id, @RequestBody OrderListBean ol) {
		olService.updateReplyById(id, ol);
	}
	
	
	@DeleteMapping("/order/delete1/{id}")
	public void delete1(@PathVariable Integer id) {
		olService.deleteODById(id);
	}
	
}
