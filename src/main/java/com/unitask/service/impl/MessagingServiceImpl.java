package com.unitask.service.impl;

import com.unitask.dao.AppUserDAO;
import com.unitask.dao.ChatMessagesDao;
import com.unitask.dao.ChatsDao;
import com.unitask.dto.messaging.MessageContent;
import com.unitask.dto.messaging.request.CreateChatMessage;
import com.unitask.dto.messaging.response.MessagingResponseVO;
import com.unitask.entity.AppUser;
import com.unitask.entity.ChatMessages;
import com.unitask.entity.Chats;
import com.unitask.exception.ServiceAppException;
import com.unitask.service.MessagingService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessagingServiceImpl implements MessagingService {

    @Autowired
    private ChatsDao chatsDao;
    @Autowired
    private ChatMessagesDao chatMessagesDao;
    @Autowired
    private AppUserDAO appUserDAO;

    @Override
    public MessagingResponseVO createChat(CreateChatMessage createChatMessage) {
        AppUser sender = appUserDAO.findById(createChatMessage.getSender());
        AppUser receiver = appUserDAO.findById(createChatMessage.getReceiver());
        if (sender == null || receiver == null) {
            throw new ServiceAppException(HttpStatus.BAD_REQUEST, "Invalid Sender and Receiver");
        }
        MessageContent messageContent = new MessageContent(true, createChatMessage.getMessage(), LocalDateTime.now());
        List<MessageContent> messageContentList = new ArrayList<>();
        messageContentList.add(messageContent);

        ChatMessages entity = new ChatMessages();
        entity.setSender(sender.getId());
        entity.setRecipient(receiver.getId());
        entity.setMessageContentList(messageContentList);
        ChatMessages chatMessages = chatMessagesDao.save(entity);

        Chats chats = new Chats();
        chats.setMessageId(chatMessages.getId());
        chats.setSent(sender);
        chats.setReceive(receiver);
        Chats savedChats = chatsDao.save(chats);

        return new MessagingResponseVO(savedChats, chatMessages);
    }

    @Override
    public MessagingResponseVO response(String messagingId, String message, String userName) {
        AppUser appUser = appUserDAO.findByEmail(userName);
        if (null == appUser){
            throw new ServiceAppException(HttpStatus.BAD_REQUEST, "User does not exists");
        }
        Chats chats = chatsDao.findByMessagingId(messagingId);
        ChatMessages chatMessages = chatMessagesDao.findById(messagingId);
        if (null == chats){
            throw new ServiceAppException(HttpStatus.BAD_REQUEST, "Chat does not exists");
        }
        if (appUser.getId().equals(chats.getSent().getId()) || appUser.getId().equals(chats.getReceive().getId())){
            boolean sender = appUser.getId().equals(chats.getSent().getId());
            MessageContent messageContent = new MessageContent(sender, message, LocalDateTime.now());
            List<MessageContent> messageContentList = chatMessages.getMessageContentList();
            messageContentList.add(messageContent);
            chatMessages.setMessageContentList(messageContentList);
            chatMessages = chatMessagesDao.save(chatMessages);
        } else {
            throw new ServiceAppException(HttpStatus.BAD_REQUEST, "Invalid Chat");
        }
        return new MessagingResponseVO(chats, chatMessages);
    }
}
