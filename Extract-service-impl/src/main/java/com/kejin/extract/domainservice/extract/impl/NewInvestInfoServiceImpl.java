package com.kejin.extract.domainservice.extract.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.kejin.extract.domainservice.extract.NewInvestInfoService;
import com.kejin.extract.entity.kejinTest.DNewInvestModel;
import com.kejin.extract.mmmoney.reader.dao.DNewInvestReaderDao;

/**
 * @Author Leo
 */
@Service("newInvestInfoService")
public class NewInvestInfoServiceImpl implements NewInvestInfoService {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private DNewInvestReaderDao dNewInvestReaderDao;

    @Override
    public List<DNewInvestModel> readFromBidOrder(Date beginTime, Date endTime, int continueRead) {
        int pageSize = 100;

        List<DNewInvestModel> bids = new ArrayList<DNewInvestModel>();

        logger.info("开始抽取投资的信息，时间段为："+beginTime+" 至 "+endTime);
        
        PageHelper.startPage(continueRead, pageSize);
        List<Map<String, Object>> bidInfos = dNewInvestReaderDao
        		.readFromBidOrder(beginTime, endTime);
        
        if (bidInfos.size() > 0) {
            for(Map<String,Object> m : bidInfos){

                DNewInvestModel bid = new DNewInvestModel();

                bid.setBidOrderNo(m.get("BID_ORDER_NO") != null ? (String) m.get("BID_ORDER_NO") : null);
                bid.setMemberId(m.get("MEMBER_ID") != null ? (String) m.get("MEMBER_ID") : null);
                bid.setOperationDate(m.get("CREATE_TIME") != null ? (Date) m.get("CREATE_TIME") : null);
                bid.setStatus(m.get("STATUS") != null ? (String) m.get("STATUS") : null);
                bids.add(bid);
            }
        }
        logger.info("线上定期投资信息是："+ JSON.toJSONString(new ArrayList<List<DNewInvestModel>>().add(bids)));
        logger.info("结束抽取定期投资的信息");

        return bids;
    }
}
