package com.unitask.dto.messaging.response;

import com.unitask.dto.messaging.MessageContent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageContentVO {

    private String message;
    private LocalDateTime timeStamp;
    private boolean sender;

    MessageContentVO(MessageContent messageContent) {
        this.message = messageContent.getMessage();
        this.timeStamp = messageContent.getTimestamp();
        this.sender = messageContent.isSender();
    }

}
