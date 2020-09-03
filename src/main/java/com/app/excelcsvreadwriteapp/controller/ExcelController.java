package com.app.excelcsvreadwriteapp.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.excelcsvreadwriteapp.entity.User;
import com.app.excelcsvreadwriteapp.service.ExcelExportService;

@Controller
public class ExcelController {
	
	@Autowired
	ExcelExportService excelExportService;
	
	@GetMapping("/")
    public String index() {
        return "index";
    }
	
	
	
	
	
	
	@GetMapping("/download/user-report")
    public void downloadExcel(HttpServletResponse response) throws IOException {
		
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=user.xlsx");
        ByteArrayInputStream stream = excelExportService.userListToExcelFile(createUserData());
        IOUtils.copy(stream, response.getOutputStream());
        System.out.println("excel report downloaded successfully...........");
    }
	
	
	
	

	private List<User> createUserData() {

		List<User> users = new ArrayList<User>();
		users.add(new User("Lipsa", "Patra", "BBSR","abc@gmail.com" ));
		users.add(new User("Ravish", "Sharma", "Banglore","ravi@gmail.com"));
		users.add(new User("Julia", "Robert",  "Amsterdam","robert@gmail.com"));
		users.add(new User("Meghna", "Morkel", "London","megha@gmail.com"));
		users.add(new User("Morish", "Harison",  "USA","mharison@yahoo.co.in"));
    	return users;
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	/*read from excel*/
	
	
	@GetMapping("/readExcel")
	
	public @ResponseBody List<User> readExcel() {
	
	
	String path = "C://Users//lipsa//Desktop//user.xlsx" ;

	List<User> userList  = excelExportService.ReadDataFromExcel(path);
		return userList;
	}

}


