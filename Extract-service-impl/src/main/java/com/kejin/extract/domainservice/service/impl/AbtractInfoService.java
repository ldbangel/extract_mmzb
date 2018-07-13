package com.kejin.extract.domainservice.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kejin.extract.kejin.service.dao.OperationInfoDao;


@Service("operationInfoService")
@SuppressWarnings("unchecked")
public class AbtractInfoService{
	
	@Autowired
	private OperationInfoDao operationInfoDao;
	
	/**
	 * 返回历史所有数据
	 * @param method
	 * @return
	 * @throws Exception
	 */
	protected <T> T getAllInfoData(Method method) throws Exception{
		
		//获取全部统计数据
		T allData = (T) method.invoke(operationInfoDao,null, new Date());		
		return allData;
	}
	
	/**
	 * 获取对应的年数据，originTime为空则为本年的数据
	 * @param method
	 * @param originTime
	 * @return
	 * @throws Exception
	 */
	protected <T> T getYearInfoData(Method method,Date originTime) throws Exception{
		
		Calendar yearBegin = Calendar.getInstance();
		if(originTime!=null){
			yearBegin.setTime(originTime);
		}else{
			yearBegin.setTime(new Date());
		}		
		yearBegin.set(Calendar.DATE, yearBegin.get(Calendar.DATE)-1);
    	yearBegin.setTime(yearBegin.getTime());
		yearBegin.set(Calendar.DAY_OF_YEAR,1);
		
		yearBegin.set(yearBegin.get(Calendar.YEAR),
				yearBegin.get(Calendar.MONTH),
				yearBegin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		Calendar yearEnd = Calendar.getInstance();
		yearEnd.setTime(yearBegin.getTime());
		yearEnd.set(Calendar.YEAR, yearEnd.get(Calendar.YEAR) + 1);
		
		//获取全部统计数据
		T yearData = (T) method.invoke(operationInfoDao,yearBegin.getTime(), yearEnd.getTime());		
		return yearData;
	}
	
	
	protected <T> T getMonthInfoData(Method method,Date originTime) throws Exception{
		
		Calendar monthBegin = Calendar.getInstance();		
		if(originTime!=null){
			monthBegin.setTime(originTime);
		}else{
			monthBegin.setTime(new Date());
		}		
		monthBegin.set(Calendar.DATE, monthBegin.get(Calendar.DATE)-1);
		monthBegin.setTime(monthBegin.getTime());
		monthBegin.set(Calendar.DAY_OF_MONTH,1);
		
		monthBegin.set(monthBegin.get(Calendar.YEAR),
				monthBegin.get(Calendar.MONTH),
				monthBegin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		Calendar monthEnd = Calendar.getInstance();
		monthEnd.setTime(monthBegin.getTime());
		monthEnd.set(Calendar.MONTH, monthEnd.get(Calendar.MONTH) + 1);
		
		//获取全部统计数据
		T monthData = (T) method.invoke(operationInfoDao,monthBegin.getTime(), monthEnd.getTime());		
		return monthData;
	}


	protected <T> T getWeekInfoData(Method method,Date originTime) throws Exception{
		
		Calendar weekBegin = Calendar.getInstance();
		Date beginDate = null;
		if(originTime!=null){
			beginDate = originTime;
		}else{
			beginDate = new Date();
		}	
		
		weekBegin.setTime(beginDate);
		weekBegin.set(Calendar.DATE, weekBegin.get(Calendar.DATE)-1);
		weekBegin.setTime(weekBegin.getTime());
		
		weekBegin.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		
		weekBegin.set(weekBegin.get(Calendar.YEAR),
				weekBegin.get(Calendar.MONTH),
				weekBegin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		Calendar weekEnd = Calendar.getInstance();
		weekEnd.setTime(weekBegin.getTime());
		weekEnd.set(Calendar.DATE, weekEnd.get(Calendar.DATE)+7);
		
		T weekData = (T) method.invoke(operationInfoDao,weekBegin.getTime(), weekEnd.getTime());
		return weekData;
	}

	
	protected <T> T getDayIntervalInfoData(Method method,Date originBeginTime,Date originEndTime) throws Exception{
		
		Calendar dayBegin = Calendar.getInstance();
		if(originBeginTime!=null){
			dayBegin.setTime(originBeginTime);
		}else{
			dayBegin.setTime(new Date());
		}	
	
		dayBegin.set(dayBegin.get(Calendar.YEAR),
				dayBegin.get(Calendar.MONTH),
				dayBegin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		Calendar dayEnd = Calendar.getInstance();
		if(originEndTime!=null){
			dayEnd.setTime(originEndTime);
		}else{
			dayEnd.setTime(dayBegin.getTime());			
			dayEnd.set(Calendar.DATE, dayEnd.get(Calendar.DATE) + 1);
		}
		
		dayEnd.set(dayEnd.get(Calendar.YEAR),
				dayEnd.get(Calendar.MONTH),
				dayEnd.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		T dayData = (T) method.invoke(operationInfoDao,dayBegin.getTime(), dayEnd.getTime());
		return dayData;
	}
	
	
	
	protected <T> Map<String,T> getDayDetail(Method method,Date originBeginTime,Date originEndTime) throws Exception{
		
		Map<String,T> dayDetail = new TreeMap<String,T>(new Comparator<String>(){  
  
               public int compare(String o1, String o2) {  
                
            	 String[] s1 = o1.split("-");
            	 String[] s2 = o2.split("-");
            	 
            	 for(int i =0 ; i < s1.length;i++){
            		 if(s1[i].equals(s2[i])){
            			 continue;
            		 }else{
            			 Integer i1 = Integer.valueOf(s1[i]);
            			 Integer i2 = Integer.valueOf(s2[i]);
            			 return i1.compareTo(i2);
            		 }
            	 }            	   
                //指定排序器按照降序排列  
                return 0;  
            }     
        });
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar begin =  Calendar.getInstance();
		if(originBeginTime!=null){
			begin.setTime(originBeginTime);
		}else{
			begin.setTime(new Date());
			/*//这里一个bug，每当周日，本周每日报表就会出问题，初步认为是周日calender.monday为下周周一，因为周日为每周的第一天
			begin.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);*/
			begin.set(Calendar.DATE, begin.get(Calendar.DATE)-7);
		}
		begin.set(begin.get(Calendar.YEAR),
				begin.get(Calendar.MONTH),
				begin.get(Calendar.DAY_OF_MONTH), 0, 0, 0);


		Calendar end =  Calendar.getInstance();
		if(originEndTime!=null){
			end.setTime(originEndTime);
		}else{
			end.setTime(begin.getTime());
			end.set(Calendar.DATE, end.get(Calendar.DATE)+7);
		}
		
		while(begin.before(end)){
			Calendar tempEnd = Calendar.getInstance();
			tempEnd.setTime(begin.getTime());
			tempEnd.set(Calendar.DATE, tempEnd.get(Calendar.DATE) + 1);
			
            Date beginDate = begin.getTime();
			
			//获取本日的数据
			T dayData = (T) method.invoke(operationInfoDao,beginDate, tempEnd.getTime());
			
			String day = fmt.format(beginDate);
			dayDetail.put(day, dayData);
			
			begin = tempEnd;
		}
		
		return dayDetail;
	}
	
	//获取前四周的详细数据
	protected <T> Map<String,T> getWeekDetail(Method method,Date originBeginTime,Date originEndTime) 
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Map<String,T> weekDetail = new TreeMap<String,T>(new Comparator<String>(){  
            public int compare(String o1, String o2) {  
            	String[] s1 = o1.split("-");
            	String[] s2 = o2.split("-");
         	 
            	for(int i =0 ; i < s1.length;i++){
            		if(s1[i].equals(s2[i])){
            			continue;
            		}else{
            			Integer i1 = Integer.valueOf(s1[i]);
            			Integer i2 = Integer.valueOf(s2[i]);
            			return i1.compareTo(i2);
            		}
            	}            	   
            	//指定排序器按照降序排列  
             return 0;  
            }     
		});
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar finalTime =  Calendar.getInstance();
		if(originBeginTime!=null){
			finalTime.setTime(originBeginTime);
		}else{
			finalTime.setTime(new Date());
			finalTime.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		}
		finalTime.set(finalTime.get(Calendar.YEAR),
				finalTime.get(Calendar.MONTH),
				finalTime.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		
		for (int i = 0; i < 4; i++) {
			Date endDate = finalTime.getTime();
			finalTime.set(Calendar.DATE, finalTime.get(Calendar.DATE)-7);
			Date beginDate = finalTime.getTime();
			T weekOfMonthData = (T) method.invoke(operationInfoDao, beginDate, endDate);
			
			weekDetail.put(fmt.format(beginDate), weekOfMonthData);
		}

		return weekDetail;
	}

	
	protected <T> Map<String,T> getMonthDetail(Method method,Date originBeginTime) throws Exception{
		
		 Map<String,T> monthDetail = new TreeMap<String,T>(new Comparator<String>(){  
	            public int compare(String o1, String o2) {  
	             
	         	 String[] s1 = o1.split("-");
	         	 String[] s2 = o2.split("-");
	         	 
	         	 for(int i =0 ; i < s1.length;i++){
	         		 if(s1[i].equals(s2[i])){
	         			 continue;
	         		 }else{
	         			 Integer i1 = Integer.valueOf(s1[i]);
	         			 Integer i2 = Integer.valueOf(s2[i]);
	         			 return i1.compareTo(i2);
	         		 }
	         	 }            	   
	             //指定排序器按照降序排列  
	             return 0;  
	         }     
	     });
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM");
		
		Calendar monthDay = Calendar.getInstance();
		if(originBeginTime!=null){
			monthDay.setTime(originBeginTime);
		}else{
			monthDay.setTime(new Date());
		}		
		monthDay.add(Calendar.MONTH, 0);
		monthDay.set(Calendar.DAY_OF_MONTH,1);
		
		monthDay.set(monthDay.get(Calendar.YEAR),
				monthDay.get(Calendar.MONTH),
				monthDay.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		do{//获取对应的年每个月的数据
			
			Date endDate = monthDay.getTime();
			
			monthDay.set(Calendar.MONTH, monthDay.get(Calendar.MONTH) - 1);			
			Date beginDate = monthDay.getTime();
			
			T monthOfYearData = (T) method.invoke(operationInfoDao,beginDate, endDate);
			
			monthDetail.put(fmt.format(beginDate), monthOfYearData);
						
		}while(monthDay.get(Calendar.MONTH) != Calendar.JANUARY);
		
		return monthDetail;
	}
}
