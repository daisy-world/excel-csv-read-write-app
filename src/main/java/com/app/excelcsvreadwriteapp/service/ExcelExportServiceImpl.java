package com.app.excelcsvreadwriteapp.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.app.excelcsvreadwriteapp.entity.User;

@Service
public class ExcelExportServiceImpl implements ExcelExportService {

	@Override
	public ByteArrayInputStream userListToExcelFile(List<User> users) {
		
		  String[] row_heading = {"First Name","Last Name","Email","Address"};


		try{
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("Users");
			
			Row headerRow = sheet.createRow(0);
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
	        
		    Font font = workbook.createFont();
	        font.setColor(IndexedColors.RED.getIndex());
	        headerCellStyle.setFont(font);
	        
	        // Creating header
	        for (int i = 0; i < row_heading.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(row_heading[i]);
				cell.setCellStyle(headerCellStyle);
			}
	        
	        // Creating data rows for each user
	        for(int i = 0; i < users.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue(users.get(i).getFirstName());
	        	dataRow.createCell(1).setCellValue(users.get(i).getLastName());
	        	dataRow.createCell(2).setCellValue(users.get(i).getEmail());
	        	dataRow.createCell(3).setCellValue(users.get(i).getAddress());
	        }
	
	        // Making size of column auto resize to fit with data
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public List<User> ReadDataFromExcel(String path) {

		List<User> userList = new ArrayList<User>();
		
		try {
			Workbook workbook = WorkbookFactory.create(new File(path));
			
			// Retrieving the number of sheets in the Workbook
	        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
	        System.out.println("Retrieving Sheets using for-each loop");
	        for(Sheet sheet: workbook) {
	        	
	        	
	        	int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	        	System.out.println("rowCount........ "  +  rowCount);
	        	 for (int i=1; i<=rowCount; i++) {
	        		 Row row = sheet.getRow(i);
	        		 System.out.println("no of rows.... "  +  row.getRowNum() );
	        		 String firstName = row.getCell(0).getStringCellValue();
	        		 String lastName = row.getCell(1).getStringCellValue();
	        		 String email = row.getCell(2).getStringCellValue();
	        		 String address = row.getCell(3).getStringCellValue();
	             	
	                 System.out.println("firstName........ "  + firstName);	
	                 
	                 User user = new User(firstName, lastName, email, address);
	                 userList.add(user);
	                 
	        	 }
	        	
	        }
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userList;
	
		
		
	}
	
	
	
	
	
	
	
	}
	
	


