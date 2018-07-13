package com.kejin.extract.mmmoney.service.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserBaseInfoDetailDao {
	
	public Map<String,BigDecimal> getCreditBalance(@Param("memberId") String memberId); 
	
	public Map<String,BigDecimal> getCurrentBalance(@Param("memberId") String memberId); 
	
	public List<Map<String,Object>> getAccountBalance(@Param("memberId") String memberId); 
	
	public Map<String,BigDecimal> getInterestBalance(@Param("memberId") String memberId); 
	
	public List<Map<String,Object>> getAccumulateInvestInfo(@Param("memberId") String memberId); 
	
	public List<Map<String,Object>> getAccumulaterRecoveryInfo(@Param("memberId") String memberId);

}
