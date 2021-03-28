package com.chat.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

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
        model.addAttribute("username", name);  
        model.addAttribute("email", user.getEmail());
		return "profile";
	}
	


	
}
