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

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    private String id;
    @NotBlank
    private String title;
    @NotBlank
    private String article;
    private List<String> idComment;
    @CreatedDate
    private Date created_at;
    @LastModifiedDate
    private Date updated_at;

}
