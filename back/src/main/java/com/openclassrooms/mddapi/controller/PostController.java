package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.entity.Post;
import com.openclassrooms.mddapi.entity.dto.PostDTO;
import com.openclassrooms.mddapi.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @Operation(
            summary = "Get all post",
            description = "Return all post in MDD")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @GetMapping("")
    public ArrayList<Post> getAll(){
        return postService.getAll();
    }

    @Operation(
            summary = "Get a post by Id",
            description = "Returns a post whose url contains the identifier")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable String id){
        return postService.getPostById(id);
    }

    @Operation(
            summary = "Get all post by topic id",
            description = "returns all posts that belong to a topic by passing the topic's iD in the url")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @GetMapping("/topic/{id}")
    public ArrayList<Post> getAllPostByTopicId(@PathVariable String id){
        return postService.getAllByTopicId(id);
    }

    @Operation(
            summary = "Get all posts for a topic array",
            description = "returns all posts matching the topic id array passed as parameter. " +
                    "You must pass an object containing an array with the key \"topics\"," +
                    "for example: { \"topics\" : [\"IdTopics\"]}")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @PostMapping("/topics")
    public ArrayList<Post> getAllPostByTopics(@RequestBody HashMap<String, String[]> topics){
        return postService.getAllByTopics(topics);
    }

    @Operation(
            summary = "Get all posts for a topic array",
            description = "returns all posts matching the topic id array passed as parameter. " +
                    "You must pass an object containing an array with the key \"topics\"," +
                    "for example: { \"topics\" : [\"IdTopics\"]}")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @GetMapping("/topic")
    public ArrayList<Post> getAllPostByTopicAllId(@RequestBody HashMap<String, ArrayList<String>> idsTopic){
        return postService.getAllByTopicAllId(idsTopic);
    }

    @Operation(
            summary = "Create a post",
            description = "returns a post object")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @PostMapping("")
    public Post createPost(@Valid @RequestBody PostDTO postDTO){
        return postService.createPost(postDTO);
    }
}
