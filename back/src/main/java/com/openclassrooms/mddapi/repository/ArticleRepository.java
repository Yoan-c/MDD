package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {
}
