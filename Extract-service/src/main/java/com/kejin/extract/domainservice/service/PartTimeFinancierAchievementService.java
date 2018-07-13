package com.kejin.extract.domainservice.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PartTimeFinancierAchievementService {
    public List<Map<String, Object>> getPartTimeFinancierAchievementDayData(Date beginTime, Date endTime);
}
