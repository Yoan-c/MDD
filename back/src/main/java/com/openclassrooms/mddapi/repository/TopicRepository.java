package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.Topic;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

@Repository
public interface TopicRepository extends MongoRepository<Topic, String> {
    Boolean existsByLabel(@NotBlank String label);
}
