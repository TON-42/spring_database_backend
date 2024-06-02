package com.springtel.service;

import com.springtel.model.User;
import com.springtel.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Long getMessagesCountByUser (User user){
        return messageRepository.countByFromUser(user);
    }
}
