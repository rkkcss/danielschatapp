package com.chat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chat.entity.User;
import com.chat.repository.UserRepository;
import com.chat.sevice.ProfileService;

@Controller
public class ProfileController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	ProfileService profileService;
	
	@RequestMapping("/profile")
	public String profileDetails(Model model, Authentication loggedInUser, User user) {
		loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String name = loggedInUser.getName();
        
        user = userRepo.findByUsername(name);

        model.addAttribute("user", user);
		return "profile";
	}
	
	@PostMapping("/saveProfileSettings")
	public String savechanges(@ModelAttribute User newUser, User user, Model model, Authentication loggedInUser) {
		loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String name = loggedInUser.getName();
        
        user= userRepo.findByUsername(name);

        profileService.saveProfileChanges(user, newUser);
		
        model.addAttribute("user", user);
		return "profile";
	}



	
}
