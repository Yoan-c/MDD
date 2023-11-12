package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.Post;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    Boolean existsByTitle(@NotBlank String title);
    ArrayList<Post> findAllByIdTopic(@NotBlank String idTopic);
}
