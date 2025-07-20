package com.unitask.service.impl;

import com.unitask.constant.Enum.MessageStatus;
import com.unitask.dao.AppUserDAO;
import com.unitask.dao.ChatsDao;
import com.unitask.dao.MessagingDao;
import com.unitask.dto.messages.request.SendMessageRequest;
import com.unitask.dto.messages.response.MessageVo;
import com.unitask.entity.AppUser;
import com.unitask.entity.Chats;
import com.unitask.entity.Message;
import com.unitask.exception.ServiceAppException;
import com.unitask.mapper.MessageMapper;
import com.unitask.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    ChatsDao chatsDao;
    @Autowired
    MessagingDao messagingDao;
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    private AppUserDAO appUserDAO;

    @Override
    public MessageVo sendMessage(SendMessageRequest request, String userName) {

        AppUser appUser = appUserDAO.findByEmail(userName);
        if (appUser == null) {
            throw new ServiceAppException(HttpStatus.BAD_REQUEST, "User not found");
        }
        Message msg = new Message();
        msg.setChatId(request.getChatId());
        msg.setSender(appUser.getId().toString());
        msg.setSenderName(appUser.getUserName());
        msg.setText(request.getText());
        msg.setAttachments(request.getAttachments());
        msg.setTimestamp(LocalDateTime.now());
        msg.setStatus(MessageStatus.SENT);
        if (request.getReplyTo() != null) {
            msg.setReplyTo(request.getReplyTo());
        }
        Message saved = messagingDao.save(msg);

        Chats chats = chatsDao.findById(request.getChatId());
        if (null != chats) {
            Chats.LastMessage lastMsg = new Chats.LastMessage();
            lastMsg.setText(saved.getText());
            lastMsg.setSender(saved.getSender());
            lastMsg.setTimestamp(saved.getTimestamp());
            chats.setLastMessage(lastMsg);
            chatsDao.save(chats);
        }
        return messageMapper.entityToVo(saved);
    }

    @Override
    public List<MessageVo> getMessageChat(String id) {
        List<Message> message = messagingDao.findByChatId(id);
        if (CollectionUtils.isEmpty(message)){
            return new ArrayList<>();
        }
        return message.stream().map(messageMapper::entityToVo).toList();
    }

}
