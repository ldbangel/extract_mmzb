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
import com.kejin.extract.domainservice.construct.ChargeInfoConstruct;
import com.kejin.extract.domainservice.extract.ChargeInfoService;
import com.kejin.extract.entity.kejinTest.DChargeModel;
import com.kejin.extract.kejin.process.dao.DChargeDao;


@Service("chargeInfoConstruct")
public class ChargeInfoConstructImpl implements ChargeInfoConstruct   {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DChargeDao dChargeDao;
	@Resource(name="chargeInfoService")
	private ChargeInfoService chargeInfoService;

    @Override
    @Transactional
	public int constructCharge(Date recordBeginDatetime ,Date recordEndDatetime){
    	int continueReadOnline = 1;
	    int continueReadOffline = 1;
	    
	    int handlerCount = 0;
	    
	    while(continueReadOnline>=0||continueReadOffline>=0){
	    	List<DChargeModel> chargesOnline = new ArrayList<DChargeModel>();
    	    if(continueReadOnline>=0){
				 chargesOnline = chargeInfoService.readFromPayment(
						recordBeginDatetime, recordEndDatetime, continueReadOnline);
    	    }
    	    
    	    if(chargesOnline.size()==0){
    	    	continueReadOnline =-1;
    	    }else{
    	    	continueReadOnline++;
    	    }
	
    	    List<DChargeModel> chargesOffline = new ArrayList<DChargeModel>();
    	    if(continueReadOffline>=0){
			     chargesOffline = chargeInfoService.readFromCounter(
						recordBeginDatetime, recordEndDatetime,continueReadOffline);
    	    }
    	    
    	    if(chargesOffline.size()==0){
    	    	continueReadOffline = -1;
    	    }else{
    	    	continueReadOffline++;
    	    }
	
			chargesOnline.addAll(chargesOffline);
	
			logger.info("抽取的充值记录为"
					+ JSON.toJSONString(new ArrayList<DChargeModel>()
							.addAll(chargesOnline)));
	
			Set<String> requestNos = new HashSet<String>();
			for (DChargeModel charge : chargesOnline) {
				requestNos.add(charge.getRequestNo());
			}
	        
			if(requestNos.size()>0){
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("requestNos", requestNos);
				List<DChargeModel> oldCharges = dChargeDao.select(parameter);
		
				requestNos.clear();
				for (DChargeModel oldCharge : oldCharges) {
					requestNos.add(oldCharge.getRequestNo());
				}
			}
	
			for (DChargeModel charge : chargesOnline) {
				if (requestNos.contains(charge.getRequestNo())) {
					logger.info("更新充值记录"+JSON.toJSONString(new ArrayList<DChargeModel>().add(charge)));
					dChargeDao.update(charge);
				} else {
					logger.info("插入充值记录"+JSON.toJSONString(new ArrayList<DChargeModel>().add(charge)));
					dChargeDao.insert(charge);
				}
			}
			
			handlerCount=handlerCount+chargesOnline.size();
	    }
        return handlerCount;
    }




}
