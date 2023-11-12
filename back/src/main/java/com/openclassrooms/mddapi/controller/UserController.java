package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.entity.dto.UserDTO;
import com.openclassrooms.mddapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    public UserController(UserService us){
        this.userService = us;
    }

    @Operation(
            summary = "Get all topic by user",
            description = "Return all posts subscribed by the user")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @GetMapping("/topic")
    private ArrayList<Topic> getAllTopicByUser() {
        return userService.getAllTopicByUser();
    }

    @Operation(
            summary = "Get the user who is logged in ",
            description = "Return informations about the user")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @GetMapping("/me")
    private UserDTO getMe() {
        return userService.getMe();
    }

    @Operation(
            summary = "Update the user who is logged in ",
            description = "Return a new token to identify the user")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @PatchMapping("/me")
    private HashMap<String, String> UpdateMe(@RequestBody HashMap<String, String> userInfo) {
        HashMap<String, String> token = new HashMap<>();
        String jwt = userService.updateMe(userInfo);
        token.put("token", jwt);
        return token;
    }

    @Operation(
            summary = "Update the topic subscription about user who is logged in ",
            description = "Send id topic in the URL for update the user subscription")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @PatchMapping("/sub/{idTopic}")
    private void UpdateUserSub(@PathVariable String idTopic) {
        userService.subscribe(idTopic);
    }

    @Operation(
            summary = "Unsbuscribe user",
            description = "Send id topic in the URL for update the user subscription")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = Exception.class)))
    })
    @PatchMapping("/unsub/{idTopic}")
    private void UpdateUserUnsub(@PathVariable String idTopic) {
        userService.unSubscribe(idTopic);
    }
}
