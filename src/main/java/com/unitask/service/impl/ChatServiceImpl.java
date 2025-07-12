package com.unitask.service.impl;

import com.unitask.dao.ChatsDao;
import com.unitask.dto.chat.request.CreateChatRequest;
import com.unitask.dto.chat.response.ChatVo;
import com.unitask.entity.Chats;
import com.unitask.mapper.ChatMapper;
import com.unitask.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatsDao chatsDao;
    @Autowired
    private ChatMapper chatMapper;

    @Override
    public ChatVo createChat(CreateChatRequest request) {

        if (!request.isGroup() && request.getMemberIds().size() == 2) {
            Chats chats = chatsDao.findByMembersAndGroupStatus(request.getMemberIds(), false);
            if (chats != null) {
                return chatMapper.entityToVo(chats);
            }
        }
        Chats chats = new Chats();
        chats.setMembers(request.getMemberIds());
        chats.setGroup(request.isGroup());
        chats.setGroupName(request.getGroupName());
        chats.setGroupIcon(request.getGroupIcon());
        return chatMapper.entityToVo(chatsDao.save(chats));
    }

    @Override
    public List<ChatVo> getChatForUser(String search, String username) {
        return chatsDao.findByList(search, username).stream().map(chat -> chatMapper.entityToVo(chat)).toList();
    }


}
