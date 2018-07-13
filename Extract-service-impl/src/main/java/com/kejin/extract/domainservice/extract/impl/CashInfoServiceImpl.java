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
import com.kejin.extract.domainservice.extract.CashInfoService;
import com.kejin.extract.entity.kejinTest.DCashModel;
import com.kejin.extract.mmmoney.reader.dao.DCashReaderDao;

@Service("cashInfoService")
public class CashInfoServiceImpl implements CashInfoService  {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DCashReaderDao dCashReaderDao;

	@Override
	public List<DCashModel> readFromPayment(Date beginTime, Date endTime,int continueRead) {
		int pageSize = 100;

		List<DCashModel> cashs = new ArrayList<DCashModel>();

		logger.info("开始抽取提现的信息，时间段为："+beginTime+" 至    {1}"+endTime);

		PageHelper.startPage(continueRead, pageSize);
		List<Map<String, Object>> cashInfos = dCashReaderDao.readFromPayment(beginTime, endTime);
		if (cashInfos.size() > 0) {
			for (Map<String, Object> m : cashInfos) {
				DCashModel cash = new DCashModel();

				cash.setPaymentOrderNo(m.get("requestNo") != null ? (String) m.get("requestNo") : null);
				cash.setMemberId(m.get("memberid") != null ? (String) m.get("memberid") : null);
				cash.setName(m.get("name") != null ? (String) m.get("name") : null);
				cash.setPhone(m.get("phone") != null ? (String) m.get("phone") : null);
				cash.setCardNo(m.get("bankcard") != null ? (String) m.get("bankcard") : null);
				cash.setAmount(m.get("amount") != null ? (BigDecimal) m.get("amount") : null);
				cash.setFee(m.get("fee") != null ? (BigDecimal) m.get("fee"): null);
				cash.setPayStatus(m.get("withdrawstatus") != null ? (String) m.get("withdrawstatus") : null);
				cash.setConfirmStatus(m.get("withdrawoperation") != null ? (String) m.get("withdrawoperation") : null);
				cash.setOrderTime(m.get("gmt_create") != null ? (Date) m.get("gmt_create") : null);
				cash.setResultTime(m.get("gmt_modify") != null ? (Date) m.get("gmt_modify") : null);
				cash.setPlatformUserNo(m.get("platformUserNo") != null ? (String) m.get("platformUserNo") : null);
				cash.setUserRole(m.get("userRole") != null ? (String) m.get("userRole") : null);

				cashs.add(cash);
			}
		}
		logger.info("提现信息是："
				+ JSON.toJSONString(new ArrayList<List<DCashModel>>()
						.add(cashs)));
		logger.info("结束抽取提现的信息");
		return cashs;

	}
	
	
}
