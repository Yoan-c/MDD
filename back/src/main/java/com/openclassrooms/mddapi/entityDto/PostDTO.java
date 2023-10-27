package com.openclassrooms.mddapi.entityDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

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
