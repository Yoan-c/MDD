package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.entity.dto.LoginDTO;
import com.openclassrooms.mddapi.entity.dto.RegisterDTO;
import com.openclassrooms.mddapi.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(
            summary = "Sign up to MDD",
            description = "Send a Register object (name, email, password) to sign up.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = HashMap.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = Exception.class),
                    mediaType = "application/json")})
    })
    @PostMapping("/register")
    public ResponseEntity<HashMap<String, String>> register(@Valid @RequestBody RegisterDTO request){
        HashMap<String, String> token = new HashMap<>();
        authService.register(request);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @Operation(
            summary = "Log in to MDD",
            description = "Send a Login object (name or email, password) to log in. This Url return un object\"" +
                    "\"with a token. The key to get the token is \"token\" by exemple {\"token : \" token string\"}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = HashMap.class),
                    mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = BadCredentialsException.class),
                    mediaType = "application/json")})
    })
    @PostMapping("/login")
    public ResponseEntity<HashMap<String, String>> login(@Valid @RequestBody LoginDTO login){
        HashMap<String, String> token = new HashMap<>();
        token.put("token", authService.login(login));
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @Operation(
            summary = "Log out to MDD",
            description = "MDD application logout")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400")
    })
    @GetMapping("/logout")
    public void logout(){
        authService.logout();
    }
}
