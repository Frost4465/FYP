package com.unitask.mapper;

import com.unitask.dto.chat.response.ChatVo;
import com.unitask.entity.Chats;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    @Mapping(target = "timestamp", source = "chats.lastMessage.timestamp")
    @Mapping(target = "sender", source = "chats.lastMessage.sender")
    @Mapping(target="text", source = "chats.lastMessage.text")
    ChatVo entityToVo(Chats chats);

}
