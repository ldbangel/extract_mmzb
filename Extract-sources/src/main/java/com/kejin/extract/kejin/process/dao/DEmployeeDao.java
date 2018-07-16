package com.kejin.extract.kejin.process.dao;

import java.util.List;

import com.kejin.extract.entity.kejinTest.DEmployeeModel;
import com.kejin.extract.entity.service.User;

public interface DEmployeeDao {
	
	public List<DEmployeeModel> select();
	
	public List<DEmployeeModel> selectActiveUser();
	
	public void updateStatus(List<DEmployeeModel> list);
	
	public void insertEmployee(List<DEmployeeModel> list);
	
	public List<DEmployeeModel> selectHistoryEmployee(List<User> userList);
	
	public void updateStatus2Active(List<DEmployeeModel> list);

}
