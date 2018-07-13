package com.kejin.extract.domainservice.construct.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.kejin.extract.domainservice.construct.NewInvestInfoConstruct;
import com.kejin.extract.domainservice.extract.NewInvestInfoService;
import com.kejin.extract.entity.kejinTest.DNewInvestModel;
import com.kejin.extract.kejin.process.dao.DNewInvestDao;

/**
 * @Author Leo
 */
@Service("newInvestInfoConstruct")
public class NewInvestInfoConstructImpl implements NewInvestInfoConstruct {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private DNewInvestDao dNewInvestDao;
    @Resource(name="newInvestInfoService")
    private NewInvestInfoService newInvestInfoService;

    @Override
    public int constructNewInvest(Date recordBeginDatetime, Date recordEndDatetime) {
        int continueRead = 1;
        int handlerCount = 0;

        while (continueRead >= 0) {

            List<DNewInvestModel> investInfos = newInvestInfoService
                    .readFromBidOrder(recordBeginDatetime, recordEndDatetime,
                            continueRead);

            if (investInfos.size() > 0) {
                Set<String> bidOrderNos = new HashSet<String>();
                for (DNewInvestModel invest : investInfos) {
                    bidOrderNos.add(invest.getBidOrderNo());
                }

                Map<String, Object> parameter = new HashMap<String, Object>();
                parameter.put("bidOrderNos", bidOrderNos);
                List<DNewInvestModel> oldInvests = dNewInvestDao
                        .select(parameter);

                bidOrderNos.clear();
                for (DNewInvestModel oldInvest : oldInvests) {
                    bidOrderNos.add(oldInvest.getBidOrderNo());
                }

                for (DNewInvestModel invest : investInfos) {
                    if (bidOrderNos.contains(invest.getBidOrderNo())) {
                        logger.info("更新投资记录"
                                + JSON.toJSONString(new ArrayList<DNewInvestModel>()
                                .add(invest)));
                        dNewInvestDao.update(invest);
                    } else {
                        logger.info("插入投资记录"
                                + JSON.toJSONString(new ArrayList<DNewInvestModel>()
                                .add(invest)));
                        dNewInvestDao.insert(invest);
                    }
                }
                handlerCount=handlerCount+investInfos.size();
                continueRead++;
            } else {
                continueRead = -1;
            }
        }

        return handlerCount;
    }
}
