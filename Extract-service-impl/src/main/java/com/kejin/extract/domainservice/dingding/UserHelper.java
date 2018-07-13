package com.kejin.extract.domainservice.dingding;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;
import com.kejin.extract.common.utils.HttpUtil;
import com.kejin.extract.common.utils.JsonUtil;
import com.kejin.extract.entity.service.User;

public class UserHelper {
	/**
	 * 获取部门成员基本信息
	 */
	public static List<User> getDepartmentUsersBase(String accessToken, String parentDeptId){
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token",accessToken));
		params.add(new BasicNameValuePair("department_id",parentDeptId));
		String simpleList = HttpUtil.getResponsebyGet("https://oapi.dingtalk.com/user/simplelist", params);
		String userArray = JsonUtil.getVauleFromJson("userlist", simpleList);
    	List<User> userList = JSON.parseArray(userArray, User.class);
		return userList;
	}
	
	/**
	 * 获取部门成员详细信息
	 */
	public static List<User> getDepartmentUsersDetails(String accessToken, String parentDeptId){
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token",accessToken));
		params.add(new BasicNameValuePair("department_id",parentDeptId));
		String detailsList = HttpUtil.getResponsebyGet("https://oapi.dingtalk.com/user/list", params);
		String userArray = JsonUtil.getVauleFromJson("userlist", detailsList);
    	List<User> userList = JSON.parseArray(userArray, User.class);
		return userList;
	}
	
}
