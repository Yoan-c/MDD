package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.entity.Post;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.entityDto.PostDTO;
import com.openclassrooms.mddapi.error.ApiCustomError;
import com.openclassrooms.mddapi.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.validation.Validator;
import java.util.ArrayList;

@Slf4j
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final TopicService topicService;

    public PostService(PostRepository pr, Validator val, TopicService ts, UserService us){
        this.postRepository = pr;
        this.topicService = ts;
        this.userService = us;
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
        User user = userService.getUserByContext();
        post.setPost(postDTO.getPost());
        post.setTitle(postDTO.getTitle());
        post.setIdTopic(postDTO.getIdTopic());
        post.setIdUser(user.getId());
        Post newPost = postRepository.insert(post);
        user.getIdPost().add(newPost.getId());
        userService.saveUser(user);
        return post;
    }

    public Boolean isPostExist(String idPost){
        return postRepository.existsById(idPost);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }
}
