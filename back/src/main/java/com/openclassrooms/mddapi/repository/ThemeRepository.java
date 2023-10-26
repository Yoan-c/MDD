package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.Theme;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends MongoRepository<Theme, String> {
}
