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
import com.kejin.extract.domainservice.extract.BonusInfoService;
import com.kejin.extract.entity.kejinTest.DBonusModel;
import com.kejin.extract.mmmoney.reader.dao.DBonusReaderDao;

@Service("bonusInfoService")
public class BonusInfoServiceImpl implements BonusInfoService  {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DBonusReaderDao dBonusReaderDao;
	
	@Override
	public List<DBonusModel> readFromDpm(Date beginTime, Date endTime, int continueRead) {
		int pageSize = 100;

		List<DBonusModel> cashs = new ArrayList<DBonusModel>();

		logger.info("开始抽取提现的信息，时间段为："+beginTime+" 至    "+endTime );

		PageHelper.startPage(continueRead, pageSize);
		List<Map<String, Object>> bonusInfos = dBonusReaderDao.readFromBusinessDetail(beginTime, endTime);
		
		if (bonusInfos.size() > 0) {
			for (Map<String, Object> m : bonusInfos) {
				DBonusModel bonus = new DBonusModel();

				bonus.setMemberId(m.get("memberId") != null ? (String) m.get("memberId") : null);
				bonus.setAmount(m.get("amount") != null ? (BigDecimal) m.get("amount") : null);
				bonus.setRecordDate(m.get("create_time") != null ? (Date) m.get("create_time") : null);			
				bonus.setPlatformUserNo(m.get("platformUserNo") != null ? (String) m.get("platformUserNo") : null);
				bonus.setRequestNo(m.get("request_no") != null ? (String) m.get("request_no") : null);
				bonus.setBonusType(m.get("bonus_type") != null ? (String) m.get("bonus_type") : null);
				
				cashs.add(bonus);
			}
		}
		logger.info("提现信息是："
				+ JSON.toJSONString(new ArrayList<List<DBonusModel>>()
						.add(cashs)));
		logger.info("结束抽取提现的信息");
		return cashs;

	}
	
	
}
