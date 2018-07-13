package com.kejin.extract.domainservice.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kejin.extract.domainservice.common.Excel2CashDetailsUtil;
import com.kejin.extract.domainservice.service.ActionDetailsInfoService;
import com.kejin.extract.kejin.service.dao.ActionDetailsDao;

@Service("actionDetailsInfoService")
public class ActionDetailsInfoServiceImpl implements ActionDetailsInfoService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private ActionDetailsDao actionDetailsDao;

	@Override
	/**
	 * 获取全部提现明细
	 */
	public List<Map<String, Object>> getCashDetails(Date beginTime, Date endTime) {
		
		List<Map<String,Object>> listCashInfo = actionDetailsDao.selectCashDetails(beginTime, endTime);
		if(listCashInfo != null && listCashInfo.size() != 0){
			for (int i = 0; i < listCashInfo.size(); i++) {
				listCashInfo.get(i).put("allAmount", ((BigDecimal)listCashInfo.get(i).get("allAmount")).setScale(0, BigDecimal.ROUND_HALF_UP));
				Map<String,Object> recoveryMapInfo = actionDetailsDao.selectNearestRecoveryDetail(listCashInfo.get(i));
				if(recoveryMapInfo != null){
					listCashInfo.get(i).put("regularAmount", recoveryMapInfo.get("regularAmount"));
					listCashInfo.get(i).put("creditAmount", recoveryMapInfo.get("creditAmount"));
				}else{
					listCashInfo.get(i).put("regularAmount", null);
					listCashInfo.get(i).put("creditAmount", null);
				}
			}
		}
		for (Map<String,Object> cash : listCashInfo) {
			BigDecimal b1 = ((BigDecimal)cash.get("allAmount")).subtract((BigDecimal) (cash.get("regularAmount") != null?cash.get("regularAmount") : new BigDecimal(0))).abs() ;
			BigDecimal b2 = ((BigDecimal)cash.get("allAmount")).subtract((BigDecimal) (cash.get("creditAmount") != null?cash.get("creditAmount") : new BigDecimal(0))).abs() ;
			if(b1.compareTo(b2) == 0){
				cash.put("cashOrigin", "余额提现");
			}else{
				BigDecimal min = b1.compareTo(b2) <= 0 ? b1:b2;
				if(min.compareTo(b1) == 0){
					cash.put("cashOrigin", "定期提现");
				}else if(min.compareTo(b2) == 0){
					cash.put("cashOrigin", "债权提现");
				}
			}
		}
		return listCashInfo;
	}
	
	/**
	 * 大额提现明细(大于1000)
	 */
	@Override
	public List<Map<String, Object>> getLargeCashDetails(Date beginTime, Date endTime) {
		List<Map<String, Object>> listCashInfo = getCashDetails(beginTime,endTime);
		
		List<Map<String,Object>> listCash = new ArrayList<Map<String,Object>>();
		//筛选出大额提现明细
		for(Map<String,Object> cash : listCashInfo){
			if(((BigDecimal) cash.get("allAmount")).compareTo(new BigDecimal(10000)) > 0){
				listCash.add(cash);
			}
		}
		return listCash;
	}

	@Override
	/**
	 * 获取大额投资明细(大于5万)
	 */
	public List<Map<String, Object>> getInvestDetails(Date beginTime, Date endTime) {
		List<Map<String,Object>> listInvestInfo = actionDetailsDao.selectInvestDetails(beginTime, endTime);
		for(Map<String,Object> invest : listInvestInfo){
			//下面两行是为了取整
			invest.put("allAmount", ((BigDecimal)invest.get("allAmount")).setScale(0, BigDecimal.ROUND_HALF_UP));
			invest.put("creditAmount", ((BigDecimal)invest.get("creditAmount")).setScale(0, BigDecimal.ROUND_HALF_UP));
			
			if(((BigDecimal)invest.get("reinvestAmount")).compareTo(new BigDecimal(0)) > 0){
				if(((BigDecimal)invest.get("newAmount")).compareTo(new BigDecimal(0)) == 0){
					invest.put("investType", "老用户复投");
				}else{
					invest.put("investType", "老用户新增");
				}
			}else{
				if(((Date)invest.get("firstInvestDatetime")) != null 
						|| ((Date)invest.get("firstInvestDatetimeOfCurrent")) != null){
					if(((Date)invest.get("firstInvestDatetime")) != null 
							&& (((Date)invest.get("firstInvestDatetime")).getTime() < ((Date)invest.get("operationDate")).getTime())){
						invest.put("investType", "老用户新增");
					}else if(((Date)invest.get("firstInvestDatetimeOfCurrent")) != null 
							&& (((Date)invest.get("firstInvestDatetimeOfCurrent")).getTime() < ((Date)invest.get("operationDate")).getTime())){
						invest.put("investType", "老用户新增");
					}else{
						invest.put("investType", "新用户新增");
					}
				}else{
					invest.put("investType", "新用户新增");
				}
			}
		}
		return listInvestInfo;
	}

	@Override
	/**
	 * 获取大额充值明细(大于5万)
	 */
	public List<Map<String, Object>> getChargeDetails(Date beginTime, Date endTime) {
		List<Map<String,Object>> listChargeInfo = actionDetailsDao.selectChargeDetails(beginTime, endTime);
		for(Map<String,Object> charge : listChargeInfo){
			charge.put("chargeAmount", ((BigDecimal)charge.get("chargeAmount")).setScale(0, BigDecimal.ROUND_HALF_UP));
			charge.put("regularAmount", ((BigDecimal)charge.get("regularAmount")).setScale(0, BigDecimal.ROUND_HALF_UP));
			charge.put("creditAmount", ((BigDecimal)charge.get("creditAmount")).setScale(0, BigDecimal.ROUND_HALF_UP));
			charge.put("balance", ((BigDecimal)charge.get("balance")).setScale(0, BigDecimal.ROUND_HALF_UP));
		}
		return listChargeInfo;
	}

	@Override
	/**
	 * 获取大额回款明细(大于5万)
	 */
	public List<Map<String, Object>> getRecoveryDetails(Date beginTime, Date endTime) {
		List<Map<String,Object>> listRecoveryInfo = actionDetailsDao.selectRecoveryDetails(beginTime, endTime);
		for(Map<String,Object> recovery : listRecoveryInfo){
			//下面几行是取整
			recovery.put("regularRecoveryAmount", ((BigDecimal)recovery.get("regularRecoveryAmount")).setScale(0, BigDecimal.ROUND_HALF_UP));
			recovery.put("cashAmount", ((BigDecimal)recovery.get("cashAmount")).setScale(0, BigDecimal.ROUND_HALF_UP));
			recovery.put("balance", ((BigDecimal)recovery.get("balance")).setScale(0, BigDecimal.ROUND_HALF_UP));
			
			if(recovery.get("cashAmount") != null 
					&& ((BigDecimal) recovery.get("cashAmount")).compareTo(new BigDecimal(0)) > 0){
				recovery.put("reinvestStatus", "已提现");
			}else{
				if(recovery.get("reinvestAmount") != null 
						&& ((BigDecimal) recovery.get("reinvestAmount")).compareTo(new BigDecimal(0)) > 0){
					recovery.put("reinvestStatus", "已复投");
				}else{
					recovery.put("reinvestStatus", "未复投");
				}
			}
		}
		return listRecoveryInfo;
	}

	/**
	 * 导出提现明细的excel表格
	 */
	@Override
	public void exportCashDetailsExcel(Date beginTime, Date endTime) {
		List<Map<String, Object>> listCashInfo = getCashDetails(beginTime, endTime);
		try {
			Excel2CashDetailsUtil.excelUtil(listCashInfo);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Cash_Details提现明细excel报表导出错误！",e);
		}
	}

	

}
