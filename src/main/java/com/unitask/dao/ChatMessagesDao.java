package com.unitask.dao;

import com.unitask.entity.ChatMessages;
import com.unitask.repository.ChatMessagesRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMessagesDao {

    @Autowired
    private ChatMessagesRepository chatMessagesRepository;

    public ChatMessages save(ChatMessages chatMessages) {
        if (null == chatMessages) {
            return null;
        }
        return chatMessagesRepository.save(chatMessages);
    }

    public ChatMessages findById(String id) {
        if (StringUtils.isBlank(id)) {
            return null;
        }
        return chatMessagesRepository.findById(id).orElse(null);
    }


}
