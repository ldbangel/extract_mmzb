package com.kejin.extract.domainservice.extract;

import java.util.Date;
import java.util.List;

import com.kejin.extract.entity.kejinTest.DNewInvestModel;

/**
 * @Author Leo
 */
public interface NewInvestInfoService {
    public abstract List<DNewInvestModel> readFromBidOrder(Date beginTime, Date endTime, int continueRead);
}
