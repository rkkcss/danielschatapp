package com.chat.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chat.entity.User;
import com.chat.repository.UserRepository;


@Service
public class SettingService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	EmailService emailService;

	public boolean dbUsernameCheck(User userChange, User originalUser) {
		
		if (originalUser.getUsername().equals(userChange.getUsername()) || userRepo.findByUsername(userChange.getUsername()) != null) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean dBPasswordCheck(User userChange, User originalUser) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    	boolean passMatch = encoder.matches(userChange.getPassword(), originalUser.getPassword());
		
		if (passMatch == false ) {
			return false;
		}else {
			return true;
		}
	}
	
	public void changeUsername(User userChange, User originalUser) {
//			emailService.sendUsernameChange(originalUser.getEmail(), userChange.getUsername(), originalUser.getUsername(), userChange.getUsername());
			originalUser.setUsername(userChange.getUsername());
			System.out.println(originalUser.getUsername());
			userRepo.save(originalUser);
	}
	
//Megnézi az input emailek egyeznek-e vagy van-e már a db-ben
	public boolean emailCheck(User userChange, User originalUser) {
		
		if (originalUser.getEmail().equals(userChange.getEmail()) || userRepo.findByEmail(userChange.getEmail()) != null) {
			return false;
		}else {
			return true;
		}

	}
//Megváltoztatja az adatbázisban a kívánt emailt
	public void changeEmail(User userChange, User originalUser) {
		emailService.sendEmailChange(originalUser.getUsername(), originalUser.getEmail(), userChange.getEmail());
		originalUser.setEmail(userChange.getEmail());
		userRepo.save(originalUser);
	}
}
