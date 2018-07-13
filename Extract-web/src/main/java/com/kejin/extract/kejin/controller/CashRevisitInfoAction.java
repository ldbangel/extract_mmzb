package com.kejin.extract.kejin.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kejin.extract.common.utils.JsonUtil;
import com.kejin.extract.domainservice.user.CashRevisitInfoService;
import com.kejin.extract.entity.kejinTest.DCashRevisitModel;

/**
 * 大额提现回访记录上传
 * @author liudongbo
 *
 */
@Controller
public class CashRevisitInfoAction {

    @Resource(name = "cashRevisitInfoService")
    private CashRevisitInfoService cashRevisitInfoService;
	
	@RequestMapping(value = "/user/uploadCashReturnVisitFeedback.htm", method = RequestMethod.POST)
	@ResponseBody
	public String uploadCashReturnVisitFeedback(@RequestParam("file") MultipartFile file, @RequestParam("style") String style)
			throws InvalidFormatException, IOException, ParseException{
		Map<String, String> vo = JsonUtil.toMap(style);
		if (vo == null || vo.isEmpty()) {
		    return "failed!,please set the style value like {\"phone\":1,\"memberId\":2,\"financialManager\":3}";
		}
		Workbook workbook = WorkbookFactory.create(file.getInputStream());
		Sheet sheet = workbook.getSheetAt(0);
		int totalRows = sheet.getPhysicalNumberOfRows();
		if (totalRows < 1) {
		    return "the first excel sheet is empty!";
		}
		
		List<DCashRevisitModel> modelList = new ArrayList<DCashRevisitModel>();
		Integer memberIdCell = (vo.get("memberId") == null) ? 0 : Integer.valueOf(vo.get("memberId"));
		Integer nameCell = (vo.get("name") == null) ? 1 : Integer.valueOf(vo.get("name"));
		Integer phoneCell = (vo.get("phone") == null) ? 2 : Integer.valueOf(vo.get("phone"));
		Integer cashAmountCell = (vo.get("cashAmount") == null) ? 3 : Integer.valueOf(vo.get("cashAmount"));
		Integer orderTimeCell = (vo.get("orderTime") == null) ? 4 : Integer.valueOf(vo.get("orderTime"));
		Integer feedbackCell = (vo.get("feedback") == null) ? 5 : Integer.valueOf(vo.get("feedback"));
		Integer managerNameCell = (vo.get("managerName") == null) ? 6 : Integer.valueOf(vo.get("managerName"));
		int header = (vo.get("header") == null) ? 1 : Integer.valueOf(vo.get("header"));
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");     
		
		for (int r = header; r < totalRows; r++) {
			DCashRevisitModel revisitModel = new DCashRevisitModel();
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            row.getCell(memberIdCell).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(phoneCell).setCellType(Cell.CELL_TYPE_STRING);
            row.getCell(cashAmountCell).setCellType(Cell.CELL_TYPE_STRING);
            String memberId = (row.getCell(memberIdCell)==null)?null:row.getCell(memberIdCell).getStringCellValue();
            Date orderTime = format.parse((row.getCell(orderTimeCell)==null)?null:row.getCell(orderTimeCell).getStringCellValue()) ;
            String name = (row.getCell(nameCell)==null)?null:row.getCell(nameCell).getStringCellValue();
            String phone = (row.getCell(phoneCell)==null)?null:row.getCell(phoneCell).getStringCellValue();
            BigDecimal cashAmount = new BigDecimal((row.getCell(cashAmountCell)==null)?null:row.getCell(cashAmountCell).getStringCellValue());
            String feedback = (row.getCell(feedbackCell)==null)?null:row.getCell(feedbackCell).getStringCellValue();
            String managerName = (row.getCell(managerNameCell)==null)?null:row.getCell(managerNameCell).getStringCellValue();
            
            if(memberId == null || orderTime == null || name == null || phone == null || cashAmount == null){
            	continue;
            }else{
            	revisitModel.setMemberId(memberId);
            	revisitModel.setOrderTime(orderTime);
            	revisitModel.setName(name);
            	revisitModel.setPhone(phone);
            	revisitModel.setCashAmount(cashAmount);
            	revisitModel.setFeedback(feedback);
            	revisitModel.setManagerName(managerName);
            }
            modelList.add(revisitModel);
        }
		int result = cashRevisitInfoService.saveRevisitNote(modelList);
		if(result > 0){
			return "Success";
		}else{
			return "Fail";
		}
	}
}	
