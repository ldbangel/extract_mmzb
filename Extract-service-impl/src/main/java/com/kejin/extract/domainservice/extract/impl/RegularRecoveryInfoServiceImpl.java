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
import com.kejin.extract.domainservice.extract.RegularRecoveyInfoService;
import com.kejin.extract.entity.kejinTest.DRegularRecoveryModel;
import com.kejin.extract.mmmoney.reader.dao.DRegularRecoveryReaderDao;

@Service("regularRecoveyInfoService")
public class RegularRecoveryInfoServiceImpl implements RegularRecoveyInfoService {
	private Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private DRegularRecoveryReaderDao dRegularRecoveryReaderDao;
	

	/* (non-Javadoc)
	 * @see com.kejin.extract.domainservice.extract.impl.RegularRecoveyInfoService#readFromPayment(java.util.Date, java.util.Date)
	 */
	@Override
	public List<DRegularRecoveryModel> readCreateFromRecovery(Date beginTime, Date endTime,int continueRead){
		int pageSize = 100;

		logger.info("开始抽取回款的信息，时间段为："+beginTime+" 至   "+endTime);

		PageHelper.startPage(continueRead, pageSize);
		List<Map<String, Object>> recoveryInfos = dRegularRecoveryReaderDao
				.readCreateFromRecovery(beginTime, endTime);
		
		List<DRegularRecoveryModel> recoverys = coverMapToRegularRecoveryModel(recoveryInfos);		       
			
		logger.info("回款信息是："+JSON.toJSONString(new ArrayList<List<DRegularRecoveryModel>>().add(recoverys)));
		return recoverys;		
	}
	
	@Override
	public List<DRegularRecoveryModel> readModifiedFromRecovery(Date beginTime, Date endTime,int continueRead){
		
		int pageSize = 100;

		logger.info("开始抽取回款的信息，时间段为："+beginTime+" 至   "+endTime);

		PageHelper.startPage(continueRead, pageSize);
		List<Map<String, Object>> recoveryInfos = dRegularRecoveryReaderDao
				.readModifiedFromRecovery(beginTime, endTime);
		
		List<DRegularRecoveryModel> recoverys = coverMapToRegularRecoveryModel(recoveryInfos);		       
			
		logger.info("回款信息是："+JSON.toJSONString(new ArrayList<List<DRegularRecoveryModel>>().add(recoverys)));
		return recoverys;		
	}
	
	public List<DRegularRecoveryModel> coverMapToRegularRecoveryModel(List<Map<String, Object>> recoveryInfos){
		List<DRegularRecoveryModel> recoverys = new ArrayList<DRegularRecoveryModel>();
		if (recoveryInfos.size() > 0) {
			for(Map<String,Object> m : recoveryInfos){
				DRegularRecoveryModel recovery = new DRegularRecoveryModel();
				
				recovery.setRecoveryOrderDetailNo(m.get("RECOVERY_ORDER_DETAIL_NO") != null ? (String) m.get("RECOVERY_ORDER_DETAIL_NO") : null);
				recovery.setMemberId(m.get("MEMBER_ID") != null ? (String) m.get("MEMBER_ID") : null);
				recovery.setAmount(m.get("AMOUNT") != null ? (BigDecimal) m.get("AMOUNT") : null);
				recovery.setPrincipal(m.get("PRINCIPAL") != null ? (BigDecimal) m.get("PRINCIPAL") : null);
				recovery.setInterest(m.get("INTEREST") != null ? (BigDecimal) m.get("INTEREST") : null);
				recovery.setRepayTerm(m.get("REPAY_TERM") != null ? String.valueOf(m.get("REPAY_TERM")) : null);
				recovery.setTotalTerm(m.get("TOTAL_TERM") != null ? String.valueOf(m.get("TOTAL_TERM")) : null);
				recovery.setSubjectNo(m.get("SUBJECT_NO") != null ? (String) m.get("SUBJECT_NO") : null);
				recovery.setLoanTerm(m.get("LOAN_TERM") != null ? (String) m.get("LOAN_TERM") : null);
				recovery.setCreatTime(m.get("CREATE_TIME") != null ? (Date) m.get("CREATE_TIME") : null);
				
				recoverys.add(recovery);
			}			
			
	    }
		
		return recoverys;
	}
	
	
}
