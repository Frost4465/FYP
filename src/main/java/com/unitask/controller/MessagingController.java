package com.unitask.controller;

import com.unitask.dto.chat.request.CreateChatRequest;
import com.unitask.dto.chat.response.ChatVo;
import com.unitask.dto.messages.request.SendMessageRequest;
import com.unitask.dto.messages.response.MessageVo;
import com.unitask.security.JwtUtils;
import com.unitask.service.ChatService;
import com.unitask.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping(path = "messages")
public class MessagingController {

    @Autowired
    private ChatService chatService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private JwtUtils jwtUtils;

    private String getCurrentAuthUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    @PostMapping("/chats")
    public ChatVo createChat(@RequestBody CreateChatRequest createChatRequest) {
        String userName = getCurrentAuthUsername();
        return chatService.createChat(createChatRequest, userName);
    }

    @GetMapping("/getChat/{id}")
    public ChatVo getChat(@PathVariable String id) {
        return chatService.getChatById(id);
    }

    @PostMapping("/sendMessage")
    public MessageVo sendMessage(@RequestBody SendMessageRequest request){
        String userName = getCurrentAuthUsername();
        return messageService.sendMessage(request, userName);
    }

    @GetMapping("/getMessage/{id}")
    public List<MessageVo> getMessageChat(@PathVariable("id") String id){
        return messageService.getMessageChat(id);
    }

    @GetMapping("/list")
    public List<ChatVo> getChatForUser(){
        String userName = getCurrentAuthUsername();
        return chatService.getChatForUser(userName);
    }

//    @GetMapping("/chats/{id}")
//    public MessagingResponseVO getChats(@PathVariable("id") String id) {
//        return chatService.getChat(id);
//    }

//    @GetMapping("/listChat")
//    public List<?> listChat() {
//        return messagingService.getChatList(getCurrentAuthUsername());
//    }

//    @PostMapping("/response")
//    public MessagingResponseVO response(String messagingId, String message) {
//        return messagingService.response(messagingId, message, getCurrentAuthUsername());
//    }

}
