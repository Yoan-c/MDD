package com.openclassrooms.mddapi.entity.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String post;
    @NotBlank
    private String idTopic;
}
