package com.kejin.extract.kejin.process.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.kejinTest.DRegularRecoveryModel;


public interface DRegularRecoveryDao {
	
	
	public List<DRegularRecoveryModel> select(Map<String, Object> parameter);

	public List<DRegularRecoveryModel> selectByRangeTime(@Param("startDate") Date start, @Param("endDate") Date end);

	public int insert(DRegularRecoveryModel dRegularRecoveryModel);
	
	public int update(DRegularRecoveryModel dRegularRecoveryModel);
	
	//查询所有不在d_reinvest_and_new表中的但是在d_regular_recovery表中的数据
	public List<DRegularRecoveryModel> selectNotInReinvestAndNew();

}
