package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.entity.Post;
import com.openclassrooms.mddapi.entityDto.PostDTO;
import com.openclassrooms.mddapi.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

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

    @PostMapping("")
    public Post createPost(@Valid @RequestBody PostDTO postDTO){
        return postService.createPost(postDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable String id){
        postService.deletePost(id);
    }
}
