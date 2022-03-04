package com.app.excelcsvreadwriteapp.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ParseLong;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.StrRegEx;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.app.excelcsvreadwriteapp.entity.User;
@Service
public class CsvServiceImpl implements CsvService {

	

	@Override
	public void readDataFromCsv(String path) throws IOException {
		 ICsvBeanReader beanReader = null;
	        try {
	            beanReader = new CsvBeanReader(new FileReader(path), CsvPreference.STANDARD_PREFERENCE);

	            // the header elements are used to map the values to the bean (names must match)
	            final String[] header = beanReader.getHeader(true);
	            //final CellProcessor[] processors = getProcessors();
	            CellProcessor[] processors = getProcessors();
	            
	            User user;
	            while ((user = beanReader.read(User.class, header,processors)) != null) {
	                // process user
	                System.out.println(user);
	            }
	        } finally {
	            if (beanReader != null) {
	                beanReader.close();
	            }
	        }
	}
	
	 private static CellProcessor[] getProcessors() {
	        final String emailRegex = "[a-z0-9\\._]+@[a-z0-9\\.]+";
	        StrRegEx.registerMessage(emailRegex, "must be a valid email address");
	 
	        final CellProcessor[] processors = new CellProcessor[] {
	                new NotNull(), // First Name
	                new NotNull(), // Last Name
	                new StrRegEx(emailRegex), // Email

	                new NotNull() // address
	        };
	        return processors;
	    }
	 


}
