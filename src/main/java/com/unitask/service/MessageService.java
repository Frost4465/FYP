package com.unitask.service;

import com.unitask.dto.messages.request.SendMessageRequest;
import com.unitask.dto.messages.response.MessageVo;

public interface MessageService {

    MessageVo sendMessage(SendMessageRequest request);

}
