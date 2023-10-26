package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.entityDto.Login;
import com.openclassrooms.mddapi.entityDto.Register;
import com.openclassrooms.mddapi.error.ApiCustomError;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthService( UserRepository ur,
                        PasswordEncoder pe,
                        Validator val,
                        AuthenticationManager at,
                        JwtService js){
        this.userRepository = ur;
        this.passwordEncoder = pe;
        this.validator = val;
        this.authenticationManager = at;
        this.jwtService = js;
    }


    public void register(Register request){
        if (!checkValidationObject(request)) {
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

    public String login(Login login) {

        if (!checkValidationObject(login)) {
            throw new ApiCustomError("Veuillez remplir les champs", HttpStatus.BAD_REQUEST);
        }
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword())
            );
        }
        catch (BadCredentialsException ex){
            throw new BadCredentialsException("Bad credential");
        }
        Optional<User> user = userRepository.findUserByEmail(login.getEmail());
        if (user.isEmpty()) {
            throw new BadCredentialsException("Bad credential");
        }
        return jwtService.generateToken(user.get());

    }
    public boolean checkValidationObject(Register register) {
        Set<ConstraintViolation<Register>> violations = validator.validate(register);
        return violations.isEmpty();
    }

    public boolean checkValidationObject(Login login) {
        Set<ConstraintViolation<Login>> violations = validator.validate(login);
        return violations.isEmpty();
    }
}
