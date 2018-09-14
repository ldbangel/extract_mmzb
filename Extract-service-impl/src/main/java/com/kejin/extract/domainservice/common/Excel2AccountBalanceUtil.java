package com.kejin.extract.domainservice.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import com.kejin.extract.common.utils.DateFormatUtils;
import com.kejin.extract.common.utils.SysConstantsConfig;

public class Excel2AccountBalanceUtil {
	@SuppressWarnings("deprecation")
	public static void excelUtil(List<Map<String,Object>> balanceList,String financialManager)
			throws IOException{
		//创建HSSFWorkbook对象(excel的文档对象)  
		HSSFWorkbook wb = new HSSFWorkbook();  
		//建立新的sheet对象（excel的表单）  
		HSSFSheet sheet = wb.createSheet("账户余额"); 
		
		//设置列宽
		sheet.setColumnWidth(0, 15*256);
		sheet.setColumnWidth(1, 15*256);
		sheet.setColumnWidth(2, 20*256);
		sheet.setColumnWidth(3, 15*256);
		sheet.setColumnWidth(4, 15*256);
		sheet.setColumnWidth(5, 15*256);
		sheet.setColumnWidth(6, 25*256);
		sheet.setColumnWidth(7, 15*256);
		sheet.setColumnWidth(8, 25*256);
		sheet.setColumnWidth(9, 15*256);
		sheet.setColumnWidth(10, 25*256);
		sheet.setColumnWidth(11, 15*256);
		sheet.setColumnWidth(12, 25*256);
		sheet.setColumnWidth(13, 15*256);
		
		
		//字体设置
		HSSFFont font = wb.createFont();    
		font.setFontName("仿宋_GB2312");    
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示    
		font.setFontHeightInPoints((short) 12);
		
		//生成一个样式
		HSSFCellStyle style1 = wb.createCellStyle();
		style1.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
		style1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
		style1.setFont(font);
		style1.setWrapText(false);
		
		HSSFCellStyle style2 = wb.createCellStyle();
		HSSFFont font1 = wb.createFont(); 
		font1.setFontHeightInPoints((short) 11);
		style2.setAlignment(CellStyle.ALIGN_LEFT);//水平居左
		style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
		style2.setFont(font1);
		style2.setWrapText(false);
		
		HSSFCellStyle style3 = wb.createCellStyle();
		style3.setAlignment(CellStyle.ALIGN_RIGHT);//水平居右
		style3.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
		style3.setFont(font1);
		style3.setWrapText(false);
		
		DecimalFormat format1 = new DecimalFormat("0.00%");
		
		HSSFRow row1 = sheet.createRow(0);  
		Calendar now = Calendar.getInstance();
		
		String[] header = {"会员ID","姓名","手机号码","账户余额","零点账户余额","客户经理","最近投资时间","最近投资金额","最近回款时间","最近回款金额","最近充值时间","最近充值金额","最近提现时间","最近提现金额"};
		
		for (short i = 0; i < header.length; i++) {
	        row1.createCell(i).setCellValue(header[i]);
	        row1.getCell(i).setCellStyle(style1);
	    }
		for (int i = 0; i < balanceList.size(); i++) {
			HSSFRow row = sheet.createRow(i+1);
			
			row.createCell(0).setCellValue((String) balanceList.get(i).get("memberId"));
			row.createCell(1).setCellValue((String) balanceList.get(i).get("authName"));
			row.createCell(2).setCellValue((String) balanceList.get(i).get("phoneNum"));
			row.createCell(3).setCellValue(StringUtils.substringBefore(balanceList.get(i).get("balance").toString(), "."));
			if(balanceList.get(i).get("yestodayBalance")!=null){
				row.createCell(4).setCellValue(((BigDecimal) balanceList.get(i).get("yestodayBalance")).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
			}else{
				row.createCell(4).setCellValue("");
			}
			
			if(balanceList.get(i).get("financialManager") != null){
				row.createCell(5).setCellValue((String) balanceList.get(i).get("financialManager"));
			}else{
				row.createCell(5).setCellValue("");
			}
			if(balanceList.get(i).get("latestInvestTime") != null){
				row.createCell(6).setCellValue(balanceList.get(i).get("latestInvestTime").toString());
				row.createCell(7).setCellValue(((BigDecimal) balanceList.get(i).get("latestInvestAmount")).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
			}else{
				row.createCell(6).setCellValue("");
				row.createCell(7).setCellValue("");
			}
			if(balanceList.get(i).get("latestRecoveryTime") != null){
				row.createCell(8).setCellValue(balanceList.get(i).get("latestRecoveryTime").toString());
				row.createCell(9).setCellValue(((BigDecimal) balanceList.get(i).get("latestRecoveryAmount")).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
			}else{
				row.createCell(8).setCellValue("");
				row.createCell(9).setCellValue("");
			}
			if(balanceList.get(i).get("latestChargeTime") != null){
				row.createCell(10).setCellValue(balanceList.get(i).get("latestChargeTime").toString());
				row.createCell(11).setCellValue(((BigDecimal) balanceList.get(i).get("latestChargeAmount")).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
			}else{
				row.createCell(10).setCellValue("");
				row.createCell(11).setCellValue("");
			}
			if(balanceList.get(i).get("latestCashTime") != null){
				row.createCell(12).setCellValue(balanceList.get(i).get("latestCashTime").toString());
				if(balanceList.get(i).get("latestCashAmount") == null){
					System.out.println("ok ");
				}
				row.createCell(13).setCellValue(((BigDecimal) balanceList.get(i).get("latestCashAmount")).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
			}else{
				row.createCell(12).setCellValue("");
				row.createCell(13).setCellValue("");
			}
			
			row.getCell(0).setCellStyle(style2);
			row.getCell(1).setCellStyle(style2);
			row.getCell(2).setCellStyle(style2);
			row.getCell(3).setCellStyle(style3);
			row.getCell(4).setCellStyle(style3);
			row.getCell(5).setCellStyle(style2);
			row.getCell(6).setCellStyle(style2);
			row.getCell(7).setCellStyle(style3);
			row.getCell(8).setCellStyle(style2);
			row.getCell(9).setCellStyle(style3);
			row.getCell(10).setCellStyle(style2);
			row.getCell(11).setCellStyle(style3);
			row.getCell(12).setCellStyle(style2);
			row.getCell(13).setCellStyle(style3);
		}
		
		String fileName = "default.xls";
		try {
			fileName = "AccountBalance"+DateFormatUtils.getCurrentHoursDate()+financialManager+".xls";
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		//输出流
		FileOutputStream fos = null;
        try {
			 fos = new FileOutputStream(SysConstantsConfig.EXCEL_SAVE_PATH + fileName); 
			 wb.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
        	if(fos != null){
        		fos.close();
        	}
        }
	}
	
}
