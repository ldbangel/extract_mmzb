package com.kejin.extract.kejin.process.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.kejinTest.DEmployeeModel;
import com.kejin.extract.entity.service.User;

public interface DEmployeeDao {
	
	public List<DEmployeeModel> select();
	
	public List<DEmployeeModel> selectActiveUser();
	
	public void updateStatus(@Param("list") List<DEmployeeModel> list);
	
	public void insertEmployee(@Param("list") List<DEmployeeModel> list);
	
	public List<DEmployeeModel> selectHistoryEmployee(@Param("list") List<User> userList);
	
	public void updateStatus2Active(@Param("list") List<DEmployeeModel> list);

}
