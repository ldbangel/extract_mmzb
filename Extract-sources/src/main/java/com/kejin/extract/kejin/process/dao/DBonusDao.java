package com.kejin.extract.kejin.process.dao;

import java.util.List;
import java.util.Map;

import com.kejin.extract.entity.kejinTest.DBonusModel;



public interface DBonusDao {
	
	
	public List<DBonusModel> select(Map<String, Object> parameter);
	

	public int insert(DBonusModel dBonusModel);
	
	public int update(DBonusModel dBonusModel);

}
