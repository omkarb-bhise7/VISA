package com.example.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;


@Entity

@Table(name="email_details")
public class EmailDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	   @Column(name = "recipient")
	private String recipient ;
	   @Column(name = "subject")
	private String subject;
    @Column(name = "msgBody")
	private String msgBody;
	@Column(name = "attachment")
	private String attachment;
	
	@Column(name = "Date_Time")
    private LocalDateTime sentDateTime;  // new column for date and time
	
	public EmailDetails() {
		super();
}
      
	@Override
	public String toString() {
		return "EmailDetails [id=" + id + ", recipient=" + recipient + ", subject=" + subject + ", msgBody=" + msgBody
				+ ", attachment=" + attachment + ", sentDateTime=" + sentDateTime + "]";
	}


	public EmailDetails(Long id, String recipient, String subject, String msgBody, String attachment, LocalDateTime sentDateTime) {
		super();
		this.id = id;
		this.recipient = recipient;
		this.subject = subject;
		this.msgBody = msgBody;
		this.attachment = attachment;
		this.sentDateTime = sentDateTime;
	}

    @PrePersist
    public void prePersist() {
        this.sentDateTime = LocalDateTime.now();  // Automatically set the current date and time
    }


	public EmailDetails(String to, String subject2, String message, Object object) {
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMsgBody() {
		return msgBody;
	}
	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public LocalDateTime getSentDateTime() {
        return sentDateTime;
    }

    public void setSentDateTime(LocalDateTime sentDateTime) {
        this.sentDateTime = sentDateTime;
    }
}
	

	
	
	
	
	
	

