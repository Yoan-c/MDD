package com.openclassrooms.mddapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    @Id
    private String id;
    @NotBlank
    private String label;
    @NotBlank
    private String topic;
    @CreatedDate
    private Date created_at;
    @LastModifiedDate
    private Date updated_at;
}
