package com.kejin.extract.domainservice.construct;

import java.util.Date;

public interface MemberBalanceInfoConstruct {
	public String memberBalanceConstruct();
	
	public int deleteEmptyMemberBalance(Date date);
	
	public String settleBalanceUpdate();
}
