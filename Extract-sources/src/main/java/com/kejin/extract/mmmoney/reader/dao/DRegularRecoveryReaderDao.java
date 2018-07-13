package com.kejin.extract.mmmoney.reader.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DRegularRecoveryReaderDao {
	
	/**
	 *定期回款---创建的
	 */
	public List<Map<String, Object>> readCreateFromRecovery(
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	/**
	 *定期回款---更新的
	 */
	public List<Map<String, Object>> readModifiedFromRecovery(
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

}
