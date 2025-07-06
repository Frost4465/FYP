package com.unitask.service;

import com.unitask.dto.messaging.request.CreateChatMessage;
import com.unitask.dto.messaging.response.MessagingResponseVO;

public interface MessagingService {

    MessagingResponseVO createChat(CreateChatMessage createChatMessage);
    MessagingResponseVO response(String messagingId, String message, String userName);

}
