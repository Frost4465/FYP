package com.unitask.service.impl;

import com.unitask.constant.Enum.MessageStatus;
import com.unitask.dao.ChatsDao;
import com.unitask.dao.MessagingDao;
import com.unitask.dto.messages.request.SendMessageRequest;
import com.unitask.dto.messages.response.MessageVo;
import com.unitask.entity.Chats;
import com.unitask.entity.Message;
import com.unitask.mapper.MessageMapper;
import com.unitask.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    ChatsDao chatsDao;
    @Autowired
    MessagingDao messagingDao;
    @Autowired
    MessageMapper messageMapper;

    @Override
    public MessageVo sendMessage(SendMessageRequest request) {
        Message msg = new Message();
        msg.setChatId(request.getChatId());
        msg.setSender(request.getSenderId());
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

}
