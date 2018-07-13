package com.kejin.extract.domainservice.user;

import java.util.List;

import com.kejin.extract.entity.kejinTest.DCashRevisitModel;

public interface CashRevisitInfoService {
	public int saveRevisitNote(List<DCashRevisitModel> modelList);
}
