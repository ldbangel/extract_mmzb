package com.kejin.extract.domainservice.extract;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.kejin.extract.entity.kejinTest.DUserModel;

public interface MemberInfoService {
	
	/**
	 * 获取用户的memberid和注册时间
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<DUserModel> readFromMemberInfo(Date beginTime, Date endTime,int continueRead);
	
	/**
	 * 获取用书是投资用户还是融资用户
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	//public List<DUserModel> readFromLoan(Date beginTime, Date endTime,int continueRead);
	
	/**
	 * 获取对用用户的银行信息
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<DUserModel> readFromMemberBank(Date beginTime, Date endTime,int continueRead);

	/**
	 * 获取用书是手机号、渠道码等信息用户
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<DUserModel> readFromMemberIdentity(Date beginTime, Date endTime,int continueRead);

	/**
	 * 获取首次投资的记录
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<DUserModel> readFromInvestBid(Date beginTime, Date endTime,int continueRead);
	
	/**
	 * 获取朋友码的记录
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List<DUserModel> readFromVerify(Date beginTime, Date endTime,int continueRead);	
	
	public void combineUsers(HashMap<String,DUserModel > memToUser,List<DUserModel> users);
	
	public DUserModel combinUser(DUserModel memUser , DUserModel user );

}
