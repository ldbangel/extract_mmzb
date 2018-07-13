package com.kejin.extract.mmmoney.reader.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DBonusReaderDao {
	
	/**
	 * 奖金列表
	 */
	public List<Map<String, Object>> readFromBusinessDetail(
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	
	/**
	 * 读取requestNo
	 */
	public List<String> readSuccesssRequestNo(
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

}
