package com.kejin.extract.kejin.process.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kejin.extract.entity.kejinTest.DUserModel;
import com.kejin.extract.entity.service.User;

public interface DUserDao {
	
	public List<DUserModel> select(Map<String, Object> parameter);
	
	public List<DUserModel> selectByMemberId(String id);
	
	public List<DUserModel> selectByPhone(List<User> userList);
	
	//查询昨日注册但没有投资用户
	public List<Map<String,Object>> selectRegisterNoInvestUsers(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	//查询昨日注册并有投资用户
	public List<Map<String,Object>> selectRegisterAndInvestUsers(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	public int insert(DUserModel dUserModel);
	
	public int update(DUserModel dUserModel);
	
	public int syncPlatformUserNo(List<DUserModel> userInfos);
	
	public int updateRecommandFcode(DUserModel dUserModel);
	
	public List<Map<String,Object>> selectByPlatUserNo(List<Map<String,Object>> list);

}
