package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.service.TopicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/api/topic")
@CrossOrigin(origins = "http://localhost:4200")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService ts){
        this.topicService = ts;
    }

    @Operation(
            summary = "Get all topics",
            description = "return an array of topics")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @GetMapping("")
    public ArrayList<Topic> getAllTopic(){
        return topicService.getAll();
    }

    @Operation(
            summary = "Get a topic by Id",
            description = "Return a topic with the identifier passed in the URL parameters")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @GetMapping("/{id}")
    public Topic getTopicById(@PathVariable String id){
        return topicService.getTopicById(id);
    }

    @Operation(
            summary = "Create a topic",
            description = "Create a topic and return the topic")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @PostMapping("")
    public Topic postTopic(@RequestBody Topic topic){
        topicService.createTopic(topic);
        return topic;
    }
}
