package com.kejin.extract.kejin.process.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.kejinTest.DMemberBalanceModel;

public interface DMemberBalanceDao {
	
	public int insertMemberBalances(@Param("dMemberBalanceModels") List<DMemberBalanceModel> dMemberBalanceModels);
	
	public List<DMemberBalanceModel> selectMemberBalanceInfo(Date date);
	
	public int deleteEmptyBalances(@Param("time") Date date);
	
	public List<String> selectPlatformUserNo();
	
	public void updateBalanceByPlatformUserNo(DMemberBalanceModel model);
	
	public void insertMemberBalanceSingle(DMemberBalanceModel model);
	
	public List<Map<String,Object>> selectMemberBalanceByMemberId(@Param("result") List<Map<String, Object>> result);
}
