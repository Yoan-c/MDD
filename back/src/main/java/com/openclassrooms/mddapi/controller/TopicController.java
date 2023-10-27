package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api/topic")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService ts){
        this.topicService = ts;
    }

    @GetMapping("")
    public ArrayList<Topic> getAllTopic(){
        return topicService.getAll();
    }

    @GetMapping("/{id}")
    public Topic getTopicById(@PathVariable String id){
        return topicService.getTopicById(id);
    }

    @PostMapping("")
    public Topic postTopic(@RequestBody Topic topic){
        topicService.createTopic(topic);
        return topic;
    }
}
