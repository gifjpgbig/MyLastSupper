package com.projectdemo.deliver.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.deliver.bean.DelivererBean;
import com.projectdemo.deliver.bean.TransportationBean;
import com.projectdemo.deliver.service.TransportationService;

@RestController
@CrossOrigin()
public class TransportationController {

	@Autowired
	private TransportationService tranService;

//	Create_Json
	@PostMapping("/transportation")
	public String createTrans(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		JSONObject obj = new JSONObject(json);
		Integer id =obj.isNull("id")? null : obj.getInt("id");
		if(id!=null && tranService.exist(id)) {
			responseJson.put("message", "資料已存在");
			responseJson.put("success",false);
		}else {
			TransportationBean create = tranService.create(json);
			if(create!=null) {
				responseJson.put("message", "資料新增成功");
				responseJson.put("success",true);
			}else {
				responseJson.put("message", "資料新增失敗");
				responseJson.put("success",false);
			}
		}
		return responseJson.toString();
	}
	
//	CRUD:R
//	@GetMapping("/transportation/id")
//	public Object findById(@RequestParam(value="id", required=false) String idStr) {
//		if(idStr!=null && idStr.matches("^\\d+$")) {
//			int id = Integer.parseInt(idStr);
//			TransportationBean trans = tranService.findTranById(id);
//				if(trans!=null) {
//					return trans;
//				}else {
//					return "id不存在";
//				}
//			}else {
//				return "請提供有效id";
//			}
//		}
	
//	Find_ID_Json
	@GetMapping("/transportation/{id}")
	public String findByTransID(@PathVariable(value="id") Integer id) {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		
		TransportationBean trans = tranService.findTranById(id);
		if(trans!=null) {
			JSONObject item = new JSONObject()
			.put("id", trans.getId())
			.put("type", trans.getType())
			.put("brand", trans.getBrand())
			.put("license", trans.getLicense());
			
			array=array.put(item);
		}
		responseJson = responseJson.put("list", array);
		return responseJson.toString();
	}
	
//	Update_Json
	@PostMapping("/transportation/{id}")
	public String  updateTransById(@PathVariable(value="id")Integer id, @RequestBody String json) {
	JSONObject responJson = new JSONObject();
	
	if(!tranService.exist(id)) {
		responJson.put("message","資料不存在");
		responJson.put("success",false);
	}else {
		TransportationBean trans = tranService.modify(json);
		if(trans!=null) {
			responJson.put("message","資料修改成功");
			responJson.put("success",true);
		}else {
			responJson.put("message","資料修改失敗");
			responJson.put("success",false);
		}
	}
	return responJson.toString();
	}

	
//	CRUD:D
//	@DeleteMapping("/transportation/delete")
//	public String deleteTrans(@RequestParam("id")Integer id) {
//		boolean isDeleted = tranService.deleteTransById(id);
//		if(isDeleted){
//			return "刪除成功";
//		}else {
//			return "刪除成功";
//		}
//	}
	
//	Delete_Json
	@DeleteMapping("/transportation/{id}")
	public String remove(@PathVariable(value="id") Integer id) {
		JSONObject responJson = new JSONObject();
		if(!tranService.exist(id)) {
			responJson.put("message","資料不存在");
			responJson.put("success",false);
		}else {
			if(tranService.remove(id)){
				responJson.put("message","刪除資料成功");
				responJson.put("success",true);
			}else {
				responJson.put("message","刪除資料失敗");
				responJson.put("success",false);
			}
		}
		return responJson.toString();
	}
	
//	以Deliverer ID查詢擁有的Transportation列表
	@PostMapping("/transportation/find")
	public String findTransByDelivererId(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		
		JSONObject countJson = new JSONObject(json);
		long count = tranService.count(countJson);
//		查詢總筆數存放responseJson中
		responseJson.put("count", count);
		
		List<TransportationBean> transportation = tranService.findAllByDelivererId2(json);
		JSONArray array = new JSONArray();
		if(transportation!=null && !transportation.isEmpty()) {
			
			for(TransportationBean tranAll: transportation) {
				
				Integer delerID = 0;
				
//				獲取 TransportationBean 中的 DelivererBean 物件
				DelivererBean delivererBean = tranAll.getDeliverer();
				
				if(delivererBean!=null) {
					delerID = delivererBean.getId();
				}else {
					return null;
				}
				
				JSONObject item = new JSONObject()
						.put("id", tranAll.getId())
						.put("type", tranAll.getType())
						.put("brand", tranAll.getBrand())
						.put("license", tranAll.getLicense());
				
				array = array.put(item);
			}
			responseJson.put("list", array);
		}else {
			responseJson.put("error","Deliverer的ID發生錯誤");
		}
		return responseJson.toString();
	}
	
}
