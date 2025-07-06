package com.unitask.repository;

import com.unitask.entity.ChatMessages;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessagesRepository extends MongoRepository<ChatMessages, String> {
}
