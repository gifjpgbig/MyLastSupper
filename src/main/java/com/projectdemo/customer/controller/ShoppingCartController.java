package com.projectdemo.customer.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.customer.bean.ShoppingCartBean;
import com.projectdemo.customer.service.AddressService;
import com.projectdemo.customer.service.ShoppingCartService;

@RestController
@CrossOrigin()
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private AddressService addressService;

//	<---------------------------------------------------------------------------------------------->
//	create
	@PostMapping("/shoppingCart/create/{cid}/{did}/{sid}")
	public String shoppingCartCreate(@RequestBody ShoppingCartBean shoppingCart, @PathVariable Integer cid,
			@PathVariable Integer did, @PathVariable Integer sid) {
		return shoppingCartService.ShoppingCartCreate(shoppingCart, cid, did, sid);
	}

//	<---------------------------------------------------------------------------------------------->
//	read
	@GetMapping("/shoppingCart/read/{id}")
	public ShoppingCartBean shoppingCartRead(@PathVariable Integer id) {
		return shoppingCartService.ShoppingCartRead(id);
	}

	@GetMapping("/shoppingCart/findByCusID/{cid}")
	public String findShoppingCartByCusID(@PathVariable("cid") Integer cid) {
		JSONObject responseJson = new JSONObject();
		JSONArray shoppingCartArray = new JSONArray();
		List<ShoppingCartBean> shoppingCartList = shoppingCartService.findShoppingCartByCusID(cid);
		for(ShoppingCartBean shoppingCart:shoppingCartList) {
			JSONObject jsonShoppingCart = new JSONObject()
			.put("id",shoppingCart.getId())
			.put("dish_name",shoppingCart.getDish().getName())
			.put("amount",shoppingCart.getAmount())
			.put("customization",shoppingCart.getCustomization())
			.put("dish_price",shoppingCart.getDishPrice())
			.put("total_price",shoppingCart.getTotalPrice())
			.put("fk_shop_id",shoppingCart.getShop().getId())
			.put("shop_name",shoppingCart.getShop().getName());
			shoppingCartArray.put(jsonShoppingCart);
		}
		responseJson.put("shoppingCartList",shoppingCartArray);
		return responseJson.toString(); 
	}
	
//	<---------------------------------------------------------------------------------------------->
//	update	
	@PutMapping("/shoppingCart/update/{id}/{amount}")
	public ShoppingCartBean shoppingCartUpdateAmount(@PathVariable Integer id, @PathVariable Integer amount) {
		return shoppingCartService.shoppingCartUpdateAmount(id, amount);
	}
	
	@PutMapping("shoppingCart/checkout")
	public String shoppingCartCheckOut(
			@RequestParam("selectedProduct")List<String> selectedProduct,
			@RequestParam("totalPrice")Integer totalPrice,
			@RequestParam("customerID")Integer customerID,
			@RequestParam("shopID")Integer shopID) {

		System.out.println("總金額" + totalPrice);
		System.out.println("customerID: " +customerID);
		System.out.println("shopID: " +shopID);
		return "結帳成功";
	}

//	<---------------------------------------------------------------------------------------------->
//	delete
	@DeleteMapping("/shoppingCart/delete/{id}")
	public String deleteShoppingCart(@PathVariable Integer id) {
		return shoppingCartService.deleteShoppingCart(id);
	}
}
