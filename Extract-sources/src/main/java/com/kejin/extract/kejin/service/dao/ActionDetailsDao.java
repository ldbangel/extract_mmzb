package com.kejin.extract.kejin.service.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ActionDetailsDao {
	//获取提现明细
	public List<Map<String,Object>> selectCashDetails(@Param("beginTime") Date beginTime,@Param("endTime") Date endTime );
	//获取提现人员的最近回款项，用来判断提现来源
	public Map<String,Object> selectNearestRecoveryDetail(Map<String,Object> map);
	
	//获取投资明细
	public List<Map<String,Object>> selectInvestDetails(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	//获取充值明细
	public List<Map<String,Object>> selectChargeDetails(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	//获取回款明细
	public List<Map<String,Object>> selectRecoveryDetails(@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	
}
