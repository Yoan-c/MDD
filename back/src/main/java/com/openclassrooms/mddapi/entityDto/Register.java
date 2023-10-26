package com.openclassrooms.mddapi.entityDto;

import lombok.Data;
import javax.validation.constraints.NotBlank;


@Data
public class Register {
    @NotBlank
    private String pseudo;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;
}
