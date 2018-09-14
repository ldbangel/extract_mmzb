package com.kejin.extract.domainservice.construct.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kejin.extract.domainservice.construct.LatestActionInfoConstruct;
import com.kejin.extract.domainservice.extract.LatestActionInfoService;
import com.kejin.extract.entity.kejinTest.DLatestActionModel;
import com.kejin.extract.kejin.process.dao.DLatestActionDao;

@Service("latestActionInfoConstruct")
public class LatestActionInfoConstructImpl implements LatestActionInfoConstruct {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DLatestActionDao dLatestActionDao;
	@Resource(name="latestActionInfoService")
	private LatestActionInfoService latestActionInfoService;

	@Override
	public int constructLatestAction(Date recordBeginDatetime, Date recordEndDatetime) {
		int continueRead = 0;
		int handlerCount = 0;
		while(continueRead >= 0){
			Map<String, DLatestActionModel> memToLatestAction = new HashMap<String, DLatestActionModel>(); 
			List<DLatestActionModel> latestAction = latestActionInfoService
					.readLatestInvestInfo(recordBeginDatetime, recordEndDatetime, continueRead);
			combineLatestActions(memToLatestAction, latestAction);
			
			latestAction = latestActionInfoService
					.readLatestRecoveryInfo(recordBeginDatetime, recordEndDatetime, continueRead);
			combineLatestActions(memToLatestAction, latestAction);
			
			latestAction = latestActionInfoService
					.readLatestChargeInfo(recordBeginDatetime, recordEndDatetime, continueRead);
			combineLatestActions(memToLatestAction, latestAction);
			
			latestAction = latestActionInfoService
					.readLatestCashInfo(recordBeginDatetime, recordEndDatetime, continueRead);
			combineLatestActions(memToLatestAction, latestAction);
			
			List<DLatestActionModel> insertModels = new ArrayList<DLatestActionModel>();
			List<DLatestActionModel> updateModels = new ArrayList<DLatestActionModel>();
			if(memToLatestAction.keySet()!=null 
					&& memToLatestAction.keySet().size()>0){
				List<String> memberList = dLatestActionDao.selectCustomerRecords(memToLatestAction.keySet());
				for(String memberId : memberList){
					DLatestActionModel model = memToLatestAction.get(memberId);
					updateModels.add(model);
					memToLatestAction.remove(memberId);
				}
				for(String mmeberId : memToLatestAction.keySet()){
					if(mmeberId != null 
							&& memToLatestAction.get(mmeberId)!=null){
						insertModels.add(memToLatestAction.get(mmeberId));
					}
				}
			}
			
			
			
			if(insertModels.size() > 0 || updateModels.size() > 0){
				int result = 0;
				if(insertModels.size() > 0){
					result = dLatestActionDao.insertLatestActionRecord(insertModels);
				}
				if(updateModels.size() > 0){
					result = result+dLatestActionDao.updateLatestActionRecord(updateModels);
				}
				logger.info(new Date()+"----insert into d_latest_action count : " + result);
				handlerCount = handlerCount + insertModels.size() + updateModels.size();
				continueRead++;
			}else{
				continueRead = -1;
			}
		}
		return handlerCount;
	}
	
	public Map<String, DLatestActionModel> combineLatestActions(Map<String, DLatestActionModel> latestActionMap,
			List<DLatestActionModel> modelList){
		for(DLatestActionModel investAction : modelList){
			DLatestActionModel model = latestActionMap.get(investAction.getMemberId());
			if(model != null){
				combineLatestAction(investAction, model);
			}else{
				latestActionMap.put(investAction.getMemberId(), investAction);
			}
		}
		
		return latestActionMap;
	}
	
	
	public DLatestActionModel combineLatestAction(DLatestActionModel latestAction, DLatestActionModel toLatestAction){
		if(latestAction.getLatestInvestAmount()!=null){
			toLatestAction.setLatestInvestAmount(latestAction.getLatestInvestAmount());
		}
		
		if(latestAction.getLatestInvestTime()!=null){
			toLatestAction.setLatestInvestTime(latestAction.getLatestInvestTime());
		}
		
		if(latestAction.getLatestRecoveryAmount()!=null){
			toLatestAction.setLatestRecoveryAmount(latestAction.getLatestRecoveryAmount());
		}
		
		if(latestAction.getLatestRecoveryTime()!=null){
			toLatestAction.setLatestRecoveryTime(latestAction.getLatestRecoveryTime());
		}
		
		if(latestAction.getLatestChargeAmount()!=null){
			toLatestAction.setLatestChargeAmount(latestAction.getLatestChargeAmount());
		}
		
		if(latestAction.getLatestChargeTime()!=null){
			toLatestAction.setLatestChargeTime(latestAction.getLatestChargeTime());
		}
		
		if(latestAction.getLatestCashAmount()!=null){
			toLatestAction.setLatestCashAmount(latestAction.getLatestCashAmount());
		}
		
		if(latestAction.getLatestCashTime()!=null){
			toLatestAction.setLatestCashTime(latestAction.getLatestCashTime());
		}
		
		return toLatestAction;
	}
	
	
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String, String>();
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		map.put("4", "4");
		map.put("5", "5");
		
		map.remove("1");
		System.out.println(map.toString());
	}
	
	

}
