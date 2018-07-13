package com.kejin.extract.domainservice.construct.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kejin.extract.domainservice.construct.MemberBalanceInfoConstruct;
import com.kejin.extract.entity.kejinTest.DMemberBalanceModel;
import com.kejin.extract.kejin.process.dao.DMemberBalanceDao;
import com.kejin.extract.mmmoney.reader.dao.DMemberBalanceReaderDao;

@Service("memberBalanceInfoConstruct")
public class MemberBalanceConstructImpl implements MemberBalanceInfoConstruct {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DMemberBalanceReaderDao dMemberBalanceReaderDao;
	@Autowired
	private DMemberBalanceDao dMemberBalanceDao;

	/**
	 * 生成个人资产记录
	 */
	@Override
	@Transactional
	public String memberBalanceConstruct() {
		logger.info("当前时间为："+ new Date());
		List<DMemberBalanceModel> list = dMemberBalanceReaderDao.readBalanceFromInvest();
		logger.info("插入时间开始："+ new Date());
		int result = dMemberBalanceDao.insertMemberBalances(list);
		logger.info("插入记录数为："+result+"-----------插入时间结束为：" + new Date());
		return null;
	}

	@Override
	@Transactional
	public int deleteEmptyMemberBalance(Date date) {
		int result = dMemberBalanceDao.deleteEmptyBalances(date);
		return result;
	}

	/**
	 * 更新个人资产表，更新账户余额
	 */
	@Override
	@Transactional
	public String settleBalanceUpdate() {
		List<DMemberBalanceModel> list = dMemberBalanceReaderDao.readBalanceFromSettle();
		List<String> platformUserNos = dMemberBalanceDao.selectPlatformUserNo();
		StringBuffer sb = new StringBuffer();
		if(platformUserNos != null && platformUserNos.size() > 0){
			for(String string : platformUserNos){
				sb.append(string+"_");
			}
		}
		if(list != null && list.size() > 0){
			for(DMemberBalanceModel model : list){
				if(sb.toString().contains(model.getPlatformUserNo())){
					//更新member_balance
					dMemberBalanceDao.updateBalanceByPlatformUserNo(model);
				}else{
					//插入member_balance
					dMemberBalanceDao.insertMemberBalanceSingle(model);
				}
			}
		}
		return null;
	}
	
}
