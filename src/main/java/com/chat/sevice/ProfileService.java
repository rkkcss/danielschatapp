package com.chat.sevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.entity.User;
import com.chat.repository.UserRepository;

@Service
public class ProfileService {

	@Autowired
	UserRepository userRepo;
	
	public void saveProfileChanges(User oldUser, User newUser) {
		oldUser.setAboutMe(newUser.getAboutMe());
		oldUser.setAge(newUser.getAge());
		oldUser.setSirName(newUser.getSirName());
		oldUser.setFirstName(newUser.getFirstName());
		userRepo.save(oldUser);
	}
}
