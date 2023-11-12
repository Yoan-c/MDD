package com.openclassrooms.mddapi.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    @Id
    private String id;
    private String label;
    private String topic;
    @CreatedDate
    private Date created_at;
    @LastModifiedDate
    private Date updated_at;
}
