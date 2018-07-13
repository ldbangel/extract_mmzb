package com.kejin.extract.domainservice.extract.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.kejin.extract.domainservice.extract.ChannelInfoService;
import com.kejin.extract.entity.kejinTest.DChannelModel;
import com.kejin.extract.mmmoney.reader.dao.DChannelReaderDao;

@Service("channelInfoService")
public class ChannelInfoServiceImpl implements ChannelInfoService {

	@Autowired
	private DChannelReaderDao dChannelReaderDao;
	
	@Override
	public List<DChannelModel> readChannelFromEmployee(Date beginTime, Date endTime, int continueRead) {
		int pageSize = 100;
		
		PageHelper.startPage(continueRead, pageSize);
		List<DChannelModel> channelList = dChannelReaderDao.readChannelFromEmployee(beginTime, endTime);
		return channelList;
	}

}
