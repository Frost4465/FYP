package com.unitask.dao;

import com.unitask.entity.Message;
import com.unitask.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessagingDao {

    @Autowired
    MessageRepository messageRepository;

    public Message save(Message message){
        if (null == message){
            return null;
        }
        return messageRepository.save(message);
    }

}
