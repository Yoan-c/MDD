package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.entityDto.UserDTO;
import com.openclassrooms.mddapi.error.ApiCustomError;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TopicService topicService;

    public UserService(UserRepository ur, TopicService ts) {
        this.userRepository = ur;
        this.modelMapper = new ModelMapper();
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.topicService = ts;
    }

    public User saveUser (User user){
        return userRepository.save(user);
    }

    public boolean checkUserExist(String email){
        Optional<User> user = userRepository.findUserByEmail(email);
        return user.isPresent();
    }
    public User getUserByContext(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findUserByEmail(email);
        return user.orElse(null);
    }

    public UserDTO getMe(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = Optional.ofNullable(userRepository.findUserByEmail(email).orElseThrow(() ->
                new ApiCustomError("an error occured", HttpStatus.NOT_FOUND)));
        if (user.isPresent()) {
            UserDTO userDTO = modelMapper.map(user.get(), UserDTO.class);
            log.info(user.get().toString());
            return userDTO;
        }
        return null;
    }

    public UserDTO updateMe(HashMap<String, String> userInfo) {
        User user = getUserByContext();
        if (userInfo.containsKey("email") && checkUserExist(userInfo.get("email"))){
            throw new ApiCustomError("Email is already taken", HttpStatus.BAD_REQUEST);
        }
        convertUserDtoToUser(userInfo, user);
        return modelMapper.map(user, UserDTO.class);
    }

    public void convertUserDtoToUser(HashMap<String, String> userInfo, User user){
        if (userInfo.containsKey("pseudo"))
            user.setPseudo(userInfo.get("pseudo"));
        if (userInfo.containsKey("email"))
            user.setEmail(userInfo.get("email"));
        if (userInfo.containsKey("password") && userInfo.containsKey("confirmPassword")) {
            if (userInfo.get("password").equals(userInfo.get("confirmPassword")))
                user.setPassword(passwordEncoder.encode(userInfo.get("password")));
        }
        this.saveUser(user);
    }

    public void subscribe(String idTopic) {
        User user = getUserByContext();
        if (topicService.isTopicExist(idTopic)) {
            List<String> idsTopic = user.getIdTopic();
            if (!idsTopic.contains(idTopic)) {
                user.getIdTopic().add(idTopic);
                this.saveUser(user);
            }
        }
    }
    public void unSubscribe(String idTopic) {
        User user = getUserByContext();
        if (topicService.isTopicExist(idTopic)) {
            List<String> idsTopic = user.getIdTopic();
            if (idsTopic.contains(idTopic)) {
                idsTopic.remove(idTopic);
                this.saveUser(user);
            }
        }
    }
}
