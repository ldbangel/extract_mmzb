package com.kejin.extract.time.task.screenShot;

import java.io.IOException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kejin.extract.common.enums.ScreenShotTypeEnum;
import com.kejin.extract.domainservice.common.ScreenShotUtils;
import com.kejin.extract.time.task.common.DaemonTask;

/**
 * 业绩日报屏幕截图
 * @author Liu Dongbo
 *
 */
public class ScreenShotDayAchievementDaemon implements DaemonTask{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void execute() {
		try {
			logger.info("day achievement report screen shot start");
			ScreenShotUtils.ScreenShot(ScreenShotTypeEnum.DAY_ACHIEVEMENT_REPORT);
			logger.info("-------end--------");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
