package com.kejin.extract.domainservice.construct.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.kejin.extract.domainservice.construct.BonusInfoConstruct;
import com.kejin.extract.domainservice.extract.BonusInfoService;
import com.kejin.extract.entity.kejinTest.DBonusModel;
import com.kejin.extract.kejin.process.dao.DBonusDao;

@Service("bonusInfoConstruct")
public class BonusInfoConstructImpl implements BonusInfoConstruct    {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DBonusDao dBonusDao;
	@Resource(name="bonusInfoService")
	private BonusInfoService bonusInfoService;

	@Override
	@Transactional
	public int constructBonus(Date recordBeginDatetime, Date recordEndDatetime) {
		int continueRead = 1;
		int handlerCount = 0;
		
		while (continueRead >= 0) {
			List<DBonusModel> bonusInfos = bonusInfoService
					.readFromDpm(recordBeginDatetime, recordEndDatetime, continueRead);

			if (bonusInfos.size() > 0) {
				logger.info("抽取的奖金记录为"
						+ JSON.toJSONString(new ArrayList<DBonusModel>()
								.addAll(bonusInfos)));

				/*Set<String> voucherNos = new HashSet<String>();
				for (DBonusModel bonus : bonusInfos) {
					voucherNos.add(bonus.getVoucherNo());
				}

				if(voucherNos.size()>0){
					Map<String, Object> parameter = new HashMap<String, Object>();
					parameter.put("voucherNos", voucherNos);
					List<DBonusModel> oldBonuies = dBonusDao.select(parameter);
	
					voucherNos.clear();
					for (DBonusModel oldBonus : oldBonuies) {
						voucherNos.add(oldBonus.getVoucherNo());
					}
				}

				for (DBonusModel bonus : bonusInfos) {
					if (voucherNos.contains(bonus.getVoucherNo())) {
						logger.info("更新提现记录"
								+ JSON.toJSONString(new ArrayList<DBonusModel>()
										.add(bonus)));
						dBonusDao.update(bonus);
					} else {
						logger.info("插入提现记录"
								+ JSON.toJSONString(new ArrayList<DBonusModel>()
										.add(bonus)));
						dBonusDao.insert(bonus);
					}
				}*/
				for (DBonusModel bonus : bonusInfos) {
					logger.info("插入奖金记录"
							+ JSON.toJSONString(new ArrayList<DBonusModel>()
									.add(bonus)));
					dBonusDao.insert(bonus);	
				}
				
				handlerCount = handlerCount +bonusInfos.size();
				continueRead++;
			}else{
				continueRead = -1;
			}
		}
		
		return handlerCount;
	}

}
