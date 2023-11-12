package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.entity.dto.LoginDTO;
import com.openclassrooms.mddapi.entity.dto.RegisterDTO;
import com.openclassrooms.mddapi.error.ApiCustomError;
import com.openclassrooms.mddapi.repository.UserRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Validator validator;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public void register(RegisterDTO request){
        if (!checkValidationObject(request)) {
            throw new ApiCustomError("Veuillez remplir tous les champs", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.findUserByEmail(request.getEmail()).isPresent()) {
            throw new ApiCustomError("Cet e-mail est déjà pris", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.findUserByPseudo(request.getPseudo()).isPresent()) {
            throw new ApiCustomError("Ce pseudo est déjà pris", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPseudo(request.getPseudo());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    public String login(LoginDTO login) {
        String emailUser;
        if (!checkValidationObject(login)) {
            throw new ApiCustomError("Veuillez remplir les champs", HttpStatus.BAD_REQUEST);
        }
        emailUser = checkLoginUser(login.getEmail());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(emailUser, login.getPassword())
            );
        }
        catch (BadCredentialsException ex){
            throw new BadCredentialsException("Identifant / mot de passe incorrect");
        }
        Optional<User> user = userRepository.findUserByEmail(emailUser);
        if (user.isEmpty()) {
            throw new BadCredentialsException("Identifant / mot de passe incorrect");
        }
        return jwtService.generateToken(user.get());
    }

    public String checkLoginUser(String id){
        Optional<User> userByEmail = userRepository.findUserByEmail(id);
        Optional<User> userByPseudo;
        if (userByEmail.isPresent()) {
            return userByEmail.get().getEmail();
        }
        userByPseudo = userRepository.findUserByPseudo(id);
        if (userByPseudo.isPresent()){
            return userByPseudo.get().getEmail();
        }
        throw new ApiCustomError("Identifant / mot de passe incorrect", HttpStatus.BAD_REQUEST);
    }

    public boolean checkValidationObject(RegisterDTO register) {
        Set<ConstraintViolation<RegisterDTO>> violations = validator.validate(register);
        return violations.isEmpty();
    }
    public boolean checkValidationObject(LoginDTO login) {
        Set<ConstraintViolation<LoginDTO>> violations = validator.validate(login);
        return violations.isEmpty();
    }

    public void logout() {
        SecurityContextHolder.clearContext();
    }
}
