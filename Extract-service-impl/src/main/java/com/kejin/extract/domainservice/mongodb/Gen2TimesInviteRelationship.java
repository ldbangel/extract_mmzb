package com.kejin.extract.domainservice.mongodb;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONObject;

public class Gen2TimesInviteRelationship {
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis() ;
		InputStream is = new FileInputStream("D:/newInvite.xlsx");
		XSSFWorkbook  excel = null;
		excel = new XSSFWorkbook(is);
		XSSFSheet sheet = excel.getSheetAt(0);
		int totalRows = sheet.getPhysicalNumberOfRows();
		if (totalRows < 1) {
            System.out.println("the first excel sheet is empty!");
            return;
        }
		long endtime1 = System.currentTimeMillis() ;
		System.out.println("excel读取时间为："+(endtime1-startTime));
		List<RelationshipModel> allRelationshipList = new ArrayList<RelationshipModel>();
		for (int r = 0; r < totalRows; r++) {
			RelationshipModel model = new RelationshipModel();
			XSSFRow row = sheet.getRow(r);
            if (row == null || row.getCell(0)==null) {
                continue;
            }
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            if(row.getCell(1)!=null){
            	row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            }
            if(row.getCell(2)!=null){
            	row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            }
            if(row.getCell(3)!=null){
            	row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            }
            if(row.getCell(4)!=null){
            	row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
            }
            model.setMemberId(row.getCell(0)==null?null:row.getCell(0).getStringCellValue());
            model.setName(row.getCell(1)==null?null:row.getCell(1).getStringCellValue());
            //model.setPhone(row.getCell(2)==null?null:row.getCell(2).getStringCellValue());
            model.setFcode(row.getCell(2)==null?null:row.getCell(2).getStringCellValue());
            List<RelationshipModel> list = new ArrayList<RelationshipModel>();
            RelationshipModel invited  = new RelationshipModel();
            invited.setMemberId(row.getCell(3)==null?null:row.getCell(3).getStringCellValue());
            invited.setName(row.getCell(4)==null?null:row.getCell(4).getStringCellValue());
            //invited.setPhone(row.getCell(6)==null?null:row.getCell(6).getStringCellValue());
            list.add(invited);
            model.setInviteList(list);
            allRelationshipList.add(model);
		}
		long endtime2 = System.currentTimeMillis() ;
		System.out.println("hello is ok");
		System.out.println("遍历时间为："+(endtime2-endtime1));
	
		
		List<RelationshipModel> resultList = getRelationship(allRelationshipList);
		System.out.println("一次邀请总共:" + resultList.size());
		
		Map<String,RelationshipModel> relaMap = new HashMap<String,RelationshipModel>(); 
		StringBuffer sb = new StringBuffer();
		for(RelationshipModel model1 : resultList){
			relaMap.put(model1.getMemberId(), model1);
			sb.append(model1.getMemberId()).append(",");
		}
		for(RelationshipModel mo : resultList){
			if(mo.getInviteList() != null){
				for(RelationshipModel m : mo.getInviteList()){
					if(sb.toString().contains(m.getMemberId())){
						if(m.getInviteList()==null){
							m.setInviteList(new ArrayList<RelationshipModel>());
						}
						m.getInviteList().addAll(relaMap.get(m.getMemberId()).getInviteList());
					}
				}
			}
		}
		long endtime3 = System.currentTimeMillis() ;
		System.out.println("总时间为："+(endtime3-startTime));
		
		for(RelationshipModel mo : resultList){
			String json = JSONObject.toJSONString(mo);
			MongodbOperateService.insert(json);
		}
	}
	
	private static List<RelationshipModel> getRelationship(List<RelationshipModel> relationshipList){
		List<RelationshipModel> resultList = new ArrayList<RelationshipModel>();
		StringBuffer sb = new StringBuffer();
		for(RelationshipModel model : relationshipList){
			if(sb.toString().contains(model.getMemberId())){
				for(RelationshipModel rm : resultList){
					if(model.getMemberId().equals(rm.getMemberId())){
						rm.getInviteList().addAll(model.getInviteList());
						break;
					}
				}
			}else{
				sb.append(model.getMemberId()).append(",");
				resultList.add(model);
			}
		}
		return resultList;
	}
}
