package com.openclassrooms.mddapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Comment {
    @Id
    private String id;
    @NotBlank
    private String comment;
    @NotBlank
    private String idPost;
    @CreatedDate
    private Date created_at;
    @LastModifiedDate
    private Date updated_at;


}
