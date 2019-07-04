package com.altimetrik.training;

import java.sql.*;

public class ConnectionOfDatabase {

	public static Connection conn = null;
	public static PreparedStatement stmt = null;
	public static PreparedStatement stmt1 = null;

	public static void insertRecordn(String invoiceNo, String invoiceDate, String customerPO, String address,
			String amount) throws SQLIntegrityConstraintViolationException, NullPointerException {

		try {
			conn = ConnectToDatabase.connect();
			// STEP 4: Checking the Invoice number, is it duplicated or not
			stmt1 = conn.prepareStatement("SELECT * FROM INVOICE_ACCOUNT_PAYABLE WHERE INVOICENO = ? ");
			stmt1.setString(1, invoiceNo);
			
			// STEP 5: It returns true if its duplicated or else False
			ResultSet resultSet = stmt1.executeQuery();
			
			if (resultSet.next()) {
				System.out.println("the invioce number is already exist");
			} else {

				// STEP 6: Execute a query
				stmt = conn.prepareStatement("INSERT INTO INVOICE_ACCOUNT_PAYABLE VALUES(?,?,?,?,?,?)");

				// STEP 7: Inserting values into the table.
				stmt.setString(1, invoiceNo);
				stmt.setString(2, invoiceDate);
				stmt.setString(3, customerPO);
				stmt.setString(4, address);
				stmt.setString(5, amount);
				stmt.setString(6, "False");
				int i = stmt.executeUpdate();

				System.out.println(" Invoice received and stored in database" + i);
				CheckTable.DatabaseTable(invoiceNo);
				
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
				if (stmt1 != null)
					stmt1.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try

	}

}