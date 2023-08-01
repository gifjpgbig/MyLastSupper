package com.projectdemo.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.shop.bean.HolidayBean;
import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.dao.HolidayRepository;
import com.projectdemo.shop.dao.ShopRepository;

@Service
public class HolidayService {

	@Autowired
	private HolidayRepository holidayRepository;
	
	@Autowired
	private ShopRepository shopRepository;
	
	public HolidayBean add(HolidayBean holiday) {
		return holidayRepository.save(holiday);
	}
	
	public HolidayBean findById(Integer id) {
		Optional<HolidayBean> optional = holidayRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	public List<HolidayBean> findAllByShopId(Integer id) {
		Optional<ShopBean> optional = shopRepository.findById(id);
		if(optional.isPresent()) {
			return holidayRepository.findAllByShop(optional.get());
		}
		return null;
	}
	
	public HolidayBean update(Integer id, HolidayBean bean) {
		Optional<HolidayBean> optional = holidayRepository.findById(id);
		if(optional.isPresent()) {
			HolidayBean oldHoliday = optional.get();
			oldHoliday.setRestDate(bean.getRestDate());
			oldHoliday.setWholeDay(bean.isWholeDay());
			oldHoliday.setStartTime(bean.getStartTime());
			oldHoliday.setEndTime(bean.getEndTime());
			
			return holidayRepository.save(oldHoliday);
		}
		return null;
	}
	
	public boolean delete(Integer id) {
		try {
			holidayRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return false;
	}
}
