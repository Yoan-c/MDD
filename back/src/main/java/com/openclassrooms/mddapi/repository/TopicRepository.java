package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.Topic;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends MongoRepository<Topic, String> {
    Boolean existsByLabel(@NotBlank String label);
}
