package com.kejin.extract.kejin.service.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PartTimeFinancierAchDao {
	//获取兼职理财师业绩
	public List<Map<String, Object>> getPartTimeFinancierAchievementInfo(@Param("beginTime") Date beginTime,
		@Param("endTime") Date endTime);
}
