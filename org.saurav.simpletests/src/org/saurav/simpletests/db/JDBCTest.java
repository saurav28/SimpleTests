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
	
	// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "org.postgresql.Driver";  
	   static final String DB_URL = "jdbc:postgresql://1ocalhost:5432/employee";

	   //  Database credentials
	   static final String USER = "postgres";
	   static final String PASS = "admin";

	public static void main (String a[]){
		
		 Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      System.out.println("loading driver");
			   Class.forName("org.postgresql.Driver");

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql;
		      sql = "SELECT * FROM employee";
		      ResultSet rs = stmt.executeQuery(sql);
		      System.out.println("execution is successfull:: " +rs.next());
		
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
