package com.chat.sevice;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.chat.entity.User;
import com.chat.repository.UserRepository;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;

@Service
public class ProfileService {

	@Autowired
	UserRepository userRepo;
	
	
}
