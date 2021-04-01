package com.chat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chat.entity.ChatMessage;

import com.chat.entity.User;
import com.chat.repository.MessageRepository;
import com.chat.repository.UserRepository;
import com.chat.sevice.ChatMessageService;
import com.chat.sevice.EmailService;
import com.chat.sevice.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
public class HomeController {
	
    @Autowired
    ChatController chatController;

	@Autowired
	EmailService emailService;
	
    @Autowired
    private MessageRepository messageRepo;

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepo;

    @Autowired
    private ChatMessageService messageService;

    
    @RequestMapping("/login")
    public String loginEndpoint() {
        return "login";
    }

    @RequestMapping("/")
    public String login() {
        return "login";
    }
    
    @RequestMapping("/profiles/{username}")
    public String userProfiles(@PathVariable (value = "username") String username, Model model) {
    	
    	User user = userRepo.findByUsername(username);
    	
    	model.addAttribute("user", user);
    	
    	return "userprofiles";
    }
    
    @RequestMapping("/home")
    public String homePage(@ModelAttribute @Valid User user, BindingResult result, Model model, ChatMessage message) {
        //autentikált felhasználó elérése
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String name = loggedInUser.getName();


        model.addAttribute("user", getUserList());
        user = userRepo.findByUsername(name);

        model.addAttribute("loggedUser", user);
        model.addAttribute("username", user.getUsername());
        
        //Üres chatmessage objektum az üzenetek elmentése miatt 
        model.addAttribute("message", new ChatMessage());
        model.addAttribute("chatMessage", message());
        return "/home";
    }

    @RequestMapping("/registration")
    public String registerControl(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Registration page");
        return "registration";
    }

    //Regisztráció kezelése
    @PostMapping("/reg")
    public String reg(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        User alreadyExist = userRepo.findByUsername(user.getUsername());
        User emailExist = userRepo.findByEmail(user.getEmail());
        String password = user.getPassword();
        String passwordAgain = user.getPasswordAgain();
        
        if (alreadyExist != null) {
            model.addAttribute("alreadyExist", "This username is already exists!");
            return "registration";
        }else if (emailExist != null) {
        	model.addAttribute("emailExist","This e-mail address is already exists!");
        	return "registration";
        }else if(passwordCheck(password, passwordAgain) != false) {
        	model.addAttribute("passwordNotMatch", "Passwords not match.");
        	return "registration";
        }else if(result.hasErrors()) {
        	model.addAttribute("notOk", "Something is not okey.");
        	return "registration";
        }
        
        else {
        	
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); //'password' átkódolása és annak továbbítása
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            
//            emailService.sendMessage(user.getEmail(), user.getUsername());
            
            userService.userSave(user); // user objektum átadása a service rétegnek
            return "notification";
        }
    }


    
    @PostMapping(value = "/savemessages")
    public String saveM(@ModelAttribute ChatMessage message, Model model, BindingResult result) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        
        
        User user = new User();
        
        if (userRepo.findByUsername(username) == null || result.hasErrors()) {
            model.addAttribute("saveMessageError", "Something went wrong!");
        } else {
            user = userRepo.findByUsername(username);
            message.setUser(user);
            message.setSender(username);
            messageService.saveMessage(message);
        }

        return "redirect:/home";
    }

    //Regisztráltak listába rakása adatbázisból
    private List<User> getUserList() {
        List<User> user = userRepo.findAll();
        return user;
    }
    
    //Beszélgetés listázása az adatbázisból
    private List<ChatMessage> message() {
        List<ChatMessage> chatMessage = messageRepo.findAll();
        return chatMessage;
    }

    public boolean passwordCheck(String passwordOne, String passwordTwo) {
    	if (passwordOne.equals(passwordTwo)) {
			return false;
		}else {
			return true;	
		}
    }

}
