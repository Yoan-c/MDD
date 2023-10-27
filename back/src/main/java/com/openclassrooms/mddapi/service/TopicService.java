package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.entityDto.Login;
import com.openclassrooms.mddapi.error.ApiCustomError;
import com.openclassrooms.mddapi.repository.TopicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final Validator validator;

    public TopicService(TopicRepository tr, Validator val){
        this.topicRepository = tr;
        this.validator = val;
    }

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
            throw new ApiCustomError("Topic already exists", HttpStatus.BAD_REQUEST);
        topicRepository.insert(topic);
    }
    public boolean checkValidationTopic(Topic topic) {
        Set<ConstraintViolation<Topic>> violations = validator.validate(topic);
        return violations.isEmpty();
    }

    public void deleteTopic(String id) {
        if (topicRepository.existsById(id))
            topicRepository.deleteById(id);
    }

    public Topic updateTopic(HashMap<String, String> topicInfo, String id) {
        if (!topicRepository.existsById(id))
            throw new ApiCustomError("Topic does not exist", HttpStatus.BAD_REQUEST);
        Optional<Topic> topic = topicRepository.findById(id);
        return topic.map(value -> updateValueTopic(topicInfo, value)).orElse(null);
    }

    private Topic updateValueTopic(HashMap<String, String> topicInfo, Topic topic) {
        String label;
        String topicContain;
        if (topicInfo.containsKey("label")){
            label = topicInfo.get("label");
            if (topicRepository.existsByLabel(label))
                throw new ApiCustomError("Title already exist", HttpStatus.BAD_REQUEST);
            topic.setLabel(label);
        }
        if (topicInfo.containsKey("topic")){
            topicContain = topicInfo.get("topic");
            topic.setTopic(topicContain);
        }
        return topicRepository.save(topic);
    }
}
