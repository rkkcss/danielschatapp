/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.controller;

import com.chat.repository.UserRepository;
import com.chat.sevice.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.chat.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;


/**
 *
 * @author Dani
 */

@Controller
public class SettingsController {
    
    @Autowired
    UserRepository userRepo;
    
    @Autowired
    SettingService settingService;
    
    @GetMapping("/settings")
    public String settinsUrl(Model model, User user){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
    	
        user = userRepo.findByUsername(username);

    	model.addAttribute("user", user);
        return "settings"; 
    }
    

    
    @PostMapping("/changeUsername")
    public String changeUserName(@ModelAttribute User userChange, BindingResult result, Model model){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
    
        User originalUser = userRepo.findByUsername(username);
        model.addAttribute("user", originalUser);
        
    	if (settingService.dBPasswordCheck(userChange, originalUser) == false) {
    		model.addAttribute("pwNotMatch","Password not match!");
			return "settings";
			
    	}else if(settingService.dbUsernameCheck(userChange, originalUser) == false){
    		model.addAttribute("usedUsername","Username is already used.");
    		return "settings";
		}else {
    
			settingService.changeUsername(userChange, originalUser);
    	
		return "change";
		}
    	
    }
    
    @PostMapping("/changeEmail")
    public String changeEmail(@ModelAttribute User userChange, BindingResult result, Model model){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
    
        User originalUser = userRepo.findByUsername(username);
        model.addAttribute("user", originalUser);
        
    	if (settingService.dBPasswordCheck(userChange, originalUser) == false) {
			model.addAttribute("emailPwNotMatch","Password not match!");
			return "settings";
			
    	}else if(settingService.emailCheck(userChange, originalUser) == false){
    		model.addAttribute("usedEmail","This e-mail address is already used.");
    		return "settings";
		}else {
    
		settingService.changeEmail(userChange, originalUser);
    	
		return "change";
		}
    	
    }
    

    
    
}
