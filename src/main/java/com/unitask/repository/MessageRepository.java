package com.unitask.repository;

import com.unitask.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MessageRepository extends MongoRepository<Message, String> {

    List<Message> findByChatId(String chatId);
}
