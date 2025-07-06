package com.unitask.repository;

import com.unitask.entity.Chats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatsRepository extends JpaRepository<Chats, Long> {


    @Query("select c from Chats c where c.messageId = ?1")
    Chats findByMessageId(String messageId);
}
