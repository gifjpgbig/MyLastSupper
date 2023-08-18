package com.projectdemo.menu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.menu.bean.MenuCustomizationOptionsBean;
import com.projectdemo.menu.dao.MenuCustomizationOptionsRepository;
import com.projectdemo.menu.dao.MenuCustomizationRepository;


@Service
public class MenuCustomizationOptionsService {
	@Autowired
	private MenuCustomizationOptionsRepository menuCustomizationOptionsRepository;

	@Autowired
	private MenuCustomizationRepository menuCustomizationRepository;
	 
	public MenuCustomizationOptionsBean creatMenuCustomizationOptions(MenuCustomizationOptionsBean menuCustomizationOptionsBean) {
		Integer id = menuCustomizationOptionsBean.getMenuCustomization().getId();
		if(menuCustomizationRepository.existsById(id)) {
			return menuCustomizationOptionsRepository.save(menuCustomizationOptionsBean);
		}
		return null;
	}

	public MenuCustomizationOptionsBean getMenuCustomizationOptionsById(Integer id) {
		return menuCustomizationOptionsRepository.findById(id).orElse(null);
	}
//	用加點選項的id去找到全部的加點品項
	public List<MenuCustomizationOptionsBean> findMenuCustomizationOptionsByOptionsId(Integer OptionsId) {
		return menuCustomizationOptionsRepository.findMenuCustomizationOptionsByOptionsId(OptionsId);
	}

	public MenuCustomizationOptionsBean updateMenuCustomizationOptions(Integer id , MenuCustomizationOptionsBean menuCustomizationOptionsBean) {
		Optional<MenuCustomizationOptionsBean> findById = menuCustomizationOptionsRepository.findById(id);
		if(findById.isPresent()) {
			MenuCustomizationOptionsBean oldBean = findById.get();
			oldBean.setOptionMoney(menuCustomizationOptionsBean.getOptionMoney());
			oldBean.setOptionName(menuCustomizationOptionsBean.getOptionName());
			return menuCustomizationOptionsRepository.save(oldBean);
		}
		return null;
	}
//	如果連加點選項都刪除了 就整個刪掉
	public boolean deleteMenuCustomizationOptionsByCustId(Integer custId){
//		先建立list確認用加點選項找到的資料裡面有東西
		List<MenuCustomizationOptionsBean> find = findMenuCustomizationOptionsByOptionsId(custId);
		if (find != null && !find.isEmpty()) {
//			找到後 刪除所有品項內有包含這個id的資料
			menuCustomizationOptionsRepository.deleteAllMenuCustomizationOptionsByOptionsId(custId);
			return true;
		}
		return false;
	}
	
	
//	刪除單個加點品項 要透過id去刪除
	public boolean deleteMenuCustomizationOptions(Integer id) {
		if (menuCustomizationOptionsRepository.findById(id).isPresent()) {
			menuCustomizationOptionsRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
