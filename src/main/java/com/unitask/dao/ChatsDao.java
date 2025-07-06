package com.unitask.dao;

import com.unitask.entity.Chats;
import com.unitask.repository.ChatsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatsDao {

    @Autowired
    private ChatsRepository chatsRepository;

    public Chats save(Chats chats) {
        if (chats == null) {
            return null;
        }
        return chatsRepository.save(chats);
    }

    public Chats findByMessagingId(String id){
        if (StringUtils.isBlank(id)){
            return null;
        }
        return chatsRepository.findByMessageId(id);
    }


}
