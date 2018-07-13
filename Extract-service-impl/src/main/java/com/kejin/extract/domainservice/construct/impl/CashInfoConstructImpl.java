package com.kejin.extract.domainservice.construct.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.kejin.extract.domainservice.construct.CashInfoConstruct;
import com.kejin.extract.domainservice.extract.CashInfoService;
import com.kejin.extract.entity.kejinTest.DCashModel;
import com.kejin.extract.kejin.process.dao.DCashDao;

@Service("cashInfoConstruct")
public class CashInfoConstructImpl implements CashInfoConstruct   {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DCashDao dCashDao;
	@Resource(name="cashInfoService")
	private CashInfoService cashInfoService;

    @Override
    @Transactional
	public int constructCash(Date recordBeginDatetime, Date recordEndDatetime) {
		int continueRead = 1;
		int handlerCount = 0;

		while (continueRead >= 0) {
			List<DCashModel> cashInfos = cashInfoService
					.readFromPayment(recordBeginDatetime, recordEndDatetime, continueRead);
			if (cashInfos.size() > 0) {
				logger.info("抽取的提现记录为"
						+ JSON.toJSONString(new ArrayList<DCashModel>()
								.addAll(cashInfos)));

				Set<String> paymentOrderNos = new HashSet<String>();
				for (DCashModel cash : cashInfos) {
					paymentOrderNos.add(cash.getPaymentOrderNo());
				}
                
				if(paymentOrderNos.size()>0){
					Map<String, Object> parameter = new HashMap<String, Object>();
					parameter.put("paymentOrderNos", paymentOrderNos);
					List<DCashModel> oldCashs = dCashDao.select(parameter);
	
					paymentOrderNos.clear();
					for (DCashModel oldCash : oldCashs) {
						paymentOrderNos.add(oldCash.getPaymentOrderNo());
					}
				}
				for (DCashModel cash : cashInfos) {
					if (paymentOrderNos.contains(cash.getPaymentOrderNo())) {
						logger.info("更新提现记录"
								+ JSON.toJSONString(new ArrayList<DCashModel>()
										.add(cash)));
						dCashDao.update(cash);
					} else {
						logger.info("插入提现记录"
								+ JSON.toJSONString(new ArrayList<DCashModel>()
										.add(cash)));
						dCashDao.insert(cash);
					}
				}
				handlerCount =handlerCount+cashInfos.size();
				continueRead++;
			}else{
				continueRead = -1;
			}
			
		}
		return handlerCount;
	}

}
