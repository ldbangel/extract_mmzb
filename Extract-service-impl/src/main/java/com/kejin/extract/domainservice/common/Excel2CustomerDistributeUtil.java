package com.kejin.extract.domainservice.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
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

public class Excel2CustomerDistributeUtil {
	public static void excelUtil(List<Map<String,Object>> registerList1, List<Map<String,Object>> registerList2)
			throws IOException{
		//创建HSSFWorkbook对象(excel的文档对象)  
		HSSFWorkbook wb = new HSSFWorkbook();  
		//建立新的sheet对象（excel的表单）  
		HSSFSheet sheet1 = wb.createSheet("张斌"); 
		HSSFSheet sheet2 = wb.createSheet("郑汉英"); 
		
		sheetGenerater(registerList1, sheet1, wb);
		sheetGenerater(registerList2, sheet2, wb);
		
		String fileName = "default.xls";
		try {
			fileName = "department" + DateFormatUtils.getYesterdayDate()+".xls";
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
	
	public static void sheetGenerater(List<Map<String,Object>> registerList, HSSFSheet sheet, HSSFWorkbook wb){
		//设置列宽
		sheet.setColumnWidth(0, 15*256);
		sheet.setColumnWidth(1, 15*256);
		sheet.setColumnWidth(2, 20*256);
		sheet.setColumnWidth(3, 20*256);
		sheet.setColumnWidth(4, 20*256);
		sheet.setColumnWidth(5, 20*256);
		sheet.setColumnWidth(6, 20*256);
		sheet.setColumnWidth(7, 20*256);
		sheet.setColumnWidth(8, 20*256);
		sheet.setColumnWidth(9, 20*256);
		sheet.setColumnWidth(10, 20*256);
		
		
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
		
		HSSFRow row1 = sheet.createRow(0);  
		
		String[] header = {"会员ID","姓名","手机号码","性别","年龄","注册日期","首投时间","首投金额","项目期限","推荐人","渠道"}; 
		
		for (short i = 0; i < header.length; i++) {
	        row1.createCell(i).setCellValue(header[i]);
	        row1.getCell(i).setCellStyle(style1);
	    }
		for (int i = 0; i < registerList.size(); i++) {
			HSSFRow row = sheet.createRow(i+1);
			
			row.createCell(0).setCellValue((String) registerList.get(i).get("memberId"));
			if(registerList.get(i).get("name") == null){
				row.createCell(1).setCellValue("");
			}else{
				row.createCell(1).setCellValue((String) registerList.get(i).get("name"));
			}
			row.createCell(2).setCellValue((String) registerList.get(i).get("phone"));
			if(registerList.get(i).get("gender") == null){
				row.createCell(3).setCellValue("");
			}else if("0".equals(registerList.get(i).get("gender").toString())){
				row.createCell(3).setCellValue("男");
			}else if("1".equals(registerList.get(i).get("gender").toString())){
				row.createCell(3).setCellValue("女");
			}else{
				row.createCell(3).setCellValue("");
			}
			if(registerList.get(i).get("age") == null){
				row.createCell(4).setCellValue("");
			}else{
				row.createCell(4).setCellValue(registerList.get(i).get("age").toString());
			}
			row.createCell(5).setCellValue(registerList.get(i).get("registerDatetime").toString());
			row.createCell(6).setCellValue(registerList.get(i).get("operationDate").toString());
			if(registerList.get(i).get("amount") != null){
				row.createCell(7).setCellValue(((BigDecimal) registerList.get(i).get("amount")).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
			}else{
				row.createCell(7).setCellValue((new BigDecimal(0)).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
			}
			if(registerList.get(i).get("subjectLife") == null){
				row.createCell(8).setCellValue("");
			}else{
				row.createCell(8).setCellValue((String) registerList.get(i).get("subjectLife"));
			}
			if(registerList.get(i).get("referee") == null){
				row.createCell(9).setCellValue("");
			}else{
				row.createCell(9).setCellValue((String) registerList.get(i).get("referee"));
			}
			if(registerList.get(i).get("channelName") == null){
				row.createCell(10).setCellValue("");
			}else{
				row.createCell(10).setCellValue((String) registerList.get(i).get("channelName"));
			}
			
			row.getCell(0).setCellStyle(style2);
			row.getCell(1).setCellStyle(style2);
			row.getCell(2).setCellStyle(style2);
			row.getCell(3).setCellStyle(style2);
			row.getCell(4).setCellStyle(style2);
			row.getCell(5).setCellStyle(style2);
			row.getCell(6).setCellStyle(style2);
			row.getCell(7).setCellStyle(style3);
			row.getCell(8).setCellStyle(style2);
			row.getCell(9).setCellStyle(style2);
			row.getCell(10).setCellStyle(style2);
		}
	}
	
}
