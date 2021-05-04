package com.yvolabs.rest.api.mongodb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "todos")

public class TodoDTO {
    @Id
    private String id;

    @NotNull(message = "todo cannot be null")
    @Size(min = 5, message = "todo must me at least 5 chars long")
    private String todo;

    @NotNull(message = "description cannot be null")
    @Size(min = 5, message = "description must me at least 5 chars long")
    private String description;

    private Boolean completed = false;
    private Date createdAt;
    private Date updatedAt;
}
