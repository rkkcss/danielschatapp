/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.controller;

import com.chat.repository.UserRepository;
import com.chat.sevice.SettingService;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.chat.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
    @RequestMapping("/changeUsername")
    public String settingsSave(@ModelAttribute User userChange, BindingResult result, Model model, User user){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
    
        user = userRepo.findByUsername(username);

        
    	if (settingService.dBPasswordCheck(userChange, user) == false) {
			model.addAttribute("pwNotMatch","Password not match!");
			return "settings";
			
    	}else if(settingService.dbUsernameCheck(userChange, user) == false){
    		model.addAttribute("usedUsername","Username is already used.");
    		return "settings";
		}else {
    	
		settingService.changeUsername(userChange, user);
    	return "change";
		}
    	
    }
    
    @RequestMapping("/changeEmail")
    public String changeEmail(@ModelAttribute User userChange, BindingResult result, Model model){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
    
        User originalUser = userRepo.findByUsername(username);

        
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
