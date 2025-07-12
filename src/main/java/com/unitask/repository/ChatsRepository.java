package com.unitask.repository;

import com.unitask.entity.Chats;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatsRepository extends MongoRepository<Chats, String> {

    Chats findByMembersAndIsGroup(List<String> members, boolean isGroup);

    List<Chats> findByGroupNameLikeAndMembersContainsOrderByLastMessage_TimestampDesc(String groupName, String members);


}
