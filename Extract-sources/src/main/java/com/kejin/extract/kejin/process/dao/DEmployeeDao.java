package com.kejin.extract.kejin.process.dao;

import java.util.List;

import com.kejin.extract.entity.kejinTest.DEmployeeModel;

public interface DEmployeeDao {
	
	public List<DEmployeeModel> select();
	
	public List<DEmployeeModel> selectActiveUser();
	
	public void updateStatus(List<DEmployeeModel> list);
	
	public void insertEmployee(List<DEmployeeModel> list);

}
