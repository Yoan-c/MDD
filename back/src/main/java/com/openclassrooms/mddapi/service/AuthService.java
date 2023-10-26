package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.entityDto.Register;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;

    public AuthService( UserRepository ur,
                        PasswordEncoder pe,
                        Validator val){
        this.userRepository = ur;
        this.passwordEncoder = pe;
        this.validator = val;
    }


    public void register(Register request){
        if (!checkRegisterObject(request)) {
            return;
        }
        if (userRepository.findUserByEmail(request.getEmail()).isPresent()) {
            return;
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    public boolean checkRegisterObject(Register register) {
        Set<ConstraintViolation<Register>> violations = validator.validate(register);
        return violations.isEmpty();
    }
}
