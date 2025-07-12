package com.unitask.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document(collection = "chats")
public class Chats {
    @Id
    private String id;
    private List<String> members;
    private boolean isGroup;
    private String groupName;
    private String groupIcon;
    private LastMessage lastMessage;

    @Getter
    @Setter
    public static class LastMessage {
        private String text;
        private String sender;
        private LocalDateTime timestamp;
    }
}
