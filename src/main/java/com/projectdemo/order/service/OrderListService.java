package com.projectdemo.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.projectdemo.customer.bean.CustomerBean;
import com.projectdemo.customer.repository.CustomerRepository;
import com.projectdemo.customer.service.CustomerService;
import com.projectdemo.order.bean.OrderListBean;
import com.projectdemo.order.dao.OrderListRepository;
import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.dao.ShopRepository;
import com.projectdemo.shop.service.ShopService;


@Configuration
public class OrderListService {
	
	@Autowired
	private OrderListRepository oLRepo;
	
	@Autowired
	private CustomerRepository cusRepo;
	
	@Autowired
	private ShopRepository shopRepo;
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private CustomerService customerService;
	
	//客戶歷史訂單
	public List<OrderListBean> findOrderByCustomerId(Integer id){
		CustomerBean bean = customerService.findCustomerById(id);
		return oLRepo.findByCustomer(bean);
	}
	//店家歷史訂單
	public List<OrderListBean> findOrderByShopId(Integer id){
		ShopBean bean = shopService.findById(id);
		return oLRepo.findByShop(bean);
	}
	//改變訂單狀態
	public void updateStatusById(Integer id, OrderListBean ol) {
	    Optional<OrderListBean> optional = oLRepo.findById(id);
	    if (optional.isPresent()) {
			OrderListBean olbean = optional.get();
			System.out.println("original olbean:" + olbean.toString());
			olbean.setStatus(ol.getStatus());
			System.out.println("after olbean:" + olbean.toString());
			oLRepo.save(olbean);
	    }
	}
	//改變客戶針對訂單的評論、店家的評價、餐點的評論
	public void updateReviewsById(Integer id, OrderListBean ol) {
		OrderListBean olbean = oLRepo.findById(id).get();
		olbean.setShopReview(ol.getShopReview());
		olbean.setShopComments(ol.getShopComments());
		olbean.setDishComments(ol.getDishComments());
		oLRepo.save(olbean);
	}
	//改變店家回覆客戶的評論
	public void updateReplyById(Integer id, OrderListBean ol) {
		OrderListBean olbean = oLRepo.findById(id).get();
		olbean.setShopFeedbackReply(ol.getShopFeedbackReply());
		oLRepo.save(olbean);
	}
	
	
	public OrderListBean addOrder(OrderListBean ol) {
		return oLRepo.save(ol);
	}
	
	public OrderListBean findOrderById(Integer id) {
		return oLRepo.findById(id).get();
	}
	
	public OrderListBean updateOrderById(Integer id, OrderListBean updatedOd) {
	    Optional<OrderListBean> optional = oLRepo.findById(id);
	    if (optional.isPresent()) {
	        return oLRepo.save(updatedOd);
	    }
	    return null;
	}
	
	public void deleteODById(Integer id) {
		oLRepo.deleteById(id);
	}

}
