package com.kejin.extract.kejin.process.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.kejinTest.DCashModel;
import com.kejin.extract.entity.kejinTest.DLoanModel;

public interface DLoanDao {
	
	public List<DCashModel> select(Map<String, Object> parameter);
	
	public int insert(List<DLoanModel> listLoans);
	
	public int update(List<DLoanModel> listLoans);
	
	public List<Map<String,Object>> selectCashDetails(@Param("beginTime") Date beginTime,@Param("endTime") Date endTime );
	
	public Map<String,Object> selectNearestRecoveryDetail(Map<String,Object> map);
	
}
