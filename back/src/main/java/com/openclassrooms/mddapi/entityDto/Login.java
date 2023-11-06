package com.openclassrooms.mddapi.entityDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
