package com.kejin.extract.kejin.process.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.kejinTest.DCurrentInvetModel;


public interface DCurrentInvestDao {
	
	
	public List<DCurrentInvetModel> select(Map<String, Object> parameter);

	public List<DCurrentInvetModel> selectByRangeTime(@Param("startDate") Date start, @Param("endDate") Date end);

	public int insert(DCurrentInvetModel dCurrentInvetModel);
	
	public int update(DCurrentInvetModel dCurrentInvetModel);

}
