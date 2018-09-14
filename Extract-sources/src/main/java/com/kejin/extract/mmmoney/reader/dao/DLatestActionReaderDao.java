package com.kejin.extract.mmmoney.reader.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.kejinTest.DLatestActionModel;

public interface DLatestActionReaderDao {
	public List<DLatestActionModel> readLatestInvest(@Param("beginTime") Date beginTime,@Param("endTime") Date endTime,
			@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
	
	public List<DLatestActionModel> readLatestRecovery(@Param("beginTime") Date beginTime,@Param("endTime") Date endTime,
			@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
	
	public List<DLatestActionModel> readLatestCharge(@Param("beginTime") Date beginTime,@Param("endTime") Date endTime,
			@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
	
	public List<DLatestActionModel> readLatestCash(@Param("beginTime") Date beginTime,@Param("endTime") Date endTime,
			@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
}
