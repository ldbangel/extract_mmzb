package com.kejin.extract.domainservice.dingding;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiProcessinstanceCreateRequest.FormComponentValueVo;
import com.dingtalk.api.request.SmartworkBpmsProcessinstanceCreateRequest;
import com.dingtalk.api.response.SmartworkBpmsProcessinstanceCreateResponse;
import com.taobao.api.ApiException;

public class ApprovalProcessHelper {
	public static void Test() throws ApiException{
		String access_token = AuthHelper.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient("https://eco.taobao.com/router/rest");
		SmartworkBpmsProcessinstanceCreateRequest req = new SmartworkBpmsProcessinstanceCreateRequest();
		//req.setAgentId(41605932L);
		req.setProcessCode("PROC-EF6YJL35P2-SCKICSB7P750S0YISYKV3-17IBLGZI-1");
		req.setOriginatorUserId("manager432");
		req.setDeptId(100L);
		req.setApprovers("zhangsan,lisi");
		req.setCcList("zhangsan,lisi");
		req.setCcPosition("START");
		List<FormComponentValueVo> list2 = new ArrayList<FormComponentValueVo>();
		FormComponentValueVo obj3 = new FormComponentValueVo();
		list2.add(obj3);
		obj3.setName("请假类型");
		obj3.setValue("事假");
		obj3.setExtValue("总天数:1");
		req.setFormComponentValues(JSON.toJSONString(list2));
		SmartworkBpmsProcessinstanceCreateResponse rsp = client.execute(req, access_token);
		System.out.println(rsp.getBody());
	}
	
	public static void main(String[] args) throws ApiException, OApiException {
		List<String> callBackTag = new ArrayList<String>();
		callBackTag.add("bpms_task_change");
		callBackTag.add("bpms_instance_change");
		String token = "";
		String aesKey = "";
		String url = "https://liudongbo.vaiwan.com/";
		EventChangeHelper.registerEventChange(AuthHelper.getAccessToken(), callBackTag, token, aesKey, url);
	}
}
