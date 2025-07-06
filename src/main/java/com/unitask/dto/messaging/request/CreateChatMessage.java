package com.unitask.dto.messaging.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateChatMessage {

    private Long sender;
    private Long receiver;
    private String message;

}
