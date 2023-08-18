package com.projectdemo.deliver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projectdemo.deliver.bean.DelivererBean;
import com.projectdemo.deliver.bean.TransportationBean;
import com.projectdemo.deliver.dao.TransportationRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
@Transactional(rollbackFor = { Exception.class })
public class TransportationService {

	@Autowired
	private TransportationRepository tranRepo;
	
	@Autowired
	private DelivererService deService;
	
	@Autowired
	private EntityManager entityManager;
	
//	find by ID
	public TransportationBean findTranById(Integer id) {
		Optional<TransportationBean> optional = tranRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

//	Exist check by id
	public boolean exist(Integer id) {
		return id != null && tranRepo.findById(id).isPresent();
	}

//	CRUD:C
//	public TransportationBean addTrans(TransportationBean tran) {
//		return tranRepo.save(tran);
//	}

//	Create_Json
	public TransportationBean create(String json) {
		try {
			JSONObject obj = new JSONObject(json);

			String type = obj.isNull("type") ? null : obj.getString("type");
			String brand = obj.isNull("brand") ? null : obj.getString("brand");
			String license = obj.isNull("license") ? null : obj.getString("license");
			Integer delivererId = obj.isNull("deliverer") ? null: obj.getJSONObject("deliverer").getInt("id");

			TransportationBean insert = new TransportationBean();

			insert.setType(type);
			insert.setBrand(brand);
			insert.setLicense(license);
			
		       if (delivererId != null) {
		            DelivererBean deliverer = deService.findDelerById(delivererId);
		            if (deliverer != null) {
		                insert.setDeliverer(deliverer);
		            }
		        }
			
			return tranRepo.save(insert);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	Update以id
//	public void updateTransById(Integer id, String type, String brand, String license) {
//		TransportationBean trans = tranRepo.findById(id).orElse(null);
//		if(trans!=null) {			
//			tranRepo.updateById(id, type, brand, license);
//		}
//	}

//	Update_JSON
	public TransportationBean modify(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			String type = obj.isNull("type") ? null : obj.getString("type");
			String brand = obj.isNull("brand") ? null : obj.getString("brand");
			String license = obj.isNull("license") ? null : obj.getString("license");

			TransportationBean update = tranRepo.findById(id).get();
			update.setType(type);
			update.setBrand(brand);
			update.setLicense(license);
			return tranRepo.save(update);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	Delete
	public boolean deleteTransById(Integer id) {
		if (id != null && tranRepo.existsById(id)) {
			tranRepo.deleteById(id);
			return true;
		}
		return false;
	}
//	Exist by id
	public boolean exists(Integer id) {
		return id!=null& tranRepo.findById(id).isPresent();
	}
//	remove
	public boolean remove(Integer id) {
		try {
			if(exist(id)) {
				tranRepo.deleteById(id);
				return true;
			}else {
				return false;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
//	提供fk_deliverer_id則計算特定外送員的交通工具數量
//	如未提供fk_deliverer_id，則計算所有交通工具記錄的數量
	public long count(JSONObject obj) {
		Integer delivererId = obj.isNull("fk_deliverer_id") ? null: obj.getInt("fk_deliverer_id");
		Integer id=obj.isNull("id") ? null : obj.getInt("id");
		String type = obj.isNull("type") ? null : obj.getString("type");
		String brand = obj.isNull("brand") ? null : obj.getString("brand");
		String license = obj.isNull("license") ? null : obj.getString("license");
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		
//		from TransportationBean
		Root<TransportationBean> root = criteriaQuery.from(TransportationBean.class);
//		select count(*)
		criteriaQuery=criteriaQuery.select(criteriaBuilder.count(root));
		
//		儲存查詢條件
		ArrayList<Object> predicate = new ArrayList<>();
		
		if(delivererId !=null) {
			Join<TransportationBean, TransportationBean> join=root.join("deliverer");
			Predicate delivererPredicate = criteriaBuilder.equal(join.get("id"),delivererId);
			predicate.add(delivererPredicate);
		}
//		組合篩選條件
		if(!predicate.isEmpty()) {
			criteriaQuery = criteriaQuery.where(predicate.toArray(new Predicate[0]));
		}
		
		TypedQuery<Long> typeQuery = entityManager.createQuery(criteriaQuery);
		return typeQuery.getSingleResult().longValue();
	}
	
//	Metohd1. 找尋transportation by DelivererID
//	public List<TransportationBean> findAllByDelivererId1(Integer id){
//		List<TransportationBean> list = tranRepo.findByDelivererId(id);
//		if(list.isEmpty()) {
//			return null;
//		}else {
//			return list;
//		}
//	}
//	Metohd2. 找尋transportation by DelivererID(Json) 
	public List<TransportationBean> findAllByDelivererId2(String json){
		JSONObject obj = new JSONObject(json);
		
		Integer delivererId = obj.isNull("fk_deliverer_id") ? null: obj.getInt("fk_deliverer_id");
//		Integer id=obj.isNull("id") ? null : obj.getInt("id");
//		String type = obj.isNull("type") ? null : obj.getString("type");
//		String brand = obj.isNull("brand") ? null : obj.getString("brand");
//		String license = obj.isNull("license") ? null : obj.getString("license");
		
//		if(obj.has("delerID") && obj.get("delerID") !=null && !obj.get("delerID").equals("")) {
		if(delivererId != null) {
			List<TransportationBean> transList= tranRepo.findByDelivererId(delivererId);
			
			return transList;
		}else {
			return null;
		}
	}
}
