package com.kejin.extract.domainservice.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import com.kejin.extract.common.utils.DateFormatUtils;
import com.kejin.extract.common.utils.SysConstantsConfig;
import com.kejin.extract.entity.kejinTest.DMemberBalanceModel;

public class ExcelUtil {
	@SuppressWarnings("deprecation")
	public static void excelUtil(List<DMemberBalanceModel> memberBalanceList)
			throws IOException{
		//创建HSSFWorkbook对象(excel的文档对象)  
		HSSFWorkbook wb = new HSSFWorkbook();  
		//建立新的sheet对象（excel的表单）  
		HSSFSheet sheet = wb.createSheet("资产明细"); 
		
		//设置列宽
		sheet.setColumnWidth(0, 15*256);
		sheet.setColumnWidth(1, 15*256);
		sheet.setColumnWidth(2, 20*256);
		sheet.setColumnWidth(3, 15*256);
		sheet.setColumnWidth(4, 15*256);
		sheet.setColumnWidth(5, 15*256);
		sheet.setColumnWidth(6, 15*256);
		sheet.setColumnWidth(7, 15*256);
		
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
		style1.setWrapText(true);
		
		HSSFCellStyle style2 = wb.createCellStyle();
		HSSFFont font1 = wb.createFont(); 
		font1.setFontHeightInPoints((short) 11);
		style2.setAlignment(CellStyle.ALIGN_LEFT);//水平居左
		style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
		style2.setFont(font1);
		style2.setWrapText(false);
		
		HSSFRow row1 = sheet.createRow(0);  
		
		String[] header = {"会员ID","用户类型","手机号码","姓名","推荐人","余额","定期债权","总资产"}; 
		
		for (short i = 0; i < header.length; i++) {
	        row1.createCell(i).setCellValue(header[i]);
	        row1.getCell(i).setCellStyle(style1);
	    }
		for (int i = 0; i < memberBalanceList.size(); i++) {
			HSSFRow row=sheet.createRow(i+1);
			row.createCell(0).setCellValue(memberBalanceList.get(i).getMemberId());
			row.createCell(1).setCellValue(memberBalanceList.get(i).getUserRole());
			row.createCell(2).setCellValue(memberBalanceList.get(i).getPhoneNum());
			row.createCell(3).setCellValue(memberBalanceList.get(i).getAuthName());
			row.createCell(4).setCellValue(memberBalanceList.get(i).getrAuthName());
			row.createCell(5).setCellValue(memberBalanceList.get(i).getBalance().toString());
			row.createCell(6).setCellValue((memberBalanceList.get(i).getCreditNumbers()
					.add(memberBalanceList.get(i).getBlockedFund())
					.toString()));
			row.createCell(7).setCellValue((memberBalanceList.get(i).getBalance()
					.add(memberBalanceList.get(i).getCreditNumbers())
					.add(memberBalanceList.get(i).getBlockedFund())
					.toString()));
			row.getCell(0).setCellStyle(style2);
			row.getCell(1).setCellStyle(style2);
			row.getCell(2).setCellStyle(style2);
			row.getCell(3).setCellStyle(style2);
			row.getCell(4).setCellStyle(style2);
			row.getCell(5).setCellStyle(style2);
			row.getCell(6).setCellStyle(style2);
			row.getCell(7).setCellStyle(style2);
		}
		
		String fileName = "default.xls";
		try {
			fileName = "Operation"+DateFormatUtils.getYesterdayDate()+".xls";
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		//输出流
//		OutputStream os=null;
		FileOutputStream fos = null;
        try {
			 /*response.reset();
			 response.setCharacterEncoding("UTF-8");
			 response.setHeader("Content-Disposition", "attachment; filename="+fileName);
//			 response.setContentType("application/vnd.ms-excel;charset=utf-8"); 
			 response.setContentType("application/octet-stream;charset=UTF-8");  //流输出
			 os = new BufferedOutputStream(response.getOutputStream());*/
			 
			 fos = new FileOutputStream(SysConstantsConfig.EXCEL_SAVE_PATH  + fileName); 
			 wb.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            /*if(os!=null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
        	if(fos != null){
        		fos.close();
        	}
        }
	}
	
	public static void main(String[] args) {
		List<DMemberBalanceModel> list = new ArrayList<DMemberBalanceModel>();
		DMemberBalanceModel model = new DMemberBalanceModel();
		model.setMemberId("100000420401");
		model.setMemType(1);
		model.setPhoneNum("12899999999");
		model.setAuthName("刘东波");
		model.setrAuthName("猪猪");
		model.setBalance(new BigDecimal(1000));
		model.setCreditNumbers(new BigDecimal(1000));
		model.setBlockedFund(new BigDecimal(1000));
		
		list.add(model);
		try {
			excelUtil(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
