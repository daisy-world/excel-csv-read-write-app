package com.app.excelcsvreadwriteapp.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.app.excelcsvreadwriteapp.entity.User;

public interface ExcelExportService {
	
	public  ByteArrayInputStream userListToExcelFile(List<User> users);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public List<User> ReadDataFromExcel(String path);

}
