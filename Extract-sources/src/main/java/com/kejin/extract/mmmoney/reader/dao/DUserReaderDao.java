package com.kejin.extract.mmmoney.reader.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DUserReaderDao {
	
	
	public List<Map<String, Object>> readFromMemberInfo(
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

	/*public List<Map<String, Object>> readFromLoan(
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime,
			@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);*/

	public List<Map<String, Object>> readFromMemberBank(
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

	/*public List<Map<String, Object>> readFromCertInfo(
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime,
			@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);*/

	public List<Map<String, Object>> readFromMemberIdentity(
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

	public List<Map<String, Object>> readFromInvestBid(
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	/*public List<Map<String, Object>> readFromInvestBidCurrent(
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime,
			@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);*/
	
	public List<Map<String, Object>> readFromVerify(
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	public List<Map<String, Object>> syncPlatformUserNo();

}
