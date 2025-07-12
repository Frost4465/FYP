package com.unitask.dto.chat.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatVo {

    private String id;
    private List<String> members;
    private boolean isGroup;
    private String groupName;
    private String groupIcon;
    private String text;
    private String sender;
    private String timestamp;
}
