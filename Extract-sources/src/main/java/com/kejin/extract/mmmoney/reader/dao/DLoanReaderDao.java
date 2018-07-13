package com.kejin.extract.mmmoney.reader.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.kejinTest.DLoanModel;

public interface DLoanReaderDao {
	public List<DLoanModel> readFromLoanCreate(@Param("beginTime") Date beginTime,@Param("endTime") Date endTime);
	
	public List<DLoanModel> readFromLoanModified(@Param("beginTime") Date beginTime,@Param("endTime") Date endTime);
}
