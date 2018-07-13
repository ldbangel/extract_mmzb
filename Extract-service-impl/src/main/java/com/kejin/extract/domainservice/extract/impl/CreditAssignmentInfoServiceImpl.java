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
import com.kejin.extract.domainservice.extract.CreditAssignmentInfoService;
import com.kejin.extract.entity.kejinTest.DCreditAssigmentModel;
import com.kejin.extract.mmmoney.reader.dao.DCreditAssignmentReaderDao;

@Service("creditAssignmentInfoService")
public class CreditAssignmentInfoServiceImpl implements CreditAssignmentInfoService  {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DCreditAssignmentReaderDao dCreditAssignmentReaderDao;
	
	/* (non-Javadoc)
	 * @see com.kejin.extract.domainservice.extract.impl.ChargeInfoService#readFromPayment(java.util.Date, java.util.Date)
	 */
	@Override
	public List<DCreditAssigmentModel> readFromCreditAssignment(Date beginTime, Date endTime,int continueRead){
		int pageSize = 100;
		
		List<DCreditAssigmentModel> assignments = new ArrayList<DCreditAssigmentModel>();

		logger.info("开始抽取债权转让的信息，时间段为："+beginTime+" 至   "+endTime);

		PageHelper.startPage(continueRead, pageSize);
		List<Map<String, Object>> creditAssignmentInfos = dCreditAssignmentReaderDao
				.readFromCreditAssignment(beginTime, endTime);
		
		if (creditAssignmentInfos.size() > 0) {
			for (Map<String, Object> m : creditAssignmentInfos) {
				DCreditAssigmentModel assignment = new DCreditAssigmentModel();

				assignment.setBidOrderNo(m.get("BID_ORDER_NO") != null ? (String) m.get("BID_ORDER_NO") : null);
				assignment.setAssignmentId(m.get("assignmentId") != null ? (String) m.get("assignmentId") : null);
				assignment.setAssignmentName(m.get("assignmentName") != null ? (String) m.get("assignmentName") : null);
				assignment.setTransferId(m.get("transferId") != null ? (String) m.get("transferId") : null);
				assignment.setTransferName(m.get("transferName") != null ? (String) m.get("transferName") : null);
				assignment.setAssignAmount(m.get("assignAmount") != null ? (BigDecimal) m.get("assignAmount") : null);
				assignment.setPayAmount(m.get("payAmount") != null ? (BigDecimal) m.get("payAmount") : null);				
				assignment.setPayCredit(m.get("payCredit") != null ? (BigDecimal) m.get("payCredit"):null);
				assignment.setPayPremium(m.get("payPremium") != null ? (BigDecimal) m.get("payPremium"):null);
				assignment.setPayDate(m.get("payDate") != null ? (Date) m.get("payDate") : null);
				assignment.setSubjectNo(m.get("subjectNo") != null ? (String) m.get("subjectNo") : null);
				assignment.setSubjectName(m.get("subjectName") != null ? (String) m.get("subjectName") : null);
				assignment.setSubjectLife(m.get("subjectLife") != null ? (String) m.get("subjectLife") : null);
				assignment.setRate(m.get("rate") != null ? (BigDecimal) m.get("rate") : null);
				assignment.setRepayType(m.get("repayType") != null ? (String) m.get("repayType") : null);
				assignment.setAssignAllAmount(m.get("assignAllAmount") != null ? (BigDecimal) m.get("assignAllAmount") : null);
				assignment.setMinAmount(m.get("minAmount") != null ? (BigDecimal) m.get("minAmount") : null);
				assignment.setDiscount(m.get("discount") != null ? (BigDecimal) m.get("discount") : null);
				assignment.setAssignBeginDate(m.get("assignBeginDate") != null ? (Date) m.get("assignBeginDate") : null);
				assignment.setAssignEndDate(m.get("assignEndDate") != null ? (Date) m.get("assignEndDate") : null);
				assignment.setPayFee(m.get("payFee") != null ? (BigDecimal) m.get("payFee") : null);
				assignments.add(assignment);
			}

		}
		logger.info("债权转让信息是："
				+ JSON.toJSONString(new ArrayList<List<DCreditAssigmentModel>>()
						.add(assignments)));
		logger.info("结束抽取债权转让的信息");
		return assignments;
		
	}
	

	
}
