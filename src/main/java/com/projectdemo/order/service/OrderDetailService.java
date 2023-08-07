package com.projectdemo.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.projectdemo.order.bean.OrderDetailBean;
import com.projectdemo.order.dao.OrderDetailRepository;


@Configuration
public class OrderDetailService {

	@Autowired
	private OrderDetailRepository odRepo;
	
	public OrderDetailBean addOD(OrderDetailBean od) {
		return odRepo.save(od);
	}
	
	
	public OrderDetailBean findODById(Integer id) {
		return odRepo.findById(id).get();
	}
	
	
	public List<OrderDetailBean> findAllById(Integer id){
		return odRepo.findAllByOrderId(id);
	}
	
	
	
	 public List<OrderDetailBean> findOrderDetailById(Integer id) {
	        return odRepo.findOrderDetailById(id);
	 }
	
	
//	public OrderDetailBean updateODBById(Integer id, OrderDetailBean od) {
//		Optional<OrderDetailBean> optional = odRepo.findById(id);
//		if(optional.isPresent() && od.getId() == id) {
//			return odRepo.save(od);
//		}
//		return null;
//	}
	
	public OrderDetailBean updateODBById(Integer id, OrderDetailBean updatedOd) {
	    Optional<OrderDetailBean> optional = odRepo.findById(id);
	    if (optional.isPresent()) {
	        OrderDetailBean existingOd = optional.get();
	        // 先將 existingOd 的 orderList 設置為 null，避免循環參考
//	        existingOd.setOrderList(updatedOd.getOrderList());
	        
	        // 將更新的屬性設置到 existingOd 中
	        existingOd.setAmount(updatedOd.getAmount());
	        existingOd.setTotalPrice(updatedOd.getTotalPrice());
	        existingOd.setCustomization(updatedOd.getCustomization());

	        // 保存更新後的 existingOd
	        return odRepo.save(existingOd);
	    }
	    return null;
	}
	
	public void deleteODById(Integer id) {
		odRepo.deleteById(id);
	}
	
}
