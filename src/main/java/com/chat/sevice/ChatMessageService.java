/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.sevice;

import com.chat.entity.ChatMessage;
import com.chat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dani
 */
@Service
public class ChatMessageService {
    
    @Autowired
    MessageRepository messageRepo;
    
    public void saveMessage(ChatMessage message){

        messageRepo.save(message);
    }
}
