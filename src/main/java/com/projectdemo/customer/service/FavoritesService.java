package com.projectdemo.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.customer.bean.CustomerBean;
import com.projectdemo.customer.bean.FavoritesBean;
import com.projectdemo.customer.repository.CustomerRepository;
import com.projectdemo.customer.repository.FavoritesRepository;
import com.projectdemo.model.bean.shop.ShopBean;
import com.projectdemo.model.repository.ShopRepository;

@Service
public class FavoritesService {
	
	@Autowired
	private FavoritesRepository favoritesRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ShopRepository shopRepository;
	public FavoritesBean createFavorites(Integer cusID,Integer shopID) {
		FavoritesBean favorites = new FavoritesBean();
		Optional<CustomerBean> CusOptional = customerRepository.findById(cusID);
		Optional<ShopBean> ShopOptional = shopRepository.findById(shopID);
		if(CusOptional.isPresent() && ShopOptional.isPresent()) {
			favorites.setCustomer(CusOptional.get());
			favorites.setShop(ShopOptional.get());
			return favoritesRepository.save(favorites);
		}
		return null;
	}
	
	public FavoritesBean checkIfExists(Integer cusID, Integer shopID){
		List<FavoritesBean> allFavoritesList = favoritesRepository.findAll();
		FavoritesBean result = null;
		for(FavoritesBean favorite: allFavoritesList) {
			if(cusID == favorite.getCustomer().getCustomerID() && shopID == favorite.getShop().getId()) { 
				result = favorite;
				break;
			}
		}
		return result;
	}
	
	public List<FavoritesBean> findFavoriteShopByCusID(Integer cusID){
		return favoritesRepository.findFavoriteShopByCusID(cusID);
	}	
	
	public void deleteFavortesByID(Integer id) {
		favoritesRepository.deleteById(id);
	}
}
