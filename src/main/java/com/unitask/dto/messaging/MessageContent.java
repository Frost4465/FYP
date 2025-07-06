package com.unitask.dto.messaging;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageContent {

    private boolean sender;
    private String message;
    private LocalDateTime timestamp;

    public MessageContent(boolean sender, String message, LocalDateTime timestamp) {
        this.sender = sender;
        this.message = message;
        this.timestamp = timestamp;
    }


}
