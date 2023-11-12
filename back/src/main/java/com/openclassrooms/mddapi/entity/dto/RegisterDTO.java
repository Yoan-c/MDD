package com.openclassrooms.mddapi.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDTO {
    private String pseudo;
    private String email;
    private String password;
}
