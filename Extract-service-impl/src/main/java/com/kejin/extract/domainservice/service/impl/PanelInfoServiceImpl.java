package com.kejin.extract.domainservice.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kejin.extract.domainservice.service.PanelInfoService;
import com.kejin.extract.entity.service.BaseInvestInfoModel;
import com.kejin.extract.mmmoney.reader.dao.DRegularInvestReaderDao;

@Service("panelInfoService")
public class PanelInfoServiceImpl implements PanelInfoService {
	@Autowired
	private DRegularInvestReaderDao dRegularInvestReaderDao;

	@Override
	public List<BaseInvestInfoModel> getInvestTradeTimeData() {
		Calendar calendarBegin = Calendar.getInstance();
		calendarBegin.set(calendarBegin.get(Calendar.YEAR),
				calendarBegin.get(Calendar.MONTH),
				calendarBegin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

		Calendar calendarEnd = Calendar.getInstance();
		calendarEnd.setTime(calendarBegin.getTime());
		calendarEnd.set(Calendar.DATE, calendarEnd.get(Calendar.DATE) + 1);
		
		Date beginTime = calendarBegin.getTime();
		Date endTime = calendarEnd.getTime();	
		
		List<BaseInvestInfoModel> investInfos = dRegularInvestReaderDao.getBaseInvestInfo(beginTime, endTime);
		
		Calendar now = Calendar.getInstance();
	    int nowHour = now.get(Calendar.HOUR_OF_DAY);
	    List<BaseInvestInfoModel> investInfoNew = buildInvestInfos(investInfos, nowHour);
	    
		return investInfoNew;
	}
	
	private List<BaseInvestInfoModel> buildInvestInfos(List<BaseInvestInfoModel> investInfos, int nowHour) {
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
	    return investInfosNew;
	}

}
