package com.kejin.extract.kejin.process.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.kejinTest.DLatestActionModel;

public interface DLatestActionDao {
	
	public List<String> selectCustomerRecords(@Param("set") Set<String> params);
	
	public int insertLatestActionRecord(@Param("list") List<DLatestActionModel> list);
	
	public int updateLatestActionRecord(@Param("list") List<DLatestActionModel> list);
	
	public List<Map<String,Object>> selectLatestActionRecord(@Param("list") List<Map<String,Object>> list);

}
