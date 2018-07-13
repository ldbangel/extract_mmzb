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
import com.kejin.extract.common.utils.JsonUtil;
import com.kejin.extract.domainservice.extract.RegularInvestInfoService;
import com.kejin.extract.entity.kejinTest.DRegularInvestModel;
import com.kejin.extract.mmmoney.reader.dao.DRegularInvestReaderDao;

@Service("regularInvestInfoService")
public class RegularInvestInfoServiceImpl implements RegularInvestInfoService   {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DRegularInvestReaderDao dRegularInvestReaderDao;

	@Override
	public List<DRegularInvestModel> readCreateFromBidOrder(Date beginTime, Date endTime,int continueRead){
	    int pageSize = 100;
		
		logger.info("开始抽取定期投资的信息，时间段为："+beginTime+" 至    "+endTime);

		PageHelper.startPage(continueRead, pageSize);
		List<Map<String, Object>> bidInfos = dRegularInvestReaderDao
				.readCreateFromBidOrder(beginTime, endTime);
		
		List<DRegularInvestModel> bids = coverMapToRegularInvestModel(bidInfos);
					        
		logger.info("线上定期投资信息是："+JSON.toJSONString(new ArrayList<List<DRegularInvestModel>>().add(bids)));
		logger.info("结束抽取定期投资的信息");
		return bids;		
	 }
	
	public List<DRegularInvestModel> readModifiedFromBidOrder(Date beginTime, Date endTime,int continueRead){
	    int pageSize = 100;
		
		logger.info("开始抽取定期投资的信息，时间段为："+beginTime+" 至    "+endTime);

		PageHelper.startPage(continueRead, pageSize);
		List<Map<String, Object>> bidInfos = dRegularInvestReaderDao
				.readModifiedFromBidOrder(beginTime, endTime);
		
		List<DRegularInvestModel> bids = coverMapToRegularInvestModel(bidInfos);
					        
		logger.info("线上定期投资信息是："+JSON.toJSONString(new ArrayList<List<DRegularInvestModel>>().add(bids)));
		return bids;		
 }
	
	public List<DRegularInvestModel> coverMapToRegularInvestModel(List<Map<String, Object>> bidInfos){
		List<DRegularInvestModel> bids = new ArrayList<DRegularInvestModel>();
		if (bidInfos.size() > 0) {
			for(Map<String,Object> m : bidInfos){
				DRegularInvestModel bid = new DRegularInvestModel();
				
				bid.setBidOrderNo(m.get("BID_ORDER_NO") != null ? (String) m.get("BID_ORDER_NO") : null);
				bid.setMemberId(m.get("MEMBER_ID") != null ? (String) m.get("MEMBER_ID") : null);
				bid.setSubjectNo(m.get("SUBJECT_NO") != null ? (String) m.get("SUBJECT_NO") : null);
				bid.setSubjectName(m.get("SUBJECT_NAME") != null ? (String) m.get("SUBJECT_NAME") : null);
				bid.setSubjectLife(m.get("LOAN_TERM") != null ? (String) m.get("LOAN_TERM") : null);
				bid.setSubjectType(m.get("SUBJECT_TYPE") != null ? (String) m.get("SUBJECT_TYPE") : null);
				bid.setRate(m.get("REWARD_RATE") != null ? (BigDecimal) m.get("REWARD_RATE") : null);
				bid.setRepayType(m.get("REPAY_TYPE") != null ? (String) m.get("REPAY_TYPE") : null);
				bid.setAmount(m.get("AMOUNT") != null ? (BigDecimal) m.get("AMOUNT") : null);
				bid.setOperationType(m.get("SUBMIT_TYPE") != null ? (String) m.get("SUBMIT_TYPE") : null);
				bid.setOperationDate(m.get("CREATE_TIME") != null ? (Date) m.get("CREATE_TIME") : null);				
				bid.setOperationStatus(m.get("STATUS") != null ? (String) m.get("STATUS") : null);
				if(m.get("EXTENSION") != null){
					String platform = JsonUtil.getVauleFromJson("plantform", (String)m.get("EXTENSION"));
					bid.setPlatform(platform);
				}else{
					bid.setPlatform("");
				}
				bids.add(bid);
			}
		}
		
		return bids;
	}
	
}
