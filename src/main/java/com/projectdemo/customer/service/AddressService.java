package com.projectdemo.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.projectdemo.customer.bean.AddressBean;
import com.projectdemo.customer.bean.CustomerBean;
import com.projectdemo.customer.repository.AddressRepository;
import com.projectdemo.customer.repository.CustomerRepository;

@Configuration
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public AddressBean createAddress(AddressBean address,Integer id) {
		Optional<CustomerBean> customerOptional = customerRepository.findById(id);
		if(customerOptional.isPresent()) {
			CustomerBean customer = customerOptional.get();
			address.setCustomer(customer);
			return addressRepository.save(address);
		} else {
			return null;
		}
		
	}
	
	public String sendAddressToOrderListAddress(AddressBean addressBean) {
		System.out.println("正在傳送地址");
		addressBean.getLocation();
		return "送出地址成功";
	}
	
	public List<AddressBean> findAddressByCusID(Integer cid) {
		return addressRepository.findAddressByCusID(cid);
	}
	
	public AddressBean addressFindByID(Integer id) {
		return addressRepository.findAddressByID(id);
	}
	
	public void addressUpdate(AddressBean addressBean) {
		Integer id = addressBean.getId();
		String building = addressBean.getBuilding();
		String deliveryInstructions = addressBean.getDeliveryInstructions();
		String deliveryOption = addressBean.getDeliveryOption();
		String floorNo = addressBean.getFloorNo();
		String location = addressBean.getLocation();
		addressRepository.addressUpdate(id, building, deliveryInstructions, deliveryOption, floorNo, location);
	}
}
