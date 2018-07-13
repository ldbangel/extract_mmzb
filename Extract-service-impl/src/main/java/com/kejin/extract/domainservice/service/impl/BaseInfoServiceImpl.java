package com.kejin.extract.domainservice.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.kejin.extract.domainservice.service.BaseInfoService;
import com.kejin.extract.entity.service.BaseInvestInfoModel;
import com.kejin.extract.entity.service.BaseUserInfoModel;
import com.kejin.extract.entity.service.util.BaseInfo;
import com.kejin.extract.kejin.service.dao.BaseInfoDao;
import com.kejin.extract.mmmoney.reader.dao.DRegularInvestReaderDao;


@Service("baseInfoService")
public class BaseInfoServiceImpl implements BaseInfoService {
	@Autowired
	private BaseInfoDao baseInfoDao;
	@Autowired
	private DRegularInvestReaderDao dRegularInvestReaderDao;
	
	
	/* (non-Javadoc)
	 * @see com.kejin.extract.domainservice.service.impl.BaseInfoService#getBaseInfo()
	 */
	@Override
	public String getBaseInfo(){
		
		Calendar calendarBegin = Calendar.getInstance();

		calendarBegin.set(calendarBegin.get(Calendar.YEAR),
				calendarBegin.get(Calendar.MONTH),
				calendarBegin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

		Calendar calendarEnd = Calendar.getInstance();

		calendarEnd.setTime(calendarBegin.getTime());

		calendarEnd.set(Calendar.DATE, calendarEnd.get(Calendar.DATE) + 1);
		
		Date beginTime = calendarBegin.getTime();
		Date endTime = calendarEnd.getTime();	
		
	    List<BaseUserInfoModel> userInfos =  baseInfoDao.getBaseUserInfo(beginTime,endTime );
		
	    List<BaseInvestInfoModel> investInfos = dRegularInvestReaderDao.getBaseInvestInfo(beginTime, endTime);
	    	    
	    Calendar now = Calendar.getInstance();
		
	    int nowHour = now.get(Calendar.HOUR_OF_DAY);
	    
	    BaseInfo baseInfo = new BaseInfo();
	    List<BaseUserInfoModel> userInfosNew = buildUserInfos(userInfos,nowHour,baseInfo);
	    
	    List<BaseInvestInfoModel> investInfoNew = buildInvestInfos(investInfos, nowHour, baseInfo);
	    
	    Map<String,Object> infoMap = new HashMap<String,Object>();
	    infoMap.put("registerNum", baseInfo.getRegisterNum());
	    infoMap.put("registerInfos", userInfosNew);
	    infoMap.put("investNum", baseInfo.getInvestNum());
	    infoMap.put("investAmount",  String.format("%.2f",baseInfo.getInvestAmount()));
	    infoMap.put("investInfos", investInfoNew);
	    
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    infoMap.put("now", format.format(now.getTime()));
	    
	    String info = JSON.toJSONString(infoMap);
		
		return info;
	}
	
	
	private List<BaseUserInfoModel> buildUserInfos(List<BaseUserInfoModel> userInfos,int nowHour,BaseInfo baseInfo) {
		Integer registerNum = 0;
		int dayHour = 0;
		List<BaseUserInfoModel> userInfosNew = new ArrayList<BaseUserInfoModel>();
		for (BaseUserInfoModel user : userInfos) {
			Integer hour = user.getRegisterHour();

			while (dayHour < hour) {
				BaseUserInfoModel tempUser = new BaseUserInfoModel();
				tempUser.setRegisterHour(dayHour);
				tempUser.setRegisterNum(0);
				dayHour++;
				userInfosNew.add(tempUser);
			}
			userInfosNew.add(user);
			dayHour++;
			registerNum = registerNum + user.getRegisterNum();
		}

		while (dayHour <= nowHour) {
			BaseUserInfoModel tempUser = new BaseUserInfoModel();
			tempUser.setRegisterHour(dayHour);
			tempUser.setRegisterNum(0);
			dayHour++;
			userInfosNew.add(tempUser);
		}
        
		baseInfo.setRegisterNum(registerNum);
		return userInfosNew;
	}
	
	
	private List<BaseInvestInfoModel> buildInvestInfos(List<BaseInvestInfoModel> investInfos,int nowHour,BaseInfo baseInfo) {
		
		Integer investNum = 0;
		Double investAmount = 0.00d;
		int dayHour = 0;
		List<BaseInvestInfoModel> investInfosNew = new ArrayList<BaseInvestInfoModel>();
		for (BaseInvestInfoModel invest : investInfos) {
			Integer hour = invest.getInvestHour();

			while (dayHour < hour) {
				BaseInvestInfoModel tempInvest = new BaseInvestInfoModel();
				tempInvest.setInvestNum(0);
				tempInvest.setInvestHour(dayHour);
				tempInvest.setInvestAmount(new BigDecimal(0));
				dayHour++;	
				investInfosNew.add(tempInvest);
			}
			investInfosNew.add(invest);
			dayHour++;
			investNum= investNum+ invest.getInvestNum();
			investAmount = investAmount+ invest.getInvestAmount().doubleValue();
		}
		
		while (dayHour <= nowHour) {
			BaseInvestInfoModel tempInvest = new BaseInvestInfoModel();
			tempInvest.setInvestNum(0);
			tempInvest.setInvestHour(dayHour);
			tempInvest.setInvestAmount(new BigDecimal(0));
			dayHour++;	
			investInfosNew.add(tempInvest);
		}
		baseInfo.setInvestNum(investNum);
		baseInfo.setInvestAmount(investAmount);
	    return investInfosNew;
	}

}
