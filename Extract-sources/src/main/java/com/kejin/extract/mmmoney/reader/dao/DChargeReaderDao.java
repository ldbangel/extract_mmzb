package com.kejin.extract.mmmoney.reader.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DChargeReaderDao {
	
	/**
	 * 线上充值
	 */
	public List<Map<String, Object>> readChargeOnline(
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	
	/**
	 * 线下充值
	 * @param beginTime
	 * @param endTime
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> readChargeOffline(
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);



}
