package com.kejin.extract.domainservice.user.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.kejin.extract.domainservice.user.UserTradeInfoDetailService;
import com.kejin.extract.entity.service.CashInfoModel;
import com.kejin.extract.entity.service.ChargeInfoModel;
import com.kejin.extract.entity.service.dynamic.InvestBidDetailModel;
import com.kejin.extract.entity.service.dynamic.RecoveryDetailModel;
import com.kejin.extract.kejin.service.dao.UserDetailInfoDao;
import com.kejin.extract.mmmoney.service.dao.InvestDetailDao;

@Service("userTradeInfoDetailService")
public class UserTradeInfoDetailServiceImpl implements UserTradeInfoDetailService  {
	@Autowired
	private UserDetailInfoDao userDetailInfoDao;
	@Autowired
	private InvestDetailDao investDetailDao;
	
	private int pageSize = 50;
   
	@Override
	public String getUserInvestInfoDetail(String memberId,Date begin,Date end,Integer page){	
		PageHelper.startPage(page, pageSize);
		List<InvestBidDetailModel> investBids = investDetailDao
				.querySubjectBid(memberId,begin,end);
		return JSON.toJSONStringWithDateFormat(investBids,"yyyy-MM-dd HH:mm:ss",SerializerFeature.WriteMapNullValue);
	}
	
	public String getUserRecoveryInfoDetail(String memberId,Date begin,Date end,Integer page){
		PageHelper.startPage(page, pageSize);
		List<RecoveryDetailModel>  recoveys  =  investDetailDao
				.queryRecoveryDetail(memberId, begin, end);
		return JSON.toJSONStringWithDateFormat(recoveys,"yyyy-MM-dd HH:mm:ss",SerializerFeature.WriteMapNullValue);
	}
	
	public String getUseChargeInfoDetail(String memberId,Date begin,Date end,Integer page){
		PageHelper.startPage(page, pageSize);
		List<ChargeInfoModel> chargeInfos = userDetailInfoDao
				.queryChargeInfo(memberId, begin, end);
		return JSON.toJSONStringWithDateFormat(chargeInfos,"yyyy-MM-dd HH:mm:ss",SerializerFeature.WriteMapNullValue);
	}
	
	public String getUserCashInfoDetail(String memberId,Date begin,Date end,Integer page){
		PageHelper.startPage(page, pageSize);
		List<CashInfoModel> cashInfos = userDetailInfoDao
				.queryCashInfo(memberId, begin, end);		
		return JSON.toJSONStringWithDateFormat(cashInfos,"yyyy-MM-dd HH:mm:ss",SerializerFeature.WriteMapNullValue);
	}
}
