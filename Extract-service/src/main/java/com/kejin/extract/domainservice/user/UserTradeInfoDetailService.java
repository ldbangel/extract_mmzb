package com.kejin.extract.domainservice.user;

import java.util.Date;

public interface UserTradeInfoDetailService {

	/**
	 * 获取用户详细的投资信息
	 * @param memberId
	 * @param begin
	 * @param end
	 * @param page
	 * @return
	 */
	public abstract String getUserInvestInfoDetail(String memberId, Date begin,
			Date end, Integer page);
	
	/**
	 * 获取用户详细的回款记录
	 * @param memberId
	 * @param begin
	 * @param end
	 * @param page
	 * @return
	 */
    public String getUserRecoveryInfoDetail(String memberId,Date begin,Date end,Integer page);
	
    /**
     * 获取对应的充值记录
     * @param memberId
     * @param begin
     * @param end
     * @param page
     * @return
     */
	public String getUseChargeInfoDetail(String memberId,Date begin,Date end,Integer page);
	
	/**
	 * 获取对应的提现记录
	 * @param memberId
	 * @param begin
	 * @param end
	 * @param page
	 * @return
	 */
	public String getUserCashInfoDetail(String memberId,Date begin,Date end,Integer page);
	

}