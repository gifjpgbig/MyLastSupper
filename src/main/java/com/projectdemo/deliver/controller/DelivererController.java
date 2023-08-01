package com.projectdemo.deliver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.deliver.bean.DelivererBean;
import com.projectdemo.deliver.service.DelivererService;

@RestController
public class DelivererController {
	
	@Autowired
	private DelivererService deService;
	
//	CRUD:C (沒處理exist問題)
	@PostMapping("/deliverer/adddDliverer")
	public DelivererBean insertDeliverer1(@RequestBody DelivererBean DB) {
		return deService.addDeliverer(DB);
	}
	
//	CRUD:R (name)
	@GetMapping("/deliverer/name")
	public DelivererBean findByNameQuery(@RequestParam("n") String delivererName ){
		if(delivererName!=null) {
			return deService.findDelerByName(delivererName);
		}else {
			return null;
		}
	}
//	CRUD:R(id)
	@GetMapping("/deliverer/id")
	public Object findById(@RequestParam(name="id", required=false)String idStr) {
		if(idStr!=null && idStr.matches("^\\d+$")) {
			int id = Integer.parseInt(idStr);
			DelivererBean deler = deService.findDelerById(id);
			if(deler!=null) {
				return deler;
			}else {
				return "id不存在";
			}
		}else {
			return "請提供有效id";
		}
	}
	
//	模糊搜尋
	@GetMapping("/deliverer/nameLike")
	public List<DelivererBean> findByNameLike(@RequestParam("n") String delivererName ){
		return deService.findDelerByNameLike(delivererName);
	}
	
//	CRUD:U
	@PutMapping("/deliverer/updateName")
	public void updateNameById(@RequestParam("n") String newName,@RequestParam("id") Integer id) {
		deService.updateDelerInfoById(id, newName);
	}
	
//	CRUD:D
	@DeleteMapping("/deliverer/delete")
	public String deleteDeler(@RequestParam("id")Integer id) {
		deService.deleteDelerById(id);
		return "刪除成功";
//		return "redirect:/deliverer/page";
	}
	
}
