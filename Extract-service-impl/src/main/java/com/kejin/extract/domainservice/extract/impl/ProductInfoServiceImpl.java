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
import com.kejin.extract.domainservice.extract.ProductInfoService;
import com.kejin.extract.entity.kejinTest.DProductModel;
import com.kejin.extract.mmmoney.reader.dao.DProductReaderDao;

@Service("productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService  {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DProductReaderDao dProductReaderDao;
	
	
	/* (non-Javadoc)
	 * @see com.kejin.extract.domainservice.extract.impl.ProductInfoService#readFromInvest(java.util.Date, java.util.Date)
	 */
	@Override
	public List<DProductModel> readFromInvest(Date beginTime, Date endTime,int continueRead){
		int pageSize = 100;

		List<DProductModel> products = new ArrayList<DProductModel>();
		
		logger.info("开始抽取产品的信息，时间段为："+beginTime+" 至    "+endTime);
		
		PageHelper.startPage(continueRead, pageSize);
		List<Map<String, Object>> productInfos = dProductReaderDao
				.readFromInvest(beginTime, endTime);

		if (productInfos.size() > 0) {
			for(Map<String,Object> m : productInfos){
				DProductModel product = new DProductModel();
				product.setMemberId(m.get("SUBMIT_MEMBER_ID")!=null?(String)m.get("SUBMIT_MEMBER_ID"):null);
				product.setName(m.get("AUTH_NAME")!=null?(String)m.get("AUTH_NAME"):null);
				product.setCompanyName(m.get("COMPANY_NAME")!=null?(String)m.get("COMPANY_NAME"):null);
				product.setPhone(m.get("phone")!=null?(String)m.get("phone"):null);
				product.setMail(m.get("mail")!=null?(String)m.get("mail"):null);
				product.setLoanProjectNo(m.get("APPLY_NO")!=null?(String)m.get("APPLY_NO"):null);
				product.setProjectNo(m.get("SUBJECT_NO")!=null?(String)m.get("SUBJECT_NO"):null);
				product.setProjectName_loan(m.get("AGREEMENT_NAME")!=null?(String)m.get("AGREEMENT_NAME"):null);
				product.setProjectName_invest(m.get("SUBJECT_NAME")!=null?(String)m.get("SUBJECT_NAME"):null);
				product.setRate(m.get("REWARD_RATE")!=null?(BigDecimal)m.get("REWARD_RATE"):null);
				product.setLoanTerm(m.get("LOAN_TERM")!=null?(String)m.get("LOAN_TERM"):null);
				product.setRepayType(m.get("REPAY_TYPE")!=null?(String)m.get("REPAY_TYPE"):null);
				product.setApplyAmount(m.get("APPLY_AMOUNT")!=null?(BigDecimal)m.get("APPLY_AMOUNT"):null);
				product.setBiddedAmount(m.get("BIDDED_AMOUNT")!=null?(BigDecimal)m.get("BIDDED_AMOUNT"):null);
				product.setBidableAmount(m.get("BIDDABLE_AMOUNT")!=null?(BigDecimal)m.get("BIDDABLE_AMOUNT"):null);
				product.setStatus(m.get("STATUS")!=null?(String)m.get("STATUS"):null);
				product.setLineDatetime(m.get("CREATE_TIME")!=null?(Date)m.get("CREATE_TIME"):null);
				product.setCarryInterestDatetime(m.get("INTEREST_DATE")!=null?(Date)m.get("INTEREST_DATE"):null);	
				product.setProjectManagerA(m.get("projectManagerA")!=null?(String)m.get("projectManagerA"):null);
				product.setProjectManagerA(m.get("projectManagerB")!=null?(String)m.get("projectManagerB"):null);
				products.add(product);
			}
		}
		logger.info("产品信息是："+JSON.toJSONString(new ArrayList<List<DProductModel>>().add(products)));
		logger.info("结束抽取产品的信息");
		return products;		
		
	}
}
