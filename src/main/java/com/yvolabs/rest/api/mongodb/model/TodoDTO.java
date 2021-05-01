package com.yvolabs.rest.api.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "todos")
public class TodoDTO {
    @Id
    private String id;

    private String todo;
    private String description;
    private Boolean completed;
    private Date createdAt;
    private Date updatedAt;
}
