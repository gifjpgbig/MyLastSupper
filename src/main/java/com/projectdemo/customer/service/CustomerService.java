package com.projectdemo.customer.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.customer.bean.CustomerBean;
import com.projectdemo.customer.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	//create
	//<-------------------------------------------------------------------------------------------------------->
	public CustomerBean createCustomer(CustomerBean customer) {
		return customerRepository.save(customer);
	}
	
	//read
	//<-------------------------------------------------------------------------------------------------------->
	public CustomerBean findCustomerByName(String name) {
		return customerRepository.findCustomerByName(name);
	}

	public CustomerBean findCustomerById(Integer id) {
		return customerRepository.findById(id).get();
	}

	public List<CustomerBean> findAllCustomer(String sortOrder) {
		return customerRepository.findAllCustomer(sortOrder);
	}

	public List<CustomerBean> findCustomerByAccount(String account) {
		return customerRepository.findCustomersByAccountContaining(account);
	}
	
	public CustomerBean CustomerLogin(String account ,String password) {
		return customerRepository.CustomerLogin(account,password);
	}
	
	//update
	//<-------------------------------------------------------------------------------------------------------->
	public void updateCustomerByID(Integer id, String name, String email, LocalDate birthday, String phone,
			String password) {
		customerRepository.updateCustomerByID(id, name, email, birthday, phone, password);
	}

	public void updatePhotoByID(Integer id, byte[] photoBytes) throws IOException {
		customerRepository.updatePhotoByID(id, photoBytes);
	}
	//delete
	//<-------------------------------------------------------------------------------------------------------->
	public void deleteCustomerByID(Integer id) {
		customerRepository.deleteById(id);
	}
}
