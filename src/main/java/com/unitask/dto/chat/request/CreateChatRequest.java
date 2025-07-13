package com.unitask.dto.chat.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateChatRequest {

    private List<Long> memberIds;
    private boolean isGroup;
    private String groupName;
    private String groupIcon;

}
