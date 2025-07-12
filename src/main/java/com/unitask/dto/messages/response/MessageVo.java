package com.unitask.dto.messages.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MessageVo {

    private String id;
    private String chatId;
    private String sender;
    private String text;
    private List<String> attachments;
    private LocalDateTime timestamp;
    private String status;
    private String replyTo;

}
