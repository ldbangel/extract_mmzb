package com.kejin.extract.domainservice.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.kejin.extract.domainservice.growingio.ChartBean;
import com.kejin.extract.domainservice.growingio.GrowingIODataService;
import com.kejin.extract.domainservice.service.PlatformInfoService;
import com.kejin.extract.kejin.service.dao.OperationInfoDao;
import com.kejin.extract.mmmoney.service.dao.OperationInfoDao2;

/**
 * 区分ios、Android、H5、PC
 * @author liudongbo
 *
 */
@Service("platformInfoService")
public class PlatformInfoServiceImpl implements PlatformInfoService {
	@Autowired
	private OperationInfoDao operationInfoDao;
	@Autowired
	private OperationInfoDao2 operationInfoDao2;

	@Override
	public Map<String,Object> getPlatformInfo(Date beginTime, Date endTime) {
		Calendar oneMonthAgoBeginTime = Calendar.getInstance();
		oneMonthAgoBeginTime.setTime(beginTime);
		oneMonthAgoBeginTime.set(Calendar.DATE, oneMonthAgoBeginTime.get(Calendar.DATE) - 30);
		
		Map<String,Object> map  = new HashMap<String, Object>();
		List<Map<String,Object>> list1 = operationInfoDao.getFirstInvestPlatformInfo(beginTime, endTime);
		List<Map<String,Object>> list2 = operationInfoDao.getInvestNumPlatformInfo(beginTime, endTime);
		List<Map<String,Object>> list3 = operationInfoDao2.getRecoveryRateInfo(oneMonthAgoBeginTime.getTime(), endTime);
		List<Map<String,Object>> list4 = operationInfoDao2.getRegisterPlatformInfo(beginTime, endTime);
		List<Map<String,Object>> list5 = operationInfoDao2.getTieCardPlatformInfo(beginTime, endTime);
		
		convertTo(list1,"firstInvest");
		convertTo(list2,"investNum");
		convertTo(list4,"registerNum");
		
		map.put("firstInvest", list1);
		map.put("investNum", list2);
		map.put("recoveryRate", list3);
		map.put("registerNum", list4);
		map.put("tieNum", list5);
		return map;
	}
	
	@Override
	public List<Map<String, Object>> getGrowingioDayInfo() {
		List<Map<String, Object>> list = getGrowingioData(1);
		return list;
	}
	
	
	@Override
	public List<Map<String, Object>> getGrowingioMonthInfo() {
		List<Map<String, Object>> list = getGrowingioData(30);
		return list;
	}
	
	private List<Map<String, Object>> getGrowingioData(int days){
		ChartBean chartBean = GrowingIODataService.getChart(GrowingIODataService.authToken(), "39l6GD3P", days);
		System.out.println(JSON.toJSON(chartBean));
		List<Map<String,Object>> list = new  ArrayList<Map<String,Object>>();
		for(List<String> data : chartBean.getData()){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("platform", data.get(0));
			map.put("loginNum", data.get(1));
			map.put("visitNum", data.get(2));
			list.add(map);
		}
		return list;
	}
	
	private void convertTo(List<Map<String,Object>> list, String type){
		StringBuffer sb = new StringBuffer();
		for (Map<String,Object> m : list) {
			sb.append(m.get("platform"));
		}
		Map<String,Object> m = new HashMap<String, Object>();
		if(sb.toString().contains("iOS") == false){
			m.put("platform", "iOS");
			if("firstInvest".equals(type)){
				m.put("newNums", 0);
				m.put("sumAmount", 0);
			}else if("investNum".equals(type)){
				m.put("investNum", 0);
			}else if("registerNum".equals(type)){
				m.put("registerNum", 0);
			}
			list.add(m);
		}
		if(sb.toString().contains("Android") == false){
			m.put("platform", "Android");
			if("firstInvest".equals(type)){
				m.put("newNums", 0);
				m.put("sumAmount", 0);
			}else if("investNum".equals(type)){
				m.put("investNum", 0);
			}else if("registerNum".equals(type)){
				m.put("registerNum", 0);
			}
			list.add(m);
		}
		if(sb.toString().contains("H5") == false){
			m.put("platform", "H5");
			if("firstInvest".equals(type)){
				m.put("newNums", 0);
				m.put("sumAmount", 0);
			}else if("investNum".equals(type)){
				m.put("investNum", 0);
			}else if("registerNum".equals(type)){
				m.put("registerNum", 0);
			}
			list.add(m);
		}
		if(sb.toString().contains("PC") == false){
			m.put("platform", "PC");
			if("firstInvest".equals(type)){
				m.put("newNums", 0);
				m.put("sumAmount", 0);
			}else if("investNum".equals(type)){
				m.put("investNum", 0);
			}else if("registerNum".equals(type)){
				m.put("registerNum", 0);
			}
			list.add(m);
		}
	}
	
	public static void main(String[] args) {
		ChartBean chartBean = GrowingIODataService.getChart(GrowingIODataService.authToken(), "39l6GD3P", 1);
		System.out.println(JSON.toJSON(chartBean));
		List<Map<String,Object>> list = new  ArrayList<Map<String,Object>>();
		for(List<String> data : chartBean.getData()){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("platform", data.get(0));
			map.put("loginNum", data.get(1));
			map.put("visitNum", data.get(2));
			list.add(map);
		}
		System.out.println(list.toString());
	}
	



}
