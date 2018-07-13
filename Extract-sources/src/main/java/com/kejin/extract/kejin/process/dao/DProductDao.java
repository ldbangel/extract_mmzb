package com.kejin.extract.kejin.process.dao;

import java.util.List;
import java.util.Map;

import com.kejin.extract.entity.kejinTest.DProductModel;

public interface DProductDao {
	
	
	public List<DProductModel> select(Map<String, Object> parameter);
	

	public int insert(DProductModel dProductModel);
	
	public int update(DProductModel dProductModel);

}
