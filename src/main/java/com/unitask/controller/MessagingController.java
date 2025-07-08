package com.unitask.controller;

import com.unitask.dto.messaging.request.CreateChatMessage;
import com.unitask.dto.messaging.response.MessagingResponseVO;
import com.unitask.security.JwtUtils;
import com.unitask.service.MessagingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping(path = "messaging")
public class MessagingController {

    @Autowired
    private MessagingService messagingService;
    @Autowired
    private JwtUtils jwtUtils;

    private String getCurrentAuthUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    @PostMapping("/createChat")
    public MessagingResponseVO createChat(CreateChatMessage createChatMessage) {
        return messagingService.createChat(createChatMessage);
    }

//    @GetMapping("/listChat")
//    public List<?> listChat() {
//        return messagingService.getChatList(getCurrentAuthUsername());
//    }

    @PostMapping("/response")
    public MessagingResponseVO response(String messagingId, String message) {
        return messagingService.response(messagingId, message, getCurrentAuthUsername());
    }

}
