package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.entity.Comment;
import com.openclassrooms.mddapi.entityDto.CommentDTO;
import com.openclassrooms.mddapi.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService cs){
        this.commentService = cs;
    }

    @GetMapping("/{idPost}")
    private ArrayList<Comment> getAllCommentById(@PathVariable String idPost){
        return commentService.getAllCommentByPost(idPost);
    }

    @PostMapping("")
    private Comment postComment(@Valid @RequestBody CommentDTO commentDTO){
        return commentService.createComment(commentDTO);
    }
}
