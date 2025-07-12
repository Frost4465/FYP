package com.unitask.controller;

import com.unitask.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping(path = "friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    private String getCurrentAuthUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/list")
    public ResponseEntity<?> createChat(String search) {
        return ResponseEntity.ok(friendService.getFriendList(getCurrentAuthUsername(), search));
    }

    @PostMapping("/addFriends/{id}")
    public ResponseEntity<?> addFriends(@PathVariable("id") Long id) {
        friendService.addFriend(getCurrentAuthUsername(), id);
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/removeFriend/{id}")
    public ResponseEntity<?> removeFriend(@PathVariable("id") Long id){
        friendService.removeFriend(getCurrentAuthUsername(), id);
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/addFriendList")
    public ResponseEntity<?> addFriendList(){
        return ResponseEntity.ok(friendService.addFriendList(getCurrentAuthUsername()));
    }

}
