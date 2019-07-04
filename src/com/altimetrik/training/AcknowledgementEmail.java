package com.altimetrik.training;
import java.util.Properties;    
import javax.mail.*;    
import javax.mail.internet.*;    
public class AcknowledgementEmail{    



    public static void Mail(){  
          //Get properties object    
    	final String from = "madhusudantn.1si15cs054@gmail.com";// change accordingly
		final String password = "$hantha8494865609";// change accordingly

		String to = ReceiveEmailWithAttachment.FromMail;// change accordingly
		
          Properties props = new Properties();    
          props.put("mail.smtp.host", "smtp.gmail.com");    
          props.put("mail.smtp.socketFactory.port", "465");    
          props.put("mail.smtp.socketFactory.class",    
                    "javax.net.ssl.SSLSocketFactory");    
          props.put("mail.smtp.auth", "true");    
          props.put("mail.smtp.port", "465");    
          //get Session   
          Session session = Session.getDefaultInstance(props,    
           new javax.mail.Authenticator() {    
           protected PasswordAuthentication getPasswordAuthentication() {    
           return new PasswordAuthentication(from,password);  
           }    
          });    
          //compose message    
          try {    
           MimeMessage message = new MimeMessage(session);    
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));    
           message.setSubject("AKNOWLEDGEMENT");    
           message.setText(" Hii\n INVOICE APPROVED sucessfully");    
           
           //send message  
           Transport.send(message);    
           System.out.println("message sent successfully");    

       
          } catch (MessagingException e) {throw new RuntimeException(e);}    
             
    
}
}

