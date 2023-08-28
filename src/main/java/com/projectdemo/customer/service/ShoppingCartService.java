package com.projectdemo.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.projectdemo.customer.bean.CustomerBean;
import com.projectdemo.customer.bean.ShoppingCartBean;
import com.projectdemo.customer.repository.CustomerRepository;
import com.projectdemo.customer.repository.ShoppingCartRepository;
import com.projectdemo.menu.bean.DishBean;
import com.projectdemo.menu.dao.DishRepository;
import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.dao.ShopRepository;

@Service
public class ShoppingCartService {
	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private DishRepository dishRepository;
	@Autowired
	private ShopRepository shopRepository;

	public String ShoppingCartCreate(ShoppingCartBean shoppingCart, Integer cid, Integer did, Integer sid) {
		Optional<CustomerBean> customerOptional = customerRepository.findById(cid);
		Optional<DishBean> dishOptional = dishRepository.findById(did);
		Optional<ShopBean> shopOptional = shopRepository.findById(sid);
		if (customerOptional.isPresent() && dishOptional.isPresent() && shopOptional.isPresent()) {
			CustomerBean customerBean = customerOptional.get();
			DishBean dishBean = dishOptional.get();
			ShopBean shopBean = shopOptional.get();
			shoppingCart.setPurchased(false);
			shoppingCart.setCustomer(customerBean);
			shoppingCart.setDish(dishBean);
			shoppingCart.setShop(shopBean);
			shoppingCartRepository.save(shoppingCart);
			return "新增成功";
		}
		return "新增失敗";
	}

	public ShoppingCartBean ShoppingCartRead(Integer id) {
		return shoppingCartRepository.findById(id).get();
	}
	
	public List<ShoppingCartBean> findShoppingCartByCusID(Integer cid){
		return shoppingCartRepository.findShoppingCartByCusID(cid);
	}
	
	

	public String shoppingCartUpdateAmount(ShoppingCartBean shoppingCart) {
		ShoppingCartBean shoppingCartBean = shoppingCartRepository.findById(shoppingCart.getId()).get();
		Integer oldAmount = shoppingCartBean.getAmount();
		Integer newAmount = shoppingCart.getAmount();
		Integer totalPrice = (newAmount - oldAmount) * (shoppingCart.getDishPrice()) + shoppingCart.getTotalPrice();
		shoppingCartRepository.ShoppingCartUpdate(shoppingCart.getId(),shoppingCart.getAmount(),totalPrice);
		return "更改成功，請重新點擊商家名稱確認";
	}


	public String deleteShoppingCart(Integer id) {
		if (shoppingCartRepository.existsById(id)) {
			shoppingCartRepository.deleteById(id);
			return "刪除成功";
		}
		return "查無此資料";
	}

}
