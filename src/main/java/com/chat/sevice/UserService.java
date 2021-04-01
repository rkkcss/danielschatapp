package com.chat.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.entity.User;
import com.chat.repository.UserRepository;

@Service
public class UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	public void userSave(User user) {
		
		user.setPasswordAgain("");
		
		userRepository.save(user);
		
		
	}
	
}
