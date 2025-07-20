package com.unitask.controller;

import com.unitask.dto.messages.request.SendMessageRequest;
import com.unitask.dto.messages.response.MessageVo;
import com.unitask.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketChatController {

    @Autowired
    MessageService messageService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private String getCurrentAuthUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/messages")
    public void sendMessage(
            @DestinationVariable String chatId,
            @Payload SendMessageRequest request
    ) {
        String userName = getCurrentAuthUsername();
        MessageVo msg = messageService.sendMessage(request, userName);
        simpMessagingTemplate.convertAndSend("/topic/messages." + chatId, msg);
    }
}