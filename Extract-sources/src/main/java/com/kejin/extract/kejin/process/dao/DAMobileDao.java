package com.kejin.extract.kejin.process.dao;

import java.util.List;
import java.util.Map;

import com.kejin.extract.entity.kejinTest.DAMobileModel;



public interface DAMobileDao {
	
	
	public List<DAMobileModel> select(Map<String, Object> parameter);
	


}
