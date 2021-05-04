package com.yvolabs.rest.api.mongodb.config;

import com.yvolabs.rest.api.mongodb.model.TodoDTO;
import com.yvolabs.rest.api.mongodb.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class ValidationConfigTest {
    @Autowired
    private TodoRepository todoRepository;

    @Test
    void shouldFailOnInvalidEntity(){
        TodoDTO invalidTodo = new TodoDTO();
        invalidTodo.setTodo("");
        invalidTodo.setDescription("");

        // org.assertj.core.api.Assertions.*
        assertThatThrownBy(() -> todoRepository.save(invalidTodo))
                .isInstanceOf(ConstraintViolationException.class);

        // org.junit.jupiter.api.Assertions.*
        assertThrows(ConstraintViolationException.class, () ->todoRepository.save(invalidTodo));

    }

    @Test
    void shouldPassOnValidEntity(){
        TodoDTO invalidTodo = new TodoDTO();
        invalidTodo.setTodo("valid todo");
        invalidTodo.setDescription("valid todo desc");

        assertDoesNotThrow(() -> todoRepository.save(invalidTodo) );

    }

}