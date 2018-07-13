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
import com.kejin.extract.domainservice.construct.RegularRecoveryInfoConstruct;
import com.kejin.extract.domainservice.extract.RegularRecoveyInfoService;
import com.kejin.extract.entity.kejinTest.DRegularRecoveryModel;
import com.kejin.extract.kejin.process.dao.DRegularRecoveryDao;


@Service("regularRecoveryInfoConstruct")
public class RegularRecoveryInfoConstructImpl implements RegularRecoveryInfoConstruct  {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DRegularRecoveryDao dRegularRecoveryDao;
	
	@Resource(name="regularRecoveyInfoService")
	private RegularRecoveyInfoService regularRecoveyInfoService;

	@Transactional
	public int constructRegularRecovery(Date recordBeginDatetime ,Date recordEndDatetime){
    	
		int continueRead1 = 1;
		int continueRead2 = 1;
		int handlerCount = 0;

		while (continueRead1 >= 0) {
			List<DRegularRecoveryModel> recoveryInfos = regularRecoveyInfoService
					.readCreateFromRecovery(recordBeginDatetime, recordEndDatetime, continueRead1);
			
			if (recoveryInfos != null && recoveryInfos.size() > 0) {
				for (DRegularRecoveryModel recovery : recoveryInfos) {
					logger.info("插入活期投资记录"
							+ JSON.toJSONString(new ArrayList<DRegularRecoveryModel>()
									.add(recovery)));
					dRegularRecoveryDao.insert(recovery);
				}
				handlerCount = handlerCount+recoveryInfos.size();
				continueRead1++;
			} else {
				continueRead1 = -1;
			}
		}
		
		
		while(continueRead2 > 0){
			List<DRegularRecoveryModel> recoveryInfos = regularRecoveyInfoService
					.readModifiedFromRecovery(recordBeginDatetime, recordEndDatetime, continueRead1);
			
			if(recoveryInfos != null && recoveryInfos.size() > 0){
				Set<String> recoveryOrderDetailNos = new HashSet<String>();
				for (DRegularRecoveryModel recovery : recoveryInfos) {
					recoveryOrderDetailNos.add(recovery.getRecoveryOrderDetailNo());
				}

				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("recoveryOrderDetailNos", recoveryOrderDetailNos);
				List<DRegularRecoveryModel> oldRecoverys = dRegularRecoveryDao.select(parameter);

				recoveryOrderDetailNos.clear();
				for (DRegularRecoveryModel oldRecovery : oldRecoverys) {
					recoveryOrderDetailNos.add(oldRecovery.getRecoveryOrderDetailNo());
				}
				
				for (DRegularRecoveryModel recovery : recoveryInfos) {
					if (recoveryOrderDetailNos.contains(recovery.getRecoveryOrderDetailNo())) {
						logger.info("更新活期投资记录"
								+ JSON.toJSONString(new ArrayList<DRegularRecoveryModel>()
										.add(recovery)));
						dRegularRecoveryDao.update(recovery);
					}
				}
				handlerCount = handlerCount+recoveryInfos.size();
				continueRead1++;
			} else {
				continueRead1 = -1;
			}
		}
		return handlerCount;
    }

}
