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
import com.kejin.extract.domainservice.construct.CreditAssignmentInfoConstruct;
import com.kejin.extract.domainservice.extract.CreditAssignmentInfoService;
import com.kejin.extract.entity.kejinTest.DCreditAssigmentModel;
import com.kejin.extract.kejin.process.dao.DCreditAssignmentDao;

@Service("creditAssignmentInfoConstruct")
public class CreditAssignmentInfoConstructImpl implements CreditAssignmentInfoConstruct    {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DCreditAssignmentDao dCreditAssignmentDao;
	@Resource(name="creditAssignmentInfoService")
	private CreditAssignmentInfoService creditAssignmentInfoService;

	/* (non-Javadoc)
	 * @see com.kejin.extract.domainservice.construct.impl.CreditAssignmentInfoConstruct#constructCreditAssignment(java.util.Date, java.util.Date)
	 */
	@Override
	@Transactional
	public int  constructCreditAssignment(Date recordBeginDatetime ,Date recordEndDatetime){
		int continueRead = 1;
		int handlerCount = 0;

		while (continueRead >= 0) {
	    	List<DCreditAssigmentModel>  assignmentInfos = creditAssignmentInfoService
	    			.readFromCreditAssignment(recordBeginDatetime, recordEndDatetime,continueRead);
	    	
	    	if(assignmentInfos.size()>0){
		    	Set<String> bidOrderNos = new HashSet<String>();
		    	for(DCreditAssigmentModel assignment : assignmentInfos){
		    		bidOrderNos.add(assignment.getBidOrderNo());
		    	}  
		    	
		    	if(bidOrderNos.size()>0){    	
			    	Map<String, Object> parameter = new HashMap<String, Object>();
					parameter.put("bidOrderNos", bidOrderNos);
					List<DCreditAssigmentModel> oldAssignments = dCreditAssignmentDao.select(parameter);
			    	
					bidOrderNos.clear();
			    	for(DCreditAssigmentModel oldAssignment : oldAssignments){
			    		bidOrderNos.add(oldAssignment.getBidOrderNo());
			    	}
		    	}
		    	
		    	for(DCreditAssigmentModel assignment : assignmentInfos){
		    		if (bidOrderNos.contains(assignment.getBidOrderNo())) {
						logger.info("更新活期投资记录"+JSON.toJSONString(new ArrayList<DCreditAssigmentModel>().add(assignment)));
						dCreditAssignmentDao.update(assignment);
					} else {
						logger.info("插入活期投资记录"+JSON.toJSONString(new ArrayList<DCreditAssigmentModel>().add(assignment)));
						dCreditAssignmentDao.insert(assignment);
					}
		    	} 
		    	
		    	handlerCount =handlerCount+assignmentInfos.size();
		    	continueRead++;
	    	}else{
	    		continueRead =-1;
	    	}
		}
		return handlerCount;
    }

}
