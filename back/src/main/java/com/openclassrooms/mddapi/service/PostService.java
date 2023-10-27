package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.entity.Post;
import com.openclassrooms.mddapi.entityDto.PostDTO;
import com.openclassrooms.mddapi.error.ApiCustomError;
import com.openclassrooms.mddapi.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Set;

@Slf4j
@Service
public class PostService {

    private final PostRepository postRepository;
    private final Validator validator;

    private final TopicService topicService;

    public PostService(PostRepository pr, Validator val, TopicService ts){
        this.postRepository = pr;
        this.validator = val;
        this.topicService = ts;
    }

    public ArrayList<Post> getAll(){
        return (ArrayList<Post>) postRepository.findAll();
    }

    public Post getPostById(String id) {
        if (postRepository.existsById(id))
            return postRepository.findById(id).orElse(null);
        return null;
    }

    public Post createPost(PostDTO postDTO) {
        if (postRepository.existsByTitle(postDTO.getTitle())){
            throw new ApiCustomError("Title already exists", HttpStatus.BAD_REQUEST);
        }
        if (!topicService.isTopicExist(postDTO.getIdTopic()))
            throw new ApiCustomError("Topic does not exist", HttpStatus.BAD_REQUEST);
        Post post = new Post();
        post.setPost(postDTO.getPost());
        post.setTitle(postDTO.getTitle());
        post.setIdTopic(postDTO.getIdTopic());
        return postRepository.insert(post);
    }
}
