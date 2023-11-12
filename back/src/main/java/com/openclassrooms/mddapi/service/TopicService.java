package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.error.ApiCustomError;
import com.openclassrooms.mddapi.repository.TopicRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final Validator validator;

    public Topic getTopicById(String idTopic){
        Optional<Topic> topic = topicRepository.findById(idTopic);
        return topic.orElse(null);
    }

    public Boolean isTopicExist(String idTopic){
        return topicRepository.existsById(idTopic);
    }

    public ArrayList<Topic> getAll() {
        return (ArrayList<Topic>) topicRepository.findAll();
    }

    public void createTopic(Topic topic) {
        if (!checkValidationTopic(topic))
            throw new ApiCustomError("Veuillez remplir les champs", HttpStatus.BAD_REQUEST);
        if (topicRepository.existsByLabel(topic.getLabel()))
            throw new ApiCustomError("Le thème existe déjà", HttpStatus.BAD_REQUEST);
        topicRepository.insert(topic);
    }
    public boolean checkValidationTopic(Topic topic) {
        Set<ConstraintViolation<Topic>> violations = validator.validate(topic);
        return violations.isEmpty();
    }
}
