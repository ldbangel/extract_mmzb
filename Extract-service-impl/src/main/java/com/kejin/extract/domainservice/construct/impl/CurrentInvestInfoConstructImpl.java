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
import com.kejin.extract.domainservice.construct.CurrentInvestInfoConstruct;
import com.kejin.extract.domainservice.extract.CurrentInvestInfoService;
import com.kejin.extract.entity.kejinTest.DCurrentInvetModel;
import com.kejin.extract.kejin.process.dao.DCurrentInvestDao;

@Service("currentInvestInfoConstruct")
public class CurrentInvestInfoConstructImpl implements CurrentInvestInfoConstruct   {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DCurrentInvestDao dCurrentInvestDao;
	
	@Resource(name="currentInvestInfoService")
	private CurrentInvestInfoService currentInvestInfoService;

	/* (non-Javadoc)
	 * @see com.kejin.extract.domainservice.construct.impl.CurrentInvestInfoConstruct#constructCurrentInvest(java.util.Date, java.util.Date)
	 */
	@Override
	@Transactional
	public int  constructCurrentInvest(Date recordBeginDatetime ,Date recordEndDatetime){
    	
		int continueRead = 1;
		int handlerCount = 0;

		while (continueRead >= 0) {
		
	    	List<DCurrentInvetModel>  investInfos =currentInvestInfoService.readFromBidOrder(recordBeginDatetime, recordEndDatetime,continueRead);
	    	
	    	if(investInfos.size()>0){
		    	
		    	Set<String> bidOrderNos = new HashSet<String>();
		    	for(DCurrentInvetModel invest : investInfos){
		    		bidOrderNos.add(invest.getBidOrderNo());
		    	}  
		    	
		    	if(bidOrderNos.size()>0){
			    	Map<String, Object> parameter = new HashMap<String, Object>();
					parameter.put("bidOrderNos", bidOrderNos);
					List<DCurrentInvetModel> oldInvests = dCurrentInvestDao.select(parameter);
			    	
					bidOrderNos.clear();
			    	for(DCurrentInvetModel oldInvest : oldInvests){
			    		bidOrderNos.add(oldInvest.getBidOrderNo());
			    	}
		    	}
		    	
		    	for(DCurrentInvetModel invest : investInfos){
		    		if (bidOrderNos.contains(invest.getBidOrderNo())) {
						logger.info("更新活期投资记录"+JSON.toJSONString(new ArrayList<DCurrentInvetModel>().add(invest)));
						dCurrentInvestDao.update(invest);
					} else {
						logger.info("插入活期投资记录"+JSON.toJSONString(new ArrayList<DCurrentInvetModel>().add(invest)));
						dCurrentInvestDao.insert(invest);
					}
		    	} 
		    	handlerCount =handlerCount+investInfos.size();
		    	continueRead++;
	    	}else{
	    		continueRead =-1;
	    	}
	    }
		return handlerCount;
		
    }

}
