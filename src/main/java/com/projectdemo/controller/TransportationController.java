package com.projectdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.model.bean.deliver.TransportationBean;
import com.projectdemo.model.service.TransportationService;

@RestController
public class TransportationController {

	@Autowired
	private TransportationService tranService;

//	CRUD:C
	@PostMapping("/transportation/addTransportation")
	public TransportationBean insertTrans(@RequestBody TransportationBean TB) {
	return tranService.addTrans(TB);	
	}
	
//	CRUD:R
	@GetMapping("/transportation/id")
	public Object findById(@RequestParam("id") String idStr) {
		if(idStr!=null && idStr.matches("^\\d+$")) {
			int id = Integer.parseInt(idStr);
			TransportationBean trans = tranService.findTranById(id);
				if(trans!=null) {
					return trans;
				}else {
					return "id不存在";
				}
			}else {
				return "請提供有效id";
			}
		}
//	CRUD:U
	@Transactional
	@PutMapping("/transportation/update")
	public void updateById
	(@RequestParam("id") Integer id,@RequestParam("type") String type,
	@RequestParam("brand")String brand,@RequestParam("license") String license){
		tranService.updateTransById(id, type, brand, license);
	}
	
//	CRUD:D
	@DeleteMapping("/transportation/delete")
	public String deleteTrans(@RequestParam("id")Integer id) {
		tranService.deleteTransById(id);
		return "刪除成功";
//		return "redirect:/transportation/page";
	}
		
}
