package com.unitask.dto.messages.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SendMessageRequest {

    private String chatId;
    private String text;
    private List<String> attachments;
    private String replyTo;

}
