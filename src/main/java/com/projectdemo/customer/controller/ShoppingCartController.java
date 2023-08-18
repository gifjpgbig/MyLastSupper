package com.projectdemo.customer.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.customer.bean.AddressBean;
import com.projectdemo.customer.bean.ShoppingCartBean;
import com.projectdemo.customer.service.AddressService;
import com.projectdemo.customer.service.ShoppingCartService;


@RestController
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private AddressService addressService;
	
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
	
	@GetMapping("/shoppingCart/sendShoppingCart")
	public void sendShoppingCart(@RequestBody List<ShoppingCartBean> shoppingCartList,@RequestBody AddressBean addressBean) {
		//處理orderList table
		shoppingCartService.SendshoppingCartToOrderList(shoppingCartList);
		//處理orderList table中的address
		addressService.sendAddressToOrderListAddress(addressBean);
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
