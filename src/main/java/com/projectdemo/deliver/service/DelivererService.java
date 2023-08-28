package com.projectdemo.deliver.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projectdemo.deliver.bean.DelivererBean;
import com.projectdemo.deliver.dao.DelivererRepository;
import com.projectdemo.order.bean.OrderListBean;
import com.projectdemo.order.service.OrderListService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
@Transactional(rollbackFor= {Exception.class})
public class DelivererService {

	@Autowired
	private DelivererRepository delivererRepo;
	
	@Autowired
	//馬老寫getSession，需要了解差異
	private EntityManager entityManager;
	
////	Exist by Name 檢查外送員登入使用
//	public boolean checkIfDelivererExist(String name) {
//		DelivererBean dbDeliverer = delivererRepo.findByName2(name);
//		if (dbDeliverer != null) {
//			return true;
//		} else {
//			return false;
//		}
//	}	
//	id search
	public DelivererBean findDelerById(Integer id) {
		Optional<DelivererBean> optional = delivererRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

//	查詢 Name
	public DelivererBean findDelerByName(String name) {
		DelivererBean findName = delivererRepo.findByName2(name);
		if (findName != null) {
			return findName;
		} else {
			return null;
		}
	}
//	Update_基本：以id改Name加入updateTime
//	public void updateDelerInfoById(Integer id, String name) {
//	DelivererBean deliverer = delivererRepo.findById(id).orElse(null);
//		if(deliverer!=null) {
//			deliverer.onUpdate();
//			deliverer.setName(name);
//			delivererRepo.save(deliverer);
//		}
//	}
	
//	CRUD:C用到
//	public DelivererBean addDeliverer(DelivererBean deler) {
//		return delivererRepo.save(deler);
//	}
	
//	Create_Json
	public DelivererBean create(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			
			String name=obj.isNull("name") ? null : obj.getString("name");
			String account=obj.isNull("account") ? null : obj.getString("account");
			String password=obj.isNull("password") ? null : obj.getString("password");
			String email=obj.isNull("email") ? null : obj.getString("email");
			String phone=obj.isNull("phone") ? null : obj.getString("phone");
			LocalDate birthday=obj.isNull("birthday") ? null : LocalDate.parse(obj.getString("birthday"));
			byte[] photo=obj.isNull("photo")? null : obj.getString("photo").getBytes(StandardCharsets.UTF_8);
			
			DelivererBean insert = new DelivererBean();
			
			insert.setName(name);
			insert.setAccount(account);
			insert.setPassword(password);
			insert.setEmail(email);
			insert.setPhone(phone);
			insert.setBirthday(birthday);
			insert.setPhoto(photo);
			insert.onCreate();
			return delivererRepo.save(insert);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	Update_Json：馬老做法
	public DelivererBean modify(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer id=obj.isNull("id") ? null : obj.getInt("id");
			String name=obj.isNull("name") ? null : obj.getString("name");
			String account=obj.isNull("account") ? null : obj.getString("account");
			String password=obj.isNull("password") ? null : obj.getString("password");
			String email=obj.isNull("email") ? null : obj.getString("email");
			String phone=obj.isNull("phone") ? null : obj.getString("phone");
			LocalDate birthday=obj.isNull("birthday") ? null : LocalDate.parse(obj.getString("birthday"));
			byte[] photo=obj.isNull("photo")? null : obj.getString("photo").getBytes(StandardCharsets.UTF_8);
			
			DelivererBean update = delivererRepo.findById(id).get();
			update.setName(name);
			update.setAccount(account);
			update.setPassword(password);
			update.setEmail(email);
			update.setPhone(phone);
			update.setBirthday(birthday);
			update.setPhoto(photo);
			
			//更新日期
			update.onUpdate();
			return delivererRepo.save(update);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	Delete
//	public boolean deleteDelerById(Integer id) {
//		if (id != null && delivererRepo.existsById(id)) {
//			delivererRepo.deleteById(id);
//			return true;
//		}
//		return false;
//	}
	
//	ID查帳號存在
	public boolean exists(Integer id) {
		return id!=null && delivererRepo.findById(id).isPresent();
	}	
	
//	Delete_Json
	public boolean remove(Integer id) {
		try {
			if(exists(id)) {
				delivererRepo.deleteById(id);
				return true;
			}else {
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
//	找到所有外送員，且可以根據不同條件查找外送員
	public Page<DelivererBean> findAll(String json){
	JSONObject obj = new JSONObject(json);
	
	Integer pageNumber = obj.getInt("start");
	Integer rows =obj.getInt("rows")==0? Integer.MAX_VALUE: obj.getInt("rows");
	Sort.Direction sortOrder = obj.getString("sortOrder").equals("asc")? Sort.Direction.ASC : Sort.Direction.DESC;
	String sortType = obj.getString("sortType");
	PageRequest pgb = PageRequest.of(pageNumber,rows,sortOrder, sortType);
	
//	使用外送員ID查找(純找ID版本)
//		if(obj.has("delerID") && obj.get("delerID") !=null && !obj.get("delerID").equals("")) {
//			return delivererRepo.findByDelivererId(obj.getInt("delerID"), pgb);
//		}
	
//	可查詢ID或姓名:delerID表示有填入資料，但不知型態(成功版本)
//	if(obj.has("delerID") && obj.get("delerID") !=null && !obj.get("delerID").equals("")) {
//		Object delverIdValue = obj.get("delerID");
//		if(delverIdValue instanceof Integer) {
//			return delivererRepo.findByDelivererId((int) delverIdValue, pgb);
//		}else if(delverIdValue instanceof String){
//			String nameLike = (String) delverIdValue;
//			return delivererRepo.findByNameLike2(nameLike,pgb);
//		}else {
//			return null;
//		}
//	}
//	return delivererRepo.findAll(pgb);
//	}	
//	可查詢ID或姓名:delerID表示有填入資料，但不知型態(簡化版本)
	if(obj.has("delerID") && obj.get("delerID") !=null && !obj.get("delerID").equals("")) {
		Object delverIdValue = obj.get("delerID");
		
		if(delverIdValue instanceof Integer) {
			return delivererRepo.findByDelivererId((int) delverIdValue, pgb);
		}else if(delverIdValue instanceof String){
				try {
					//測試輸入是否能轉為整數
					int intValue = Integer.parseInt((String) delverIdValue);
					return delivererRepo.findByDelivererId(intValue, pgb);
				}catch(NumberFormatException e) {
					
					String nameLike = (String) delverIdValue;
					return delivererRepo.findByNameLike2(nameLike,pgb);
				}
			}else {
				return null;
			}
		}
	return delivererRepo.findAll(pgb);
	}
	
//	查詢 NameLike(模糊搜尋)
	public List<DelivererBean> findDelerByNameLike(String name) {
		List<DelivererBean> list = delivererRepo.findByNameLike(name);

		if (list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}

//	"select count * from DelivererBean where id=:id" +
//	"and name like %:name% and account=:account"+
//	"and email = :email and phone =:phone"+
//	"and birthday <= :birthday and createDate >= :createDate"+
//	"and updateDate >= :updateDate"
	public long count(JSONObject obj) {
//		System.out.println("obj=" +obj);
		Integer id=obj.isNull("id") ? null : obj.getInt("id");
		String name=obj.isNull("name") ? null : obj.getString("name");
		String account=obj.isNull("account") ? null : obj.getString("account");
		String password=obj.isNull("password") ? null : obj.getString("password");
		String email=obj.isNull("email") ? null : obj.getString("email");
		String phone=obj.isNull("phone") ? null : obj.getString("phone");
		LocalDate birthday=obj.isNull("birthday") ? null : LocalDate.parse(obj.getString("birthday"));
		LocalDate createdate=obj.isNull("createdate") ? null : LocalDate.parse(obj.getString("createdate"));
		LocalDate updatedate=obj.isNull("updatedate") ? null : LocalDate.parse(obj.getString("updatedate"));
		byte[] photo=obj.isNull("photo")? null : obj.getString("photo").getBytes(StandardCharsets.UTF_8);
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		
//		from DelivererBean
		Root<DelivererBean> root = criteriaQuery.from(DelivererBean.class);
//		select count(*)
		criteriaQuery = criteriaQuery.select(criteriaBuilder.count(root));
		
		List<Predicate> pridecates = new ArrayList<>();
//		###############	以下尚未驗證	###############
		if(id!=null) {
			pridecates.add(criteriaBuilder.equal(root.get("id"), id));
		}
		if(name!=null) {
			pridecates.add(criteriaBuilder.equal(root.get("name"), "%"+name+"%"));
		}
		if(account!=null) {
			pridecates.add(criteriaBuilder.equal(root.get("account"), "%"+account+"%"));
		}
		if(email!=null) {
			pridecates.add(criteriaBuilder.equal(root.get("email"), email));
		}
		if(birthday!=null) {
			pridecates.add(criteriaBuilder.lessThanOrEqualTo(root.get("birthday"), birthday));
		}
		if(createdate!=null) {
			pridecates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdate"), createdate));
		}
		if(updatedate!=null) {
			pridecates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("updatedate"), updatedate));
		}
//		###############	以上尚未驗證	###############
		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getSingleResult().longValue();
	}

//	外送員登入å
	public DelivererBean login(String delername, String password) {
		DelivererBean deler=delivererRepo.findByName2(delername);
			if(deler!=null) {
				if(password!=null) {
					String fromDB = deler.getPassword();
					if(fromDB.equals(password)) {
						return deler;
					}
				}
			}
			return null;
	}
//	外送員改密碼
	public boolean changePassword(String delername,String oldPassword,String newPassword) {
		DelivererBean deler = this.login(delername, oldPassword);
		if(deler!=null) {
			if(newPassword!=null) {
				deler.onUpdate();
				deler.setPassword(newPassword);
				delivererRepo.save(deler);
				return true;
			}
		}
		return false;
	}
	
}
