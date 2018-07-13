package com.kejin.extract.domainservice.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import com.kejin.extract.common.utils.DateFormatUtils;
import com.kejin.extract.common.utils.SysConstantsConfig;

public class Excel2CashDetailsUtil {
	@SuppressWarnings("deprecation")
	public static void excelUtil(List<Map<String,Object>> cashDetailsList)
			throws IOException{
		//创建HSSFWorkbook对象(excel的文档对象)  
		HSSFWorkbook wb = new HSSFWorkbook();  
		//建立新的sheet对象（excel的表单）  
		HSSFSheet sheet = wb.createSheet("提现明细"); 
		
		//设置列宽
		sheet.setColumnWidth(0, 15*256);
		sheet.setColumnWidth(1, 15*256);
		sheet.setColumnWidth(2, 15*256);
		sheet.setColumnWidth(3, 20*256);
		sheet.setColumnWidth(4, 15*256);
		sheet.setColumnWidth(5, 15*256);
		sheet.setColumnWidth(6, 15*256);
		sheet.setColumnWidth(7, 15*256);
		sheet.setColumnWidth(8, 15*256);
		
		//生成一个样式
		HSSFCellStyle style1 = wb.createCellStyle();
		
		//字体设置
		HSSFFont font = wb.createFont();    
		font.setFontName("仿宋_GB2312");    
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示    
		font.setFontHeightInPoints((short) 12);
		
//		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
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
		
		HSSFRow row1 = sheet.createRow(0);  
		
		String[] header = {"会员ID","姓名","提现角色","手机号码","省份","城市","提现金额","提现来源","渠道"}; 
		
		for (short i = 0; i < header.length; i++) {
	        row1.createCell(i).setCellValue(header[i]);
	        row1.getCell(i).setCellStyle(style1);
	    }
		for (int i = 0; i < cashDetailsList.size(); i++) {
			HSSFRow row = sheet.createRow(i+1);
			
			row.createCell(0).setCellValue((String)cashDetailsList.get(i).get("memberId"));
			row.createCell(1).setCellValue((String) cashDetailsList.get(i).get("name"));
			row.createCell(2).setCellValue((String) cashDetailsList.get(i).get("userRole"));
			row.createCell(3).setCellValue((String) cashDetailsList.get(i).get("phone"));
			row.createCell(4).setCellValue((String) cashDetailsList.get(i).get("phoneProvince"));
			row.createCell(5).setCellValue((String) cashDetailsList.get(i).get("phoneCity"));
			row.createCell(6).setCellValue(cashDetailsList.get(i).get("allAmount").toString());
			row.createCell(7).setCellValue((String) cashDetailsList.get(i).get("cashOrigin"));
			row.createCell(8).setCellValue((String) (cashDetailsList.get(i).get("channelName") != null ? cashDetailsList.get(i).get("channelName") : ""));
			
			row.getCell(0).setCellStyle(style2);
			row.getCell(1).setCellStyle(style2);
			row.getCell(2).setCellStyle(style2);
			row.getCell(3).setCellStyle(style2);
			row.getCell(4).setCellStyle(style2);
			row.getCell(5).setCellStyle(style2);
			row.getCell(6).setCellStyle(style2);
			row.getCell(7).setCellStyle(style2);
			row.getCell(8).setCellStyle(style2);
		}
		
		String fileName = "default.xls";
		try {
			fileName = "CashDetails"+DateFormatUtils.getYesterdayDate()+".xls";
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
