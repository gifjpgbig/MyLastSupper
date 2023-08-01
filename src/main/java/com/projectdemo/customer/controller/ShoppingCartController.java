package com.projectdemo.customer.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.customer.bean.ShoppingCartBean;
import com.projectdemo.customer.service.ShoppingCartService;

import lombok.experimental.PackagePrivate;


@RestController
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@PostMapping("/shoppingCart/create/{cid}/{did}/{sid}")
	public String shoppingCartCreate
	(@RequestBody ShoppingCartBean shoppingCart,@PathVariable Integer cid,@PathVariable Integer did,@PathVariable Integer sid) {
		return shoppingCartService.ShoppingCartCreate(shoppingCart, cid, did, sid);
	}
	
	@GetMapping("/shoppingCart/read/{id}")
	public ShoppingCartBean shoppingCartRead(@PathVariable Integer id) {
//		System.out.println(shoppingCartService.ShoppingCartRead(id).getAmount());
//		return "查詢成功";
		return shoppingCartService.ShoppingCartRead(id);
	}
	
	@GetMapping("/shoppingCart/readAndSendToOrderDetail/{id}")
	public void sendShoppingCart(@PathVariable Integer id,@RequestBody ShoppingCartBean shoppingCart) {
	}
	@PutMapping("/shoppingCart/update/{id}/{amount}")
	public ShoppingCartBean  shoppingCartUpdateAmount(@PathVariable Integer id,@PathVariable Integer amount) {
		return shoppingCartService.shoppingCartUpdateAmount(id,amount);
	}
	
	@DeleteMapping("/shoppingCart/delete/{id}")
	public String deleteShoppingCart(@PathVariable Integer id) {
		return shoppingCartService.deleteShoppingCart(id);
	}
}
