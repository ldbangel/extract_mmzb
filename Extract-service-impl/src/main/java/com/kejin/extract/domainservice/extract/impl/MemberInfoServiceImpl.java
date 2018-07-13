package com.kejin.extract.domainservice.extract.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.kejin.extract.common.utils.IdCardUtil;
import com.kejin.extract.domainservice.extract.MemberInfoService;
import com.kejin.extract.entity.kejinTest.DAMobileModel;
import com.kejin.extract.entity.kejinTest.DAPlaceModel;
import com.kejin.extract.entity.kejinTest.DUserModel;
import com.kejin.extract.kejin.process.dao.DAMobileDao;
import com.kejin.extract.kejin.process.dao.DAPlaceDao;
import com.kejin.extract.mmmoney.reader.dao.DUserReaderDao;

@Service("memberInfoService")
public class MemberInfoServiceImpl implements MemberInfoService {
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	DUserReaderDao dUserReaderDao;
	@Autowired
	DAMobileDao dAMobileDao;
	@Autowired
	DAPlaceDao dAPlaceDao;
	
	/**
	 * 获取用户的memberid和注册时间
	 */
	public List<DUserModel> readFromMemberInfo(Date beginTime, Date endTime, int continueRead) {
		int pageSize = 1000;

		List<DUserModel> users = new ArrayList<DUserModel>();
		logger.info("开始抽取memberInfo的信息，时间段为："+beginTime+" 至    "+endTime);

		PageHelper.startPage(continueRead, pageSize);
		List<Map<String, Object>> memberInfos = dUserReaderDao.readFromMemberInfo(beginTime, endTime);

		if (memberInfos.size() > 0) {
			for (Map<String, Object> m : memberInfos) {
				DUserModel user = new DUserModel();

				String memberId = m.get("MEMBER_ID") != null ? (String) m.get("MEMBER_ID") : null;
				Date creatTime = m.get("CREATE_TIME") != null ? (Date) m.get("CREATE_TIME") : null;

				user.setMemberId(memberId);
				user.setMemberType(3);
				user.setRegisterDatetime(creatTime);

				users.add(user);
			}
		}
		logger.info("结束抽取memberInfo的信息");
		return users;
	}

	/**
	 * 获取用户是投资用户还是融资用户
	 */
	/*public List<DUserModel> readFromLoan(Date beginTime, Date endTime, int continueRead) {
		int pageSize = 100;
		int offset = 0+continueRead*pageSize;

		List<DUserModel> users = new ArrayList<DUserModel>();
		logger.info("开始抽取loan的信息，时间段为：{0} 至    {1}", beginTime, endTime);

		List<Map<String, Object>> memberInfos = dUserReaderDao
				.readFromLoan(beginTime, endTime, offset, pageSize);

		if (memberInfos.size() > 0) {
			for (Map<String, Object> m : memberInfos) {
				DUserModel user = new DUserModel();

				String memberId = m.get("SUBMIT_MEMBER_ID") != null ? (String) m.get("SUBMIT_MEMBER_ID") : null;
				user.setMemberId(memberId);
				user.setMemberType(1);
				users.add(user);
			}
		}
		logger.info("结束抽取loan的信息");
		return users;
	}*/

	/**
	 * 获取对应用户的银行信息
	 */
	public List<DUserModel> readFromMemberBank(Date beginTime, Date endTime, int continueRead) {
		int pageSize = 1000;

		List<DUserModel> users = new ArrayList<DUserModel>();

		logger.info("开始抽取MemberBank的信息，时间段为："+beginTime+" 至   "+endTime);

		PageHelper.startPage(continueRead, pageSize);
		List<Map<String, Object>> memberInfos = dUserReaderDao.readFromMemberBank(beginTime, endTime);

		if (memberInfos.size() > 0) {
			for (Map<String, Object> m : memberInfos) {

				DUserModel user = new DUserModel();
				String memberId = m.get("memberId") != null ? (String) m.get("memberId") : null;
				String accountName = m.get("bankAccountName") != null ? (String) m.get("bankAccountName") : null;
				String authName = m.get("authName") != null ? (String) m.get("authName") : null;
				String accountNo = m.get("bankCardNo") != null ? (String) m.get("bankCardNo") : null;
				String bankName = m.get("bankName") != null ? (String) m.get("bankName") : null;
				Date tieCarDatetime = m.get("CREAT_TIME") != null ? (Date) m.get("CREAT_TIME") : null;
				String authNo = m.get("idCardNo") != null ? (String) m.get("idCardNo") : null;
				String platformUserNo = m.get("platformUserNo") != null ? (String) m.get("platformUserNo") : null;
				String userRole = m.get("userRole") != null ? (String) m.get("userRole") : null;
				String isActivate = m.get("isActive") != null ? (String) m.get("isActive") : null;

				user.setMemberId(memberId);
				user.setBankCardAcountName(accountName);
				user.setBankCardNum(accountNo);
				user.setBankCardHeadOffice(bankName);
				user.setTieCarDatetime(tieCarDatetime);
				user.setCertNum(authNo);
				user.setPlatformUserNo(platformUserNo);
				user.setUserRole(userRole);
				user.setIsActivate(isActivate);
				user.setName(authName);
				
				// 需要分析身份证号确定性别年龄等
				IdCardUtil idCard = new IdCardUtil(authNo);

				if (idCard.validate()) {
					if (idCard.isFemal()) {
						user.setGender(1);
					} else {
						user.setGender(0);
					}
					user.setAge(idCard.getBirthDate());

					String adressCode = idCard.getAddressCode();

					Map<String, Object> parameter = new HashMap<String, Object>();
					parameter.put("num", adressCode);

					List<DAPlaceModel> places = dAPlaceDao.select(parameter);

					if (places.size() > 0) {
						DAPlaceModel place = places.get(0);
						user.setNativeProvince(place.getProvince());
						user.setNativeCity(place.getCity());
						user.setNativeArea(place.getArea());
					}
				}
				users.add(user);
			}
		}
		logger.info("结束抽取MemberBank的信息");
		return users;
	}


