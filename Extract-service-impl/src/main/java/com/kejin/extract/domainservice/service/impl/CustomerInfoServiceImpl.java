package com.kejin.extract.domainservice.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kejin.extract.domainservice.common.Excel2CustomerDistributeUtil;
import com.kejin.extract.domainservice.service.CustomerInfoService;
import com.kejin.extract.kejin.process.dao.DUserDao;

@Service("customerInfoService")
public class CustomerInfoServiceImpl implements CustomerInfoService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DUserDao dUserDao;

	@Override
	public Map<String,List<Map<String,Object>>> customerDistribute(Date beginTime, Date endTime) {
		Map<String,List<Map<String,Object>>> map = new HashMap<String,List<Map<String,Object>>>();
		List<Map<String,Object>> noinvestList = dUserDao.selectRegisterNoInvestUsers(beginTime, endTime);
		List<Map<String,Object>> investList = dUserDao.selectRegisterAndInvestUsers(beginTime, endTime);
		
		List<Map<String,Object>> department1 = new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> department2 = new ArrayList<Map<String,Object>>();
		
		for (int i = 0; i < noinvestList.size(); i++) {
			noinvestList.get(i).put("subjectLife", "");
			noinvestList.get(i).put("amount", null);
			noinvestList.get(i).put("operationDate", "");
		}
		
		/**
		 * 新增注册用户分配规则
		 * 一、注册投资和注册未投资用户应该均分
		 * 二、先分注册未投资的客户
		 * 	1、如果这批客户数是偶数，直接均分到两个部门就好
		 * 	2、如果是客户数是奇数，先均分前n-1个用户数，最后一个随机分给1部或者2部
		 * 三、分注册投资客户
		 * 	1、如果这批客户数是偶数，直接均分到两个部门就好
		 * 	2、如果是客户数是奇数，先均分前n-1个用户数
		 * 	  a、如果前面未投资的客户数量1部和2部一样多，那么这最后一个随机分给1部或者2部
		 * 	  b、如果前面未投资客户数量1部多于2部，那么这最后一个客户直接分给2部
		 * 	  c、如果前面未投资客户数量1部少于2部，那么这以后一个客户直接分给1部
		 */
		/*if(noinvestList != null && noinvestList.size() > 0){
			if(noinvestList.size()%2 == 0){
				for (int i = 0; i < noinvestList.size(); i++) {
					if(i%2 == 0){
						department1.add(noinvestList.get(i));
					}else{
						department2.add(noinvestList.get(i));
					}
					
				}
			}else{
				for (int i = 0; i < noinvestList.size()-1; i++) {
					if(i%2 == 0){
						department1.add(noinvestList.get(i));
					}else{
						department2.add(noinvestList.get(i));
					}
				}
				Random random = new Random();  
				int randomNum = random.nextInt(9);
				if(randomNum%2 == 0){
					department1.add(noinvestList.get(noinvestList.size()-1));
				}else{
					department2.add(noinvestList.get(noinvestList.size()-1));
				}
			}
		}
		//这两个参数用来记录没投资的用户两个部门各分了多少个
		int temp1 = department1.size();
		int temp2 = department2.size();
		
		if(investList != null && investList.size() > 0){
			if(investList.size()%2 == 0){
				for (int i = 0; i < investList.size(); i++) {
					if(i%2 == 0){
						department1.add(investList.get(i));
					}else{
						department2.add(investList.get(i));
					}
				}
			}else{
				for (int i = 0; i < investList.size()-1; i++) {
					if(i%2 == 0){
						department1.add(investList.get(i));
					}else{
						department2.add(investList.get(i));
					}
				}
				if(temp1 == temp2){
					Random random = new Random();  
					int randomNum = random.nextInt(9);
					if(randomNum%2 == 0){
						department1.add(investList.get(investList.size()-1));
					}else{
						department2.add(investList.get(investList.size()-1));
					}
				}else if(temp1 > temp2){
					department2.add(investList.get(investList.size()-1));
				}else{
					department1.add(investList.get(investList.size()-1));
				}
			}
		}*/
		
		
		
		/**
		 * 一部和二部按照2:1的比例分配
		 */
		if(noinvestList != null && noinvestList.size() > 0){
			for (int i = 0; i < noinvestList.size(); i++) {
				if(i%3 == 0 || i%3 == 1){
					department1.add(noinvestList.get(i));
				}else{
					department2.add(noinvestList.get(i));
				}
			}
		}
		if(investList != null && investList.size() > 0){
			for (int i = 0; i < investList.size(); i++) {
				if(i%3 == 0 || i%3 == 1){
					department1.add(investList.get(i));
				}else{
					department2.add(investList.get(i));
				}
			}
		}
		
		map.put("department1", department1);
		map.put("department2", department2);
		return map;
	}

	
	public void GeneraterDistributeExcel(Date beginTime, Date endTime){
		Map<String,List<Map<String,Object>>> map =  customerDistribute(beginTime, endTime);
		List<Map<String,Object>> department1 = map.get("department1");
		List<Map<String,Object>> department2 = map.get("department2");
		try {
			logger.info("------注册客户名单分配excel生成-----");
			Excel2CustomerDistributeUtil.excelUtil(department1, department2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
