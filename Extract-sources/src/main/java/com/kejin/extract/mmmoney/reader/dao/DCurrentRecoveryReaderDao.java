package com.kejin.extract.mmmoney.reader.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DCurrentRecoveryReaderDao {
	
	/**
	 *定期回款
	 */
	public List<Map<String, Object>> readFromRecovery(
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
}
