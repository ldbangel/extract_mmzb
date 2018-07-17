package com.kejin.extract.logdata.service.dao;

import java.util.List;
import java.util.Map;

public interface AccessLogDao {
	public List<Map<String,Object>> getLogUrlStatistics();
}
