package com.unitask.service.impl;

import com.unitask.dao.AppUserDAO;
import com.unitask.dao.ChatsDao;
import com.unitask.dto.chat.request.CreateChatRequest;
import com.unitask.dto.chat.response.ChatVo;
import com.unitask.entity.AppUser;
import com.unitask.entity.Chats;
import com.unitask.exception.ServiceAppException;
import com.unitask.mapper.ChatMapper;
import com.unitask.service.ChatService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    ChatsDao chatsDao;
    @Autowired
    private ChatMapper chatMapper;
    @Autowired
    private AppUserDAO appUserDAO;

    @Override
    public ChatVo createChat(CreateChatRequest request, String userName) {
        AppUser appUser = appUserDAO.findByEmail(userName);
        List<AppUser> userList = appUserDAO.findByIds(request.getMemberIds());
        if (CollectionUtils.isEmpty(userList)){
            throw new ServiceAppException(HttpStatus.BAD_REQUEST, "Users not found");
        }
        userList.add(appUser);
        List<String> memberIds = userList.stream().map(val -> val.getId().toString()).toList();
        if (!request.isGroup() && request.getMemberIds().size() == 1) {
            Chats chats = chatsDao.findByMembersAndGroupStatus(memberIds, false);
            if (chats != null) {
                return chatMapper.entityToVo(chats);
            }
        }
        Chats chats = new Chats();
        chats.setMembers(memberIds);
        chats.setGroup(request.isGroup());
        chats.setGroupName(request.getGroupName());
        chats.setGroupIcon(request.getGroupIcon());
        return chatMapper.entityToVo(chatsDao.save(chats));
    }

    @Override
    public List<ChatVo> getChatForUser(String username) {
        AppUser appUser = appUserDAO.findByEmail(username);
        if (appUser == null) {
            throw new ServiceAppException(HttpStatus.BAD_REQUEST, "User does not exists");
        }
        return chatsDao.findByList(appUser.getId().toString()).stream().map(chat -> chatMapper.entityToVo(chat)).toList();
    }


}
