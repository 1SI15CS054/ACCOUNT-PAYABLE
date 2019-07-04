package com.altimetrik.training;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
	private static Scanner read;
	

	public static void main(String[] args) throws IOException, SQLException {
		
		//Initializing the Required Data for Email
		String pop3Host = "pop.gmail.com";
		String mailStoreType = "pop3";		
		final String userName = "madhusudantn.1si15cs054@gmail.com";// Mail ID		
		final String password1 = "$hantha8494865609"; //Password
		//*************Make required changes in your mail setting*************\\
		
		//Calling the ReceiveEmailWithAttachment to receive the attachment PDF
		//ReceiveEmailWithAttachment.receiveEmail(pop3Host, mailStoreType, userName, password1);
		read = new Scanner(System.in);
		int choice;
		
		String INVOICENO1=null;
		while(true){
		System.out.println("Enter Your Choice\n 1.To Check the messages and store in the data base	and  approving the data base"
				+ "2.Approve the Invoice without acknowledgement\n3. To View The Whole Table\nAnyother button to Exit ");
		
		choice = read.nextInt();
		
		
		switch(choice){
		//Calling the ReceiveEmailWithAttachment to receive the attachment PDF and storing it in database
		    case 1:ReceiveEmailWithAttachment.receiveEmail(pop3Host, mailStoreType, userName, password1);
		    		break;
		    		
			case 2:System.out.println("Enter the Invoice number to approve");
					INVOICENO1 = read.next();
					CheckTable.DatabaseTable(INVOICENO1);
					//ApproveInvoice.Approve(INVOICENO1);
					break;
			case 3:ViewTable.DatabaseTable();
			break;
			default : 
						System.exit(0);
			
	}
	
   }
   }
}
