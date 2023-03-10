package com.example.demo;


import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.apache.poi.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class controller {

	@GetMapping("/hel")
    JSONArray return1(){
		ResultSet rs = null;
		//Creating a JSONObject object
	      JSONObject jsonObject = new JSONObject();
	      //Creating a json array
	      JSONArray array = new JSONArray();
	      
	      Workbook wb = new HSSFWorkbook();
	      
	      org.apache.poi.ss.usermodel.Sheet sheet =  wb.createSheet();
	      Row row = sheet.createRow(0);
	      
	      Cell cell = row.createCell(0);
	      cell.setCellValue("CONFIGVALUE");
	      Cell cell1 = row.createCell(1);
	      cell1.setCellValue("CANVAL");
	      
	      
	      /*
	      try {
			FileOutputStream fileout = new FileOutputStream("C:\\Users\\BANERS22\\Documents\\test.xlsx");
			try {
				wb.write(fileout);
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	      
	    		  
	      
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@unxs0615.ghanp.kfplc.com:1527/FTIIB101","baners22[MBREPOS]","Ae12#627D39Ns4mA");  
			  
			//step3 create the statement object  
			Statement stmt=con.createStatement();
			
			rs = stmt.executeQuery("Select * from MBCONFIG");
			
			
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		  
		  
		  
		
        try {
        	
        	int rownum = 1;
			int colnum = 0;
			
        	
        	while(rs.next()) {
        		JSONObject record = new JSONObject();
                //Inserting key-value pairs into the json object
                record.put("Config", rs.getString("CONFIGVALUE"));
                record.put("Canval", rs.getString("CANVAL"));
                array.add(record);
                
                Row rowN = sheet.createRow(rownum++);
      	      
      	      	Cell cellN = rowN.createCell(0);
      	      	cellN.setCellValue(rs.getString("CONFIGVALUE"));
      	      	Cell cellN1 = rowN.createCell(1);
      	      	cellN1.setCellValue(rs.getString("CANVAL"));
                
        	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        try {
			FileOutputStream fileout = new FileOutputStream("C:\\Users\\BANERS22\\Documents\\test.xls");
			try {
				wb.write(fileout);
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
		return array;
    }
	
	
	
	
	@GetMapping("/test_vendor")
    JSONArray return2(){
        
        ResultSet rs = null;
		//Creating a JSONObject object
	      JSONObject jsonObject = new JSONObject();
	      //Creating a json array
	      JSONArray array = new JSONArray();
	      String[] ColName = {"OPCO_ID","LEGACY_VENDOR_NUMBER_GFR","SUBRANGE_NUMBER","ECC_VENDOR_NUMBER","VENDOR_TYPE","VENDOR_ACCOUNT_GROUP","CASE","CROSS_FLAG","DI_TIMESTAMP"};
	      
	      Workbook wb = new HSSFWorkbook();
	      
	      org.apache.poi.ss.usermodel.Sheet sheet =  wb.createSheet();
	      Row row = sheet.createRow(0);
	      
	      
	      for(int i = 0; i < ColName.length; i++) {
	    	  
	    	  Cell cell = row.createCell(i);
	    	  cell.setCellValue(ColName[i]);
	      
	      }
	     
	      
	      
	      /*
	      try {
			FileOutputStream fileout = new FileOutputStream("C:\\Users\\BANERS22\\Documents\\test.xlsx");
			try {
				wb.write(fileout);
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	      
	    		  
	      
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@unxs0486.ghanp.kfplc.com:1527/SSTG1.WORLD","baners22[MBROKER_ODS_BDFR]","BAERG23#nScue22O");  
			  
			//step3 create the statement object  
			Statement stmt=con.createStatement();
			
			rs = stmt.executeQuery("Select * from ECCLEGACYVENDOR_SYNC");
			
			
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		  
		  
		  
        try {
        	
        	int rownum = 1;
			int colnum = 0;
			
        	
        	while(rs.next()) {
        		//JSONObject record = new JSONObject();
                //Inserting key-value pairs into the json object
                //record.put("Config", rs.getString("CONFIGVALUE"));
                //record.put("Canval", rs.getString("CANVAL"));
                //array.add(record);
                
                Row rowN = sheet.createRow(rownum++);
      	      
                
                for(int i = 0; i < ColName.length; i++) {
      	    	  
      	    	  Cell cell = rowN.createCell(i);
      	    	  cell.setCellValue(rs.getNString(ColName[i]));
      	      
      	      }
                
                
        	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        try {
			FileOutputStream fileout = new FileOutputStream("C:\\Users\\BANERS22\\Documents\\ECCLEGACYVENDOR_SYNC.xls");
			try {
				wb.write(fileout);
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
		return array;
        
        
        
        }
	
	
	
	/*
    @GetMapping(value = "/get-file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public String getFile() throws IOException {
        final InputStream in = getClass().getResourceAsStream("/com/baeldung/produceimage/data.txt");
        return "hello";
    }
    
    */
	
	@CrossOrigin(allowedHeaders = "Access-Control-Allow-Origin")
    @RequestMapping(value = {"/fileDownload"}, method = RequestMethod.GET )
    public ResponseEntity<Resource> getFile() throws IOException {
        MediaType contentType = MediaType.APPLICATION_OCTET_STREAM ;
        /*InputStream in = getClass()
          .getResourceAsStream("C:\\downloads\\mtputty_export.xml");*/
        InputStreamResource resource = new InputStreamResource(new FileInputStream(new File("C:\\Users\\BANERS22\\Documents\\ECCLEGACYVENDOR_SYNC.xls")));
       
        return ResponseEntity.ok()
        		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=abc.xls")
                  .contentType(contentType)
                  .body(resource);
        
    }
	
	
	@CrossOrigin(allowedHeaders = "Access-Control-Allow-Origin")
	@GetMapping("/test_vendor_V")
	public ResponseEntity<Resource> vendor() throws IOException {
        
        ResultSet rs = null;
		//Creating a JSONObject object
	      JSONObject jsonObject = new JSONObject();
	      //Creating a json array
	      JSONArray array = new JSONArray();
	      String[] ColName = {"OPCO_ID","LEGACY_VENDOR_NUMBER_GFR","SUBRANGE_NUMBER","ECC_VENDOR_NUMBER","VENDOR_TYPE","VENDOR_ACCOUNT_GROUP","CASE","CROSS_FLAG","DI_TIMESTAMP"};
	      
	      Workbook wb = new HSSFWorkbook();
	      
	      org.apache.poi.ss.usermodel.Sheet sheet =  wb.createSheet();
	      Row row = sheet.createRow(0);
	      
	      
	      for(int i = 0; i < ColName.length; i++) {
	    	  
	    	  Cell cell = row.createCell(i);
	    	  cell.setCellValue(ColName[i]);
	      
	      }
	     
	      
	      
	      /*
	      try {
			FileOutputStream fileout = new FileOutputStream("C:\\Users\\BANERS22\\Documents\\test.xlsx");
			try {
				wb.write(fileout);
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	      
	    		  
	      
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@unxs0486.ghanp.kfplc.com:1527/SSTG1.WORLD","baners22[MBROKER_ODS_BDFR]","BAERG23#nScue22O");  
			  
			//step3 create the statement object  
			Statement stmt=con.createStatement();
			
			rs = stmt.executeQuery("Select * from ECCLEGACYVENDOR_SYNC");
			
			
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		  
		  
		  
        try {
        	
        	int rownum = 1;
			int colnum = 0;
			
        	
        	while(rs.next()) {
        		//JSONObject record = new JSONObject();
                //Inserting key-value pairs into the json object
                //record.put("Config", rs.getString("CONFIGVALUE"));
                //record.put("Canval", rs.getString("CANVAL"));
                //array.add(record);
                
                Row rowN = sheet.createRow(rownum++);
      	      
                
                for(int i = 0; i < ColName.length; i++) {
      	    	  
      	    	  Cell cell = rowN.createCell(i);
      	    	  cell.setCellValue(rs.getNString(ColName[i]));
      	      
      	      }
                
                
        	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        try {
			FileOutputStream fileout = new FileOutputStream("ECCLEGACYVENDOR_SYNC.xls");
			try {
				wb.write(fileout);
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
        MediaType contentType = MediaType.APPLICATION_OCTET_STREAM ;
        /*InputStream in = getClass()
          .getResourceAsStream("C:\\downloads\\mtputty_export.xml");*/
        InputStreamResource resource = new InputStreamResource(new FileInputStream(new File("ECCLEGACYVENDOR_SYNC.xls")));
       
        return ResponseEntity.ok()
        		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=ECCLEGACYVENDOR_SYNC_V_BDFR.xls")
                  .contentType(contentType)
                  .body(resource);
        
        
        
        }
	


	@CrossOrigin(allowedHeaders = "Access-Control-Allow-Origin")
	@GetMapping("/test_crossdock_F")
	public ResponseEntity<Resource> crossdock() throws IOException {
        
        ResultSet rs = null;
		//Creating a JSONObject object
	      JSONObject jsonObject = new JSONObject();
	      //Creating a json array
	      JSONArray array = new JSONArray();
	      String[] ColName = {"SAP_STORE_CODE","PLATFORM_GLN_CODE","REMOVAL_DAYS","SAP_VENDOR_CODE","PURCHASE_GROUP"};
	      
	      Workbook wb = new HSSFWorkbook();
	      
	      org.apache.poi.ss.usermodel.Sheet sheet =  wb.createSheet();
	      Row row = sheet.createRow(0);
	      
	      
	      for(int i = 0; i < ColName.length; i++) {
	    	  
	    	  Cell cell = row.createCell(i);
	    	  cell.setCellValue(ColName[i]);
	      
	      }
	     
	      
	      
	      /*
	      try {
			FileOutputStream fileout = new FileOutputStream("C:\\Users\\BANERS22\\Documents\\test.xlsx");
			try {
				wb.write(fileout);
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	      
	    		  
	      
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@unxs0615.ghanp.kfplc.com:1527/FTIIB101","baners22[MBREPOS]","Ae12#627D39Ns4mA");  
			  
			//step3 create the statement object  
			Statement stmt=con.createStatement();
			
			rs = stmt.executeQuery("Select * from crossdock_platform_details");
			
			
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		  
		  
		  
        try {
        	
        	int rownum = 1;
			int colnum = 0;
			
        	
        	while(rs.next()) {
        		//JSONObject record = new JSONObject();
                //Inserting key-value pairs into the json object
                //record.put("Config", rs.getString("CONFIGVALUE"));
                //record.put("Canval", rs.getString("CANVAL"));
                //array.add(record);
                
                Row rowN = sheet.createRow(rownum++);
      	      
                
                for(int i = 0; i < ColName.length; i++) {
      	    	  
      	    	  Cell cell = rowN.createCell(i);
      	    	  cell.setCellValue(rs.getNString(ColName[i]));
      	      
      	      }
                
                
        	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        try {
			FileOutputStream fileout = new FileOutputStream("C:\\Users\\BANERS22\\Documents\\CROSSDOCK_PLATFORM_DETAILS.xls");
			try {
				wb.write(fileout);
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
        MediaType contentType = MediaType.APPLICATION_OCTET_STREAM ;
        /*InputStream in = getClass()
          .getResourceAsStream("C:\\downloads\\mtputty_export.xml");*/
        InputStreamResource resource = new InputStreamResource(new FileInputStream(new File("C:\\Users\\BANERS22\\Documents\\CROSSDOCK_PLATFORM_DETAILS.xls")));
       
        
        return ResponseEntity.status(200)
        		
        		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=CROSSDOCK_PLATFORM_DETAILS.xls")
                .contentType(contentType)
                .body(resource);
                  

        
        
        
        }
	
	
	
	
	


	@CrossOrigin(allowedHeaders = "Access-Control-Allow-Origin")
	@GetMapping("/test_cross_F")
	public ResponseEntity<Resource> crossdock_real() throws IOException {
        
        ResultSet rs = null;
		//Creating a JSONObject object
	      JSONObject jsonObject = new JSONObject();
	      //Creating a json array
	      JSONArray array = new JSONArray();
	      String[] ColName = {"SAP_STORE_CODE","PLATFORM_GLN_CODE","REMOVAL_DAYS","SAP_VENDOR_CODE","PURCHASE_GROUP"};
	      
	      Workbook wb = new HSSFWorkbook();
	      
	      org.apache.poi.ss.usermodel.Sheet sheet =  wb.createSheet();
	      Row row = sheet.createRow(0);
	      
	      
	      for(int i = 0; i < ColName.length; i++) {
	    	  
	    	  Cell cell = row.createCell(i);
	    	  cell.setCellValue(ColName[i]);
	      
	      }
	     
	      
	      
	      /*
	      try {
			FileOutputStream fileout = new FileOutputStream("C:\\Users\\BANERS22\\Documents\\test.xlsx");
			try {
				wb.write(fileout);
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	      
	    		  
	      
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection(  
			"jdbc:oracle:thin:@unxs0615.ghanp.kfplc.com:1527/FTIIB101","baners22[MBREPOS]","Ae12#627D39Ns4mA");  
			  
			//step3 create the statement object  
			Statement stmt=con.createStatement();
			
			rs = stmt.executeQuery("Select * from crossdock_platform_details");
			
			
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		  
		  
		  
        try {
        	
        	int rownum = 1;
			int colnum = 0;
			
        	
        	while(rs.next()) {
        		//JSONObject record = new JSONObject();
                //Inserting key-value pairs into the json object
                //record.put("Config", rs.getString("CONFIGVALUE"));
                //record.put("Canval", rs.getString("CANVAL"));
                //array.add(record);
                
                Row rowN = sheet.createRow(rownum++);
      	      
                
                for(int i = 0; i < ColName.length; i++) {
      	    	  
      	    	  Cell cell = rowN.createCell(i);
      	    	  cell.setCellValue(rs.getNString(ColName[i]));
      	      
      	      }
                
                
        	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        try {
			FileOutputStream fileout = new FileOutputStream("CROSSDOCK_PLATFORM_DETAILS_F.xls");
			try {
				wb.write(fileout);
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
        MediaType contentType = MediaType.APPLICATION_OCTET_STREAM ;
        /*InputStream in = getClass()
          .getResourceAsStream("C:\\downloads\\mtputty_export.xml");*/
        InputStreamResource resource = new InputStreamResource(new FileInputStream(new File("CROSSDOCK_PLATFORM_DETAILS_F.xls")));
       
        
        return ResponseEntity.status(200)
        		
        		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=CROSSDOCK_PLATFORM_DETAILS_F.xls")
                .contentType(contentType)
                .body(resource);
                  

        
        
        
        }
	
	
	
	





@CrossOrigin(allowedHeaders = "Access-Control-Allow-Origin")
@GetMapping("/test_cross_V")
public ResponseEntity<Resource> crossdock_real_1() throws IOException {
    
    ResultSet rs = null;
	//Creating a JSONObject object
      JSONObject jsonObject = new JSONObject();
      //Creating a json array
      JSONArray array = new JSONArray();
      String[] ColName = {"SAP_STORE_CODE","PLATFORM_GLN_CODE","REMOVAL_DAYS","SAP_VENDOR_CODE","PURCHASE_GROUP"};
      
      Workbook wb = new HSSFWorkbook();
      
      org.apache.poi.ss.usermodel.Sheet sheet =  wb.createSheet();
      Row row = sheet.createRow(0);
      
      
      for(int i = 0; i < ColName.length; i++) {
    	  
    	  Cell cell = row.createCell(i);
    	  cell.setCellValue(ColName[i]);
      
      }
     
      
      
      /*
      try {
		FileOutputStream fileout = new FileOutputStream("C:\\Users\\BANERS22\\Documents\\test.xlsx");
		try {
			wb.write(fileout);
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/
      
    		  
      
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//step2 create  the connection object  
		Connection con=DriverManager.getConnection(  
		"jdbc:oracle:thin:@unxs0836.gha.kfplc.com:1527/PVTIIB","baners22[MBREPOS]","Ae12#627D39Ns4mA");  
		  
		//step3 create the statement object  
		Statement stmt=con.createStatement();
		
		rs = stmt.executeQuery("Select * from crossdock_platform_details");
		
		
		
		
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
	  
	  
	  
    try {
    	
    	int rownum = 1;
		int colnum = 0;
		
    	
    	while(rs.next()) {
    		//JSONObject record = new JSONObject();
            //Inserting key-value pairs into the json object
            //record.put("Config", rs.getString("CONFIGVALUE"));
            //record.put("Canval", rs.getString("CANVAL"));
            //array.add(record);
            
            Row rowN = sheet.createRow(rownum++);
  	      
            
            for(int i = 0; i < ColName.length; i++) {
  	    	  
  	    	  Cell cell = rowN.createCell(i);
  	    	  cell.setCellValue(rs.getNString(ColName[i]));
  	      
  	      }
            
            
    	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
    
    try {
		FileOutputStream fileout = new FileOutputStream("CROSSDOCK_PLATFORM_DETAILS_V.xls");
		try {
			wb.write(fileout);
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    
    MediaType contentType = MediaType.APPLICATION_OCTET_STREAM ;
    /*InputStream in = getClass()
      .getResourceAsStream("C:\\downloads\\mtputty_export.xml");*/
    InputStreamResource resource = new InputStreamResource(new FileInputStream(new File("CROSSDOCK_PLATFORM_DETAILS_V.xls")));
   
    
    return ResponseEntity.status(200)
    		
    		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=CROSSDOCK_PLATFORM_DETAILS_V.xls")
            .contentType(contentType)
            .body(resource);
              

    
    
    
    }








@CrossOrigin(allowedHeaders = "Access-Control-Allow-Origin")
@GetMapping("/test_cross_Q")
public ResponseEntity<Resource> crossdock_real_2() throws IOException {
    
    ResultSet rs = null;
	//Creating a JSONObject object
      JSONObject jsonObject = new JSONObject();
      //Creating a json array
      JSONArray array = new JSONArray();
      String[] ColName = {"SAP_STORE_CODE","PLATFORM_GLN_CODE","REMOVAL_DAYS","SAP_VENDOR_CODE","PURCHASE_GROUP"};
      
      Workbook wb = new HSSFWorkbook();
      
      org.apache.poi.ss.usermodel.Sheet sheet =  wb.createSheet();
      Row row = sheet.createRow(0);
      
      
      for(int i = 0; i < ColName.length; i++) {
    	  
    	  Cell cell = row.createCell(i);
    	  cell.setCellValue(ColName[i]);
      
      }
     
      
      
      /*
      try {
		FileOutputStream fileout = new FileOutputStream("C:\\Users\\BANERS22\\Documents\\test.xlsx");
		try {
			wb.write(fileout);
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/
      
    		  
      
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//step2 create  the connection object  
		Connection con=DriverManager.getConnection(  
		"jdbc:oracle:thin:@unxs0635.ghanp.kfplc.com:1527/QTIIB101","baners22[MBREPOS]","hdtGL*fjqb1#lcx");  
		  
		//step3 create the statement object  
		Statement stmt=con.createStatement();
		
		rs = stmt.executeQuery("Select * from crossdock_platform_details");
		
		
		
		
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
	  
	  
	  
    try {
    	
    	int rownum = 1;
		int colnum = 0;
		
    	
    	while(rs.next()) {
    		//JSONObject record = new JSONObject();
            //Inserting key-value pairs into the json object
            //record.put("Config", rs.getString("CONFIGVALUE"));
            //record.put("Canval", rs.getString("CANVAL"));
            //array.add(record);
            
            Row rowN = sheet.createRow(rownum++);
  	      
            
            for(int i = 0; i < ColName.length; i++) {
  	    	  
  	    	  Cell cell = rowN.createCell(i);
  	    	  cell.setCellValue(rs.getNString(ColName[i]));
  	      
  	      }
            
            
    	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
    
    try {
		FileOutputStream fileout = new FileOutputStream("CROSSDOCK_PLATFORM_DETAILS_Q.xls");
		try {
			wb.write(fileout);
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    
    MediaType contentType = MediaType.APPLICATION_OCTET_STREAM ;
    /*InputStream in = getClass()
      .getResourceAsStream("C:\\downloads\\mtputty_export.xml");*/
    InputStreamResource resource = new InputStreamResource(new FileInputStream(new File("CROSSDOCK_PLATFORM_DETAILS_Q.xls")));
   
    
    return ResponseEntity.status(200)
    		
    		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=CROSSDOCK_PLATFORM_DETAILS_Q.xls")
            .contentType(contentType)
            .body(resource);
              

    
    
    
    }









@CrossOrigin(allowedHeaders = "Access-Control-Allow-Origin")
@GetMapping("/test_cross_H")
public ResponseEntity<Resource> crossdock_real_3() throws IOException {
    
    ResultSet rs = null;
	//Creating a JSONObject object
      JSONObject jsonObject = new JSONObject();
      //Creating a json array
      JSONArray array = new JSONArray();
      String[] ColName = {"SAP_STORE_CODE","PLATFORM_GLN_CODE","REMOVAL_DAYS","SAP_VENDOR_CODE","PURCHASE_GROUP"};
      
      Workbook wb = new HSSFWorkbook();
      
      org.apache.poi.ss.usermodel.Sheet sheet =  wb.createSheet();
      Row row = sheet.createRow(0);
      
      
      for(int i = 0; i < ColName.length; i++) {
    	  
    	  Cell cell = row.createCell(i);
    	  cell.setCellValue(ColName[i]);
      
      }
     
      
      
      /*
      try {
		FileOutputStream fileout = new FileOutputStream("C:\\Users\\BANERS22\\Documents\\test.xlsx");
		try {
			wb.write(fileout);
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/
      
    		  
      
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//step2 create  the connection object  
		Connection con=DriverManager.getConnection(  
		"jdbc:oracle:thin:@unxs0673.ghanp.kfplc.com:1527/HTIIB101","baners22[MBREPOS]","Ae12#627D39Ns4mA");  
		  
		//step3 create the statement object  
		Statement stmt=con.createStatement();
		
		rs = stmt.executeQuery("Select * from crossdock_platform_details");
		
		
		
		
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
	  
	  
	  
    try {
    	
    	int rownum = 1;
		int colnum = 0;
		
    	
    	while(rs.next()) {
    		//JSONObject record = new JSONObject();
            //Inserting key-value pairs into the json object
            //record.put("Config", rs.getString("CONFIGVALUE"));
            //record.put("Canval", rs.getString("CANVAL"));
            //array.add(record);
            
            Row rowN = sheet.createRow(rownum++);
  	      
            
            for(int i = 0; i < ColName.length; i++) {
  	    	  
  	    	  Cell cell = rowN.createCell(i);
  	    	  cell.setCellValue(rs.getNString(ColName[i]));
  	      
  	      }
            
            
    	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
    
    try {
		FileOutputStream fileout = new FileOutputStream("CROSSDOCK_PLATFORM_DETAILS_H.xls");
		try {
			wb.write(fileout);
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    
    MediaType contentType = MediaType.APPLICATION_OCTET_STREAM ;
    /*InputStream in = getClass()
      .getResourceAsStream("C:\\downloads\\mtputty_export.xml");*/
    InputStreamResource resource = new InputStreamResource(new FileInputStream(new File("CROSSDOCK_PLATFORM_DETAILS_H.xls")));
   
    
    return ResponseEntity.status(200)
    		
    		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=CROSSDOCK_PLATFORM_DETAILS_H.xls")
            .contentType(contentType)
            .body(resource);
              

    
    
    
    }



@CrossOrigin(allowedHeaders = "Access-Control-Allow-Origin")
@GetMapping("/test_vendor_F")
public ResponseEntity<Resource> vendor2() throws IOException {
    
    ResultSet rs = null;
	//Creating a JSONObject object
      JSONObject jsonObject = new JSONObject();
      //Creating a json array
      JSONArray array = new JSONArray();
      String[] ColName = {"OPCO_ID","LEGACY_VENDOR_NUMBER_GFR","SUBRANGE_NUMBER","ECC_VENDOR_NUMBER","VENDOR_TYPE","VENDOR_ACCOUNT_GROUP","CASE","CROSS_FLAG","DI_TIMESTAMP"};
      
      //Workbook wb = new HSSFWorkbook();
      XSSFWorkbook wb = new XSSFWorkbook(); 
      
      org.apache.poi.ss.usermodel.Sheet sheet =  wb.createSheet();
      Row row = sheet.createRow(0);
      
      
      for(int i = 0; i < ColName.length; i++) {
    	  
    	  Cell cell = row.createCell(i);
    	  cell.setCellValue(ColName[i]);
      
      }
     
      
      
      /*
      try {
		FileOutputStream fileout = new FileOutputStream("C:\\Users\\BANERS22\\Documents\\test.xlsx");
		try {
			wb.write(fileout);
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/
      
    		  
      
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//step2 create  the connection object  
		Connection con=DriverManager.getConnection(  
		"jdbc:oracle:thin:@unxs0476.gha.kfplc.com:1527/QSTG1","baners22[MBROKER_ODS_BDFR]","BAERG23#nScue22O");  
		  
		//step3 create the statement object  
		Statement stmt=con.createStatement();
		
		rs = stmt.executeQuery("Select * from ECCLEGACYVENDOR_SYNC");
		
		
		
		
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
	  
	  
	  
    try {
    	
    	int rownum = 1;
		int colnum = 0;
		
    	
    	while(rs.next()) {
    		//JSONObject record = new JSONObject();
            //Inserting key-value pairs into the json object
            //record.put("Config", rs.getString("CONFIGVALUE"));
            //record.put("Canval", rs.getString("CANVAL"));
            //array.add(record);
            
            Row rowN = sheet.createRow(rownum++);
  	      
            
            for(int i = 0; i < ColName.length; i++) {
  	    	  
  	    	  Cell cell = rowN.createCell(i);
  	    	  cell.setCellValue(rs.getNString(ColName[i]));
  	      
  	      }
            
            
    	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
    
    try {
		FileOutputStream fileout = new FileOutputStream("ECCLEGACYVENDOR_SYNC.xls");
		try {
			wb.write(fileout);
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    
    MediaType contentType = MediaType.APPLICATION_OCTET_STREAM ;
    /*InputStream in = getClass()
      .getResourceAsStream("C:\\downloads\\mtputty_export.xml");*/
    InputStreamResource resource = new InputStreamResource(new FileInputStream(new File("ECCLEGACYVENDOR_SYNC.xls")));
   
    return ResponseEntity.ok()
    		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=ECCLEGACYVENDOR_SYNC_F_BDFR.xls")
              .contentType(contentType)
              .body(resource);
    
    
    
    }


@CrossOrigin(allowedHeaders = "Access-Control-Allow-Origin")
@GetMapping("/test_vendor_H")
public ResponseEntity<Resource> vendor1() throws IOException {
    
    ResultSet rs = null;
	//Creating a JSONObject object
      JSONObject jsonObject = new JSONObject();
      //Creating a json array
      JSONArray array = new JSONArray();
      String[] ColName = {"OPCO_ID","LEGACY_VENDOR_NUMBER_GFR","SUBRANGE_NUMBER","ECC_VENDOR_NUMBER","VENDOR_TYPE","VENDOR_ACCOUNT_GROUP","CASE","CROSS_FLAG","DI_TIMESTAMP"};
      
      Workbook wb = new HSSFWorkbook();
      
      org.apache.poi.ss.usermodel.Sheet sheet =  wb.createSheet();
      Row row = sheet.createRow(0);
      
      
      for(int i = 0; i < ColName.length; i++) {
    	  
    	  Cell cell = row.createCell(i);
    	  cell.setCellValue(ColName[i]);
      
      }
     
      
      
      /*
      try {
		FileOutputStream fileout = new FileOutputStream("C:\\Users\\BANERS22\\Documents\\test.xlsx");
		try {
			wb.write(fileout);
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/
      
    		  
      
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//step2 create  the connection object  
		Connection con=DriverManager.getConnection(  
		"jdbc:oracle:thin:@unxs0666.ghanp.kfplc.com:1527/HTSTG101","baners22[MBROKER_ODS_BDFR]","G7O5y6#kj5l4TC8L");  
		  
		//step3 create the statement object  
		Statement stmt=con.createStatement();
		
		rs = stmt.executeQuery("Select * from ECCLEGACYVENDOR_SYNC");
		
		
		
		
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
	  
	  
	  
    try {
    	
    	int rownum = 1;
		int colnum = 0;
		
    	
    	while(rs.next()) {
    		//JSONObject record = new JSONObject();
            //Inserting key-value pairs into the json object
            //record.put("Config", rs.getString("CONFIGVALUE"));
            //record.put("Canval", rs.getString("CANVAL"));
            //array.add(record);
            
            Row rowN = sheet.createRow(rownum++);
  	      
            
            for(int i = 0; i < ColName.length; i++) {
  	    	  
  	    	  Cell cell = rowN.createCell(i);
  	    	  cell.setCellValue(rs.getNString(ColName[i]));
  	      
  	      }
            
            
    	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
    
    try {
		FileOutputStream fileout = new FileOutputStream("ECCLEGACYVENDOR_SYNC.xls");
		try {
			wb.write(fileout);
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    
    MediaType contentType = MediaType.APPLICATION_OCTET_STREAM ;
    /*InputStream in = getClass()
      .getResourceAsStream("C:\\downloads\\mtputty_export.xml");*/
    InputStreamResource resource = new InputStreamResource(new FileInputStream(new File("ECCLEGACYVENDOR_SYNC.xls")));
   
    return ResponseEntity.ok()
    		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=ECCLEGACYVENDOR_SYNC_H_BDFR.xls")
              .contentType(contentType)
              .body(resource);
    
    
    
    }





@CrossOrigin(allowedHeaders = "Access-Control-Allow-Origin")
@GetMapping("/test_article_H")
public ResponseEntity<Resource> article() throws IOException {
    
    ResultSet rs = null;
	//Creating a JSONObject object
      JSONObject jsonObject = new JSONObject();
      //Creating a json array
      JSONArray array = new JSONArray();
      String[] ColName = {"OPCO_ID","EAN_CODE","PMM_ARTICLE_ID","PMM_UOM_CODE","PMM_EAN_PRIMARY","ECC_ARTICLE_ID","ECC_UOM_CODE","CREATED_DT","MODIFIED_DT","DELETED_FLAG"};
      
      Workbook wb = new HSSFWorkbook();
      
      org.apache.poi.ss.usermodel.Sheet sheet =  wb.createSheet();
      Row row = sheet.createRow(0);
      
      
      for(int i = 0; i < ColName.length; i++) {
    	  
    	  Cell cell = row.createCell(i);
    	  cell.setCellValue(ColName[i]);
      
      }
     
      
      
      /*
      try {
		FileOutputStream fileout = new FileOutputStream("C:\\Users\\BANERS22\\Documents\\test.xlsx");
		try {
			wb.write(fileout);
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/
      
    		  
      
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//step2 create  the connection object  
		Connection con=DriverManager.getConnection(  
		"jdbc:oracle:thin:@unxs0666.ghanp.kfplc.com:1527/HTSTG101","baners22[MBROKER_ODS_BDFR]","G7O5y6#kj5l4TC8L");  
		  
		//step3 create the statement object  
		Statement stmt=con.createStatement();
		
		rs = stmt.executeQuery("Select * from ECCLEGACYARTICLE_SYNC");
		
		
		
		
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
	  
	  
	  
    try {
    	
    	int rownum = 1;
		int colnum = 0;
		
    	
    	while(rs.next()) {
    		//JSONObject record = new JSONObject();
            //Inserting key-value pairs into the json object
            //record.put("Config", rs.getString("CONFIGVALUE"));
            //record.put("Canval", rs.getString("CANVAL"));
            //array.add(record);
            
            Row rowN = sheet.createRow(rownum++);
  	      
            
            for(int i = 0; i < ColName.length; i++) {
  	    	  
  	    	  Cell cell = rowN.createCell(i);
  	    	  cell.setCellValue(rs.getNString(ColName[i]));
  	      
  	      }
            
            
    	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
    
    try {
		FileOutputStream fileout = new FileOutputStream("ECCLEGACYARTICLE_SYNC.xls");
		try {
			wb.write(fileout);
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    
    MediaType contentType = MediaType.APPLICATION_OCTET_STREAM ;
    /*InputStream in = getClass()
      .getResourceAsStream("C:\\downloads\\mtputty_export.xml");*/
    InputStreamResource resource = new InputStreamResource(new FileInputStream(new File("ECCLEGACYARTICLE_SYNC.xls")));
   
    return ResponseEntity.ok()
    		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=ECCLEGACYARTICLE_SYNC_H_BDFR.xls")
              .contentType(contentType)
              .body(resource);
    
    
    
    }




@CrossOrigin(allowedHeaders = "Access-Control-Allow-Origin")
@GetMapping("/test_article_Q")
public ResponseEntity<Resource> article1() throws IOException {
    
    ResultSet rs = null;
	//Creating a JSONObject object
      JSONObject jsonObject = new JSONObject();
      //Creating a json array
      JSONArray array = new JSONArray();
      String[] ColName = {"OPCO_ID","EAN_CODE","PMM_ARTICLE_ID","PMM_UOM_CODE","PMM_EAN_PRIMARY","ECC_ARTICLE_ID","ECC_UOM_CODE","CREATED_DT","MODIFIED_DT","DELETED_FLAG"};
      
      Workbook wb = new HSSFWorkbook();
      
      org.apache.poi.ss.usermodel.Sheet sheet =  wb.createSheet();
      Row row = sheet.createRow(0);
      
      
      for(int i = 0; i < ColName.length; i++) {
    	  
    	  Cell cell = row.createCell(i);
    	  cell.setCellValue(ColName[i]);
      
      }
     
      
      
      /*
      try {
		FileOutputStream fileout = new FileOutputStream("C:\\Users\\BANERS22\\Documents\\test.xlsx");
		try {
			wb.write(fileout);
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/
      
    		  
      
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//step2 create  the connection object  
		Connection con=DriverManager.getConnection(  
		"jdbc:oracle:thin:@unxs0666.ghanp.kfplc.com:1527/HTSTG101","baners22[MBROKER_ODS_BDFR]","G7O5y6#kj5l4TC8L");  
		  
		//step3 create the statement object  
		Statement stmt=con.createStatement();
		
		rs = stmt.executeQuery("Select * from ECCLEGACYARTICLE_SYNC");
		
		
		
		
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
	  
	  
	  
    try {
    	
    	int rownum = 1;
		int colnum = 0;
		
    	
    	while(rs.next()) {
    		//JSONObject record = new JSONObject();
            //Inserting key-value pairs into the json object
            //record.put("Config", rs.getString("CONFIGVALUE"));
            //record.put("Canval", rs.getString("CANVAL"));
            //array.add(record);
            
            Row rowN = sheet.createRow(rownum++);
  	      
            
            for(int i = 0; i < ColName.length; i++) {
  	    	  
  	    	  Cell cell = rowN.createCell(i);
  	    	  cell.setCellValue(rs.getNString(ColName[i]));
  	      
  	      }
            
            
    	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
    
    try {
		FileOutputStream fileout = new FileOutputStream("ECCLEGACYARTICLE_SYNC.xls");
		try {
			wb.write(fileout);
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    
    MediaType contentType = MediaType.APPLICATION_OCTET_STREAM ;
    /*InputStream in = getClass()
      .getResourceAsStream("C:\\downloads\\mtputty_export.xml");*/
    InputStreamResource resource = new InputStreamResource(new FileInputStream(new File("ECCLEGACYARTICLE_SYNC.xls")));
   
    return ResponseEntity.ok()
    		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=ECCLEGACYARTICLE_SYNC_H_BDFR.xls")
              .contentType(contentType)
              .body(resource);
    
    
    
    }


	@CrossOrigin(allowedHeaders = "Access-Control-Allow-Origin")
	@RequestMapping(value = {"/test_vendor_v1"}, method = RequestMethod.GET)
	public ResponseEntity<Resource> vendor_request(@RequestParam(value = "tierName",required = false) String tierName, @RequestParam(value = "opco",required = false) String opco) {
		
		
		
		if(tierName == "" || tierName == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		String url = null;
		String id = null;
		String pass = null;
		JSONObject pwdJ;
		
		String var = tierName + "_" + opco;
		String connUrl = "{\"F\" : \"jdbc:oracle:thin:@unxs0476.gha.kfplc.com:1527/QSTG1\" , \"V\" : \"jdbc:oracle:thin:@unxs0486.ghanp.kfplc.com:1527/SSTG1.WORLD\" , \"H\" : \"jdbc:oracle:thin:@unxs0666.ghanp.kfplc.com:1527/HTSTG101\"}";
		String schema = "{\"BDFR\" : \"[MBROKER_ODS_BDFR]\" , \"PLCA\" : \"[MBROKER_ODS_PLCA]\" , \"CAFR\" : \"[MBROKER_ODS_FRCA]\"}";
		String pwd = "{\"F\" : \"BAERG23#nScue22O\" , \"V\" : \"BAERG23#nScue22O\" , \"H\" : \"At89#jyAsw76eptF\"}";
		
		JSONParser parser = new JSONParser();
		try {
			JSONObject connUrlJ = (JSONObject) parser.parse(connUrl);
			JSONObject schemaJ = (JSONObject) parser.parse(schema);
			
			
			try {
				pwdJ = (JSONObject) parser.parse(new FileReader ("H:\\Application Service Support\\Pre-Prod Environment support\\Shounak\\demo\\demo\\src\\main\\resources\\password.json"));
				pass = pwdJ.get(tierName).toString();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pwdJ = (JSONObject) parser.parse(pwd);
			url = connUrlJ.get(tierName).toString();
			id = "baners22" + schemaJ.get(opco).toString();
			pass = pwdJ.get(tierName).toString();
			
			
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		
		
		
		
	    ResultSet rs = null;
		//Creating a JSONObject object
	      JSONObject jsonObject = new JSONObject();
	      //Creating a json array
	      JSONArray array = new JSONArray();
	      String[] ColName = {"OPCO_ID","LEGACY_VENDOR_NUMBER_GFR","SUBRANGE_NUMBER","ECC_VENDOR_NUMBER","VENDOR_TYPE","VENDOR_ACCOUNT_GROUP","CASE","CROSS_FLAG","DI_TIMESTAMP"};
	      
	      Workbook wb = new HSSFWorkbook();
	      
	      org.apache.poi.ss.usermodel.Sheet sheet =  wb.createSheet();
	      Row row = sheet.createRow(0);
	      
	      
	      for(int i = 0; i < ColName.length; i++) {
	    	  
	    	  Cell cell = row.createCell(i);
	    	  cell.setCellValue(ColName[i]);
	      
	      }
	     
	      
	      
	      
	      /*
	      try {
			FileOutputStream fileout = new FileOutputStream("C:\\Users\\BANERS22\\Documents\\test.xlsx");
			try {
				wb.write(fileout);
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	      
	    		  
	      
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection(  
			url,id,pass);  
			  
			//step3 create the statement object  
			Statement stmt=con.createStatement();
			
			rs = stmt.executeQuery("Select * from ECCLEGACYVENDOR_SYNC");
			
			
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		  
		  
		  
	    try {
	    	
	    	int rownum = 1;
			int colnum = 0;
			
	    	
	    	while(rs.next()) {
	    		//JSONObject record = new JSONObject();
	            //Inserting key-value pairs into the json object
	            //record.put("Config", rs.getString("CONFIGVALUE"));
	            //record.put("Canval", rs.getString("CANVAL"));
	            //array.add(record);
	            
	            Row rowN = sheet.createRow(rownum++);
	  	      
	            
	            for(int i = 0; i < ColName.length; i++) {
	  	    	  
	  	    	  Cell cell = rowN.createCell(i);
	  	    	  cell.setCellValue(rs.getNString(ColName[i]));
	  	      
	  	      }
	            
	            
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    
	    try {
			FileOutputStream fileout = new FileOutputStream("ECCLEGACYVENDOR_SYNC.xls");
			try {
				wb.write(fileout);
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    String file = null;
	    file = "ECCLEGACYVENDOR_SYNC_" + var + ".xls";
	    MediaType contentType = MediaType.APPLICATION_OCTET_STREAM ;
	    /*InputStream in = getClass()
	      .getResourceAsStream("C:\\downloads\\mtputty_export.xml");*/
	    InputStreamResource resource = null;
		try {
			resource = new InputStreamResource(new FileInputStream(new File("ECCLEGACYVENDOR_SYNC.xls")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    return ResponseEntity.ok()
	    		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file)
	              .contentType(contentType)
	              .body(resource);


	}

	


	@CrossOrigin(allowedHeaders = "Access-Control-Allow-Origin")
	@RequestMapping(value = {"/test_article_v1"}, method = RequestMethod.GET)
	public ResponseEntity<Resource> article_request(@RequestParam(value = "tierName",required = false) String tierName, @RequestParam(value = "opco",required = false) String opco) {
		
		
		
		if(tierName == "" || tierName == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		String url = null;
		String id = null;
		String pass = null;
		String query = null;
		JSONObject tableJ = null;
		int num = 0;
		
		String var = tierName + "_" + opco;
		String connUrl = "{\"F\" : \"jdbc:oracle:thin:@unxs0476.gha.kfplc.com:1527/QSTG1\" , \"V\" : \"jdbc:oracle:thin:@unxs0486.ghanp.kfplc.com:1527/SSTG1.WORLD\" , \"H\" : \"jdbc:oracle:thin:@unxs0666.ghanp.kfplc.com:1527/HTSTG101\"}";
		String schema = "{\"BDFR\" : \"[MBROKER_ODS_BDFR]\" , \"PLCA\" : \"[MBROKER_ODS_PLCA]\" , \"CAFR\" : \"[MBROKER_ODS_FRCA]\"}";
		String table = "{\"BDFR\" : \"ECCLEGACYARTICLE_SYNC\" , \"PLCA\" : \"ECCPMMARTICLE_SYNC\" , \"CAFR\" : \"ECCPMMARTICLE_SYNC\"}";
		String pwd = "{\"F\" : \"BAERG23#nScue22O\" , \"V\" : \"BAERG23#nScue22O\" , \"H\" : \"At89#jyAsw76eptF\"}";
		
		JSONParser parser = new JSONParser();
		try {
			JSONObject connUrlJ = (JSONObject) parser.parse(connUrl);
			JSONObject schemaJ = (JSONObject) parser.parse(schema);
			JSONObject pwdJ = (JSONObject) parser.parse(pwd);
			tableJ = (JSONObject) parser.parse(table);
			url = connUrlJ.get(tierName).toString();
			id = "baners22" + schemaJ.get(opco).toString();
			pass = pwdJ.get(tierName).toString();
			
			
			
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} 
		
		
		
		
	    ResultSet rs = null;
	    
		//Creating a JSONObject object
	      JSONObject jsonObject = new JSONObject();
	      //Creating a json array
	      JSONArray array = new JSONArray();
	      String[] ColName = {"OPCO_ID","EAN_CODE","PMM_ARTICLE_ID","PMM_UOM_CODE","PMM_EAN_PRIMARY","ECC_ARTICLE_ID","ECC_UOM_CODE","CREATED_DT","MODIFIED_DT","DELETED_FLAG"};
	      
	      //XSSFWorkbook wb = new XSSFWorkbook();
	      
	      //org.apache.poi.ss.usermodel.Sheet sheet =  wb.createSheet();
	     // Row row = sheet.createRow(0);
	      
	      
	      
	      FileOutputStream fileout = null;
		try {
			fileout = new FileOutputStream("ECCLEGACYARTICLE_SYNC.xls");
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
		File file1 = new File("test.txt");
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file1);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		for(int i = 0; i < ColName.length; i++) {
	    	  
	    	 // Cell cell = row.createCell(i);
	    	  //cell.setCellValue(ColName[i]);
	    	  
	    	  pw.write(ColName[i]);
	    	  pw.write(",");
	      
	      }
		
		pw.write("\n");
		
		
	      
	      
	      
	      /*
	      try {
			FileOutputStream fileout = new FileOutputStream("C:\\Users\\BANERS22\\Documents\\test.xlsx");
			try {
				wb.write(fileout);
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	      
	    		  
	      
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			query = "Select count(*) C from " + tableJ.get(opco).toString();
			//step2 create  the connection object  
			Connection con=DriverManager.getConnection(  
			url,id,pass);  
			  
			//step3 create the statement object  
			Statement stmt=con.createStatement();
			
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				num = Integer.parseInt((rs.getNString("C")));
				System.out.println(num);
			}
			
			int n = num / 10000;
			int i,a,b;
			int rownum = 0;
			int colnum = 0;
			for(i = 0; i <= num; i = i + 10000) {
				a = i;
				//a = rownum;
				if(a != 0 && a > rownum)
					a = rownum;
				b = 9999;
				System.out.println(a + " " + b);
				
			
				rs = null;
				query = "Select * from " + tableJ.get(opco).toString() + " order by created_dt desc offset " + String.valueOf(a) + " ROWS FETCH NEXT " +  String.valueOf(b) + " ROWS ONLY";
				System.out.println(query);
			//step2 create  the connection object   
			  
			//step3 create the statement object  
				Statement stmt1=con.createStatement();
			
				rs = stmt1.executeQuery(query);
				
				/*while(rs.next()) {
					rownum++;
				}
				System.out.println(rownum);*/
				
				
		    	while(rs.next()) {
		    		//JSONObject record = new JSONObject();
		            //Inserting key-value pairs into the json object
		            //record.put("Config", rs.getString("CONFIGVALUE"));
		            //record.put("Canval", rs.getString("CANVAL"));
		            //array.add(record);
		    		rownum++;
		    		
		    		
		            //Row rowN = sheet.createRow(rownum++);
		  	        
		    		//StringBuilder sb = new StringBuilder("");
		            for(int k = 0; k < ColName.length; k++) {
		  	    	  
		  	    	  //Cell cell = rowN.createCell(k);
		  	    	  //cell.setCellValue(rs.getNString(ColName[k]));
		            	
		            	if (k < (ColName.length - 1)) {
		            		pw.write("=\"" + rs.getNString(ColName[k]) + "\"");
		            		pw.write(",");
		            	}
		            	else {
		            		
		            		pw.println("\"" + rs.getNString(ColName[k]) + "\"");
		            		
		            	}
		            		
		  	      
		  	      }
		            
		            
		            //sb.append("\n");
		            //pw.println();
		            //pw.append(sb);
		            
		            //pw.write(sb.toString());
		            //pw.append(sb);
		            
		            
		            
		            
		    	}
		    	
		    	System.out.println(rownum);
			
			}
			System.out.println("end of loop" + rownum);
			
			
			
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		  
		pw.flush();  
		pw.close();
		  
	    /*try {
	    	
	    	/*int rownum = 1;
			int colnum = 0;
			
	    	
	    	while(rs.next()) {
	    		//JSONObject record = new JSONObject();
	            //Inserting key-value pairs into the json object
	            //record.put("Config", rs.getString("CONFIGVALUE"));
	            //record.put("Canval", rs.getString("CANVAL"));
	            //array.add(record);
	            
	            Row rowN = sheet.createRow(rownum++);
	  	      
	            
	            for(int i = 0; i < ColName.length; i++) {
	  	    	  
	  	    	  Cell cell = rowN.createCell(i);
	  	    	  cell.setCellValue(rs.getNString(ColName[i]));
	  	      
	  	      }
	            
	            
	    	}
	    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	    
	    
	   /* 
	    try {
			FileOutputStream fileout = new FileOutputStream("ECCLEGACYARTICLE_SYNC.xls");
			try {
				wb.write(fileout);
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	    
	    String file = null;
	    file = "ECCLEGACYARTICLE_SYNC_" + var + ".csv";
	    //file = "test.csv";
	    MediaType contentType = MediaType.APPLICATION_OCTET_STREAM ;
	    /*InputStream in = getClass()
	      .getResourceAsStream("C:\\downloads\\mtputty_export.xml");*/
	    InputStreamResource resource = null;
		try {
			resource = new InputStreamResource(new FileInputStream(new File("test.txt")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	    return ResponseEntity.ok()
	    		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file)
	              .contentType(contentType)
	              .body(resource);


	}
	
	



@CrossOrigin(allowedHeaders = "Access-Control-Allow-Origin")
@GetMapping("/test_vendor_H_PLCA")
public ResponseEntity<Resource> vendor24() throws IOException {
    
    ResultSet rs = null;
	//Creating a JSONObject object
      JSONObject jsonObject = new JSONObject();
      //Creating a json array
      JSONArray array = new JSONArray();
      String[] ColName = {"OPCO_ID","LEGACY_VENDOR_NUMBER_GFR","SUBRANGE_NUMBER","ECC_VENDOR_NUMBER","VENDOR_TYPE","VENDOR_ACCOUNT_GROUP","CASE","CROSS_FLAG","DI_TIMESTAMP"};
      
      //Workbook wb = new HSSFWorkbook();
      XSSFWorkbook wb = new XSSFWorkbook(); 
      
      org.apache.poi.ss.usermodel.Sheet sheet =  wb.createSheet();
      Row row = sheet.createRow(0);
      
      
      for(int i = 0; i < ColName.length; i++) {
    	  
    	  Cell cell = row.createCell(i);
    	  cell.setCellValue(ColName[i]);
      
      }
     
      
      
      /*
      try {
		FileOutputStream fileout = new FileOutputStream("C:\\Users\\BANERS22\\Documents\\test.xlsx");
		try {
			wb.write(fileout);
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/
      
    		  
      
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//step2 create  the connection object  
		Connection con=DriverManager.getConnection(  
		"jdbc:oracle:thin:@unxs0666.ghanp.kfplc.com:1527/HTSTG101","baners22[MBROKER_ODS_PLCA]","G7O5y6#kj5l4TC8L");  
		  
		//step3 create the statement object  
		Statement stmt=con.createStatement();
		
		rs = stmt.executeQuery("Select * from ECCLEGACYVENDOR_SYNC");
		
		
		
		
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
	  
	  
	  
    try {
    	
    	int rownum = 1;
		int colnum = 0;
		
    	
    	while(rs.next()) {
    		//JSONObject record = new JSONObject();
            //Inserting key-value pairs into the json object
            //record.put("Config", rs.getString("CONFIGVALUE"));
            //record.put("Canval", rs.getString("CANVAL"));
            //array.add(record);
            
            Row rowN = sheet.createRow(rownum++);
  	      
            
            for(int i = 0; i < ColName.length; i++) {
  	    	  
  	    	  Cell cell = rowN.createCell(i);
  	    	  cell.setCellValue(rs.getNString(ColName[i]));
  	      
  	      }
            
            
    	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
    
    try {
		FileOutputStream fileout = new FileOutputStream("ECCLEGACYVENDOR_SYNC.xls");
		try {
			wb.write(fileout);
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    
    
    MediaType contentType = MediaType.APPLICATION_OCTET_STREAM ;
    /*InputStream in = getClass()
      .getResourceAsStream("C:\\downloads\\mtputty_export.xml");*/
    InputStreamResource resource = new InputStreamResource(new FileInputStream(new File("ECCLEGACYVENDOR_SYNC.xls")));
   
    return ResponseEntity.ok()
    		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=ECCLEGACYVENDOR_SYNC_H_PLCA.xls")
              .contentType(contentType)
              .body(resource);
    
    
    
    }

}

