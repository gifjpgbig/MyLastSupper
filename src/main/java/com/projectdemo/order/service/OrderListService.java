package com.projectdemo.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.projectdemo.customer.bean.CustomerBean;
import com.projectdemo.customer.repository.CustomerRepository;
import com.projectdemo.customer.service.CustomerService;
import com.projectdemo.order.bean.OrderListBean;
import com.projectdemo.order.dao.OrderListRepository;
import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.dao.ShopRepository;
import com.projectdemo.shop.service.ShopService;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Configuration
public class OrderListService {
	@PersistenceContext
	private Session session;
	public Session getSession() {
		return session;
	}
	
	
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
	
	
	public long count(JSONObject obj) {
	    CriteriaBuilder criteriaBuilder = this.getSession().getCriteriaBuilder();
	    CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
	    System.out.println(obj.toString());
	    //from orderList
	    Root<OrderListBean> root = criteriaQuery.from(OrderListBean.class);
	    
	    // select count(*)
	    criteriaQuery = criteriaQuery.select(criteriaBuilder.count(root));
	    // where
	    List<Predicate> predicates = new ArrayList<>();
	    if (obj.has("cusID") && obj.get("cusID")!=null && !obj.get("cusID").equals("")) {
	        System.out.println();
	    	Integer id = obj.getInt("cusID");
//	    	Path<CustomerBean> cus = root.get("customer");

	    	predicates.add(criteriaBuilder.equal(root.get("customer").get("id"), id));
	        
	    }
//	    if (obj.has("name")) {
//	        String name = obj.getString("name");
//	        if (!name.isEmpty()) {
//	            predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
//	        }
//	    }
//	    if (obj.has("price")) {
//	        Double price = obj.getDouble("price");
//	        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price));
//	    }
//	    if (obj.has("make")) {
//	        String make = obj.getString("make");
//	        if (!make.isEmpty()) {
//	            java.util.Date date = DatetimeConverter.parse(make, "yyyy-MM-dd");
//	            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("make"), date));
//	        }
//	    }
//	    if (obj.has("expire")) {
//	        Integer expire = obj.getInt("expire");
//	        predicates.add(criteriaBuilder.equal(root.get("expire"), expire));
//	    }
	    
	    if (!predicates.isEmpty()) {
	    	criteriaQuery = criteriaQuery.where(predicates.toArray(new Predicate[0]));
	    }

	    TypedQuery<Long> typedQuery = this.getSession().createQuery(criteriaQuery);
	    long total = typedQuery.getSingleResult();
	    return total;
	}
	
	
	//找到所有訂單、且
	public Page<OrderListBean> findAll(String json){
		JSONObject obj = new JSONObject(json);
		Integer pageNumber = obj.getInt("start");
		Integer rows = obj.getInt("rows") == 0? Integer.MAX_VALUE: obj.getInt("rows");
		String sortOrder = obj.getString("sortOrder");
		String sortType = obj.getString("sortType");
		
		PageRequest pgb = PageRequest.of(pageNumber,  rows, Sort.Direction.ASC, sortType);
		return oLRepo.findAll(pgb);
	}
		
	
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
	public OrderListBean updateStatusById(Integer id, OrderListBean ol) {
	    Optional<OrderListBean> optional = oLRepo.findById(id);
	    if (optional.isPresent()) {
			OrderListBean olbean = optional.get();
//			System.out.println("original olbean:" + olbean.toString());
			olbean.setStatus(ol.getStatus());
//			System.out.println("after olbean:" + olbean.toString());
			return oLRepo.save(olbean);
	    }
	    return null;
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
	
	public boolean deleteODById(Integer id) {
		if(exists(id)) {
			oLRepo.deleteById(id);
			return true;
		}
		return false;
	}

	public boolean exists(Integer id) {
		return oLRepo.findById(id).get() != null;
	}
	
	
	
}
