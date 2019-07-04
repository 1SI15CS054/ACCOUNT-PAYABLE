package com.altimetrik.training;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewTable {
	
		public static PreparedStatement stmt3 = null;
		static Connection connection = null;

		public static void DatabaseTable() {
			try {

				// Get a result set containing all data from test_table
				connection = ConnectToDatabase.connect();
				stmt3 = connection.prepareStatement("SELECT * FROM INVOICE_ACCOUNT_PAYABLE ");
				

				ResultSet results = stmt3.executeQuery();
				
				// For each row of the result set ...
				System.out.println("The INVOICE_ACCOUNT TABLE");
				
				System.out.println("InvoiceNo        InvoiceDate       CustomerPO        Amount          Status         Address        \n\n");
				System.out.println("------------------------------------------------------------------------------------------------------------\n");
				while (results.next()) {
					
					
					System.out.println(results.getString(1) + "        " + results.getString(2) + "       "+ results.getString(3) + "          " + results.getString(5)
					+ "        " + results.getString(6)+ "      " + results.getString(4));
					System.out.println("\n*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*\n");
					}
				System.out.println("\n--------------------------------------------------------------------------------------------------------------\n");
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
