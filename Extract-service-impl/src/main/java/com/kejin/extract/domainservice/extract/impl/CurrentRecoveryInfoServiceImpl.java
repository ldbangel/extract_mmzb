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
import com.kejin.extract.domainservice.extract.CurrentRecoveryInfoService;
import com.kejin.extract.entity.kejinTest.DCurrentRecoveryModel;
import com.kejin.extract.mmmoney.reader.dao.DCurrentRecoveryReaderDao;

@Service("currentRecoveyInfoService")
public class CurrentRecoveryInfoServiceImpl implements CurrentRecoveryInfoService  {
	private Logger logger = Logger.getLogger(this.getClass());	

	@Autowired
	private DCurrentRecoveryReaderDao dCurrentRecoveryReaderDao;
	

	/* (non-Javadoc)
	 * @see com.kejin.extract.domainservice.extract.impl.CurrentRecoveryInfoService#readFromRecovry(java.util.Date, java.util.Date, int)
	 */
	@Override
	public List<DCurrentRecoveryModel> readFromRecovry(Date beginTime, Date endTime,int continueRead){
		int pageSize = 100;

		List<DCurrentRecoveryModel> recoverys = new ArrayList<DCurrentRecoveryModel>();
		
		logger.info("开始抽取回款的信息，时间段为："+beginTime+" 至   "+endTime);

		PageHelper.startPage(continueRead, pageSize);
		List<Map<String, Object>> recoveryInfos = dCurrentRecoveryReaderDao
				.readFromRecovery(beginTime, endTime);
					       
		if (recoveryInfos.size() > 0) {
			
			for(Map<String,Object> m : recoveryInfos){
				
				DCurrentRecoveryModel recovery = new DCurrentRecoveryModel();
				
				recovery.setRecoveryOrderDetailNo(m.get("RECOVERY_ORDER_DETAIL_NO") != null ? (String) m.get("RECOVERY_ORDER_DETAIL_NO") : null);
				recovery.setMemberId(m.get("MEMBER_ID") != null ? (String) m.get("MEMBER_ID") : null);
				recovery.setName(m.get("REAL_NAME") != null ? (String) m.get("REAL_NAME") : null);
				recovery.setPhone(m.get("PHONE") != null ? (String) m.get("PHONE") : null);
				recovery.setAmount(m.get("AMOUNT") != null ? (BigDecimal) m.get("AMOUNT") : null);
				recovery.setCreateDate(m.get("CREATE_TIME") != null ? (Date) m.get("CREATE_TIME") : null);
				recovery.setCredit(m.get("CREDIT_PRICE") != null ? (BigDecimal) m.get("CREDIT_PRICE") : null);
				recovery.setRewardRate(m.get("REWARD_RATE") != null ? (BigDecimal) m.get("REWARD_RATE") : null);
				
				recoverys.add(recovery);
			}			
			
	     }
		logger.info("回款信息是："+JSON.toJSONString(new ArrayList<List<DCurrentRecoveryModel>>().add(recoverys)));
		logger.info("结束抽取回款的信息");
		return recoverys;		
		
	}
	
	
}
