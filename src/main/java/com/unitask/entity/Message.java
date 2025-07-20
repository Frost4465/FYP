package com.unitask.entity;


import com.unitask.constant.Enum.MessageStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document(collection = "messages")
public class Message {
    @Id
    private String id;
    private String chatId;
    private String sender;
    private String senderName;
    private String text;
    private List<String> attachments;
    private LocalDateTime timestamp;
    private MessageStatus status;
    private String replyTo;

}