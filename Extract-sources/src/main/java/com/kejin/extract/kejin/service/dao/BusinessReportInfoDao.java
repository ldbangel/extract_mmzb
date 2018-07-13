package com.kejin.extract.kejin.service.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface BusinessReportInfoDao {
	//获取业务人员业绩
	public List<Map<String, Object>> getBusinessManagerAchievementInfo(@Param("beginTime") Date beginTime,
			@Param("endTime") Date endTime, @Param("date") Date date);
	
	//获取时间段内业绩总和
	public List<Map<String, Object>> getAccountBalanceInfo();
}
