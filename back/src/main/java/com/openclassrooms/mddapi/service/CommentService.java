package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.entity.Comment;
import com.openclassrooms.mddapi.entity.Post;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.entity.dto.CommentDTO;
import com.openclassrooms.mddapi.error.ApiCustomError;
import com.openclassrooms.mddapi.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;


    public ArrayList<Comment> getAllCommentByPost(String idPost){
        if (!postService.isPostExist(idPost))
            throw new ApiCustomError("L'article n'existe pas", HttpStatus.BAD_REQUEST);
        return commentRepository.findAllByIdPost(idPost);
    }

    public Comment createComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        if (!postService.isPostExist(commentDTO.getIdPost()))
            throw new ApiCustomError("L'article n'existe pas", HttpStatus.BAD_REQUEST);
        User user = userService.getUserByContext();
        comment = initComment(commentDTO, comment, user);
        Comment newComment = commentRepository.save(comment);
        user.getIdComment().add(newComment.getId());
        userService.saveUser(user);
        Post post = postService.getPostById(commentDTO.getIdPost());
        post.getIdComment().add(newComment.getId());
        postService.savePost(post);
        return comment;
    }

    public Comment initComment(CommentDTO commentDTO, Comment comment, User user){
        comment.setIdPost(commentDTO.getIdPost());
        comment.setComment(commentDTO.getComment());
        comment.setIdUser(user.getPseudo());
        return comment;
    }
}
