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
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository ur) {
        this.userRepository = ur;
        this.modelMapper = new ModelMapper();
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User save (User user){
        return userRepository.save(user);
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
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findUserByEmail(email);
        if (userInfo.containsKey("email") && checkUserExist(userInfo.get("email"))){
            throw new ApiCustomError("Email is already taken", HttpStatus.BAD_REQUEST);
        }
        user = convertUserDtoToUser(userInfo, user);
        return user.map(value -> modelMapper.map(value, UserDTO.class)).orElse(null);
    }

    public boolean checkUserExist(String email){
        Optional<User> user = userRepository.findUserByEmail(email);
        return user.isPresent();
    }

    public Optional<User> convertUserDtoToUser(HashMap<String, String> userInfo, Optional<User> user){
        if (user.isPresent()){
            if (userInfo.containsKey("pseudo"))
                user.get().setPseudo(userInfo.get("pseudo"));
            if (userInfo.containsKey("email"))
                user.get().setEmail(userInfo.get("email"));
            if (userInfo.containsKey("password") && userInfo.containsKey("confirmPassword")) {
                if (userInfo.get("password").equals(userInfo.get("confirmPassword")))
                    user.get().setPassword(passwordEncoder.encode(userInfo.get("password")));
            }
            userRepository.save(user.get());
        }
        return user;
    }
}
