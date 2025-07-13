package com.unitask.dao;

import com.unitask.entity.Chats;
import com.unitask.repository.ChatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import java.util.List;

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

    public Chats findByMembersAndGroupStatus(List<String> members, boolean groupStatus) {
        if (CollectionUtils.isEmpty(members)) {
            return null;
        }
        return chatsRepository.findByMembersAndIsGroup(members, groupStatus);
    }

    public Chats findById(String id) {
        if (StringUtils.isEmpty(id)){
            return null;
        }
        return chatsRepository.findById(id).orElse(null);
    }

    public List<Chats> findByList(String id){
        if (StringUtils.isEmpty(id)){
            return null;
        }
        return chatsRepository.findByMembersContaining(id);
    }

}
