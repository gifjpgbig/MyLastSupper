package com.projectdemo.deliver.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.deliver.bean.DelivererBean;
import com.projectdemo.deliver.service.DelivererService;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin()
public class DelivererController {

	@Autowired
	private DelivererService deService;

//	確認資料存在
//	@PostMapping("/deliverer/name/exists/{name}")
//	public String checkExistsName(@PathVariable(value = "name") String name) {
//		JSONObject responseJson = new JSONObject();
//
//		boolean check = deService.checkIfDelivererExist(name);
//		if (check) {
//			responseJson.put("message", "資料已存在");
//		} else {
//			responseJson.put("message", "資料不存在");
//		}
//		return responseJson.toString();
//	}

//	CRUD:C (沒處理exist問題)
//	@PostMapping("/deliverer/adddDliverer")
//	public DelivererBean insertDeliverer1(@RequestBody DelivererBean DB) {
//		return deService.addDeliverer(DB);
//	}

	//外送員登入
	@PostMapping("/deliverer/login")
	@ResponseBody
	public ResponseEntity<?> postLogin(@RequestParam String loginName, @RequestParam String loginPwd, Model model, HttpSession httpSession) {
		DelivererBean result = deService.login(loginName, loginPwd);
		
		if(result!=null) {
			httpSession.setAttribute("loginMember", result);
			return new ResponseEntity<>(result, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("帳號密碼錯誤或可能不存在", HttpStatus.BAD_REQUEST);
		}
	}
	
//	CRUD:C馬老做法
	@PostMapping("/deliverer")
	public ResponseEntity<String> create(@RequestBody String json) {
//	public String create(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();		
//		JSONObject obj = new JSONObject(json);
		
//		不檢查輸入的ID因為自動產生
//		Integer id = obj.isNull("id") ? null : obj.getInt("id");
//		
//		if(id!=null && deService.exists(id)) {
//			responseJson.put("message", "資料已存在");
//			responseJson.put("success",false);
//		}else {
			DelivererBean create = deService.create(json);
			if(create!=null) {
				responseJson.put("message", "外送員新增成功");
				responseJson.put("success", true);
				return ResponseEntity.ok(responseJson.toString());
			}else {
				responseJson.put("message", "外送員新增失敗");
				responseJson.put("success", false);
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseJson.toString());
//			}
		}
//		return responseJson.toString();
	}

//	CRUD:R (name)
	@GetMapping("/deliverer/name")
	public DelivererBean findByNameQuery(@RequestParam("n") String delivererName) {
		if (delivererName != null) {
			return deService.findDelerByName(delivererName);
		} else {
			return null;
		}
	}

//	CRUD:R(id)
//	@GetMapping("/deliverer/id")
//	public Object findById(@RequestParam(value="id", required=false)String idStr) {
//		if(idStr!=null && idStr.matches("^\\d+$")) {
//			int id = Integer.parseInt(idStr);
//			DelivererBean deler = deService.findDelerById(id);
//			if(deler!=null) {
//				return deler;
//			}else {
//				return "id不存在";
//			}
//		}else {
//			return "請提供有效id";
//		}
//	}
//	CRUD:R(id) Json
	@GetMapping("/deliverer/{id}")
	public String findById(@PathVariable(value = "id") Integer id) {
		JSONObject responseJson = new JSONObject();

		JSONArray array = new JSONArray();
		DelivererBean deler = deService.findDelerById(id);
		if (deler != null) {
			JSONObject item = new JSONObject()
					.put("id", deler.getId())
					.put("name", deler.getName())
					.put("account", deler.getAccount())
					.put("password", deler.getPassword())
					.put("email", deler.getEmail())
					.put("phone", deler.getPhone())
					.put("birthday", deler.getBirthday())
					.put("photo", deler.getPhoto())
					.put("createDate", deler.getCreateDate())
					.put("updateDate", deler.getUpdateDate());

			array = array.put(item);
		}
		responseJson = responseJson.put("list", array);
		return responseJson.toString();
	}

//	模糊搜尋
	@GetMapping("/deliverer/nameLike")
	public List<DelivererBean> findByNameLike(@RequestParam("n") String delivererName) {
		return deService.findDelerByNameLike(delivererName);
	}

//	CRUD:U
//	@PutMapping("/deliverer/updateName")
//	public void updateNameById(@RequestParam("n") String newName,@RequestParam("id") Integer id) {
//		deService.updateDelerInfoById(id, newName);
//	}

//	CRUD:U馬老做法
	@PutMapping("/deliverer/{id}")
	public String updateDelivererById(@PathVariable(name = "id") Integer id, @RequestBody String json) {
		JSONObject responseJson = new JSONObject();

		if (!deService.exists(id)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		} else {
			DelivererBean deler = deService.modify(json);
			if (deler != null) {
				responseJson.put("message", "資料修改成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "資料修改失敗");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();
	}

//	CRUD:D
//	@DeleteMapping("/deliverer/delete")
//	public String deleteDeler(@RequestParam("id") Integer id) {
//		boolean isDeleted = deService.deleteDelerById(id);
//		if (isDeleted) {
//			return "刪除成功";
//		} else {
//			return "刪除失敗，ID不存在";
//		}
//
//	}
//	CRUD:D馬老做法
	@DeleteMapping("/deliverer/{id}")
	public String remove(@PathVariable(value="id") Integer id) {
		JSONObject responseJson = new JSONObject();
		if(!deService.exists(id)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		}else {
			if(deService.remove(id)) {
				responseJson.put("message", "資料刪除成功");
				responseJson.put("success", true);
			}else {
				responseJson.put("message", "資料刪除失敗");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();
	}
	
	@PostMapping("/deliverer/find")
	public String find(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();

		try {
		JSONObject countJson = new JSONObject(json);
		long count = deService.count(countJson);
		responseJson.put("count", count);
		
		Page<DelivererBean> deliverer = deService.findAll(json);
		JSONArray array = new JSONArray();
		if(deliverer!=null && !deliverer.isEmpty()) {
			
			
			for(DelivererBean delerAll: deliverer) {
				
				Integer delerID = 0;
				String nameLike = null;
				
				if(delerAll.getId() !=null) {
					delerID = delerAll.getId();
				}else if (delerAll.getName()!=null) {
					nameLike = delerAll.getName();
				}
				
				JSONObject item = new JSONObject()
						.put("id", delerAll.getId())
						.put("name",delerAll.getName())
						.put("account",delerAll.getAccount())
						.put("email",delerAll.getEmail())
						.put("phone",delerAll.getPhone())
						.put("birthday",delerAll.getBirthday())
						.put("createdate",delerAll.getCreateDate())
						.put("updatedate",delerAll.getUpdateDate());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
	} catch (Exception e) {
		responseJson.put("error", "無效的輸入");
	}
		return responseJson.toString();
	}

}
