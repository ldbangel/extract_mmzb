package com.kejin.extract.kejin.process.dao;

import java.util.List;
import java.util.Map;

import com.kejin.extract.entity.kejinTest.DCashModel;

public interface DCashDao {
	
	public List<DCashModel> select(Map<String, Object> parameter);
	
	public int insert(DCashModel dCashModel);
	
	public int update(DCashModel dCashModel);
	
}
