package com.unitask.service;

import com.unitask.dto.chat.request.CreateChatRequest;
import com.unitask.dto.chat.response.ChatVo;

import java.util.List;

public interface ChatService {

    ChatVo createChat(CreateChatRequest request, String userName);

    List<ChatVo> getChatForUser(String username);

}
