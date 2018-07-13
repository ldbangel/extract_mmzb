package com.kejin.extract.time.task.screenShot;

import java.io.IOException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kejin.extract.common.enums.ScreenShotTypeEnum;
import com.kejin.extract.domainservice.common.ScreenShotUtils;
import com.kejin.extract.time.task.common.DaemonTask;

/**
 * 屏幕截图
 */
public class ScreenShotDayDaemon implements DaemonTask {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void execute() {
		try {
			logger.info("day screen shot start");
			ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.DAY_FUNDFLOW_REPORT);
			ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.DAY_INCREASE_REPORT);
			ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.DAY_REGULAR_REPORT);
			ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.DAY_TRADE_REPORT);
			ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.DAY_SIMPLE_REPORT);
			ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.DAY_CASH_CHART);
			logger.info("-------end--------");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
