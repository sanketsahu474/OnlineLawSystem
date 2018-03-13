package com.example.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

 @Autowired
	    MailSender mailSender;

	    @RequestMapping(path = "/email", method = RequestMethod.POST)
	    public String triggerEmail() {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setText("Hello from Spring Boot Application");
	        message.setTo("sanketsahu474@gmail.com");
	        message.setFrom("sanketsahu474@gmail.com");
	        try {
	            mailSender.send(message);
	            return "{\"message\": \"OK\"}";
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "{\"message\": \"Error\"}";
	        }
	    }

	}
