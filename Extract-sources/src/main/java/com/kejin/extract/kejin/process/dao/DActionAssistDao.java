package com.kejin.extract.kejin.process.dao;

import java.util.List;
import java.util.Map;

import com.kejin.extract.entity.kejinTest.DActionAssistModel;


public interface DActionAssistDao {
	
	public List<DActionAssistModel> select(Map<String, Object> parameter);
	
	public int insert(DActionAssistModel dActionAssistModel);
	
	public int update(DActionAssistModel dActionAssistModel);
	
	//d_action_assist表中同步失败的数据
	public List<DActionAssistModel> selectOutputFail(Map<String, Object> parameter);

}
