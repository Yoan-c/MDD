package com.openclassrooms.mddapi.entityDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    @NotBlank
    private String comment;
    @NotBlank
    private String idPost;
}
