package com.projectdemo.customer.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.customer.bean.CustomerCouponBean;
import com.projectdemo.customer.service.CustomerCouponService;

@RestController
@CrossOrigin()
public class CustomerCouponController {
	
	@Autowired
	CustomerCouponService customerCouponService;
	
// <-----------------------------------------------------------------------------------------------------------
//		create
	@PostMapping("/customerCoupon/create/{customerID}/{couponID}")
	public String createCustomerCoupon(@PathVariable Integer customerID,@PathVariable Integer couponID){
		return customerCouponService.createCustomerCoupon(customerID, couponID);
	}
	
// <-----------------------------------------------------------------------------------------------------------
//	read
	
	@GetMapping("/customerCoupon/findCustomerCouponByCusID/{customerID}")
	public String findCustomerCouponByCusID(@PathVariable Integer customerID){
		JSONObject responseJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		List<CustomerCouponBean> CustomerCouponList = customerCouponService.findCustomerCouponByCusID(customerID);
		for(CustomerCouponBean customerCoupon:CustomerCouponList) {
			JSONObject customerCouponJson = new JSONObject()
					.put("id", customerCoupon.getId())
					.put("discount", customerCoupon.getCoupon().getDiscountAmount())
					.put("coupon_code", customerCoupon.getCoupon().getCouponCode())
					.put("description", customerCoupon.getCoupon().getDescription());
			jsonArray.put(customerCouponJson);
		}
		responseJson.put("customer_coupon_list",jsonArray );
		return responseJson.toString();
	}
}
