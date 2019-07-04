package com.altimetrik.training;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.pdfbox.pdmodel.PDDocument;

public class ReceiveEmailWithAttachment {
	
	public static String FromMail;
	public static void receiveEmail(String pop3Host, String mailStoreType, String userName, String password)
			throws NullPointerException {

		// Set properties
		Properties props = new Properties();
		props.put("mail.store.protocol", "pop3");
		props.put("mail.pop3.host", pop3Host);
		props.put("mail.pop3.port", "995");
		props.put("mail.pop3.starttls.enable", "true");

		// Get the Session object.
		Session session = Session.getInstance(props);

		PDDocument pdfDocument = null;
		BodyPart bodyPart = null;

		try {
			// Create the POP3 store object and connect to the pop store.
			Store store = session.getStore("pop3s");
			store.connect(pop3Host, userName, password);

			// Create the folder object and open it in your mailbox.
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// Retrieve the messages from the folder object.
			Message[] messages = emailFolder.getMessages();
			System.out.println("Total Message" + messages.length);

			// Iterate the messages
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				Address[] toAddress = message.getRecipients(Message.RecipientType.TO);
				System.out.println("---------------------------------");
				System.out.println("Details of Email Message " + (i + 1) + " :");
				System.out.println("Subject : " + message.getSubject());
				System.out.println("From : " + message.getFrom()[0]);
				
				FromMail= (message.getFrom()[0]).toString();
				// Iterate recipients
				System.out.print("To : ");
				for (int j = 0; j < toAddress.length; j++) {
					
					System.out.println(toAddress[j].toString());
				}

				Object content = message.getContent();
				if (content instanceof Multipart) {

					// Iterate MULTIPART

					Multipart multipart = (Multipart) message.getContent();
					for (int k = 0; k < multipart.getCount(); k++) {
						bodyPart = multipart.getBodyPart(k);
						
						// Checking Whether PDF or Not
						if (bodyPart.getDisposition() != null
								&& bodyPart.getDisposition().equalsIgnoreCase(Part.ATTACHMENT)
								&& bodyPart.getFileName().contains(".pdf")) {
							
							//Loading The PDDocument Its load the file to mail without saving into fail system
							pdfDocument = PDDocument.load(bodyPart.getInputStream());

							System.out.println("File Name : " + bodyPart.getFileName());
							System.out.println("Size " + bodyPart.getSize());
							System.out.println("Content Type :" + bodyPart.getContentType());

						}
					}

				}
			}
			if (pdfDocument == null)
				System.out.println("Email Does Not Contain Any Attachment or Not Received Any Mail.......");
			else
				ReadingText.receiveMail(pdfDocument);
			// close the folder and store objects

			emailFolder.close(false);

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}