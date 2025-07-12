package com.unitask.service;

import com.unitask.dto.friend.response.FriendVo;

import java.util.List;

public interface FriendService {

    List<FriendVo> getFriendList(String userEmail, String search);

    void addFriend(String userEmail, Long friendId);
    void removeFriend(String userEmail, Long friendId);
    List<FriendVo> addFriendList(String userEmail);

}
