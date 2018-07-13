package com.kejin.extract.domainservice.construct.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kejin.extract.domainservice.construct.ChannelInfoConstruct;
import com.kejin.extract.domainservice.extract.ChannelInfoService;
import com.kejin.extract.entity.kejinTest.DChannelModel;
import com.kejin.extract.kejin.process.dao.DChannelDao;

@Service("channelInfoConstruct")
public class ChannelInfoConstructImpl implements ChannelInfoConstruct {
	@Resource(name="channelInfoService")
	private ChannelInfoService channelInfoService;
	@Autowired
	private DChannelDao dChannelDao;

	@Override
	public int constructChannel(Date recordBeginDatetime, Date recordEndDatetime) {
		int continueRead = 1;
		int handlerCount = 0;

		while (continueRead >= 0) {
			List<DChannelModel> channelList = 
					channelInfoService.readChannelFromEmployee(recordBeginDatetime, recordEndDatetime, continueRead);
			if(channelList.size() > 0){
				int result = dChannelDao.insertList(channelList);
				handlerCount = handlerCount + channelList.size();
				continueRead++;
			}else{
				continueRead = -1;
			}
		}
		return handlerCount;
	}

}
