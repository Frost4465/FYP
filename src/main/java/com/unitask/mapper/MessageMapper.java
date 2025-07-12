package com.unitask.mapper;

import com.unitask.dto.messages.response.MessageVo;
import com.unitask.entity.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {


    MessageVo entityToVo(Message message);

}
