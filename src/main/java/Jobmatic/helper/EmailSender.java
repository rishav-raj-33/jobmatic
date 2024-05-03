package Jobmatic.helper;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

@Component
public class EmailSender {
	private final String user="rishav3330";
	
	@Value("${email.key}")
	private  String password;
	@Value("${project.path}")
	private String path;
	
	public boolean sendEmail(String to,String from,String subject,String text) throws AddressException, MessagingException {
		
		
		boolean flag=false;
		
		Properties properties=new Properties();
		
		properties.put("mail.smtp.auth",true);
		properties.put("mail.smtp.starttls.enable",true);
		properties.put("mail.smtp.port","587");
		properties.put("mail.smtp.host","smtp.gmail.com");
		
		
	Session session=Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user,password);
				
			}
		});
		
		
			Message message=new MimeMessage(session);
		
			message.setFrom(new InternetAddress(from));
			message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
			message.setSubject(subject);
			message.setText(text);
			    MimeMultipart multipart = new MimeMultipart();
			    MimeBodyPart messageBodyPart = new MimeBodyPart();
			    messageBodyPart.setContent(text, "text/plain"); 
			    MimeBodyPart attachmentPart = new MimeBodyPart();
			    try {
					attachmentPart.attachFile(new File(path));
				} catch (IOException | MessagingException e) {
					e.printStackTrace();
					return flag;
				}
			    attachmentPart.setDisposition(MimeBodyPart.ATTACHMENT); 
			    multipart.addBodyPart(messageBodyPart);
			    multipart.addBodyPart(attachmentPart);
			    message.setContent(multipart);
			
			Transport.send(message);
			flag=true;
			
		
		
		
		
		
		return flag;
	}

}
