package com.projectdemo.menu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.menu.bean.MenuCustomizationBean;
import com.projectdemo.menu.dao.DishRepository;
import com.projectdemo.menu.dao.MenuCustomizationRepository;

@Service
public class MenuCustomizationService {

	@Autowired
	private MenuCustomizationRepository menuCustomizationRepository;

	@Autowired
	private DishRepository dishRepository;
	
//	建立新的加點項目的話 必須要有dish才能建立
	public MenuCustomizationBean creatMenuCustomization(MenuCustomizationBean menuCustomizationBean) {
//		因為前端送進來的資料是一個BEAN的東西 所以先判定有沒有dish後 就可以把資料save起來
		Integer id = menuCustomizationBean.getDish().getId();
		if(dishRepository.existsById(id)) {
			return menuCustomizationRepository.save(menuCustomizationBean);
		}
		return null;
	}
	
//	使用ID去更新資料
	public MenuCustomizationBean updateMenuCustomization(Integer id , MenuCustomizationBean menuCustomizationBean) {
//		先判斷這個ID裡面有沒有資料 有才做更新
		Optional<MenuCustomizationBean> findById = menuCustomizationRepository.findById(id);
		if(findById.isPresent()) {
//			建立新的BEAN取得舊的資料 然後再把新的放進去後做更新
			MenuCustomizationBean oldBean = findById.get();
			oldBean.setCustomizationName(menuCustomizationBean.getCustomizationName());
			oldBean.setMaxSelection(menuCustomizationBean.getMaxSelection());
			oldBean.setMinSelection(menuCustomizationBean.getMinSelection());
			oldBean.setRepeatable(menuCustomizationBean.isRepeatable());
//			將更新後的資料save起來
			return menuCustomizationRepository.save(oldBean);
		}
		return null;
	}
	
//	這個是找到全部有相同dishid的ID
	public List<Integer> findMenuCustomizationIdsByDishId(Integer dishId){
//		將全部相同的dishid資料先放進去
		List<MenuCustomizationBean> mclist = menuCustomizationRepository.findMenuCustomizationByDishId(dishId);
		List<Integer> mcid = new ArrayList<>();
		for(MenuCustomizationBean menuCustomizationBean : mclist) {
//			用迴圈方式 將所有的ID放進List<Integer>裡面 這樣controller就可以用
			mcid.add(menuCustomizationBean.getId());
		}
		return mcid;
	}
	
//	因為只可能找dish然後列出全部的加點項目 所以用這方法取得
	public List<MenuCustomizationBean> findAllMenuCustomizationByDishId(Integer dishId) {
		return menuCustomizationRepository.findMenuCustomizationByDishId(dishId);
	}
	
//	刪除還有一個方法 因為可能dish還在 只是要取消掉某個加點 所以要透過id去刪除
	public boolean deleteMenuCustomizationId(Integer id) {
//		先用ID去搜尋有沒有這筆資料 有的話再刪除
		if(menuCustomizationRepository.findById(id).isPresent()) {
			menuCustomizationRepository.deleteById(id);
			return true;
		}
			return false;
	}
	
//	因為有dish才會有加點選項 所以同樣刪掉的話 也要全部刪掉
	public boolean deleteAllMenuCustomizationByDishId(Integer dishId) {
//		先建立一個List 使用dishid去找資料 如果裡面有東西在執行刪除
		List<MenuCustomizationBean> find = findAllMenuCustomizationByDishId(dishId);
		if (find != null && !find.isEmpty()) {
			menuCustomizationRepository.deleteAllMenuCustomizationByDishId(dishId);
			return true;
		}
		return false;
	}
}