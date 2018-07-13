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
public class ScreenShotWeekDaemon implements DaemonTask {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void execute() {
		try {
			logger.info("week screen shot start");
			ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.WEEK_ACHIEVEMENT_REPORT);
			ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.WEEK_FUNDFLOW_REPORT);
			ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.WEEK_INCREASE_REPORT);
			ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.WEEK_REGULAR_REPORT);
			ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.WEEK_SIMPLE_REPORT);
			ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.WEEK_TRADE_REPORT);
			logger.info("-------end--------");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
