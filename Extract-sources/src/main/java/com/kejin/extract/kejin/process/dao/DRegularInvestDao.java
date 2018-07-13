package com.kejin.extract.kejin.process.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.kejinTest.DRegularInvestModel;


public interface DRegularInvestDao {
	
	public List<DRegularInvestModel> select(Map<String, Object> parameter);

	public List<DRegularInvestModel> selectByRangeTime(@Param("startDate") Date start, @Param("endDate") Date end);
	
	public List<DRegularInvestModel> selectBidFailByRangeTime(@Param("startDate") Date start, @Param("endDate") Date end);
	
	public int insert(DRegularInvestModel dRegularInvestModel);
	
	public int insertList(@Param("regularInvestModels") List<DRegularInvestModel> regularInvestModels);
	
	public int update(DRegularInvestModel dRegularInvestModel);
	
}
