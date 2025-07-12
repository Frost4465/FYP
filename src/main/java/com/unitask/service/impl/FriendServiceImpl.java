package com.unitask.service.impl;

import com.unitask.dao.AppUserDAO;
import com.unitask.dao.FriendDao;
import com.unitask.dto.friend.response.FriendVo;
import com.unitask.entity.AppUser;
import com.unitask.entity.Friend;
import com.unitask.exception.ServiceAppException;
import com.unitask.mapper.FriendMapper;
import com.unitask.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendDao friendDao;
    @Autowired
    private FriendMapper friendMapper;
    @Autowired
    private AppUserDAO appUserDAO;

    @Override
    public List<FriendVo> getFriendList(String userEmail, String search) {
        AppUser appUserId = appUserDAO.findByEmail(userEmail);
        List<Friend> friendList = friendDao.getFriendList(appUserId.getId(), search);
        return friendList.stream().map(friendMapper::entityToVo).toList();
    }

    @Override
    public void addFriend(String userEmail, Long friendId) {
        AppUser appUserId = appUserDAO.findByEmail(userEmail);
        Friend friend = friendDao.findUserFriendById(appUserId.getId(), friendId);
        if (null == friend) {
            throw new ServiceAppException(HttpStatus.BAD_REQUEST, "Unable To Add Friend");
        }
        AppUser appFriendId = appUserDAO.findById(friendId);
        List<Friend> saveFriends = new ArrayList<>();
        Friend addFriend = new Friend();
        addFriend.setUser(appUserId);
        addFriend.setFriend(appFriendId);

        Friend viseVersa = new Friend();
        addFriend.setUser(appFriendId);
        addFriend.setFriend(appUserId);
        saveFriends.add(addFriend);
        saveFriends.add(viseVersa);
        friendDao.saveAll(saveFriends);
    }

    @Override
    public void removeFriend(String userEmail, Long friendId) {
        AppUser appUserId = appUserDAO.findByEmail(userEmail);
        AppUser appFriendId = appUserDAO.findById(friendId);
        friendDao.removeFriend(appUserId, appFriendId);
        friendDao.removeFriend(appFriendId, appUserId);
    }

    @Override
    public List<FriendVo> addFriendList(String userEmail) {
        AppUser appUserId = appUserDAO.findByEmail(userEmail);
        List<AppUser> appUserList = appUserDAO.findAll();
        List<Friend> friendList = friendDao.findAll();
        List<AppUser> addedFriends = friendList.stream().filter(friend -> friend.getUser().equals(appUserId))
                .map(Friend::getFriend).toList();
        List<AppUser> notAddedFriends = appUserList.stream().filter(user -> !addedFriends.contains(addedFriends)).toList();
        return notAddedFriends.stream().map(friendMapper::appUserToFriendVo).toList();
    }
}
