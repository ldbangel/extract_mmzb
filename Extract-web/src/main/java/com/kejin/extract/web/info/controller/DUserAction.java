package com.kejin.extract.web.info.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kejin.extract.common.utils.JsonUtil;
import com.kejin.extract.domainservice.user.UserInfoDetailService;

/**
 * @Author Leo
 * define some actions for DUserModel
 */
@Controller
@Transactional
public class DUserAction {

    @Resource(name = "userInfoDetailService")
    private UserInfoDetailService userInfoDetailService;

    /**
     * update the financialManager column in table 'd_user' with the memberId column
     */
    @RequestMapping(value = "/user/updateFinancialManager.htm", method = RequestMethod.POST)
    @Transactional
    public @ResponseBody String updateFinancialManagerByMemberId(
    		@RequestParam("file") MultipartFile file, @RequestParam("style") String style) 
    				throws IOException, InvalidFormatException {
        Map<String, String> vo = JsonUtil.jsonToMap(style);
        if (vo == null || vo.isEmpty()) {
            return "failed!,please set the style value like {\"phone\":1,\"memberId\":2,\"financialManager\":3}";
        }
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        int totalRows = sheet.getPhysicalNumberOfRows();
        if (totalRows < 1) {
            return "the first excel sheet is empty!";
        }
        Integer mobileCell = (vo.get("phone") == null) ? null : Integer.valueOf(vo.get("phone"));
        Integer memberIdCell = (vo.get("memberId") == null) ? null : Integer.valueOf(vo.get("memberId"));
        Integer financialManagerCell = (vo.get("financialManager") == null) ? null : Integer.valueOf(vo.get("financialManager"));
        Integer userSourceCell = (vo.get("userSource") == null) ? null : Integer.valueOf(vo.get("userSource"));
        
        int header = (vo.get("header") == null) ? 1 : Integer.valueOf(vo.get("header"));
        for (int r = header; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            if (financialManagerCell == null) {
                return "failed! you didn`t set an financialManager value";
            }
            String financialManager = (row.getCell(financialManagerCell - 1)==null)?null:row.getCell(financialManagerCell - 1).getStringCellValue();
            String userSource = (row.getCell(userSourceCell - 1)==null)?null:row.getCell(userSourceCell - 1).getStringCellValue();
            if (memberIdCell != null && memberIdCell <= row.getPhysicalNumberOfCells() && memberIdCell > 0) {
            	row.getCell(memberIdCell - 1).setCellType(Cell.CELL_TYPE_STRING);
                String memberId = row.getCell(memberIdCell - 1).getStringCellValue();
                if ("Admin".equals(vo.get("mode"))){
                    userInfoDetailService.updateFinancialManagerByMemberId(memberId, financialManager, userSource);
                } else {
                    userInfoDetailService.addFinancialManagerByMemberId(memberId,financialManager, userSource);
                }
            } else if (mobileCell != null && mobileCell <= row.getPhysicalNumberOfCells() && mobileCell > 0) {
            	row.getCell(mobileCell - 1).setCellType(Cell.CELL_TYPE_STRING);
                String mobile = row.getCell(mobileCell - 1).getStringCellValue();
                if ("Admin".equals(vo.get("mode"))){
                    userInfoDetailService.updateFinancialManagerByMobile(mobile, financialManager, userSource);
                } else {
                    userInfoDetailService.addFinancialManagerByMobile(mobile, financialManager, userSource);
                }
            }
        }
        return "Success!";
    }

    /**
     * find out the memberId that is not valid in file
     */
    @RequestMapping(value = "/user/memberNotValid.htm", method = RequestMethod.POST)
    public @ResponseBody
    String findOutInvalidMemberIds(HttpServletRequest req,
                                   HttpServletResponse resp,
                                   @RequestParam("file") MultipartFile file,
                                   @RequestParam("style") String style) throws IOException, InvalidFormatException {
        List<String> list = new ArrayList<String>();
        Map<String, String> vo = JsonUtil.jsonToMap(style);
        if (vo == null || vo.isEmpty()) {
            list.add("failed!,please set the style value like {\"phone\":1,\"memberId\":2,\"financialManager\":3}");
            return list.toString();
        }
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        int totalRows = sheet.getPhysicalNumberOfRows();
        if (totalRows < 1) {
            list.add("the first excel sheet is empty!");
            return list.toString();
        }
        Integer memberIdCell = (vo.get("memberId") == null) ? null : Integer.valueOf(vo.get("memberId"));
        int header = (vo.get("header") == null) ? 1 : Integer.valueOf(vo.get("header"));
        for (int r = header; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            if (memberIdCell != null && memberIdCell <= row.getPhysicalNumberOfCells() && memberIdCell > 0) {
                String memberId = row.getCell(memberIdCell - 1).getStringCellValue();
                if (!userInfoDetailService.memberIdExists(memberId)) {
                    list.add(memberId);
                }
            }
        }
        return list.toString();
    }

}
