package com.kejin.extract.kejin.process.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.kejinTest.DCreditAssigmentModel;


public interface DCreditAssignmentDao {
	
	
	public List<DCreditAssigmentModel> select(Map<String, Object> parameter);

	public List<DCreditAssigmentModel> selectByRangeTime(@Param("startDate") Date start, @Param("endDate") Date end);

	public int insert(DCreditAssigmentModel dCreditAssigmentModel);
	
	public int update(DCreditAssigmentModel dCreditAssigmentModel);

}
