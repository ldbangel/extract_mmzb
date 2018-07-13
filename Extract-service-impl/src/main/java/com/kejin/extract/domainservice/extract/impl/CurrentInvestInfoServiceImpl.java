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
import com.kejin.extract.domainservice.extract.CurrentInvestInfoService;
import com.kejin.extract.entity.kejinTest.DCurrentInvetModel;
import com.kejin.extract.mmmoney.reader.dao.DCurrentInvestReaderDao;

@Service("currentInvestInfoService")
public class CurrentInvestInfoServiceImpl implements CurrentInvestInfoService  {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	 private DCurrentInvestReaderDao dCurrentInvestReaderDao;

	 /* (non-Javadoc)
	 * @see com.kejin.extract.domainservice.extract.impl.CurrentInvestInfoService#readFromBidOrder(java.util.Date, java.util.Date)
	 */
	@Override
	public List<DCurrentInvetModel> readFromBidOrder(Date beginTime,
			Date endTime, int continueRead) {
		int pageSize = 100;

		List<DCurrentInvetModel> bids = new ArrayList<DCurrentInvetModel>();

		logger.info("开始抽取活期投资的信息，时间段为："+beginTime+" 至   "+endTime);

		PageHelper.startPage(continueRead, pageSize);
		List<Map<String, Object>> bidInfos = dCurrentInvestReaderDao
				.readFromBidOrder(beginTime, endTime);

		if (bidInfos.size() > 0) {

			for (Map<String, Object> m : bidInfos) {

				DCurrentInvetModel bid = new DCurrentInvetModel();

				bid.setBidOrderNo(m.get("BID_ORDER_NO") != null ? (String) m
						.get("BID_ORDER_NO") : null);
				bid.setMemberId(m.get("MEMBER_ID") != null ? (String) m
						.get("MEMBER_ID") : null);
				bid.setSubjectNo(m.get("SUBJECT_NO") != null ? (String) m
						.get("SUBJECT_NO") : null);
				bid.setSubjectName(m.get("SUBJECT_NAME") != null ? (String) m
						.get("SUBJECT_NAME") : null);
				bid.setSubjectLife(m.get("LOAN_TERM") != null ? (String) m
						.get("LOAN_TERM") : null);
				bid.setSubjectType(m.get("SUBJECT_TYPE") != null ? (String) m
						.get("SUBJECT_TYPE") : null);
				bid.setRate(m.get("REWARD_RATE") != null ? (BigDecimal) m
						.get("REWARD_RATE") : null);
				bid.setRepayType(m.get("REPAY_TYPE") != null ? (String) m
						.get("REPAY_TYPE") : null);
				bid.setAmount(m.get("AMOUNT") != null ? (BigDecimal) m
						.get("AMOUNT") : null);
				bid.setFee(m.get("fee") != null ? (BigDecimal) m
						.get("fee") : null);
				bid.setOperation(m.get("STATUS") != null ? (String) m
						.get("STATUS") : null);
				bid.setOperationDate(m.get("CREATE_TIME") != null ? (Date) m
						.get("CREATE_TIME") : null);
				bid.setSubjectStatus(null);

				bids.add(bid);
			}

		}
		logger.info("线上活期投资信息是："
				+ JSON.toJSONString(new ArrayList<List<DCurrentInvetModel>>()
						.add(bids)));
		logger.info("结束抽取活期投资的信息");
		return bids;

	}
	
}
