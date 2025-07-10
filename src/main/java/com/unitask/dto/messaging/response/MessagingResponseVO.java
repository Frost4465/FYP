package com.unitask.dto.messaging.response;

import com.unitask.entity.ChatMessages;
import com.unitask.entity.Chats;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MessagingResponseVO {

    private String messagingId;
    private Long senderId;
    private String senderName;
    private Long receiverId;
    private String receiverName;
    private List<MessageContentVO> messageContentList;

    public MessagingResponseVO(Chats chats, ChatMessages chatMessages) {
        this.senderId = chats.getSent().getId();
        this.senderName = chats.getSent().getUserName();
        this.receiverId = chats.getReceive().getId();
        this.receiverName = chats.getReceive().getUserName();
        this.messageContentList = chatMessages.getMessageContentList().stream().map(MessageContentVO::new).toList();
        this.messagingId = chatMessages.getId();
    }

}
