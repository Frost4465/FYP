package com.unitask.dao;

import com.unitask.entity.AppUser;
import com.unitask.entity.Friend;
import com.unitask.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendDao {

    @Autowired
    private FriendRepository friendRepository;

    public List<Friend> getFriendList(Long id, String search) {
        if (null == id) {
            return new ArrayList<>();
        }
        return friendRepository.findByUserIdAndFriendUserNameLike(id, search);
    }

    public void saveAll(List<Friend> friend) {
        if (CollectionUtils.isEmpty(friend)) {
            return;
        }
        friendRepository.saveAll(friend);
    }

    public Friend findUserFriendById(Long userId, Long friendId) {
        if (userId == null || friendId == null) {
            return null;
        }
        return friendRepository.findUserFriendById(userId, friendId);
    }

    public void removeFriend(AppUser user, AppUser friend) {
        friendRepository.removeFriend(user, friend);
    }

    public List<Friend> findAll(){
        return friendRepository.findAll();
    }

}
