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
import com.kejin.extract.domainservice.construct.RegularInvestInfoConstruct;
import com.kejin.extract.domainservice.extract.RegularInvestInfoService;
import com.kejin.extract.entity.kejinTest.DRegularInvestModel;
import com.kejin.extract.kejin.process.dao.DRegularInvestDao;


@Service("regularInvestInfoConstruct")
public class RegularInvestInfoConstructImpl implements RegularInvestInfoConstruct    {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DRegularInvestDao dRegularInvestDao;
	@Resource(name="regularInvestInfoService")
	private RegularInvestInfoService regularInvestInfoService;

	@Override
	@Transactional
	public int constructRegularInvest(Date recordBeginDatetime ,Date recordEndDatetime){
		
		int continueRead1 = 1;
		int continueRead2 = 1;
		int handlerCount = 0;

		while (continueRead1 >= 0) {
			List<DRegularInvestModel> investCreateInfos = regularInvestInfoService
					.readCreateFromBidOrder(recordBeginDatetime, recordEndDatetime, continueRead1);

			if (investCreateInfos != null && investCreateInfos.size() > 0) {
				for(DRegularInvestModel invest : investCreateInfos){
					logger.info("插入定期投资记录"
							+ JSON.toJSONString(new ArrayList<DRegularInvestModel>()
									.add(invest)));
					dRegularInvestDao.insert(invest);
				}
				
				handlerCount = handlerCount + investCreateInfos.size();
				continueRead1++;
			} else {
				continueRead1 = -1;
			}
		}
		
		while(continueRead2 >= 0){
			List<DRegularInvestModel> investModifiedInfos = regularInvestInfoService
					.readModifiedFromBidOrder(recordBeginDatetime, recordEndDatetime, continueRead2);
			
			if (investModifiedInfos != null && investModifiedInfos.size() > 0) {
				Set<String> bidOrderNos = new HashSet<String>();
				for (DRegularInvestModel invest : investModifiedInfos) {
					bidOrderNos.add(invest.getBidOrderNo());
				}
				
				Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("bidOrderNos", bidOrderNos);
				List<DRegularInvestModel> oldInvests = dRegularInvestDao.select(parameter);
				
				bidOrderNos.clear();
				for (DRegularInvestModel oldInvest : oldInvests) {
					bidOrderNos.add(oldInvest.getBidOrderNo());
				}
				
				for (DRegularInvestModel invest : investModifiedInfos) {
					if (bidOrderNos.contains(invest.getBidOrderNo())) {
						logger.info("更新活期投资记录"
								+ JSON.toJSONString(new ArrayList<DRegularInvestModel>()
										.add(invest)));
						dRegularInvestDao.update(invest);
					}
				}
				
				handlerCount = handlerCount + investModifiedInfos.size();
				continueRead2++;
			}else{
				continueRead2 = -1;
			}
		}
		
		return handlerCount;
    }

}
