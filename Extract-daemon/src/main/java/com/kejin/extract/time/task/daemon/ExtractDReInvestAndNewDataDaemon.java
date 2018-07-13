package com.kejin.extract.time.task.daemon;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kejin.extract.domainservice.construct.ReInvestAndNewInfoConstruct;
import com.kejin.extract.domainservice.service.FixDataService;
import com.kejin.extract.time.task.common.AbstractExtract;
import com.kejin.extract.time.task.common.DaemonTask;

/**
 * @Author Leo
 * 复投和新增的表格数据的生成
 */
public class ExtractDReInvestAndNewDataDaemon extends AbstractExtract implements DaemonTask {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="reInvestAndNewInfoConstruct")
    private ReInvestAndNewInfoConstruct reInvestAndNewInfoConstruct;
    
    @Resource(name="fixDataService")
    private FixDataService fixDataService;

    public String HANDLE_DATA = "d_reinvest_and_new";

    //定时任务的时间应该比这个多
    public String INTERVAL_TIME = "60";

    //至少延后半个小时
    public String AFTER_TIME = "30";

    public ExtractDReInvestAndNewDataDaemon(){
        super.HANDLE_DATA = this.HANDLE_DATA;
        super.INTERVAL_TIME = this.INTERVAL_TIME;
        super.AFTER_TIME = this.AFTER_TIME;
    }

    @Override
    public int construct(Date recordBeginDatetime, Date recordEndDatetime) {
        int result = reInvestAndNewInfoConstruct.constructReInvestAndNewRecord(recordBeginDatetime, recordEndDatetime);
        //定时任务自动修复bid_fail的数据,每次更新同步完d_reinvest_and_new就会修复一下这个时间段内的数据
        int count = fixDataService.fixReinvestAndNewBidFail(recordBeginDatetime, recordEndDatetime);
        logger.info("修复bid_fail数据记录数："+count);
        return result;
    }
}
