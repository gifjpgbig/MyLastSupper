package com.projectdemo.customer.controller;

import java.util.Base64;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.customer.bean.FavoritesBean;
import com.projectdemo.customer.service.FavoritesService;

@RestController
@CrossOrigin()
public class FavoritesController {

	@Autowired
	private FavoritesService favoritesService;

	// <-------------------------------------------------------------->
	// create
	@PostMapping("/favorites/create/{cusID}/{shopID}")
	public String createOrDeleteFavorites(@PathVariable Integer cusID, @PathVariable Integer shopID) {
		FavoritesBean checkResult = favoritesService.checkIfExists(cusID, shopID);
		JSONObject responseJson = new JSONObject();
		if (checkResult != null) {
			Integer id = checkResult.getId();
			favoritesService.deleteFavortesByID(id);
			responseJson.put("message", "已從最愛商家中移除");
		} else {
			favoritesService.createFavorites(cusID, shopID);
			responseJson.put("message", "已新增至最愛商家");
		}

		return responseJson.toString();
	}

	// <-------------------------------------------------------------->
	// read
	@GetMapping("/favorites/findByCusID/{cid}") // 這個查詢沒有抓取shop的詳細資訊
	public String findFavoritesShopByCusID(@PathVariable Integer cid) {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<FavoritesBean> FavoriteShopIDList = favoritesService.findFavoriteShopByCusID(cid);
		for (FavoritesBean FavoriteShop : FavoriteShopIDList) {
			JSONObject item = new JSONObject().put("id", FavoriteShop.getShop().getId());
			array.put(item);
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

	@GetMapping("/favorites/findAllFavoritesShop/{cid}") // 這個查詢有抓取shop的詳細資訊
	public String findAllFavoritesShop(@PathVariable Integer cid) {
		List<FavoritesBean> favorites = favoritesService.findFavoriteShopByCusID(cid);
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		for (FavoritesBean favorite : favorites) {
			JSONObject item = new JSONObject().put("id", favorite.getShop().getId())
					.put("name", favorite.getShop().getName()).put("phone", favorite.getShop().getPhone())
					.put("address", favorite.getShop().getAddress())
					.put("openStatus", favorite.getShop().isOpenStatus()).put("email", favorite.getShop().getEmail());
			byte[] photoBytes = favorite.getShop().getPhoto();
			if (photoBytes != null && photoBytes.length > 0) {
				String base64Photo = Base64.getEncoder().encodeToString(photoBytes);
				item.put("photo", base64Photo);
			} else {
				item.put("photo", "");
			}
			array = array.put(item);
		}
		responseJson.put("shopList", array);
		return responseJson.toString();
	}

	@GetMapping("/favorites/checkIsExists/{cID}/{shopID}")
	public boolean checkIsiXists(@PathVariable Integer cID, @PathVariable Integer shopID) {
		FavoritesBean checkIfExists = favoritesService.checkIfExists(cID, shopID);
		if (checkIfExists == null) {
			return false;
		} else {
			return true;
		}
	}

	// <------------------------------------------------------------------->
	// update
}
