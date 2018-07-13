package com.kejin.extract.mmmoney.reader.dao;

import java.util.List;

import com.kejin.extract.entity.kejinTest.DMemberBalanceModel;

public interface DMemberBalanceReaderDao {
	public List<DMemberBalanceModel> readBalanceFromInvest();
	
	public List<DMemberBalanceModel> readBalanceFromSettle();
}
