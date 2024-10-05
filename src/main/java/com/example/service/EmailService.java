package com.example.service;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.example.entity.EmailDetails;
import jakarta.mail.MessagingException;
public interface EmailService {
	
	// simple email
	String sendSimpleMail(EmailDetails details);
	
	// send mail with attachment
	String sendMailWithAttachment(EmailDetails details, MultipartFile file)throws MessagingException;;
	
	//Method to send a simple email to multiple recipients
	String sendEmailToMultipleUsers(EmailDetails details)throws MessagingException;
	
	// Method to send an email with attachments to multiple recipients
    String sendEmailWithAttachmentToMultipleUsers(EmailDetails details) throws MessagingException;
	// save mail in DB
    EmailDetails saveEmail(EmailDetails details);  // Declares the method

    List<String> getEmailSuggestions(String query);
    
    
  
}
