package com.kejin.extract.mmmoney.reader.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.kejinTest.DChannelModel;

public interface DChannelReaderDao {
	
	/**
	 * 从员工表读取渠道
	 */
	public List<DChannelModel> readChannelFromEmployee(
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
	

}
