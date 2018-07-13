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
import com.kejin.extract.domainservice.construct.CurrentRecoveryInfoConstruct;
import com.kejin.extract.domainservice.extract.CurrentRecoveryInfoService;
import com.kejin.extract.entity.kejinTest.DCurrentRecoveryModel;
import com.kejin.extract.kejin.process.dao.DCurrentRecoveryDao;

@Service("currentRecoveryInfoConstruct")
public class CurrentRecoveryInfoConstructImpl implements CurrentRecoveryInfoConstruct {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DCurrentRecoveryDao dCurrentRecoveryDao;
	
	@Resource(name="currentRecoveyInfoService")
	private CurrentRecoveryInfoService currentRecoveyInfoService;

	/* (non-Javadoc)
	 * @see com.kejin.extract.domainservice.construct.impl.CurrentRecoveryInfoConstruct#constructRegularRecovery(java.util.Date, java.util.Date)
	 */
	@Override
	@Transactional
	public int constructRegularRecovery(Date recordBeginDatetime ,Date recordEndDatetime){
		int continueRead = 1;
		int handlerCount = 0;

		while (continueRead >= 0) {

			List<DCurrentRecoveryModel> recoveryInfos = currentRecoveyInfoService
					.readFromRecovry(recordBeginDatetime, recordEndDatetime, continueRead);
			if (recoveryInfos.size() > 0) {
				Set<String> recoveryOrderDetailNos = new HashSet<String>();
				for (DCurrentRecoveryModel recovery : recoveryInfos) {
					recoveryOrderDetailNos.add(recovery
							.getRecoveryOrderDetailNo());
				}

				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("recoveryOrderDetailNos", recoveryOrderDetailNos);
				List<DCurrentRecoveryModel> oldRecoverys = dCurrentRecoveryDao.select(parameter);

				recoveryOrderDetailNos.clear();
				for (DCurrentRecoveryModel oldRecovery : oldRecoverys) {
					recoveryOrderDetailNos.add(oldRecovery
							.getRecoveryOrderDetailNo());
				}

				for (DCurrentRecoveryModel recovery : recoveryInfos) {
					if (recoveryOrderDetailNos.contains(recovery
							.getRecoveryOrderDetailNo())) {
						logger.info("更新活期投资记录"
								+ JSON.toJSONString(new ArrayList<DCurrentRecoveryModel>()
										.add(recovery)));
						dCurrentRecoveryDao.update(recovery);
					} else {
						logger.info("插入活期投资记录"
								+ JSON.toJSONString(new ArrayList<DCurrentRecoveryModel>()
										.add(recovery)));
						dCurrentRecoveryDao.insert(recovery);
					}
				}
				handlerCount=handlerCount+recoveryInfos.size();
				continueRead++;
			} else {
				continueRead = -1;
			}
		}
		return handlerCount;
    }

}
