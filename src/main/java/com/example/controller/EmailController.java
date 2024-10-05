package com.example.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.entity.EmailDetails;
import com.example.repository.*;
import com.example.service.EmailService;

import jakarta.mail.MessagingException;
//import org.springframework.web.bind.annotation.RequestMethod;

@RestController

//@CrossOrigin(origins = "http://localhost:5173", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS })
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	@Autowired		
	private EmailDetailsRepository emailDetailsRepositor;
	        //1.sending a simple email
	@PostMapping("/sendMail")
	public String sendMail(@RequestBody EmailDetails details) {
		String status =emailService.sendSimpleMail(details);
		return status;
	}
	     @PostMapping("/sendM")
	    public ResponseEntity<String> sendMailWithAttachment(
	            @RequestParam("recipient") String recipient,
	            @RequestParam("subject") String subject,
	            @RequestParam("msgBody") String msgBody,
	            @RequestParam("file") MultipartFile file) throws MessagingException {

	        // Create the email details object
	        EmailDetails details = new EmailDetails();
	        details.setRecipient(recipient);
	        details.setSubject(subject);
	        details.setMsgBody(msgBody);

	        // Send email with the attachment
	          String status=emailService.sendMailWithAttachment(details, file);
	          // Return response to the client
	       return ResponseEntity.ok(status);
}
	//3. multiple user simple mail send  sendEmailToMultipleUsers
	@PostMapping("/sendMulitiple")
	public String sendEmailToMultipleUsers(@RequestBody EmailDetails details) throws MessagingException  {
		return emailService.sendEmailToMultipleUsers(details);
	}
	// 4. End point to send an email with attachment to multiple users
    @PostMapping("/sendMultipleWithAttachment")
    public String sendEmailWithAttachmentToMultipleUsers(@RequestBody EmailDetails emailDetails) throws MessagingException {
        return emailService.sendEmailWithAttachmentToMultipleUsers(emailDetails);
    }
    // 5.save in DB mails entery form
    @PostMapping("/save")
    public ResponseEntity<EmailDetails> saveEmail(
          @RequestParam("to") String to,
          @RequestParam("subject") String subject,
          @RequestParam("message") String message,
          @RequestParam(value = "attachment", required = false) MultipartFile attachment) {

        // Log received data for debugging
        System.out.println("Received email details: ");
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
        System.out.println("Attachment: " + (attachment != null ? attachment.getOriginalFilename() : "None"));

        // Create EmailDetails object
        EmailDetails details = new EmailDetails();
        details.setRecipient(to);
        details.setSubject(subject);
        details.setMsgBody(message);
        details.setAttachment(attachment != null ? attachment.getOriginalFilename() : null);

        // Save to repository
        emailDetailsRepositor.save(details);

        // Return response
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    
    @GetMapping("/email-suggestions")
    public ResponseEntity<List<String>> getEmailSuggestions(@RequestParam String query) {
        System.out.println("Received query in controller: " + query);
        List<String> suggestions = emailService.getEmailSuggestions(query);
        Map<String, List<String>> response = new HashMap<>();
        response.put("suggestions", suggestions); // Ensure the key matches what the frontend expects
        
        // Log what is returned from the service
        System.out.println("Suggestions from service: " + suggestions);
        
        if (suggestions.isEmpty()) {
            return ResponseEntity.noContent().build(); // Return 204 if no suggestions found
        }
        return ResponseEntity.ok(suggestions);
  

    	
    }
    

    
    
    }



    

	
	
	

