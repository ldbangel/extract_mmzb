package com.kejin.extract.domainservice.dingding;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;
import com.kejin.extract.common.utils.HttpUtil;
import com.kejin.extract.common.utils.JsonUtil;

public class DepartmentHelper {
	/**
     * 获取全部部门列表
     */
	public static List<Department> listDepartments(String accessToken){
    	List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token",accessToken));
		params.add(new BasicNameValuePair("id","1"));
    	String departmentResponse = HttpUtil.getResponsebyGet("https://oapi.dingtalk.com/department/list", params);
    	String departmentArray = JsonUtil.getVauleFromJson("department", departmentResponse);
    	List<Department> departmentlist = JSON.parseArray(departmentArray, Department.class);
    	return departmentlist;
    }
    
    /**
     * 获取子部门列表
     */
    public static List<String> getChildDepartmentsIDList(String accessToken, String parentDeptId){
    	List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("access_token",accessToken));
		params.add(new BasicNameValuePair("id",parentDeptId));
    	String childIDListResponse = HttpUtil.getResponsebyGet("https://oapi.dingtalk.com/department/list_ids", params);
    	String childIds = JsonUtil.getVauleFromJson("sub_dept_id_list", childIDListResponse);
    	List<String> childList = JSON.parseArray(childIds, String.class);
    	return childList;
    }
}
