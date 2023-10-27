package com.openclassrooms.mddapi.entityDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CommentDTO {
    @NotBlank
    private String comment;
    @NotBlank
    private String idPost;
}
