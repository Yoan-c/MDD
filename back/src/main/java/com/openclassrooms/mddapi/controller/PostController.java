package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.entity.Post;
import com.openclassrooms.mddapi.entityDto.PostDTO;
import com.openclassrooms.mddapi.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService ps){
        this.postService = ps;
    }

    @GetMapping("")
    public ArrayList<Post> getAll(){
        return postService.getAll();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable String id){
        return postService.getPostById(id);
    }

    @GetMapping("/topic/{id}")
    public ArrayList<Post> getAllPostByTopicId(@PathVariable String id){
        return postService.getAllByTopicId(id);
    }

    @GetMapping("/topic")
    public ArrayList<Post> getAllPostByTopicAllId(@RequestBody HashMap<String, ArrayList<String>> idsTopic){
        return postService.getAllByTopicAllId(idsTopic);
    }

    @PostMapping("")
    public Post createPost(@Valid @RequestBody PostDTO postDTO){
        return postService.createPost(postDTO);
    }

}
