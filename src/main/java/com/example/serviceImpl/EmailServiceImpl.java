package com.example.serviceImpl;
import java.io.File;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.EmailDetails;
import com.example.repository.EmailDetailsRepository;

import com.example.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
	
	private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Autowired																										
	private JavaMailSender javaMailSender;
	@Autowired		
     private EmailDetailsRepository emailDetailsRepositor;
	
// 1.simple mail
	@Override
	public String sendSimpleMail(EmailDetails details) {
	    try {
	        // Check for null or empty values before sending the email
	        if (details.getRecipient() == null || details.getRecipient().isEmpty()) {
	            return "Recipient address is missing";
	        }
	        if (details.getSubject() == null || details.getSubject().isEmpty()) {
	            details.setSubject("No Subject"); // Set a default subject if needed
	        }
	        if (details.getMsgBody() == null || details.getMsgBody().isEmpty()) {
	            details.setMsgBody("No content"); // Set default content if needed
	        }

	        // Log the email details
	        logger.info("Sending email to: " + details.getRecipient());
	        logger.info("Email subject: " + details.getSubject());
	        logger.info("Email content: " + details.getMsgBody());

	        // Create and configure the email message
	        SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setFrom("1999omkarbhise@gmail.com");
	        mailMessage.setTo(details.getRecipient());
	        mailMessage.setSubject(details.getSubject());
	        mailMessage.setText(details.getMsgBody());

	        // Send the email
	        javaMailSender.send(mailMessage);
	        logger.info("Email sent successfully to: " + details.getRecipient());

	        return "Mail sent successfully";

	    } catch (Exception e) {
	        logger.error("Error while sending mail: " + e.getMessage(), e);
	        return "Error while sending mail: " + e.getMessage();
	    }
	}

	@Override
	public String sendMailWithAttachment(EmailDetails details, MultipartFile file) {
		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		
	try {
		mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
		mimeMessageHelper.setFrom("1999omkarbhise@gmail.com");
		mimeMessageHelper.setTo(details.getRecipient());
		mimeMessageHelper.setSubject(details.getSubject());
		mimeMessageHelper.setText(details.getMsgBody());
		
		//attach the file
		if(file != null && !file.isEmpty()) {
			mimeMessageHelper.addAttachment(file.getOriginalFilename(),file);
		}

        // Send the email
        javaMailSender.send(mimeMessage);
        return "Mail with attachment sent successfully";
		
	}catch(MessagingException e) {
		return "Error while sending mail with attachment: " + e.getMessage();
	}catch(Exception e){
        e.printStackTrace(); // Log the stack trace
        return "General Exception: " + e.getMessage();
        }
}	
     // 3.Method to send a simple email to multiple recipients
	@Override
	public String sendEmailToMultipleUsers(EmailDetails details) {
	
		try {
	        if (details.getRecipient() == null || details.getRecipient().isEmpty()) {
	            return "Recipient addresses are missing";
	        }
	        String[] recipients = details.getRecipient().split(",");
	        if (recipients.length == 0) {
	            return "No valid recipients found";
	        } 
					// create a Mimemessage
			MimeMessage mimeMessage=javaMailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false);
			 // Split recipients by comma if multiple addresses are provided
			//String[] recipients=details.getRecipient().split(",");
			
			helper.setTo(recipients);// multiple recipients
			helper.setSubject(details.getSubject());
			helper.setText(details.getMsgBody(),false);//plain text
			
			//send mail
			javaMailSender.send(mimeMessage);
			
			return "Email sent successfully to multiple recipients!";
		}catch(Exception e) {
			return "Error while sending email:" +e.getMessage(); 
		}
}
             // 4.send t0 multiple user with attachment
	@Override
	public String sendEmailWithAttachmentToMultipleUsers(EmailDetails details) throws MessagingException {
		 MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	        
	        // Split recipients by comma
	        String[] recipients = details.getRecipient().split(",");
	        
	        helper.setTo(recipients);//multiple user
	        helper.setSubject(details.getSubject());
	        helper.setText(details.getMsgBody());
	        
	        // Add attachment if exists
	        if(details.getAttachment() !=null) {
	        	File file=new File(details.getAttachment());
	        	if(file.exists()) {
	        		helper.addAttachment(file.getName(), file);
	        	}
	        }
	        //send the mail
	        javaMailSender.send(mimeMessage);	        
	       	return  "Email with attachment sent successfully to multiple recipients!";
	       	}
// 5.save mail
	@Override
	public EmailDetails saveEmail(EmailDetails details) {
		// TODO Auto-generated method stub
		return emailDetailsRepositor.save(details);
	}
  //6. ===========
	
	@Override
	public List<String> getEmailSuggestions(String query) {
	    System.out.println("Received query in service: " + query);
	    
	    if (query == null || query.trim().isEmpty()) {
	        System.out.println("Query is null or empty, returning empty list.");
	        return Collections.emptyList();
	    }
	    
	    // Trim and log the trimmed query
	    String trimmedQuery = query.trim();
	    System.out.println("Trimmed query: " + trimmedQuery);
	    
	    List<String> suggestions = emailDetailsRepositor.findEmailSuggestions(trimmedQuery);
	    System.out.println("Suggestions from repository: " + suggestions);
	    
	    // Returning the final list of suggestions
	    System.out.println("Final suggestions to be returned: " + suggestions);
	    
	    
	    return suggestions;
	}


	
           
      
         
	
}
                  

    
	   


	
	
	
	




	
