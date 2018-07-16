package com.kejin.extract.domainservice.service;

import java.math.BigDecimal;

public interface ThreadService {
	public BigDecimal exportMemberBalanceExcel();
	
	public BigDecimal getAllBorrowersAmount();
}
