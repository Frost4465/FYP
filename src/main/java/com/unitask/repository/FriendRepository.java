package com.unitask.repository;

import com.unitask.entity.AppUser;
import com.unitask.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    @Query("select f from Friend f where f.user.Id = ?1 and (?2 is null or f.friend.userName like ?2)")
    List<Friend> findByUserIdAndFriendUserNameLike(Long Id, String userName);

    @Query("select f from Friend f where f.user.Id = ?1 and f.friend.Id = ?2")
    Friend findUserFriendById(Long Id, Long Id1);

    @Transactional
    @Modifying
    @Query("delete from Friend f where f.user = ?1 and f.friend = ?2")
    int removeFriend(AppUser user, AppUser friend);


}
