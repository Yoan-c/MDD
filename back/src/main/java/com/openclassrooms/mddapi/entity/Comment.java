package com.openclassrooms.mddapi.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;
import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Comment {
    @Id
    private String id;
    private String comment;
    private String idPost;
    private String idUser;
    @CreatedDate
    private Date created_at;
    @LastModifiedDate
    private Date updated_at;
}
