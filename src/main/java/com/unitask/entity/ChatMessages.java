package com.unitask.entity;

import com.unitask.dto.messaging.MessageContent;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "chat_messages")
public class ChatMessages {

    @Id
    private String id;
    private Long sender;
    private Long recipient;
    private List<MessageContent> messageContentList;

}
