package com.app.excelcsvreadwriteapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import com.app.excelcsvreadwriteapp.entity.User;
import com.app.excelcsvreadwriteapp.service.CsvService;

@Controller
public class CsvController {
	
	@Autowired
	CsvService service;
	
	@GetMapping("/downloadCsv")
    public void downloadCsv(HttpServletResponse response) throws IOException {
		try {
			 
	        response.setContentType("text/csv");
	        response.setHeader("Content-Disposition", "attachment; filename=users.csv");
			
	        // write to csv file //
	        
	        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),CsvPreference.STANDARD_PREFERENCE);
	                
	 
	        String[]  headings = {"First Name","Last Name","Email","Address"};
	        
	        String[] pojoclassPropertyName = { "firstName", "lastName", "email", "address"};
	              
	 
	        csvWriter.writeHeader(headings);
	        
	        List<User> userList =createUserData();

	        
	        if(null!=userList && !userList.isEmpty()){
	            for (User user : userList) {
	                csvWriter.write(user, pojoclassPropertyName);
	              }
	            }
	        csvWriter.close();

			}catch(Exception e) {
				e.printStackTrace();

			}
        System.out.println("csv report downloaded successfully...........");
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
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	@GetMapping("/readCsv")
	
	public @ResponseBody String readExcel() throws IOException {
	
		String path = "C://Users//lipsa//Downloads//user.csv" ;

		service.readDataFromCsv(path);

		return "read data from csv done !!";
	}

}
