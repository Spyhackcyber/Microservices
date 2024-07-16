package com.example.demo.utils;

import java.io.InputStream;
import java.lang.runtime.SwitchBootstraps;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.ExcelUploadEmployee;
import com.example.demo.entity.tbl_resource_pool_history;

public class ExcelUtils {

	
	//Check File is of Excel Type or not
public static boolean CheckExcelFormat(MultipartFile file) {
	
	String contentType=file.getContentType();
	if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
		return true;
	}else {
		return false;
	}
}

//Convert Excel to List of Employee
@SuppressWarnings("resource")
public static List<tbl_resource_pool_history> convertExceltoListofEmployee(InputStream is){
	
	List<tbl_resource_pool_history> Emplist=new ArrayList<>();
	
	try {
		
		XSSFWorkbook workbook =new XSSFWorkbook(is);
		//workbook.getSheetAt("data");
		XSSFSheet sheet=workbook.getSheetAt(0);
		
		int rowNumber=0;
		
		Iterator<Row> iterator=sheet.iterator();
		while(iterator.hasNext()) {
			
			Row row= iterator.next();
			if(rowNumber==0) {
				rowNumber++;
				continue;
			}
			
		Iterator<Cell> cells=	row.iterator();
		
		int cid=0;
		
		tbl_resource_pool_history  tbl_resource_pool_history =new tbl_resource_pool_history();
		
	//	ExcelUploadEmployee ExcelEmp=new ExcelUploadEmployee();
		
		while (cells.hasNext())
		{
			Cell cell =cells.next();
			
			System.out.println(cell);
			
			switch(cid) {
			case 0: 
			//	ExcelEmp.setId((int)cell.getNumericCellValue());
				tbl_resource_pool_history.setVchResourceCode(cell.getStringCellValue());
			break;
			case 1: 
				//ExcelEmp.setEmp_name(cell.getStringCellValue());
				tbl_resource_pool_history.setVchResourceName(cell.getStringCellValue());
			break;
			case 2: 
				//ExcelEmp.setEmp_email(cell.getStringCellValue());
				tbl_resource_pool_history.setVchPlatform(cell.getStringCellValue());
			break;
			case 3:
				//ExcelEmp.setEmp_phone(cell.getStringCellValue().toString());
				tbl_resource_pool_history.setVchEmail(cell.getStringCellValue());
			break;
			case 4: 
				//ExcelEmp.setEmp_location(cell.getStringCellValue())
				tbl_resource_pool_history.setVchPhoneNo(cell.getStringCellValue());
			break;
			case 5: 
				//ExcelEmp.setEmp_location(cell.getStringCellValue())
				tbl_resource_pool_history.setVchLocation(cell.getStringCellValue());
			break;
			case 6: 
				//ExcelEmp.setEmp_location(cell.getStringCellValue())
				tbl_resource_pool_history.setVchEngagementPlan(cell.getStringCellValue());
			break;
			case 7: 
				//ExcelEmp.setEmp_location(cell.getStringCellValue())
				tbl_resource_pool_history.setVchExperience(cell.getStringCellValue());
			break;
			case 8: 
				//ExcelEmp.setEmp_location(cell.getStringCellValue())
				tbl_resource_pool_history.setDtmAllocationDate(cell.getDateCellValue());
			break;
			default:
				break;
			}
			cid++;
			tbl_resource_pool_history.setBitDeletedFlag("0"); 
			
		}
		
		Emplist.add(tbl_resource_pool_history);
		
		}
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	return Emplist;
}
	
}