	/**
	 * 获取用户是手机号、渠道码等信息用户
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<DUserModel> readFromMemberIdentity(Date beginTime, Date endTime, int continueRead) {
		int pageSize = 1000;

		List<DUserModel> users = new ArrayList<DUserModel>();

		logger.info("开始抽取MemberIdentity的信息，时间段为："+beginTime+" 至    "+endTime);

		PageHelper.startPage(continueRead, pageSize);
		List<Map<String, Object>> memberInfos = dUserReaderDao.readFromMemberIdentity(beginTime, endTime);

		if (memberInfos.size() > 0) {
			for (Map<String, Object> m : memberInfos) {
				DUserModel user = new DUserModel();

				String memberId = m.get("MEMBER_ID") != null ? (String) m.get("MEMBER_ID") : null;
				String type = m.get("IDENTITY_TYPE") != null ? m.get("IDENTITY_TYPE").toString() : null;
				String info = m.get("identity") != null ? (String) m.get("identity") : null;

				user.setMemberId(memberId);

				if ("1".equals(type)) {
					user.setPhone(info);
				}
				if ("2".equals(type)) {
					user.setPhone(info);

					// 需要根据手机号分析对应的手机号归属地
					if(info.length()>7){
						String num = info.substring(0, 7);
						Map<String, Object> parameter = new HashMap<String, Object>();
						parameter.put("num", num);
	
						List<DAMobileModel> mobiles = dAMobileDao.select(parameter);
	
						if (mobiles.size() > 0) {
							DAMobileModel mobile = mobiles.get(0);
							user.setPhoneCity(mobile.getCity());
							user.setPhoneProvince(mobile.getProvince());
						}
					}else{
						logger.info(memberId +"注册的手机号不正确");
					}
				}
				if ("3".equals(type)) {
					user.setFriendCode(info);
				}
				users.add(user);
			}
		}
		logger.info("结束抽取MemberIdentity的信息");
		return users;
	}

	/**
	 * 获取首次投资的记录
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<DUserModel> readFromInvestBid(Date beginTime, Date endTime, int continueRead) {
		int pageSize = 1000;

		List<DUserModel> users = new ArrayList<DUserModel>();

		logger.info("开始抽取InvestBid的信息，时间段为："+beginTime+" 至    "+endTime);

		PageHelper.startPage(continueRead, pageSize);
		List<Map<String, Object>> memberInfos = dUserReaderDao.readFromInvestBid(beginTime, endTime);

		if (memberInfos.size() > 0) {
			for (Map<String, Object> m : memberInfos) {
				DUserModel user = new DUserModel();

				String memberId = m.get("MEMBER_ID") != null ? (String) m.get("MEMBER_ID") : null;
				BigDecimal amount = m.get("AMOUNT") != null ? (BigDecimal) m.get("AMOUNT") : null;
				Date investDate = m.get("MODIFIED_TIME") != null ? (Date) m.get("MODIFIED_TIME") : null;

				user.setMemberId(memberId);
				user.setFirstInvestDatetime(investDate);
				user.setFirstInvestAmount(amount);

				users.add(user);
			}
		}
		logger.info("结束抽取InvestBid的信息");
		return users;
	}

	/**
	 * 获取朋友码的记录
	 * 
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<DUserModel> readFromVerify(Date beginTime, Date endTime, int continueRead) {
		int pageSize = 1000;

		List<DUserModel> users = new ArrayList<DUserModel>();

		logger.info("开始抽取Verify的信息，时间段为："+beginTime+" 至   "+endTime);

		PageHelper.startPage(continueRead, pageSize);
		List<Map<String, Object>> memberInfos = dUserReaderDao.readFromVerify(beginTime, endTime);

		if (memberInfos.size() > 0) {
			for (Map<String, Object> m : memberInfos) {
				DUserModel user = new DUserModel();

				String memberId = m.get("MEMBER_ID") != null ? (String) m.get("MEMBER_ID") : null;
				String friendCode = m.get("VERIFY_ENTITY") != null ? (String) m.get("VERIFY_ENTITY") : null;

				user.setMemberId(memberId);
				user.setRecommandFriendCode(friendCode);

				users.add(user);
			}
		}
		logger.info("结束抽取Verify的信息");
		return users;
	}
		
	
	public void combineUsers(HashMap<String,DUserModel > memToUser,List<DUserModel> users){
		for(DUserModel user : users){
			String memberId = user.getMemberId();			
			DUserModel memUser = memToUser.get(memberId);	
			
			if(memUser==null){
				memUser = user ;
				memToUser.put(memberId,memUser);
			}else{				
				combinUser(memUser,user);				
			}
		}
	}
	
	public DUserModel combinUser(DUserModel memUser , DUserModel user ){
		
		if(user.getMemberType()!=null){
			memUser.setMemberType(user.getMemberType());
		}
		
		if(user.getRegisterDatetime()!=null){
			memUser.setRegisterDatetime(user.getRegisterDatetime());
		}
		
		if(user.getCertDatetime()!=null){
			memUser.setCertDatetime(user.getCertDatetime());
		}
		
		if(user.getTieCarDatetime()!=null){
			memUser.setTieCarDatetime(user.getTieCarDatetime());
		}
		
		if(user.getFirstInvestDatetime()!=null){
			memUser.setFirstInvestDatetime(user.getFirstInvestDatetime());
		}
		
		if(user.getFirstInvestAmount()!=null){
			memUser.setFirstInvestAmount(user.getFirstInvestAmount());
		}
		
		if(user.getFirstInvestDatetimeOfCurrent()!=null){
			memUser.setFirstInvestDatetimeOfCurrent(user.getFirstInvestDatetimeOfCurrent());
		}
		
		if(user.getFirstInvestAmountOfCurrent()!=null){
			memUser.setFirstInvestAmountOfCurrent(user.getFirstInvestAmountOfCurrent());
		}
		
		if(user.getFinancialManager()!=null){
			memUser.setFinancialManager(user.getFinancialManager());
		}
		
		if(user.getFriendCode()!=null){
			memUser.setFriendCode(user.getFriendCode());
		}
		
		if(user.getRecommander()!=null){
			memUser.setRecommander(user.getRecommander());
		}
		
		if(user.getRecommandFriendCode()!=null){
			memUser.setRecommandFriendCode(user.getRecommandFriendCode());
		}
		
		if(user.getName()!=null){
			memUser.setName(user.getName());
		}
		
		if(user.getCertNum()!=null){
			memUser.setCertNum(user.getCertNum());
		}
		
		if(user.getNativeProvince()!=null){
			memUser.setNativeProvince(user.getNativeProvince());
		}
		
		if(user.getNativeCity()!=null){
			memUser.setNativeCity(user.getNativeCity());
		}
				
		if(user.getNativeArea()!=null){
			memUser.setNativeArea(user.getNativeArea());
		}
		
		if(user.getAge()!=null){
			memUser.setAge(user.getAge());
		}
		
		if(user.getGender()!=null){
			memUser.setGender(user.getGender());
		}
		
		if(user.getPhone()!=null){
			memUser.setPhone(user.getPhone());
		}
		
		if(user.getPhoneProvince()!=null){
			memUser.setPhoneProvince(user.getPhoneProvince());
		}
		
		if(user.getPhoneCity()!=null){
			memUser.setPhoneCity(user.getPhoneCity());
		}
		
		if(user.getBankCardAcountName()!=null){
			memUser.setBankCardAcountName(user.getBankCardAcountName());
		}
		
		if(user.getBankCardBranchOffice()!=null){
			memUser.setBankCardBranchOffice(user.getBankCardBranchOffice());
		}
		
		if(user.getBankCardCity()!=null){
			memUser.setBankCardCity(user.getBankCardCity());
		}
		
		if(user.getBankCardHeadOffice()!=null){
			memUser.setBankCardHeadOffice(user.getBankCardHeadOffice());
		}
		
		if(user.getBankCardNum()!=null){
			memUser.setBankCardNum(user.getBankCardNum());
		}
		
		if(user.getBankCardProvince()!=null){
			memUser.setBankCardProvince(user.getBankCardProvince());
		}
		
		if(user.getPlatformUserNo()!=null){
			memUser.setPlatformUserNo(user.getPlatformUserNo());
		}
		
		if(user.getUserRole()!=null){
			memUser.setUserRole(user.getUserRole());
		}
		
		if(user.getIsActivate()!=null){
			memUser.setIsActivate(user.getIsActivate());
		}
		
		return memUser;		
	}
}
