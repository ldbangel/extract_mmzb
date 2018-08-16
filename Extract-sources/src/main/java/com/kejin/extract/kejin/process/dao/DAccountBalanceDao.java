package com.kejin.extract.kejin.process.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DAccountBalanceDao {
	
	public int insertBalanceRecord(@Param("list") List<Map<String,Object>> balanceList);

}
