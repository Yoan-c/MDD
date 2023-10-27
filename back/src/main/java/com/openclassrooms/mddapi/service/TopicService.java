package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class TopicService {

    private TopicRepository topicRepository;

    public TopicService(TopicRepository tr){
        this.topicRepository = tr;
    }

    public Topic getTopicById(String idTopic){
        Optional<Topic> topic = topicRepository.findById(idTopic);
        return topic.orElse(null);
    }

    public Boolean isTopicExist(String idTopic){
        return topicRepository.existsById(idTopic);
    }
}
