package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.entityDto.Login;
import com.openclassrooms.mddapi.entityDto.Register;
import com.openclassrooms.mddapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<HashMap<String, String>> register(@Valid @RequestBody Register request){

        HashMap<String, String> token = new HashMap<>();
        authService.register(request);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<String, String>> login(@Valid @RequestBody Login login){

        HashMap<String, String> token = new HashMap<>();
        token.put("token", authService.login(login));
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public void logout(){
        authService.logout();
    }
}
