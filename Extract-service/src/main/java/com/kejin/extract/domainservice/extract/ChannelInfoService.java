package com.kejin.extract.domainservice.extract;

import java.util.Date;
import java.util.List;

import com.kejin.extract.entity.kejinTest.DChannelModel;

public interface ChannelInfoService {

	public abstract List<DChannelModel> readChannelFromEmployee(Date beginTime,Date endTime,int continueRead);

}