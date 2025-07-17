package com.unitask.dao;

import com.unitask.entity.Message;
import com.unitask.repository.MessageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagingDao {

    @Autowired
    MessageRepository messageRepository;

    public Message save(Message message) {
        if (null == message) {
            return null;
        }
        return messageRepository.save(message);
    }

    public List<Message> findByChatId(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        return messageRepository.findByChatId(id);
    }

}
