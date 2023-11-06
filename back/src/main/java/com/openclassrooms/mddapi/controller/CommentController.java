package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.entity.Comment;
import com.openclassrooms.mddapi.entityDto.CommentDTO;
import com.openclassrooms.mddapi.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/api/comment")
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService cs){
        this.commentService = cs;
    }

    @Operation(
            summary = "Get all comment By id post",
            description = "You must pass the post id in the URL to get all the comments linked to this post." +
                    "Return an array of comment")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @GetMapping("/{idPost}")
    private ArrayList<Comment> getAllCommentById(@PathVariable String idPost){
        return commentService.getAllCommentByPost(idPost);
    }

    @Operation(
            summary = "Post a comment",
            description = "Post a comment for an post. You must pass an object containing the comment and the post id")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @PostMapping("")
    private Comment postComment(@Valid @RequestBody CommentDTO commentDTO){
        return commentService.createComment(commentDTO);
    }
}
