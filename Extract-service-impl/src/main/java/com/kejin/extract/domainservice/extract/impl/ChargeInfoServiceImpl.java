package com.kejin.extract.domainservice.extract.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.kejin.extract.domainservice.extract.ChargeInfoService;
import com.kejin.extract.entity.kejinTest.DChargeModel;
import com.kejin.extract.mmmoney.reader.dao.DChargeReaderDao;

@Service("chargeInfoService")
public class ChargeInfoServiceImpl implements ChargeInfoService  {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DChargeReaderDao dChargeReaderDao;
	
	@Override
	public List<DChargeModel> readFromPayment(Date beginTime, Date endTime, int continueRead) {
		int pageSize = 100;

		List<DChargeModel> charges = new ArrayList<DChargeModel>();

		logger.info("开始抽取线上充值的信息，时间段为："+beginTime+" 至   "+endTime);

		PageHelper.startPage(continueRead, pageSize);
		List<Map<String, Object>> chargeInfos = dChargeReaderDao.readChargeOnline(beginTime, endTime);
		
		if (chargeInfos.size() > 0) {
			for (Map<String, Object> m : chargeInfos) {
				DChargeModel charge = new DChargeModel();
				
				charge.setMemberId(m.get("memberId") != null ? (String) m.get("memberId") : null);
				charge.setAmount(m.get("amount") != null ? (BigDecimal) m.get("amount") : null);
				charge.setOnline("0");
				charge.setPayDate(m.get("gmt_create") != null ? (Date) m.get("gmt_create") : null);
				charge.setChannel(m.get("rechargeway") != null ? (String) m.get("rechargeway") : null);
				charge.setChargeStatus(m.get("rechargestatus") != null ? (String) m.get("rechargestatus") : null);
				charge.setPlatformUserNo(m.get("platformUserNo") != null ? (String) m.get("platformUserNo") : null);
				charge.setRequestNo(m.get("requestNo") != null ? (String) m.get("requestNo") : null);
				charge.setUserRole(m.get("userRole") != null ? (String) m.get("userRole") : null);

				charges.add(charge);
			}

		}
		logger.info("线上充值信息是："
				+ JSON.toJSONString(new ArrayList<List<DChargeModel>>()
						.add(charges)));
		logger.info("结束抽取线上充值的信息");
		return charges;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.kejin.extract.domainservice.extract.impl.ChargeInfoService#
	 * readFromCounter(java.util.Date, java.util.Date)
	 */
	@Override
	public List<DChargeModel> readFromCounter(Date beginTime, Date endTime, int continueRead) {
		int pageSize = 100;

		List<DChargeModel> charges = new ArrayList<DChargeModel>();

		logger.info("开始抽取线下充值的信息，时间段为："+beginTime+" 至   "+endTime);

		PageHelper.startPage(continueRead, pageSize);
		List<Map<String, Object>> chargeInfos = dChargeReaderDao.readChargeOffline(beginTime, endTime);
		if (chargeInfos.size() > 0) {
			for (Map<String, Object> m : chargeInfos) {
				DChargeModel charge = new DChargeModel();

				charge.setMemberId(m.get("memberId") != null ? (String) m.get("memberId") : null);
				charge.setAmount(m.get("amount") != null ? (BigDecimal) m.get("amount") : null);
				charge.setOnline("1");
				charge.setPayDate(m.get("modified_time") != null ? (Date) m.get("modified_time") : null);
				charge.setChargeStatus("SUCCESS");
				charge.setPlatformUserNo(m.get("platformUserNo") != null ? (String) m.get("platformUserNo") : null);
				charge.setRequestNo(m.get("request_no") != null ? (String) m.get("request_no") : null);
				charge.setUserRole(m.get("userRole") != null ? (String) m.get("userRole") : null);

				charges.add(charge);
			}
		}
		logger.info("线下充值信息是："
				+ JSON.toJSONString(new ArrayList<List<DChargeModel>>()
						.add(charges)));
		logger.info("结束抽取线下充值的信息");
		return charges;

	}
	
}
