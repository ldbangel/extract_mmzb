package com.kejin.extract.domainservice.common;

import java.util.Calendar;
import java.util.Date;

import com.kejin.extract.entity.service.util.TimeInterval;

public class TimeHandleService {
    /**
     * 生成今日的时间区间
     * @return
     */
    public static TimeInterval generateTodayTimeInterval(){
    	
    	Calendar calendarBegin = Calendar.getInstance();

		calendarBegin.set(calendarBegin.get(Calendar.YEAR),
				calendarBegin.get(Calendar.MONTH),
				calendarBegin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);

		Calendar calendarEnd = Calendar.getInstance();

		calendarEnd.setTime(calendarBegin.getTime());

		calendarEnd.set(Calendar.DATE, calendarEnd.get(Calendar.DATE) + 1);
		
		Date beginTime = calendarBegin.getTime();
		Date endTime = calendarEnd.getTime();	
		
		TimeInterval interval = new TimeInterval();
		
		interval.setBeginTime(beginTime);
		interval.setEndTime(endTime);
		
		return interval;
    }
}
