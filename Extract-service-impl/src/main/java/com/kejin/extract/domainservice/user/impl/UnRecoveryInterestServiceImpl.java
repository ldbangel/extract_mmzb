package com.kejin.extract.domainservice.user.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.kejin.extract.domainservice.user.UnRecoveryInterestService;
import com.kejin.extract.entity.service.dynamic.RecoveryDetailModel;
import com.kejin.extract.mmmoney.service.dao.InvestDetailDao;

@Service("unRecoveryInterestService")
public class UnRecoveryInterestServiceImpl implements UnRecoveryInterestService {
	
	@Autowired
	private InvestDetailDao investDetailDao;
	
	private int pageSize = 50;
	
	@Override
	public String getUserUnRecoveryInterest(String memberId,Date begin,Date end,Integer page){
		PageHelper.startPage(page, pageSize);
		List<RecoveryDetailModel> unrecoverys =	investDetailDao.queryUnRecoveryDetail(memberId, begin, end);
		
		return JSON.toJSONStringWithDateFormat(unrecoverys,"yyyy-MM-dd HH:mm:ss",SerializerFeature.WriteMapNullValue);
	}

}
