package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.entityDto.UserDTO;
import com.openclassrooms.mddapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService us){
        this.userService = us;
    }

    @GetMapping("/topic")
    private ArrayList<Topic> getAllTopicByUser() {
        return userService.getAllTopicByUser();
    }
    @GetMapping("/me")
    private UserDTO getMe() {
        return userService.getMe();
    }

    @PatchMapping("/me")
    private HashMap<String, String> UpdateMe(@RequestBody HashMap<String, String> userInfo) {
        HashMap<String, String> token = new HashMap<>();
        String jwt = userService.updateMe(userInfo);
        token.put("token", jwt);
        return token;
    }

    @PatchMapping("/sub/{idTopic}")
    private void UpdateUserSub(@PathVariable String idTopic) {
        userService.subscribe(idTopic);
    }

    @PatchMapping("/unsub/{idTopic}")
    private void UpdateUserUnsub(@PathVariable String idTopic) {
        userService.unSubscribe(idTopic);
    }
}
