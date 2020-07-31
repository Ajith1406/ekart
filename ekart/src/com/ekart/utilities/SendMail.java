package com.ekart.utilities;

import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
public class SendMail
{ 
	private static final String userName = "username";
	private static final String password = "password";
	
    public static void send(String to, String sub, 
                         String msg) throws AddressException, MessagingException
    { 
        //create an instance of Properties Class   
        Properties props = new Properties();
     
        /*  Specifies the IP address of your default mail server
     	      for e.g if you are using gmail server as an email sever
            you will pass smtp.gmail.com as value of mail.smtp host. 
            As shown here in the code. 
            Change accordingly, if your email id is not a gmail id
        */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");		
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
     
        /* Pass Properties object(props) and Authenticator object   
           for authentication to Session instance 
        */

        Session session = Session.getInstance(props,new javax.mail.Authenticator()
        {
            protected PasswordAuthentication getPasswordAuthentication()
            {
  	 	         return new PasswordAuthentication(userName,password); 
            }
        });

 
 	      /*  Create an instance of MimeMessage, 
 	          it accept MIME types and headers 
 	      */
 
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(userName));
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
        message.setSubject(sub);
        message.setText(msg);

        /* Transport class is used to deliver the message to the recipients */
       
        Transport.send(message);
        
    }  
}
