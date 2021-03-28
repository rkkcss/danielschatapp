package com.chat.sevice;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	private final Log log = LogFactory.getLog(this.getClass());
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String MESSAGE_FROM;
	
	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}


	public void sendMessage(String email, String username) {
		SimpleMailMessage message = null;
		
		try {
			message = new SimpleMailMessage();
			message.setFrom(MESSAGE_FROM);
			message.setTo(email);
			message.setSubject("Sikeres regisztráció!");
			message.setText("Kedves "+username+"!"+"\n \n Köszönöjük, hogy regisztráltál a(z) "+email+" e-mail címmel. \n \n Köszönettel, \n Dani's ChatApp.");
			javaMailSender.send(message);
			
		} catch (Exception e) {
			log.error("Hiba az e-mail küldéskor az alábbi címre: "+email+"  "+e);
		}
		
	}
	public void sendEmailChange(String username, String oldEmail, String newEmail) {
		SimpleMailMessage message = null;
		
		try {
			message = new SimpleMailMessage();
			message.setFrom(MESSAGE_FROM);
			message.setTo(newEmail);
			message.setSubject("Sikeres e-mail megváltoztatás!");
			message.setText("Kedves "+username+"!"+"\n \nSikeresen megváltoztattad a e-mail címedet "+oldEmail+"-ról, "+newEmail+"-ra. \n \n Üdvözlettel, \n Dani's ChatApp.");
			javaMailSender.send(message);
			
		} catch (Exception e) {
			log.error("Hiba az e-mail küldéskor az alábbi címre: "+newEmail+"  "+e);
		}
		
	}


	public void sendUsernameChange(String email,String username, String newUsername, String oldUsername) {
		SimpleMailMessage message = null;
		
		try {
			message = new SimpleMailMessage();
			message.setFrom(MESSAGE_FROM);
			message.setTo(email);
			message.setSubject("Sikeres felhasználónév megváltoztatás!");
			message.setText("Kedves "+username+"!"+"\n \nSikeresen megváltoztattad a felhasználó nevedet "+newUsername+"-ról, "+oldUsername+"-ra. \n \n Üdvözlettel, \n Dani's ChatApp.");
			javaMailSender.send(message);
			
		} catch (Exception e) {
			log.error("Hiba az e-mail küldéskor az alábbi címre: "+email+"  "+e);
		}
		
	}
}
