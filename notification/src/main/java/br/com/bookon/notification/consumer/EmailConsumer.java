package br.com.bookon.notification.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.bookon.notification.services.EmailService;

@Service
public class EmailConsumer {
	
	@Autowired
	private EmailService emailService;
	
	@KafkaListener(topics = "email-topic", groupId = "group-1")
	public void receiveMessage(String email) {
		emailService.sendEmail(email);
	}
}