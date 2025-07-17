package com.unitask.service;

import com.unitask.dto.messages.request.SendMessageRequest;
import com.unitask.dto.messages.response.MessageVo;

import java.util.List;

public interface MessageService {

    MessageVo sendMessage(SendMessageRequest request);

    List<MessageVo> getMessageChat(String id);

}
