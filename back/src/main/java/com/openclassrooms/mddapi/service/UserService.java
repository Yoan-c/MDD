package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.entityDto.UserDTO;
import com.openclassrooms.mddapi.error.ApiCustomError;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final TopicService topicService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository ur, TopicService ts, JwtService js, PasswordEncoder ps) {
        this.userRepository = ur;
        this.jwtService = js;
        this.topicService = ts;
        this.passwordEncoder = ps;
    }

    public User saveUser (User user){
        return userRepository.save(user);
    }

    public boolean checkUserExistByEmail(String email){
        Optional<User> user = userRepository.findUserByEmail(email);
        return user.isPresent();
    }

    public boolean checkUserExistByPseudo(String pseudo){
        Optional<User> user = userRepository.findUserByPseudo(pseudo);
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
                new ApiCustomError("Utilisateur inexistant", HttpStatus.NOT_FOUND)));
        return user.map(this::initUserDTO).orElse(null);
    }

    public UserDTO initUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPseudo(user.getPseudo());
        userDTO.setIdComment(user.getIdComment());
        userDTO.setIdPost(user.getIdPost());
        userDTO.setIdTopic(user.getIdTopic());
        userDTO.setCreated_at(user.getCreated_at());
        userDTO.setUpdated_at(user.getUpdated_at());
        return userDTO;
    }

    public String updateMe(HashMap<String, String> userInfo) {
        User user = getUserByContext();
        String email;
        String pseudo;
        if (!(userInfo.containsKey("email") && userInfo.containsKey("pseudo")))
            throw new ApiCustomError("Veuillez entrer toute les informations", HttpStatus.BAD_REQUEST);
        email = userInfo.get("email");
        pseudo = userInfo.get("pseudo");
        if (!user.getEmail().equals(email) && checkUserExistByEmail(email)){
            throw new ApiCustomError("L'email est déjà utilisé", HttpStatus.BAD_REQUEST);
        }
        if ( !user.getPseudo().equals(pseudo) && checkUserExistByPseudo(pseudo)){
            throw new ApiCustomError("Le pseudo est déjà utilisé", HttpStatus.BAD_REQUEST);
        }
        return updateUser(userInfo, user);
    }

    public String updateUser(HashMap<String, String> userInfo, User user){
        user.setPseudo(userInfo.get("pseudo"));
        user.setEmail(userInfo.get("email"));
        if (userInfo.containsKey("password") &&
                userInfo.containsKey("confirmPassword") &&
                !userInfo.get("password").isEmpty())
            user.setPassword(checkPassword(userInfo.get("password"), userInfo.get("confirmPassword")));
        this.saveUser(user);
        return jwtService.generateToken(user);
    }

    public String checkPassword(String mdp, String confirmMdp) {
        String regex = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mdp);
        if (mdp.equals(confirmMdp) && matcher.find())
            return this.passwordEncoder.encode(mdp);
        else
            throw new ApiCustomError("Vérifier les mots de passe", HttpStatus.BAD_REQUEST);
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

    public ArrayList<Topic> getAllTopicByUser() {
        User user = this.getUserByContext();
        ArrayList<Topic> topic = new ArrayList<>();
        for (String id : user.getIdTopic()){
            Topic newTopic = topicService.getTopicById(id);
            if (newTopic != null)
               topic.add(newTopic);
        }
        return topic;
    }
}