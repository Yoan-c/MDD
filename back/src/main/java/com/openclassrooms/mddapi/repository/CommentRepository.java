package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.Comment;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    ArrayList<Comment> findAllByIdPost(@NotBlank String idPost);
}
