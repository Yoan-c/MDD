package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    Boolean existsByTitle(@NotBlank String title);
}
