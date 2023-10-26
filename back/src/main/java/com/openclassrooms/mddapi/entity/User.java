package com.openclassrooms.mddapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class User {
    @Id
    private String id;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String username;
    private List<String> idComment;
    private List<String> idArticle;
    private List<String> idTheme;
    @CreatedDate
    private Date created_at;
    @LastModifiedDate
    private Date updated_at;
}
