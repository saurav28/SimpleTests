package org.saurav.simpletests.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Taken inspired from https://www.tutorialspoint.com/jdbc/jdbc-sample-code.htm
 * Adopted for postgres.
 * Have your postgres instance running and have the postgresql.jar dependency in your classpath.
 * 
 * @author i054564
 *
 */
public class JDBCTest {
//	
//	// JDBC driver name and database URL
//	   static final String JDBC_DRIVER = "org.postgresql.Driver";  
//	   static final String DB_URL = "jdbc:postgresql://1ocalhost:5432/employee";
//
//	   //  Database credentials
//	   static final String USER = "postgres";
//	   static final String PASS = "admin";
	   
	   
		// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.sap.db.jdbc.Driver";  
	   static final String DB_URL = "jdbc:sap://d3fd05b1-57ff-49dd-8cc6-cc2d9d1d5c21.hana.canary-eu10.hanacloud.ondemand.com:443?encrypt=true&validateCertificate=false";

	   //  Database credentials
	   static final String USER = "85F16399067344E49E6F1F7ADCD01DB0_1R6YX5BGRS707T6AXTQP7YKP8_RT";
	   static final String PASS = "Cr0i7.9BRfaQxoJ9LcAsVb0BxSPSZMDR3.frg_3lTeukHboa0tFDXSfLclf-ZAMx1g.SkuFuNZG_6KDFe-BU358L-XZW7k30vWXN98xqFBMeZw.Bz7nqBmJl7LbgP2XE";

	public static void main (String a[]){
		
		 Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      System.out.println("loading driver");
			   Class.forName(JDBC_DRIVER);

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      sql = "Select * from \"SYS\".\"M_DATABASE\"";
		      ResultSet rs = stmt.executeQuery(sql);
		      
		      int i = 0;
		      while(rs.next()) {
		          i++;
		      }
		      System.out.println("execution is successfull and result size is :: " + i);
		
	}
		   catch (Exception e){
			   e.printStackTrace();
		   }finally {
			   try {
			   conn.close();
			   
				stmt.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
}
}
