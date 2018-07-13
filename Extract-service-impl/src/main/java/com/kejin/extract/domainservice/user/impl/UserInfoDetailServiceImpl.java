package com.kejin.extract.domainservice.user.impl;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.kejin.extract.domainservice.user.UserInfoDetailService;
import com.kejin.extract.entity.service.UserBaseInfoModel;
import com.kejin.extract.entity.service.util.UserBaseInfoDetail;
import com.kejin.extract.integration.custody.CustodyMemberService;
import com.kejin.extract.kejin.service.dao.UserDetailInfoDao;
import com.kejin.extract.mmmoney.service.dao.InvestDetailDao;
import com.kejin.extract.mmmoney.service.dao.UserBaseInfoDetailDao;
import com.mmzb.custody.shbk.service.request.QueryUserInfoRequest;
import com.mmzb.custody.shbk.service.response.UserInformationResponse;

@Service("userInfoDetailService")
public class UserInfoDetailServiceImpl implements UserInfoDetailService {
	@Autowired
	private UserDetailInfoDao userDetailInfoDao;
	@Autowired
	private UserBaseInfoDetailDao userBaseInfoDetailDao;
	@Autowired
	private InvestDetailDao investDetailDao;
	@Resource(name="custodyMemberService")
	private CustodyMemberService custodyMemberService;
	
	
	/**
	 * 获取用户基本信息的集合
	 */
	@Override
	public String getUserBaseInfoDetail(String phone){
		UserBaseInfoModel baseInfo = userDetailInfoDao.queryUserBaseInfo(phone);
		DecimalFormat format = new DecimalFormat("#.00");
		
		UserBaseInfoDetail user = new UserBaseInfoDetail();
		if(baseInfo!=null){
			
			user.setName(baseInfo.getName());
			user.setAge(baseInfo.getAge());
			if(baseInfo.getGender()!=null){
				if(baseInfo.getGender().equals("0")){
					user.setGender("man");
				}else{
					user.setGender("woman");
				}
			}
			user.setProvince(baseInfo.getNativeCity());
			user.setPhone(baseInfo.getPhone());
			user.setReferee(baseInfo.getOrigin());
			user.setCardBank(baseInfo.getBankCardHeadOffice());
			
			user.setRegisterDatetime(baseInfo.getRegisterDatetime());
			//user.setCertDatetime(baseInfo.getCertDatetime());
			user.setTieCarDatetime(baseInfo.getTieCarDatetime());
			user.setFirstInvestDatetime(baseInfo.getFirstInvestDatetime());
			user.setFirstInvestAmount(baseInfo.getFirstInvestAmount());
			//user.setFirstInvestDatetimeOfCurrent(baseInfo.getFirstInvestDatetimeOfCurrent());
			//user.setFirstInvestAmountOfCurrent(baseInfo.getFirstInvestAmountOfCurrent());
			user.setManagerName(baseInfo.getManagerName() != null?baseInfo.getManagerName():"暂无");
			
			String memberId = baseInfo.getMemberId();
			
			Map<String, BigDecimal>  creditBalance = userBaseInfoDetailDao.getCreditBalance(memberId);
			user.setCredit(creditBalance!=null?creditBalance.get("amount"):new BigDecimal(0));
			
			Map<String, BigDecimal>   interestBalance = userBaseInfoDetailDao.getInterestBalance(memberId);
			BigDecimal unpaidInterest = interestBalance!=null?interestBalance.get("amount"):new BigDecimal(0);
			user.setUnpaidInterest(format.format(unpaidInterest));
			
			//获取账户余额
			QueryUserInfoRequest request = new QueryUserInfoRequest();
	    	request.setMemberId(memberId);
	    	request.setUserRole("INVESTOR");
	    	UserInformationResponse userinfo = custodyMemberService.queryUserInfomation(request);
	    	//这里获取可用余额，账户余额包括冻结金额(未满标)
	    	user.setCashAmount(new BigDecimal(userinfo.getAvailableAmount()==null ? "0" : userinfo.getAvailableAmount()));
	    	//获取冻结金额
	    	user.setBlockedFund(new BigDecimal(userinfo.getBalance()).subtract(new BigDecimal(userinfo.getAvailableAmount())));
			
		    //想加总额
	    	user.setAllAmount(user.getCredit().add(unpaidInterest).add(user.getCashAmount()));
		    
		    //查询累计金额
		    List<Map<String,Object>>  accumulateInvestInfo =  userBaseInfoDetailDao.getAccumulateInvestInfo(memberId);
		    for(Map<String,Object> m : accumulateInvestInfo){
		    	String type = m.get("STATUS")==null ? null : m.get("STATUS").toString();
		    	BigDecimal amount =(BigDecimal) m.get("amonut");
		    	int investNums = Integer.parseInt(m.get("investNums").toString());
		    	if(type!=null && type.equalsIgnoreCase("BID_SUCCESS")){
		    		user.setAccunmulateInvest(amount);
		    		user.setInvestNums(investNums);
		    	}	    	
		    }
		    
		    List<Map<String,Object>>  accumulateRecoveryInfo =  userBaseInfoDetailDao.getAccumulaterRecoveryInfo(memberId);
		    for(Map<String,Object> m : accumulateRecoveryInfo){
		    	String type = m.get("STATUS").toString();
		    	BigDecimal principal =(BigDecimal) m.get("principal");
		    	BigDecimal interest =(BigDecimal) m.get("interest");
		    	
		    	if(type.equalsIgnoreCase("SUCCESS")){
		    		user.setAccunmulateRecoveryPrincipal(principal);
		    		user.setAccunmulateRecoveryInterest(interest);
		    	}		    	
		    }
		    
		    Map<String, BigDecimal> accumulateCash = userDetailInfoDao.getAccumulateCash(memberId);
		    if(accumulateCash!=null){
		    	  user.setAccunmulateCash(accumulateCash.get("cashAmount"));
		    }
		  
		    Map<String, BigDecimal> accumulateCharge= userDetailInfoDao.getAccumulateCharge(memberId);
		    if(accumulateCharge!=null){
		    	user.setAccunmulateCharge(accumulateCharge.get("chargeAmount"));
		    }
		    
			return JSON.toJSONStringWithDateFormat(user,"yyyy-MM-dd HH:mm:ss",SerializerFeature.WriteMapNullValue);
		}
		return null;
	}
	
	
	@Override
	public String getMemberIdOfUser(String phone){
		UserBaseInfoModel baseInfo = userDetailInfoDao.queryUserBaseInfo(phone);
		if(baseInfo != null){
			return baseInfo.getMemberId();
		}
		return null;
		
	}

	@Override
	public void updateFinancialManagerByMobile(String mobile, String manager, String userSource) {
		userDetailInfoDao.updateFinancialManagerByPhone(mobile, manager, userSource);
	}

	@Override
	public void updateFinancialManagerByMemberId(String memberId, String manager, String userSource) {
		userDetailInfoDao.updateFinancialManagerByMemberId(memberId, manager, userSource);
	}

	@Override
	public boolean memberIdExists(String memberId) {
		return userDetailInfoDao.memberIdExists(memberId);
	}

	@Override
	public void addFinancialManagerByMobile(String mobile, String manager, String userSource) {
		userDetailInfoDao.addFinancialManagerByPhone(mobile, manager, userSource);
	}

	@Override
	public void addFinancialManagerByMemberId(String memberId, String manager, String userSource) {
		userDetailInfoDao.addFinancialManagerByMemberId(memberId, manager, userSource);
	}
}
