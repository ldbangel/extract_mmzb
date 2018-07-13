package com.kejin.extract.kejin.process.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.kejinTest.DCurrentRecoveryModel;


public interface DCurrentRecoveryDao {
	
	
	public List<DCurrentRecoveryModel> select(Map<String, Object> parameter);

	public List<DCurrentRecoveryModel> selectByRangeTime(@Param("startDate") Date start, @Param("endDate") Date end);

	public int insert(DCurrentRecoveryModel dCurrentRecoveryModel);
	
	public int update(DCurrentRecoveryModel dCurrentRecoveryModel);

}
