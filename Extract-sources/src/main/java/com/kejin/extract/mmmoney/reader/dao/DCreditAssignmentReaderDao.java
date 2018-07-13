package com.kejin.extract.mmmoney.reader.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DCreditAssignmentReaderDao {
	
	public List<Map<String, Object>> readFromCreditAssignment(
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	
	public List<Map<String, Object>> readPayfeeFromBidOrder();

}
