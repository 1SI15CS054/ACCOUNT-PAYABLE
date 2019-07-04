package com.altimetrik.training;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectToDatabase {
	public static Connection connect()
	{
		Connection conn = null;
		try {
			// STEP 1:Set all you required
			// String jdbcDriver = "oracle.jdbc.driver.OracleDriver";// "com.mysql.jdbc.Driver";<- We can use this also
			 String jdbcurl = "jdbc:oracle:thin:@localhost:1521:xe";// "jdbc:mysql://localhost/hr" <-for commented driver we need this URL
			 String user = "hr";
			 String password = "hr";
			 
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 
			// STEP 3: Open a connection
				System.out.println("Connecting to database...");
				conn = DriverManager.getConnection(jdbcurl, user, password);

		} catch (Exception e) {
			System.err.println("Failed to connect to database" + e);
		}
		return conn;
	}

}
