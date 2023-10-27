package com.openclassrooms.mddapi.entityDto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDTO {
    private String id;
    private String email;
    private String pseudo;
    private List<String> idComment;
    private List<String> idPost;
    private List<String> idTopic;
    private Date created_at;
    private Date updated_at;
}