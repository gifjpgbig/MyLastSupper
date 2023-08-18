package com.projectdemo.order.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.order.bean.DeliverDetailBean;
import com.projectdemo.order.service.DeliverDetailService;

@RestController
public class DeliverDetailController {

	@Autowired
	private DeliverDetailService ddService;
	
	@PostMapping("/order/deliver/insert1")
	public DeliverDetailBean insert1(@RequestBody DeliverDetailBean dd) {
		return ddService.addDD(dd);
	}
	
	
	@GetMapping("/order/deliver/find1/{id}")
	public DeliverDetailBean find1(@PathVariable Integer id) {
		return ddService.findDDById(id);
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
