package com.kejin.extract.mmmoney.service.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface AchievementManagerFromProdDao {
	//从备份数据库查询实时账户余额
	public List<Map<String, Object>> getAccountBalanceInfo(@Param ("oneDayAgoTime") Date oneDayAgo,
			@Param ("twoDayAgoTime") Date twoDayAgo, @Param ("threeDayAgoTime") Date threeDayAgo);
	
	public List<Map<String,String>> getValidMemberIdFromMemberInfo();
	
	public List<Map<String,String>> getValidMemberIdFromEnterpriseInfo();
	
	public List<Map<String,String>> getPhoneNumsByMemberID(List<Map<String,Object>> list);
}
