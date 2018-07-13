package com.kejin.extract.kejin.process.dao;

import java.util.List;
import java.util.Map;

import com.kejin.extract.entity.kejinTest.DChargeModel;


public interface DChargeDao {
	
	
	public List<DChargeModel> select(Map<String, Object> parameter);
	

	public int insert(DChargeModel dChargeModel);
	
	public int update(DChargeModel dChargeModel);

}
