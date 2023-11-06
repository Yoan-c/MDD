package com.openclassrooms.mddapi.entityDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class Register {

    @NotBlank
    private String pseudo;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
