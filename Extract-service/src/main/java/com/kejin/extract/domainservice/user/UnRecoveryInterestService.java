package com.kejin.extract.domainservice.user;

import java.util.Date;

public interface UnRecoveryInterestService {

	/**
	 * 获取未回款的信息
	 * @param memberId
	 * @param begin
	 * @param end
	 * @param page
	 * @return
	 */
	public abstract String getUserUnRecoveryInterest(String memberId,
			Date begin, Date end, Integer page);

}