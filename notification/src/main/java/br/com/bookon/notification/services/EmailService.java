package br.com.bookon.notification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	public void sendEmail(String receiver) {
		try {
			var message = new SimpleMailMessage();
			message.setFrom("kauanrocha.professional@gmail.com");
			message.setTo(receiver);
			message.setSubject("Email de teste");
			message.setText("Olá mundo dos emails");
			
			javaMailSender.send(message);
		} catch(MailException e) {
			System.out.println(e);
		} finally {
			System.out.println("Rodei!");
		}
	}
}
