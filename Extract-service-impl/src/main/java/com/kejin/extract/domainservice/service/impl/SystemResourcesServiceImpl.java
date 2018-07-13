package com.kejin.extract.domainservice.service.impl;

import java.io.File;
import java.text.ParseException;

import org.springframework.stereotype.Service;

import com.kejin.extract.common.utils.DateFormatUtils;
import com.kejin.extract.common.utils.SysConstantsConfig;
import com.kejin.extract.domainservice.service.SystemResourcesService;

@Service("systemResourcesService")
public class SystemResourcesServiceImpl implements SystemResourcesService {
	@Override
	public void removeUselessFiles() {
		String[] images = {"daySimpleReport.jpg","dayIncreaseReport.jpg","dayFundflowReport.jpg","dayCashChart.jpg",
				"dayTradeReport.jpg","dayRegularReport.jpg","dayAchievementReport.jpg","weekSimpleReport.jpg",
				"weekIncreaseReport.jpg","weekFundflowReport.jpg","weekTradeReport.jpg","weekRegularReport.jpg",
				"weekAchievementReport.jpg","monthSimpleReport.jpg","monthIncreaseReport.jpg","monthFundflowReport.jpg",
				"monthTradeReport.jpg","monthRegularReport.jpg"};
		String[] pdfs = {"dayOperationReport.pdf","weekOperationReport.pdf","monthOperationReport.pdf"};
		String[] excels = {"department","AccountBalance","CashDetails","Operation"};
		
		try {
			for (int i = 0; i < images.length; i++) {
				String filePath = SysConstantsConfig.IMAGE_OUT_PATH + DateFormatUtils.getOneWeekAgoDate() + images[i];
				File file = new File(filePath);
				if(file.exists()){
					file.delete();
				}
			}
			for (int i = 0; i < pdfs.length; i++) {
				String filePath = SysConstantsConfig.PDF_OUT_PATH + DateFormatUtils.getOneWeekAgoDate() + pdfs[i];
				File file = new File(filePath);
				if(file.exists()){
					file.delete();
				}
			}
			for (int i = 0; i < excels.length; i++) {
				if("AccountBalance".equals(excels[i])){
					String filePath1 = SysConstantsConfig.EXCEL_SAVE_PATH + excels[i] + DateFormatUtils.get3DaysAgoDate() + "09.xls";
					String filePath2 = SysConstantsConfig.EXCEL_SAVE_PATH + excels[i] + DateFormatUtils.get3DaysAgoDate() + "15.xls";
					File file1 = new File(filePath1);
					File file2 = new File(filePath2);
					if(file1.exists()){
						file1.delete();
					}
					if(file2.exists()){
						file2.delete();
					}
				}else{
					String filePath = SysConstantsConfig.EXCEL_SAVE_PATH + excels[i] + DateFormatUtils.get3DaysAgoDate() + ".xls";
					File file = new File(filePath);
					if(file.exists()){
						file.delete();
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String[] images = {"daySimpleReport.jpg","dayIncreaseReport.jpg","dayFundflowReport.jpg","dayCashChart.jpg",
				"dayTradeReport.jpg","dayRegularReport.jpg","dayAchievementReport.jpg","weekSimpleReport.jpg",
				"weekIncreaseReport.jpg","weekFundflowReport.jpg","weekTradeReport.jpg","weekRegularReport.jpg",
				"weekAchievementReport.jpg","monthSimpleReport.jpg","monthIncreaseReport.jpg","monthFundflowReport.jpg",
				"monthTradeReport.jpg","monthRegularReport.jpg"};
		String[] pdfs = {"dayOperationReport.pdf","weekOperationReport.pdf","monthOperationReport.pdf"};
		String[] excels = {"department","AccountBalance","CashDetails","Operation"};
		
		try {
			for (int i = 0; i < images.length; i++) {
				String filePath = "D:/mamaImageHandle/image/" + DateFormatUtils.getOneWeekAgoDate() + images[i];
				File file = new File(filePath);
				if(file.exists()){
					file.delete();
				}
			}
			for (int i = 0; i < pdfs.length; i++) {
				String filePath = "D:/mamaImageHandle/pdf/" + DateFormatUtils.getOneWeekAgoDate() + pdfs[i];
				File file = new File(filePath);
				if(file.exists()){
					file.delete();
				}
			}
			for (int i = 0; i < excels.length; i++) {
				if("AccountBalance".equals(excels[i])){
					String filePath1 = "D:/Bruce/" + excels[i] + DateFormatUtils.get3DaysAgoDate() + "09.xls";
					String filePath2 = "D:/Bruce/" + excels[i] + DateFormatUtils.get3DaysAgoDate() + "15.xls";
					File file1 = new File(filePath1);
					File file2 = new File(filePath2);
					if(file1.exists()){
						file1.delete();
					}
					if(file2.exists()){
						file2.delete();
					}
				}else{
					String filePath = "D:/Bruce/" + excels[i] + DateFormatUtils.get3DaysAgoDate() + ".xls";
					File file = new File(filePath);
					if(file.exists()){
						file.delete();
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
