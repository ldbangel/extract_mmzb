package com.kejin.extract.kejin.process.dao;

import java.util.List;
import java.util.Map;

import com.kejin.extract.entity.kejinTest.DAPlaceModel;



public interface DAPlaceDao {
	
	
	public List<DAPlaceModel> select(Map<String, Object> parameter);
	


}
