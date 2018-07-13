package com.kejin.extract.domainservice.user;

public interface UserInfoDetailService {

	/**
	 * 获取用户的基本信息
	 * @return
	 */
	public String getUserBaseInfoDetail(String phone);
	
	
	public String getMemberIdOfUser(String phone);

	/**
	 * 根据mobile去更改financialManager字段的值
	 */
	void updateFinancialManagerByMobile(String mobile, String manager, String userSource);

	/**
	 * 根据memberId去更改financialManager字段的值
	 */
	void updateFinancialManagerByMemberId(String memberId, String manager, String userSource);

	/**
	 * 判断memberId是否存在
	 */
	boolean memberIdExists(String memberId);

	/**
	 * 根据mobile去添加financialManager字段的值
	 */
	void addFinancialManagerByMobile(String mobile, String manager, String userSource);

	/**
	 * 根据memberId去添加financialManager字段的值
	 */
	void addFinancialManagerByMemberId(String memberId, String manager, String userSource);

}