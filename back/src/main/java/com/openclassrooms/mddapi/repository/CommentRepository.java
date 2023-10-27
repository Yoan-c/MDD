package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.lang.reflect.Array;
import java.util.ArrayList;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    ArrayList<Comment> findAllByIdPost(@NotBlank String idPost);
}
