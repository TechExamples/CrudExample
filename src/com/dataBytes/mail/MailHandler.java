package com.dataBytes.mail;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class MailHandler {
	
	@Autowired
	private MailSender mailSender;

	private @Value("${mail.send.message.from}") String from;
	private @Value("${mail.send.message.to}") String[] to;	
	private @Value("${mail.send.message.cc}") String[] cc;
	private @Value("${mail.send.message.replyto}") String replyTo;
	
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendMail(String from, String to, String[] cc, String replyTo, String subject, String msg) {

		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(from);
		message.setTo(to);
		message.setCc(cc);
		message.setReplyTo(replyTo);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);	
	}
	
	public void sendMail(String subject, String msg) {

		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom(from);
		message.setTo(to);
		message.setCc(cc);
		message.setReplyTo(replyTo);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);	
	}


}
