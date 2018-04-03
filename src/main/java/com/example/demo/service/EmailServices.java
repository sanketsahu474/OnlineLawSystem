package com.example.demo.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailServices {

	 public void sendEmail(SimpleMailMessage email);
}
