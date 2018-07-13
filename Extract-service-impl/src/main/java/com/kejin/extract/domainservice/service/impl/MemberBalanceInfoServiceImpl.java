package com.kejin.extract.domainservice.service.impl;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kejin.extract.domainservice.common.ExcelUtil;
import com.kejin.extract.domainservice.service.MemberBalanceInfoService;
import com.kejin.extract.entity.kejinTest.DMemberBalanceModel;
import com.kejin.extract.kejin.process.dao.DMemberBalanceDao;

@Service("memberBalanceInfoService")
public class MemberBalanceInfoServiceImpl implements MemberBalanceInfoService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private DMemberBalanceDao dMemberBalanceDao;

	@Override
	public List<DMemberBalanceModel> getMemberBalanceInfo() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DATE, cal.get(Calendar.DATE)-1);
		List<DMemberBalanceModel> list = dMemberBalanceDao.selectMemberBalanceInfo(cal.getTime());
		try {
			ExcelUtil.excelUtil(list);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Member_Balance个人资产excel报表导出错误！",e);
		}
		return list;
	}

}
