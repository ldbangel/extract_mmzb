package com.kejin.extract.domainservice.service;

import java.math.BigDecimal;

public interface ThreadService {
	public BigDecimal exportMemberBalanceExcel();
	
	public void exportMemberBalanceExcel2();
	
	public BigDecimal getAllBorrowersAmount();
}
