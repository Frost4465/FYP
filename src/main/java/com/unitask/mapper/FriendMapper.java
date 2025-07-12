package com.unitask.mapper;

import com.unitask.dto.friend.response.FriendVo;
import com.unitask.entity.AppUser;
import com.unitask.entity.Friend;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FriendMapper {

    @Mapping(target = "id", source = "friend.id")
    @Mapping(target = "name", source = "friend.userName")
    FriendVo entityToVo(Friend friend);

    @Mapping(target="id", source = "id")
    @Mapping(target = "name", source = "firstName")
    FriendVo appUserToFriendVo(AppUser appUser);

}
