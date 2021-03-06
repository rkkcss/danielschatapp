/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.repository;

import com.chat.entity.ChatMessage;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Dani
 */
public interface MessageRepository extends CrudRepository<ChatMessage, Long>{
    
    List<ChatMessage> findAll(); 
    
    
    
    ChatMessage findBySender(String sender);
}
