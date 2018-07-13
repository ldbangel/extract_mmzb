package com.kejin.extract.domainservice.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kejin.extract.domainservice.service.PartTimeFinancierAchievementService;
import com.kejin.extract.kejin.service.dao.PartTimeFinancierAchDao;

/**
 * @Author Leo
 */
@Service("partTimeFinancierAchievementService")
public class PartTimeFinancierAchievementServiceImpl implements PartTimeFinancierAchievementService {

	@Autowired
    private PartTimeFinancierAchDao partTimeFinancierAchDao;

    @Override
    public List<Map<String, Object>> getPartTimeFinancierAchievementDayData(Date beginTime, Date endTime) {
        List<Map<String, Object>> financierListMap = partTimeFinancierAchDao.getPartTimeFinancierAchievementInfo(beginTime, endTime);
        return financierListMap;
    }
}
