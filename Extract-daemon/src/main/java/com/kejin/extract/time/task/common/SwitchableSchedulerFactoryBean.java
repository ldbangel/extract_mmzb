package com.kejin.extract.time.task.common;

import org.apache.log4j.Logger;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


public class SwitchableSchedulerFactoryBean extends SchedulerFactoryBean {
	private Logger logger = Logger.getLogger(this.getClass());
    private boolean available;
    @Override
    public void afterPropertiesSet() throws Exception {
    	System.out.println("--------------");
        if(!available){
            logger.info("定时任务为关闭状态，无须初始化。");
            return;
        }
        logger.info("available="+available);
        super.afterPropertiesSet();
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
