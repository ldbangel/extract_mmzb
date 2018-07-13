package com.kejin.extract.domainservice.common;

import java.io.FileOutputStream;
import java.io.IOException;
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
	public static void excelUtil(List<Map<String,Object>> balanceList)
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
		/*sheet.setColumnWidth(5, 15*256);
		sheet.setColumnWidth(6, 15*256);
		sheet.setColumnWidth(7, 25*256);
		sheet.setColumnWidth(8, 25*256);
		sheet.setColumnWidth(9, 15*256);
		sheet.setColumnWidth(10, 15*256);
		sheet.setColumnWidth(11, 15*256);
		sheet.setColumnWidth(12, 15*256);
		sheet.setColumnWidth(13, 15*256);
		sheet.setColumnWidth(14, 15*256);
		sheet.setColumnWidth(15, 15*256);
		sheet.setColumnWidth(16, 15*256);*/
		
		
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
		
		//String[] header = {"会员ID","姓名","手机号码","账户余额","性别","年龄","客户经理","最近充值时间","最近回款时间","时间间隔","充值占比","回款占比","三天前余额","两天前余额","一天前余额","用户类型","投资次数"}; 
		String[] header = {"会员ID","姓名","手机号码","账户余额","客户经理"};
		
		for (short i = 0; i < header.length; i++) {
	        row1.createCell(i).setCellValue(header[i]);
	        row1.getCell(i).setCellStyle(style1);
	    }
		for (int i = 0; i < balanceList.size(); i++) {
			HSSFRow row = sheet.createRow(i+1);
			
			row.createCell(0).setCellValue((String) balanceList.get(i).get("memberId"));
			row.createCell(1).setCellValue((String) balanceList.get(i).get("authName"));
			row.createCell(2).setCellValue((String) balanceList.get(i).get("phoneNum"));
			row.createCell(3).setCellValue(StringUtils.substringBefore((String) balanceList.get(i).get("balance"), "."));
			/*if(balanceList.get(i).get("balance") != null){
				row.createCell(3).setCellValue(((BigDecimal) balanceList.get(i).get("balance")).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
			}else{
				row.createCell(3).setCellValue((new BigDecimal(0)).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
			}*/
			/*row.createCell(4).setCellValue((String) balanceList.get(i).get("sex"));
			row.createCell(5).setCellValue(((int)now.get(Calendar.YEAR))-(Integer.parseInt((String) balanceList.get(i).get("age"))));*/
			if(balanceList.get(i).get("financialManager") != null){
				row.createCell(4).setCellValue((String) balanceList.get(i).get("financialManager"));
			}else{
				row.createCell(4).setCellValue("");
			}
			/*if(balanceList.get(i).get("chargeTime") != null){
				row.createCell(7).setCellValue(balanceList.get(i).get("chargeTime").toString());
			}else{
				row.createCell(7).setCellValue("");
			}
			if(balanceList.get(i).get("recoveryTime") != null){
				row.createCell(8).setCellValue(balanceList.get(i).get("recoveryTime").toString());
			}else{
				row.createCell(8).setCellValue("");
			}
			if(balanceList.get(i).get("intervalDay")!=null){
				row.createCell(9).setCellValue(balanceList.get(i).get("intervalDay")+"天".toString());
			}else{
				row.createCell(9).setCellValue("");
			}
			if(balanceList.get(i).get("chargeRate")!=null){
				row.createCell(10).setCellValue(format1.format((BigDecimal) balanceList.get(i).get("chargeRate")));
			}else{
				row.createCell(10).setCellValue("");
			}
			if(balanceList.get(i).get("recoveryRate")!=null){
				row.createCell(11).setCellValue(format1.format((BigDecimal) balanceList.get(i).get("recoveryRate")));
			}else{
				row.createCell(11).setCellValue("");
			}
			if(balanceList.get(i).get("threeAgoBalance") != null){
				row.createCell(12).setCellValue(((BigDecimal) balanceList.get(i).get("threeAgoBalance")).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
			}else{
				row.createCell(12).setCellValue((new BigDecimal(0)).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
			}
			if(balanceList.get(i).get("twoAgoBalance") != null){
				row.createCell(13).setCellValue(((BigDecimal) balanceList.get(i).get("twoAgoBalance")).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
			}else{
				row.createCell(13).setCellValue((new BigDecimal(0)).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
			}
			if(balanceList.get(i).get("oneAgoBalance") != null){
				row.createCell(14).setCellValue(((BigDecimal) balanceList.get(i).get("oneAgoBalance")).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
			}else{
				row.createCell(14).setCellValue((new BigDecimal(0)).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
			}
			row.createCell(15).setCellValue((String) balanceList.get(i).get("memberType"));
			if(balanceList.get(i).get("invest_times") != null){
				row.createCell(16).setCellValue(balanceList.get(i).get("invest_times").toString());
			}else{
				row.createCell(16).setCellValue(0);
			}*/
			
			row.getCell(0).setCellStyle(style2);
			row.getCell(1).setCellStyle(style2);
			row.getCell(2).setCellStyle(style2);
			row.getCell(3).setCellStyle(style3);
			row.getCell(4).setCellStyle(style2);
			/*row.getCell(5).setCellStyle(style2);
			row.getCell(6).setCellStyle(style2);
			row.getCell(7).setCellStyle(style2);
			row.getCell(8).setCellStyle(style2);
			row.getCell(9).setCellStyle(style3);
			row.getCell(10).setCellStyle(style2);
			row.getCell(11).setCellStyle(style2);
			row.getCell(12).setCellStyle(style3);
			row.getCell(13).setCellStyle(style3);
			row.getCell(14).setCellStyle(style3);
			row.getCell(15).setCellStyle(style2);
			row.getCell(16).setCellStyle(style2);*/
		}
		
		String fileName = "default.xls";
		try {
			fileName = "AccountBalance"+DateFormatUtils.getCurrentHoursDate()+".xls";
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
