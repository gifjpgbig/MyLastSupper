package com.projectdemo.shop.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.projectdemo.shop.bean.OpenHrBean;
import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.bean.ShopCategoryBean;
import com.projectdemo.shop.dao.OpenHrRepository;
import com.projectdemo.shop.dao.ShopCategoryRepository;
import com.projectdemo.shop.dao.ShopRepository;

@Configuration
public class ShopService {

	@Autowired
	private ShopRepository shopRepository;

	@Autowired
	private ShopCategoryRepository shopCategoryRepository;

	@Autowired
	private OpenHrRepository openHrRepository;

	public ShopBean addShop(ShopBean shopBean) {
		return shopRepository.save(shopBean);
	}

	public List<ShopBean> findAll() {
		return shopRepository.findAll();
	}

	public ShopBean findById(Integer id) {
		Optional<ShopBean> optional = shopRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public ShopBean update(Integer id, ShopBean shopBean) {
		Optional<ShopBean> optional = shopRepository.findById(id);
		if (optional.isPresent()) {
			ShopBean oldShop = optional.get();
			oldShop.setName(shopBean.getName());
			oldShop.setAccount(shopBean.getAccount());
			oldShop.setPassword(shopBean.getPassword());
			oldShop.setEmail(shopBean.getEmail());
			oldShop.setPhone(shopBean.getPhone());
			oldShop.setPhoto(shopBean.getPhoto());
			oldShop.setDistrict(shopBean.getDistrict());
			oldShop.setAddress(shopBean.getAddress());
			oldShop.setLatitude(shopBean.getLatitude());
			oldShop.setLongitude(shopBean.getLongitude());
			oldShop.setReview(shopBean.getReview());
			oldShop.setBank(shopBean.getBank());
			oldShop.setOpenStatus(shopBean.isOpenStatus());
			oldShop.setUdate(LocalDateTime.now());

			return shopRepository.save(oldShop);
		}
		return null;
	}

	public boolean delete(Integer id) {
		try {
			shopRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public ShopBean findByName(String name) {
		ShopBean bean = shopRepository.findByName(name);
		if (bean != null) {
			return bean;
		}
		return null;
	}

	public List<ShopBean> findFuzzy(String name) {
		List<ShopBean> list = shopRepository.findFuzzy(name);
		if (!list.isEmpty()) {
			return list;
		}
		return null;
	}

	public Integer loginValidate(String username, String password) {
		ShopBean shopBean = shopRepository.findByAccount(username);
		if (shopBean != null) {
			if (password.equals(shopBean.getPassword())) {
				return shopBean.getId();
			}
		}
		return -1;
	}

	public List<ShopBean> batchInsert(List<ShopBean> list) {
		try {
			List<ShopBean> beans = shopRepository.saveAll(list);
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<ShopCategoryBean> catBatchInsert(List<ShopCategoryBean> list) {
		try {
			List<ShopCategoryBean> beans = shopCategoryRepository.saveAll(list);
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<OpenHrBean> openHrBatchInsert(List<OpenHrBean> list) {
		try {
			List<OpenHrBean> beans = openHrRepository.saveAll(list);
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Page<ShopBean> findAllPage(String json) {
		JSONObject obj = new JSONObject(json);

		// Create Pageable
		Integer start = obj.getInt("start");
		Integer rows = obj.getInt("rows") == 0 ? Integer.MAX_VALUE : obj.getInt("rows");
		Sort.Direction sortOrder = obj.getString("sortOrder").equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
		String sortType = obj.getString("sortType");
		PageRequest pgb = PageRequest.of(start, rows, sortOrder, sortType);

		return shopRepository.findAllPage(pgb);
	}
}
