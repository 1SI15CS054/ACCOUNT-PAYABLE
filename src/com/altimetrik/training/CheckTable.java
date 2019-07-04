package com.altimetrik.training;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckTable {
	public static PreparedStatement stmt1 = null;
	static Connection connection = null;
	public static PreparedStatement stmt4 = null;

	public static void DatabaseTable(String InvoiceNo) {
		try {

			// Get a result set containing all data from test_table
			connection = ConnectToDatabase.connect();
			stmt1 = connection.prepareStatement("SELECT * FROM INVOICE_ACCOUNT_PAYABLE WHERE INVOICENO = ? ");
			stmt1.setString(1, InvoiceNo);

			ResultSet results = stmt1.executeQuery();
			
			// For each row of the result set ...
			
			while (results.next()) {
				
				if( results.getString(6).equals("False")){
					
					stmt4 = connection.prepareStatement("UPDATE INVOICE_ACCOUNT_PAYABLE SET STATUS = ? WHERE INVOICENO = ?  ");
					 stmt4.setString(1, "True");
					 stmt4.setString(2, InvoiceNo);
					 stmt4.executeUpdate();
					 System.out.println("The Invoice Number approved");
					 AcknowledgementEmail.Mail();
					
				}else
				{
					System.out.println("The INVOICE_ACCOUNT is already approved");
				System.out.println("InvoiceNO     : "+results.getString(1) + "\nInvoiceDate : " + results.getString(2) + "\nCustomerPO    : "
						+ results.getString(3) + "\nAmount      : " + results.getString(5) + "\nstatus     :" + results.getString(6)
						+ "Address    :" + results.getString(4));
				}
			}
			connection.close();
		} catch(NullPointerException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("Could not retrieve data from the database " + e.getMessage());
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

	}

}
