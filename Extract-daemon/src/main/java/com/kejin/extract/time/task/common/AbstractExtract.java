package com.kejin.extract.time.task.common;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.kejin.extract.entity.kejinTest.DActionAssistModel;
import com.kejin.extract.kejin.process.dao.DActionAssistDao;

public abstract class AbstractExtract {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//@Resource(name="dActionAssistDao")
	@Autowired
	private DActionAssistDao dActionAssistDao;

    public String HANDLE_DATA = "abstarct";
	
    //定时任务的时间应该比这个多
    public String INTERVAL_TIME = "60";
	
	//至少延后半个小时
    public String AFTER_TIME = "30";
	
    //多次抽取
    public int RECYCLE_TIME = 100;
    
    
	
    public void execute(){
		
		int n = RECYCLE_TIME;
		while(true){
		    n = handlerData(n);		    
		    if(n<=0){
		    	break;
		    }
		}
		
		HashMap<String,Object> filterParameter = new HashMap<String,Object>();
		filterParameter.put("handleData", HANDLE_DATA);
		filterParameter.put("output", "fail");
		
		List<DActionAssistModel> actions = dActionAssistDao.select(filterParameter);
		
		for(DActionAssistModel assist : actions ){			
			handlerRedoData(assist);			
		}
		
	}
    
    @Transactional
    public int handlerData(int n){
		HashMap<String,Object> filterParameter = new HashMap<String,Object>();
		filterParameter.put("handleData", HANDLE_DATA);
		
		List<DActionAssistModel> actions = dActionAssistDao.select(filterParameter);
		
		//开始一个新的事件
		if(actions.size()==0){
			DActionAssistModel start = new DActionAssistModel();
			Calendar d1 =  new GregorianCalendar(2015, 1, 13, 00, 00, 00);			
			start.setRecordEndDatetime(d1.getTime());	
			actions.add(start);
		}
		
		DActionAssistModel dActionAssistModel = actions.get(0);
		
		Date recordBeginDatetime  = null;
		Date recordEndDatetime  = null;
		
		Calendar  calNow = Calendar.getInstance();
		calNow.setTime(new Date());
		
		Calendar  cal = Calendar.getInstance();
		
		boolean isOld = dActionAssistModel.getOutput()==null && dActionAssistModel.getRecordBeginDatetime()!=null;
		int inteval = 0;
		if(isOld){//中断了要重新执行
			recordBeginDatetime = dActionAssistModel.getRecordBeginDatetime();
			recordEndDatetime = dActionAssistModel.getRecordEndDatetime();
		}else{
            recordBeginDatetime = dActionAssistModel.getRecordEndDatetime();
            
            Calendar  temp = Calendar.getInstance();
            temp.setTime(recordBeginDatetime);
            temp.add(Calendar.DATE,1);
            
            long nowMili = calNow.getTimeInMillis();
            long beginMili = temp.getTimeInMillis();
            
            long between_days=(nowMili-beginMili)/(1000*3600*24);
            int k = 1;
            if(between_days>10){
            	k=24*2;
            }else if(between_days>5){
            	k=24;
            }else if(between_days>2){
            	k=12;
            }
						
			cal.setTime(recordBeginDatetime);
			inteval = Integer.parseInt(INTERVAL_TIME)*k;
			cal.add(Calendar.MINUTE,inteval);
			
			recordEndDatetime = cal.getTime();
		}	
		
		
		//判断当前时间是否超越	
		cal.setTime(recordEndDatetime);
		cal.add(Calendar.MINUTE,Integer.parseInt(AFTER_TIME));
		
		if(calNow.compareTo(cal)<0){
			n=0;
			return n;
		}
		
		DActionAssistModel newAction =null;
		if(isOld){
			newAction = dActionAssistModel;
		}else{
			newAction = new DActionAssistModel();
			newAction.setRecordBeginDatetime(recordBeginDatetime );			
			newAction.setRecordEndDatetime(recordEndDatetime);
			newAction.setIntervalTime(String.valueOf(inteval));
			newAction.setHandleData(HANDLE_DATA);
			newAction.setBeginDatetime(new Date());
			
			dActionAssistDao.insert(newAction);
		}
		
					
		
		try{
			
			int handlerCount = construct(recordBeginDatetime, recordEndDatetime);
			
			if(handlerCount>50){
				n++;
			}else if(handlerCount>25){
				n=n-10;
			}else if(handlerCount>10){
				n=n-20;
			}else{
				n=n-30;
			}
			
			newAction.setEndDatetime(new Date());
			newAction.setOutput("success");
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error(HANDLE_DATA+"任务出错：",ex);
			newAction.setEndDatetime(new Date());
			newAction.setOutput("fail");
			newAction.setReason("may reason"+ex.getMessage());
		}	
	
	    dActionAssistDao.update(newAction);	
	    
	    return n;
    }
    
    @Transactional
    public void handlerRedoData(DActionAssistModel assist){
    	try{
			
			construct(assist.getRecordBeginDatetime(), assist.getRecordEndDatetime());
			assist.setEndDatetime(new Date());
			assist.setOutput("success");
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error(HANDLE_DATA+"(redo)任务出错：",ex);
			assist.setEndDatetime(new Date());
			assist.setOutput("fail");
			assist.setReason("may reason"+ex.getMessage());
		}
    	
    	 dActionAssistDao.update(assist);	
    }
    
    abstract public int construct(Date recordBeginDatetime ,Date recordEndDatetime);
	

}